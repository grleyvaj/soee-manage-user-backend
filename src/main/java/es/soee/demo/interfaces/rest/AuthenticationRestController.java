package es.soee.demo.interfaces.rest;

import es.soee.demo.core.constant.URIConstant;
import es.soee.demo.core.jwt.JwtProvider;
import es.soee.demo.interfaces.model.JwtResponse;
import es.soee.demo.interfaces.model.LoginFormRequest;
import es.soee.demo.interfaces.shared.SwaggerDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = AuthenticationRestController.URI_API, produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class AuthenticationRestController {

    protected static final String URI_API = URIConstant.ENTITY_API + URIConstant.API_VERSION;
    private static final String URI_AUTH = URIConstant.URI_AUTH;

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @Autowired
    public AuthenticationRestController(AuthenticationManager authenticationManager,
                                        JwtProvider jwtProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping(value = URI_AUTH, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = SwaggerDocumentation.authenticateUser_op_summary, description = SwaggerDocumentation.authenticateUser_op_description,
            tags = "manage-users-test", responses =
    @ApiResponse(content = @Content(schema = @Schema(implementation = JwtResponse.class)),
            responseCode = "200", description = SwaggerDocumentation.authenticateUser_resp_description))
    public ResponseEntity<?> authenticateUser(
            @Parameter(description = SwaggerDocumentation.authenticateUser_pmt_description, required = true)
            @Valid @RequestBody LoginFormRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwt, "Bearer", userDetails.getUsername(), userDetails.getAuthorities()));
    }
}
