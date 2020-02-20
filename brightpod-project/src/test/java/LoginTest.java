import brightpod.Login;
import org.junit.Test;

public class LoginTest {
    @Test
    public void test_setLogin() {
        Login login = new Login();
        login.setLogin("juan.martinez.tacc1@gmail.com", "passacction20B");
//        assertThat(actual, is("7"));
    }
}
