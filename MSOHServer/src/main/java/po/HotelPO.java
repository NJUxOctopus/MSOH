package po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 * @author zqh
 */
@Entity
@Table(name = "hotel", schema = "msoh_database")
public class HotelPO implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;
    // 酒店名称
    @Column(name = "hotelName")
    private String hotelName;
    // 酒店ID
    @Id
    @Column(name = "hotelID")
    private String hotelID;
    // 酒店地址
    @Column(name = "hotelAddress")
    private String hotelAddress;
    // 酒店所处商圈
    @Column(name = "area")
    private String area;
    // 酒店简介
    @Column(name = "intro")
    private String intro;
    // 酒店设施（在数据库存储时无法存储List<String>，存成String，每个设施之间以';'分开
    @Column(name = "infra")
    private String infra;
    // 酒店房间类型（在数据库存储时无法存储List<String>，存成String，每种房间类型之间以';'分开
    @Column(name = "hotelRoomType")
    private String hotelRoomType;
    // 酒店星级
    @Column(name = "star")
    private int star;
    // 酒店评分
    @Column(name = "score")
    private double score;
    // 酒店经营许可证号
    @Column(name = "license")
    private String license;
    // 酒店照片（在数据库存储时无法存储List<String>，存成String，每个图片链接之间以';'分开
    @Column(name = "picUrl")
    private String picUrls;
    // 系统中该酒店负责人
    @Column(name = "clerkID")
    private String clerkID;


    public HotelPO() {
    }

    public HotelPO(String hotelName, String hotelAddress, String area, String intro, String infra, String hotelRoomType, int star,
                   double score, String license, String picUrls, String clerkID, String hotelID) {
        this.hotelName = hotelName;
        this.hotelID = hotelID;
        this.hotelAddress = hotelAddress;
        this.area = area;
        this.intro = intro;
        this.infra = infra;
        this.hotelRoomType = hotelRoomType;
        this.star = star;
        this.score = score;
        this.license = license;
        this.picUrls = picUrls;
        this.clerkID = clerkID;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getInfra() {
        return infra;
    }

    public void setInfra(String infra) {
        this.infra = infra;
    }

    public String getHotelRoomType() {
        return hotelRoomType;
    }

    public void setHotelRoomType(String hotelRoomType) {
        this.hotelRoomType = hotelRoomType;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getPicUrls() {
        return picUrls;
    }

    public void setPicUrls(String picUrls) {
        this.picUrls = picUrls;
    }

    public String getClerkID() {
        return clerkID;
    }

    public void setClerkID(String clerkID) {
        this.clerkID = clerkID;
    }

    public String getHotelID() {
        return hotelID;
    }

    public void setHotelID(String hotelID) {
        this.hotelID = hotelID;
    }


    @Override
    public Object clone() {
        HotelPO hotelPO = null;
        try {
            hotelPO = (HotelPO) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return hotelPO;
    }

}
