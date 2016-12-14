package vo;

import java.io.Serializable;

/**
 * @author zqh
 */
public abstract class UserVO implements Serializable {

    public String name;

    public String phone;

    public String password;

    public String ID;

    public String picUrl;

    public UserVO() {
    }

    public UserVO(String name, String phone, String password, String ID, String picUrl) {
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.ID = ID;
        this.picUrl = picUrl;
    }

    public UserVO(String name, String phone, String ID) {
        this.name = name;
        this.phone = phone;
        this.ID = ID;
    }

    public UserVO(String name, String phone, String ID, String password) {
        this.name = name;
        this.phone = phone;
        this.ID = ID;
        this.password = password;
    }

    public UserVO(String password) {
        this.password = password;
    }

}
