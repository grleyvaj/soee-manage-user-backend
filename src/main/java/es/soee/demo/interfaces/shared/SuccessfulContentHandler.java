package es.soee.demo.interfaces.shared;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

/**
 * @author Gloria R. Leyva Jerez
 * Build the content of a message, header or body of a ResponseEntity to Successful Response.
 * To Header: Create the HTTP header with a header name and description of the error occurred.
 * To Body: Create message error.
 * To Messade: Build success message
 */
@Component
public class SuccessfulContentHandler {

    private final Environment env;

    private static final String SUCCESS_HEADER = "app-success";

    @Autowired
    public SuccessfulContentHandler(Environment env) {
        this.env = env;
    }

    public String successCreateAlert(String entityName) {
        return entityName + " " + env.getProperty("add.success");
    }

    public String successUpdateAlert(String entityName) {
        return entityName + " " + env.getProperty("update.success");
    }

    public String successDeleteAlert(String entityName) {
        return entityName + " " + env.getProperty("delete.success");
    }

    public String errorChangePassword(){
        return env.getProperty("updatePassword.error");
    }

    public String successChangePassword(){
        return env.getProperty("success.error");
    }

    public String successDeleteBlockAlert(String entityName) {
        return entityName + " " + env.getProperty("deleteAll.success");
    }

    public static HttpHeaders createHeaders(String message) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(SUCCESS_HEADER, message);
        return headers;
    }
}
