package es.soee.demo.interfaces.shared;

public class SwaggerDocumentation {

    public static final String authenticateUser_op_summary = "Autenticación";
    public static final String authenticateUser_op_description = "Autenticar usuario por su email y contraseña.";
    public static final String authenticateUser_resp_description = "Autentica al usuario y retorna el token con el cual se podrá acceder a los demás endpoints de la api.";
    public static final String authenticateUser_pmt_description = "Formulario de autenticación donde se especifica el email y la contraseña.";

    public static final String registerUser_op_summary = "Alta de usuarios";
    public static final String registerUser_op_description = "Registrar un usuario con sus datos: nombre, edad, email y contraseña.";
    public static final String registerUser_head_description = "Usuario registrado satisfactoriamente";
    public static final String registerUser_resp_description = "Devuelve un recurso UserResponse con la data adicionada y sus links correspondientes de forma satisfactoria.";
    public static final String registerUser_ptm_description = "Parámetro que indica el UserRequest con la información del usuario que se desea registrar.";

    public static final String getUser_op_summary = "Detalles de un usuario";
    public static final String getUser_op_description = "Consultar los datos del usuario por su identificador.";
    public static final String getUser_resp_description = "Devuelve un recurso UsuarioResponse con la información del usuario correspondiente al identificador especificado y sus links correspondientes de forma satisfactoria.";
    public static final String getUser_pmt_description = "Parámetro que indica el identificador del usuario.";

    public static final String getAllUser_op_summary = "Listado de usuarios dados de alta";
    public static final String getAllUser_op_description = "Consultar el listado de todos los usuarios dados de alta en el sistema.";
    public static final String getAllUser_resp_description = "Devuelve el listado de usuarios dados de alta.";

    public static final String updateUser_op_summary = "Actualizar datos del usuario";
    public static final String updateUser_op_description = "Actualizar el usuario correspondiente al identificador especificado con la información proporcionada en UserRequest.";
    public static final String updateUser_head_description = "Usuario actualizado satisfactoriamente";
    public static final String updateUser_resp_description = "Devuelve un recurso UserResponse con la información actualizada y sus links correspondientes de forma satisfactoria.";
    public static final String updateUser_ptm_description = "Parámetro que indica el UserRequest con la información del usuario que se desea actualizar.";

    public static final String updatePassword_op_summary = "Actualizar contraseña del usuario";
    public static final String updatePassword_op_description = "Actualizar la contraseña del usuario correspondiente al identificador especificado.";
    public static final String updatePassword_head_description = "Contraseña actualizada satisfactoriamente";
    public static final String updatePassword_resp_description = "Devuelve un recurso PasswordChangeResponse con la información actualizada y sus links correspondientes de forma satisfactoria.";
    public static final String updatePassword_ptm_description = "Parámetro que indica el PasswordChangeRequest con la contraseña anterior y actual que se desea actualizar.";

    public static final String deleteUser_op_summary = "Eliminar usuario";
    public static final String deleteUser_op_description = "Eliminar el usuario correspondiente al identificador especificado.";
    public static final String deleteUser_head_description = "Usuario eliminado satisfactoriamente";
    public static final String deleteUser_resp_description = "Eliminado satisfactoriamente el usuario y no devuelve contenido.";
    public static final String deleteUser_ptm_description = "Parámetro que indica el identificador del usuario que desea eliminar.";

    public static final String searchUsersByFilter_op_summary = "Filtrar usuarios por filtros";
    public static final String searchUsersByFilter_op_description = "Filtrar los datos de los usuarios por: nombre, edad e email.";
    public static final String searchUsersByFilter_resp_description = "Devuelve una colección de UserResponse con la información de los usuario que se corresponden a los filtros de búsquedas establecidos.";

    public static final String filterUserByText_op_summary = "Filtrar usuarios por cadena de texto";
    public static final String filterUserByText_op_description = "Filtrar los datos de los usuarios por la cadena de texto. Realizar una búsqueda en todos los campos.";
    public static final String filterUserByText_resp_description = "Devuelve una colección de UserResponse que coinciden con la cadena especificada en alguno de sus campos.";

    public static final String filterUserByEmail_op_summary = "Buscar email";
    public static final String filterUserByEmail_op_description = "Buscar si existe un usuario con el email indicado.";
    public static final String filterUserByEmail_resp_description = "Si existe el email devuelve TRUE y en caso contrario FALSE.";
    public static final String filterUserByEmail_ptm_description = "Email del usuario.";

    public static final String pageable_description = "Parámetro para indicar la paginación";

    public static final String pageable_request = "{"
            + "\"page\": 0,"
            + "\"size\": 5,"
            + "\"sort\": \"id,asc\""
            + "}";

    public static final String string_search_description = "Cadena para realizar búsqueda completa";
    public static final String modal_search_description = "Parámetro para indicar filtros de búsqueda";
}

