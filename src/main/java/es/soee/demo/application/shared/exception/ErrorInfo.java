package es.soee.demo.application.shared.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Gloria R. Leyva Jerez
 */
@Data
@AllArgsConstructor
@Schema(name = "ErrorInfo", description = Constants.error_schema_description)
public class ErrorInfo {

    @Schema(description = Constants.uri_description, example = Constants.uri_example)
    private String uri;

    @Schema(description = Constants.method_description, example = Constants.method_example)
    private String method;

    @Schema(description = Constants.message_description, example = Constants.message_example)
    private String message;

    @JsonProperty("http_status")
    @Schema(description = Constants.httpStatus_description, example = Constants.httpStatus_example)
    private String httpStatus;

    @JsonProperty("status_code")
    @Schema(description = Constants.statusCode_description, example = Constants.statusCode_example)
    private int statusCode;

    @Schema(description = Constants.errorCode_description, example = Constants.errorCode_example)
    @JsonProperty("code_error")
    private int errorCode;

    @JsonProperty("shared.exception_type")
    @Schema(description = Constants.type_description, example = Constants.type_example)
    private String type;
}
