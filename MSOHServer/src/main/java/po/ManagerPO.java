package po;

import util.WorkerPosition;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author zqh
 */
@Entity
@Table(name = "manager", schema = "msoh_database")
public class ManagerPO implements Serializable,Cloneable {
    private static final long serialVersionUID = 1L;

    // 网站管理人员姓名
    @Column(name = "name")
    private String name;
    // 网站管理人员ID
    @Id
    @Column(name = "ID")
    private String ID;
    // 网站管理人员联系方式
    @Column(name = "phone")
    private String phone;
    // 网站管理人员密码
    @Column(name = "password")
    private String password;
    // 网站管理人员头像
    @Column(name = "picture")
    private String picUrl;
    // ְ网站管理人员职位，默认为Manager
    @Column(name = "position")
    @Enumerated(EnumType.STRING)
    private WorkerPosition position;

    public ManagerPO() {
    }

    public ManagerPO(String name, String ID, String phone, String password, String picUrl, WorkerPosition position) {
        this.name = name;
        this.ID = ID;
        this.phone = phone;
        this.password = password;
        this.picUrl = picUrl;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPic(String picUrl) {
        this.picUrl = picUrl;
    }

    public WorkerPosition getPosition() {
        return position;
    }

    public void setPosition(WorkerPosition position) {
        this.position = position;
    }

    @Override
    public Object clone(){
        ManagerPO managerPO=null;
        try{
            managerPO=(ManagerPO)super.clone();
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return managerPO;
    }
}
