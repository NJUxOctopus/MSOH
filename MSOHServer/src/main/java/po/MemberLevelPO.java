package po;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author zqh
 */
@Entity
@Table(name = "memberlevel", schema = "msoh_database")
public class MemberLevelPO implements Serializable,Cloneable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "memberlevel_id_seq")
    @SequenceGenerator(name = "memberlevel_id_seq", sequenceName = "memberlevel_id_seq", allocationSize = 1)
    @Column(name = "memberlevelID")
    // 数据库中会员等级制度的序号，自动生成
    private int memberlevelID;
    // 制定人员姓名
    @Column(name = "framerName")
    private String framerName;
    // 制定日期
    @Column(name = "frameDate")
    private Timestamp frameDate;
    // 等级个数
    @Column(name = "numOfLevels")
    private int num;
    // 等级界限值,divided by ';'
    @Column(name = "creditBoundaries")
    private String creditBoundaries;
    // 折扣,divided by ";"
    @Column(name = "discountList")
    private String discountList;

    public MemberLevelPO() {
    }

    public MemberLevelPO(String framerName, Timestamp frameDate, int num, String creditBoundaries,String discountList) {
        this.framerName = framerName;
        this.frameDate = frameDate;
        this.num = num;
        this.creditBoundaries = creditBoundaries;
        this.discountList=discountList;
    }

    public String getFramerName() {
        return framerName;
    }

    public void setFramerName(String framerName) {
        this.framerName = framerName;
    }

    public Timestamp getFrameDate() {
        return frameDate;
    }

    public void setFrameDate(Timestamp frameDate) {
        this.frameDate = frameDate;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getCreditBoundaries() {
        return creditBoundaries;
    }

    public void setCreditBoundaries(String creditBoundaries) {
        this.creditBoundaries = creditBoundaries;
    }

    public String getDiscountList() {
        return discountList;
    }

    public void setDiscountList(String discountList) {
        this.discountList = discountList;
    }

    public int getMemberlevelID() {
        return memberlevelID;
    }

    @Override
    public Object clone(){
        MemberLevelPO memberLevelPO=null;

        try{
            memberLevelPO=(MemberLevelPO)super.clone();
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
        }

        return memberLevelPO;
    }
}
