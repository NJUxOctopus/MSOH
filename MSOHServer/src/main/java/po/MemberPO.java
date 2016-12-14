package po;

import util.POUtil.MemberType;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author zqh
 */
@Entity
@Table(name = "member", schema = "msoh_database")
public class MemberPO implements Serializable,Cloneable {
    private static final long serialVersionUID = 1L;
    // 会员ID
    @Id
    @Column(name = "ID")
    private String ID;
    // 会员类型
    @Column(name = "memberType")
    @Enumerated(EnumType.STRING)
    private MemberType memberType;
    // 会员等级
    @Column(name = "level")
    private int level;
    // 会员生日
    @Column(name = "birthday")
    private Timestamp birthday;
    // 会员所在企业名称
    @Column(name = "companyName")
    private String companyName;

    public MemberPO() {
    }

    public MemberPO(String ID, MemberType memberType, int level, Timestamp birthday, String companyName) {
        this.ID = ID;
        this.memberType = memberType;
        this.level = level;
        this.birthday = birthday;
        this.companyName = companyName;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public MemberType getMemberType() {
        return memberType;
    }

    public void setMemberType(MemberType memberType) {
        this.memberType = memberType;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Timestamp getBirthday() {
        return birthday;
    }

    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public Object clone(){
        MemberPO memberPO=null;
        try{
            memberPO=(MemberPO)super.clone();
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
        }

        return memberPO;
    }
}
