package es.soee.demo.core.constant;

import org.springframework.stereotype.Component;

/**
 * @author Gloria R. Leyva Jerez
 * Class to define all api's uri
 */
@Component
public final class URIConstant {

    public static final String ENTITY_API = "/soee";
    public static final String API_VERSION = "/v1";

    public static final String URI_AUTH = "/authentication";
    public static final String ENTITY_AUTH = "Autenticación";

    public static final String URI_ENROLL = "/enroll";

    public static final String URI_USER = "/users";
    public static final String ENTITY_USER = "Usuario";

    public static final String URI_FILTER = "/filter";

    public static final String URI_CHAIN = "/chain";
    public static final String ENTITY_CHAIN = "chain";

    public static final String URI_EXIST = "/exist";
    public static final String ENTITY_EMAIL = "email";
}
