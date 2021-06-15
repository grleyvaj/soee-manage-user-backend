package es.soee.demo.application.shared.exception;


/**
 * @author Gloria R. Leyva Jerez
 *  * This class contains the OpenAPI documentation of the EXCEPTIONS
 */
public class Constants {

    /***********************
     * ExceptionResponseHandler's Constants
     **********************/

    public static final String alreadyExist_resp_description = "Se ha producido una excepción AlreadyExistsException.";
    public static final String alreadyExist_resp_name = "Devuelve un ErrorInfo al producirse una excepción AlreadyExistsException debido a la unicidad del registro.";
    public static final String alreadyExist_resp_example = "{\"uri\": \"/soee/v1/entity-uri\","
            + "\"method\": \"POST\","
            + "\"message\": \"Recurso email = leyvajerezgr@gmail.com ya existe\","
            + "\"http_status\": \"Conflict\","
            + "\"status_code\": 409,"
            + "\"code_error\": 10,"
            + "\"type\": \"AlreadyExistsException\""
            + "}";

    public static final String notFound_resp_description = "Se ha producido una excepción ResourceNotFoundException.";
    public static final String notFound_resp_name = "Devuelve un ErrorInfo al producirse una excepción ResourceNotFoundException debido a que el recurso solicitado no ha sido encontrado.";
    public static final String notFound_resp_example = "{\"uri\": \"/soee/v1/entity-uri\","
            + "\"method\": \"GET\","
            + "\"message\": \"Recurso con identificador=1 no fue encontrado\","
            + "\"http_status\": \"Not Found\","
            + "\"status_code\": 404,"
            + "\"code_error\": 4,"
            + "\"type\": \"ResourceNotFoundException\""
            + "}";

    public static final String validationViolation_description = "El recurso especificado por parámetro no es válido.";
    public static final String validationViolation_resp_name = "Devuelve un ErrorInfo al producirse una excepción MethodArgumentNotValidshared debido a que la entidad especificada por parámetro no es válida.";
    public static final String validationViolation_example = "{\"uri\": \"/soee/v1/entity-uri\","
            + "\"method\": \"PUT\","
            + "\"message\": \"Se ha producido un error porque la entidad especificada por parámetro no es válida\","
            + "\"http_status\": \"Unprocessable Entity\","
            + "\"status_code\": 422,"
            + "\"code_error\": 2,"
            + "\"type\": \"MethodArgumentNotValidshared\""
            + "}";
    
    /***********************
     * ErrorInfo's Constants
     **********************/
    public static final String error_schema_description = "Entidad que gestiona la información del error que se produce al lanzarse una excepción";

    public static final String uri_description = "URI donde se produce la excepción";
    public static final String uri_example = "/soee/v1/entity-uri";

    public static final String method_description = "Método que produce la excepción";
    public static final String method_example = "GET | POST | PUT | DELETE | ...";

    public static final String message_description = "Descripción del error";
    public static final String message_example = "Se ha producido un error debido a que ...";

    public static final String httpStatus_description = "HttpStatus de respuesta";
    public static final String httpStatus_example = "Bad Request | Not Found | Method Not Allowed | ConstraintViolationshared.exception | Precondition Failed | Unprocessable Entity | ...";

    public static final String statusCode_description = "Código de estado de respuesta automático";
    public static final String statusCode_example = "400 | 404 | 405 | 409 | 412 | 422 | ...";

    public static final String errorCode_description = "Código de error generado en el sistema";
    public static final String errorCode_example = "5| 4 | 6 | 3 | 1 | 2 | ...";

    public static final String type_description = "Excepción lanzada";
    public static final String type_example = "HttpMessageNotReadableshared.exception | ResourceNotFoundException | HttpRequestMethodNotSupportedshared.exception | ConstraintViolationshared.exception | NotValidIDshared.exception | Unprocessable Entity | ...";
}
