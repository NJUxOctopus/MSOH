package businesslogic.login_bl;

/**
 * Created by 名 on 2016/11/13.
 */
public class MockLogin extends Login{
    String ID;
    String password;

    public MockLogin(String ID,String password) {
        this.ID = ID;
        this.password=password;
    }

    public boolean login(){
        if(ID=="123456"&&password=="123456")
            return true;
        else
            return false;
    }

}
