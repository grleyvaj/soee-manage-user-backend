package es.soee.demo;

import es.soee.demo.application.service.UserApplicationService;
import es.soee.demo.interfaces.model.LoginFormRequest;
import es.soee.demo.interfaces.model.UserRequest;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = DemoApplication.class,
        webEnvironment = DEFINED_PORT
)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class DemoApplicationTests extends AbstractTest {

    private final static String uri_authentication = "http://127.0.0.1:8085/soee/v1/authentication";
    private final static String uri_enroll = "http://127.0.0.1:8085/soee/v1/enroll";
    private final static String uri_get_all = "http://127.0.0.1:8085/soee/v1/users";

    @Autowired
    private UserApplicationService userApplicationService;

    @Test
    void contextLoads() {
    }

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    @DisplayName("Test enroll user")
    public void test01_givenUserData_whenTheDataUserIsValid_thenEnrollUser() throws Exception {
        // Given: data user with name, age, email and password
        UserRequest userToEnroll = UserRequest.builder()
                .name("Gloria")
                .age(31)
                .email("leyvajerezgr@gmx.com")
                .password("gloria.soee")
                .build();
        String inputJson = super.mapToJson(userToEnroll);

        // When: data is valid and not exist user with this email
        MvcResult mvcResultExist = mockMvc.perform(MockMvcRequestBuilders
                .post(uri_enroll)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andReturn();

        // Then: register this user
        int status = mvcResultExist.getResponse().getStatus();
        assertEquals(201, status);
    }


    @Test
    @DisplayName("Test user unauthorized with valid data")
    public void test02_authenticationUser_whenUserDataIsValid_thenTheUserIsAuthentication() throws Exception {
        // Given: previously registered user
        UserRequest userToEnroll = UserRequest.builder()
                .name("Osmel")
                .age(35)
                .email("osmelpa86@gamil.com")
                .password("osmel.soee")
                .build();
        String inputJson = super.mapToJson(userToEnroll);

        MvcResult mvcResultExist = mockMvc.perform(MockMvcRequestBuilders
                .post(uri_enroll)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andReturn();

        int status = mvcResultExist.getResponse().getStatus();
        assertEquals(201, status);

        // When: this user go to authenticate
        LoginFormRequest login = LoginFormRequest.builder()
                .username("osmelpa86@gamil.com")
                .password("osmel.soee")
                .build();
        String inputJson2 = super.mapToJson(login);

        MvcResult mvcResultExist2 = mockMvc.perform(MockMvcRequestBuilders
                .post(uri_authentication)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson2))
                .andReturn();

        // Then: if data is valid, then user is authenticated
        status = mvcResultExist2.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    @DisplayName("Test get all enroll users")
    public void test03_getAllUsers_whenThereAreUserRegister_thenReturnListWithAllEnrollUsers() throws Exception {
        // Given: user data
        UserRequest userToEnroll1 = UserRequest.builder()
                .name("Gloria")
                .age(31)
                .email("leyvajerezgr@gmx.com")
                .password("gloria.soee")
                .build();
        String inputJson1 = super.mapToJson(userToEnroll1);
        // When: previously registered user
        MvcResult mvcResultExist1 = mockMvc.perform(MockMvcRequestBuilders
                .post(uri_enroll)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson1))
                .andReturn();
        int status = mvcResultExist1.getResponse().getStatus();
        assertEquals(201, status);

        // Given: user data
        UserRequest userToEnroll2 = UserRequest.builder()
                .name("Osmel")
                .age(35)
                .email("osmelpa86@gamil.com")
                .password("osmel.soee")
                .build();
        String inputJson2 = super.mapToJson(userToEnroll2);
        // When: previously registered user
        MvcResult mvcResultExist2 = mockMvc.perform(MockMvcRequestBuilders
                .post(uri_enroll)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson2))
                .andReturn();
        status = mvcResultExist2.getResponse().getStatus();
        assertEquals(201, status);
    }
}
