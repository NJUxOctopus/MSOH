package vo;

import java.io.Serializable;
import java.util.List;

/**
 * @author pxr
 */
public class HotelVO implements Serializable {

    public String hotelName;

    //public

    public String hotelAddress;

    public String area;

    public String intro;

    public String[] infra;

    public String[] roomType;

    public int star;

    public double score;

    public String license;

    public String[] picUrls;

    public String clerkID;

    public String hotelID;

    public DailyRoomInfoVO dailyRoomInfo;

    public List<CommentVO> comment;

    public String city;

    public String checkInTime;

    public String checkOutTime;


    public HotelVO() {
    }

    //加价格 促销策略
    public HotelVO(String hotelName, String hotelAddress, String area, String intro, String[] infra, String[] roomType, int star,
                   double score, String license, String[] picUrls, String clerkID, String hotelID, DailyRoomInfoVO dailyRoomInfo,
                   List<CommentVO> comment) {
        this.hotelName = hotelName;
        this.hotelAddress = hotelAddress;
        this.area = area;
        this.intro = intro;
        this.infra = infra;
        this.roomType = roomType;
        this.star = star;
        this.score = score;
        this.license = license;
        this.picUrls = picUrls;
        this.clerkID = clerkID;
        this.hotelID = hotelID;
        this.dailyRoomInfo = dailyRoomInfo;
        this.comment = comment;
    }

    /**
     * 主界面搜索界面时构造方法
     * @param city
     * @param area
     * @param star
     * @param score
     * @param checkInTime
     * @param checkOutTime
     */
    public HotelVO(String city, String area, int star, int score, String checkInTime, String checkOutTime) {
        this.city = city;
        this.area = area;
        this.star = star;
        this.score = score;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
    }

    public HotelVO(String hotelID, String hotelName, String checkInTime, String checkOutTime){
        this.hotelID = hotelID;
        this.hotelName = hotelName;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
    }

    /**
     * 修改酒店信息时使用的构造方法
     *
     * @param hotelName
     * @param hotelAddress
     * @param intro
     * @param infra
     * @param star
     * @param hotelID
     */
    public HotelVO(String hotelName, String hotelAddress, String intro, String[] infra, int star,
                   String hotelID) {
        this.hotelName = hotelName;
        this.hotelAddress = hotelAddress;
        this.intro = intro;
        this.infra = infra;
        this.star = star;
        this.hotelID = hotelID;
    }

    /**
     * 添加修改酒店使用的构造方法
     * @param ID
     * @param hotelName
     * @param hotelAddress
     * @param area
     * @param intro
     * @param infra
     * @param star
     * @param license
     */
    public HotelVO(String ID, String hotelName, String hotelAddress, String city, String area, String intro, String[] infra, int star,
                   String license) {
        this.hotelID = ID;
        this.hotelName = hotelName;
        this.hotelAddress = hotelAddress;
        this.city = city;
        this.area = area;
        this.intro = intro;
        this.infra = infra;
        this.star = star;
        this.license = license;

    }


}
