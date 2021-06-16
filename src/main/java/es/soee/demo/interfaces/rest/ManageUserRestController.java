package es.soee.demo.interfaces.rest;

import es.soee.demo.application.service.UserApplicationService;
import es.soee.demo.application.shared.validator.ValidId;
import es.soee.demo.core.constant.URIConstant;
import es.soee.demo.domain.entity.User;
import es.soee.demo.interfaces.adapter.UserAdapter;
import es.soee.demo.interfaces.adapter.UserResourceAssembler;
import es.soee.demo.interfaces.model.*;
import es.soee.demo.interfaces.shared.SuccessfulContentHandler;
import es.soee.demo.interfaces.shared.SwaggerDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.util.List;


/**
 * @author Gloria R. Leyva Jerez
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = ManageUserRestController.URI_API, produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class ManageUserRestController {

    protected static final String URI_API = URIConstant.ENTITY_API + URIConstant.API_VERSION;
    private static final String URI_ENROLL = URIConstant.URI_ENROLL;
    private static final String URI_USER = URIConstant.URI_USER;
    private static final String ENTITY_USER = URIConstant.ENTITY_USER;
    private static final String URI_FILTER = URIConstant.URI_FILTER;
    private static final String URI_CHAIN = URIConstant.URI_CHAIN;
    private static final String URI_EXIST = URIConstant.URI_EXIST;
    private static final String ENTITY_CHAIN = URIConstant.ENTITY_CHAIN;
    private static final String ENTITY_EMAIL = URIConstant.ENTITY_EMAIL;

    private final UserApplicationService userApplicationService;
    private final UserAdapter userAdapter;
    private final SuccessfulContentHandler contentHandler;
    private final UserResourceAssembler userResourceAssembler;
    private final PagedResourcesAssembler pagedResourcesAssembler;

    @PostMapping(path = URI_ENROLL, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = SwaggerDocumentation.registerUser_op_summary, description = SwaggerDocumentation.registerUser_op_description,
            tags = "manage-users-test", responses =
    @ApiResponse(headers = @Header(name = "app-success", description = SwaggerDocumentation.registerUser_head_description),
            content = @Content(schema = @Schema(implementation = UserResponse.class)),
            responseCode = "201", description = SwaggerDocumentation.registerUser_resp_description))
    public ResponseEntity<?> registerUser(
            @Parameter(description = SwaggerDocumentation.registerUser_ptm_description, required = true)
            @RequestBody @Valid UserRequest userRequest) {
        User user = userApplicationService.addUser(UserAdapter.mapToDomain(userRequest));
        RepresentationModel<UserResponse> userResource = userResourceAssembler.toModel(userAdapter.mapToResponse(user));

        String msg = contentHandler.successCreateAlert(ENTITY_USER + " " + user.getName());

        return ResponseEntity
                .created(userResource.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .headers(SuccessfulContentHandler.createHeaders(msg))
                .body(userResource);
    }

    @GetMapping(path = URI_USER + "/{userId}")
    @Operation(summary = SwaggerDocumentation.getUser_op_summary, description = SwaggerDocumentation.getUser_op_description,
            tags = "manage-users-others", responses =
    @ApiResponse(content = @Content(schema = @Schema(implementation = UserResponse.class)),
            responseCode = "200", description = SwaggerDocumentation.getUser_resp_description))
    public HttpEntity<?> getUser(
            @Parameter(description = SwaggerDocumentation.getUser_pmt_description, required = true, example = "1")
            @PathVariable @ValidId(message = "{validId.userId}") String userId) {
        User user = userApplicationService.getUser(Long.parseLong(userId));
        RepresentationModel<UserResponse> userResource = userResourceAssembler.toModel(userAdapter.mapToResponse(user));
        return ResponseEntity.ok(userResource);
    }

    @GetMapping(path = URI_USER)
    @Operation(summary = SwaggerDocumentation.getAllUser_op_summary, description = SwaggerDocumentation.getAllUser_op_description,
            tags = "manage-users-test", responses =
    @ApiResponse(responseCode = "200", description = SwaggerDocumentation.getAllUser_resp_description))
    public ResponseEntity<?> getUsers(
            @Parameter(description = SwaggerDocumentation.pageable_description, example = SwaggerDocumentation.pageable_request) Pageable pageable) {
        Page<UserResponse> users = userApplicationService.getUsers(pageable).map(userAdapter::mapToResponse);
        PagedModel collModel = pagedResourcesAssembler.toModel(users, userResourceAssembler);
        return ResponseEntity.ok(collModel);
    }

    @PutMapping(path = URI_USER + "/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = SwaggerDocumentation.updateUser_op_summary, description = SwaggerDocumentation.updateUser_op_description,
            tags = "manage-users-others", responses =
    @ApiResponse(headers = @Header(name = "app-success", description = SwaggerDocumentation.updateUser_head_description),
            content = @Content(schema = @Schema(implementation = UserResponse.class)),
            responseCode = "200", description = SwaggerDocumentation.updateUser_resp_description))
    public ResponseEntity<?> updateUser(
            @Parameter(description = SwaggerDocumentation.getUser_pmt_description, required = true, example = "1")
            @PathVariable @ValidId(message = "{validId.userId}") String userId,
            @Parameter(description = SwaggerDocumentation.updateUser_ptm_description, required = true)
            @RequestBody @Valid UserRequest userRequest) {
        User user = userApplicationService.updateUser(Long.parseLong(userId), UserAdapter.mapToDomain(userRequest));
        RepresentationModel<UserResponse> userResource = userResourceAssembler.toModel(userAdapter.mapToResponse(user));

        String msg = contentHandler.successUpdateAlert(ENTITY_USER + " " + user.getName());

        return ResponseEntity
                .created(userResource.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .headers(SuccessfulContentHandler.createHeaders(msg))
                .body(userResource);
    }

    @PatchMapping(path = URI_USER + "/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = SwaggerDocumentation.updatePassword_op_summary, description = SwaggerDocumentation.updatePassword_op_description,
            tags = "manage-users-others", responses =
    @ApiResponse(headers = @Header(name = "app-success", description = SwaggerDocumentation.updatePassword_head_description),
            content = @Content(schema = @Schema(implementation = PasswordChangeResponse.class)),
            responseCode = "200", description = SwaggerDocumentation.updatePassword_resp_description))
    public ResponseEntity<?> updatePassword(
            @Parameter(description = SwaggerDocumentation.getUser_pmt_description, required = true, example = "1")
            @PathVariable @ValidId(message = "{validId.userId}") String userId,
            @Parameter(description = SwaggerDocumentation.updatePassword_ptm_description, required = true)
            @RequestBody @Valid PasswordChangeRequest passwordChangeRequest) {
        boolean valid = userApplicationService.updatePassword(
                passwordChangeRequest.getOldPassword(),
                passwordChangeRequest.getNewPassword(),
                Long.parseLong(userId));

        String message = valid ? contentHandler.successChangePassword() : contentHandler.errorChangePassword();
        PasswordChangeResponse validResponse = PasswordChangeResponse.builder()
                .valid(valid)
                .message(message)
                .build();

        return ResponseEntity.ok()
                .headers(SuccessfulContentHandler.createHeaders(message))
                .body(validResponse);
    }

    @DeleteMapping(path = URI_USER + "/{userId}")
    @Operation(summary = SwaggerDocumentation.deleteUser_op_summary, description = SwaggerDocumentation.deleteUser_op_description,
            tags = "manage-users-others", responses =
    @ApiResponse(headers = @Header(name = "app-success", description = SwaggerDocumentation.deleteUser_head_description),
            responseCode = "204", description = SwaggerDocumentation.deleteUser_resp_description))
    public ResponseEntity deleteUser(
            @Parameter(description = SwaggerDocumentation.deleteUser_ptm_description, required = true, example = "1")
            @PathVariable @ValidId(message = "{validId.userId}") String userId) {
        userApplicationService.deleteUser(Long.parseLong(userId));

        String msg = contentHandler.successDeleteAlert(URI_API);

        return ResponseEntity.noContent()
                .headers(SuccessfulContentHandler.createHeaders(msg)).build();
    }

    @PostMapping(path = URI_USER + URI_FILTER, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = SwaggerDocumentation.searchUsersByFilter_op_summary, description = SwaggerDocumentation.searchUsersByFilter_op_description,
            tags = "manage-users-others", responses =
    @ApiResponse(content = @Content(schema = @Schema(implementation = UserResponse.class)),
            responseCode = "200", description = SwaggerDocumentation.searchUsersByFilter_resp_description))
    public ResponseEntity<?> searchUsersByFilter(
            @Parameter(description = SwaggerDocumentation.pageable_description, example = SwaggerDocumentation.pageable_request) Pageable pageable,
            @Parameter(description = SwaggerDocumentation.modal_search_description, required = true) @RequestBody @Valid UserModal modal) {
        Page<UserResponse> users = userApplicationService.getUsersByFilter(pageable, modal.getName(), modal.getAge(), modal.getEmail())
                .map(userAdapter::mapToResponse);
        PagedModel collModel = pagedResourcesAssembler.toModel(users, userResourceAssembler);

        return ResponseEntity.ok(collModel);
    }

    @GetMapping(path = URI_USER + URI_FILTER + URI_CHAIN)
    @Operation(summary = SwaggerDocumentation.filterUserByText_op_summary, description = SwaggerDocumentation.filterUserByText_op_description,
            tags = "manage-users-others", responses =
    @ApiResponse(responseCode = "200", description = SwaggerDocumentation.filterUserByText_resp_description))
    public ResponseEntity<?> searchUserByText(
            @Parameter(description = SwaggerDocumentation.pageable_description, example = SwaggerDocumentation.pageable_request) Pageable pageable,
            @Parameter(description = SwaggerDocumentation.string_search_description, required = true) @RequestParam(value = ENTITY_CHAIN) String fullText) {
        Page<UserResponse> sanctions = userApplicationService.getUsersByFullText(pageable, fullText).map(userAdapter::mapToResponse);
        PagedModel<EntityModel<UserResponse>> collModel = pagedResourcesAssembler.toModel(sanctions, userResourceAssembler);

        return ResponseEntity.ok(collModel);
    }

    @GetMapping(path = URI_USER + URI_FILTER + URI_EXIST)
    @Operation(summary = SwaggerDocumentation.filterUserByEmail_op_summary, description = SwaggerDocumentation.filterUserByEmail_op_description,
            tags = "manage-users-others", responses =
    @ApiResponse(responseCode = "200", description = SwaggerDocumentation.filterUserByEmail_resp_description))
    public ResponseEntity<?> searchUsersByEmail(
            @Parameter(description = SwaggerDocumentation.filterUserByEmail_ptm_description, required = true, example = "leyvajerezgr@gmail.com")
            @RequestParam(value = ENTITY_EMAIL) @Email(message = "{email.email}") String email) {
        boolean existEmail = userApplicationService.existEmail(email);

        return ResponseEntity.ok(existEmail);
    }

    @GetMapping(path = URI_USER + URI_FILTER + "/{email}")
    public HttpEntity<?> getUserByEmail(@PathVariable String email) {
        User user = userApplicationService.findByEmail(email);
        RepresentationModel<UserResponse> userResource = userResourceAssembler.toModel(userAdapter.mapToResponse(user));
        return ResponseEntity.ok(userResource);
    }
}
