import businesslogic.login_bl.MockLogin;

import static junit.framework.Assert.assertEquals;

/**
 * Created by pxr on 2016/11/13.
 */
public class LoginTest {

    public void testLogin(){
        MockLogin mockLogin = new MockLogin("123456","123456");
        MockLogin mockLogin1 = new MockLogin("123","123");

        assertEquals(1,mockLogin.login());
        assertEquals(0,mockLogin1.login());
    }
}
