package vo;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @author pxr
 */
public class MemberLevelVO implements Serializable {

    public String memberLevelID;

    public String framerName;

    public Timestamp frameDate;

    public int num;

    public int[] creditBoundaries;

    public List<String> discountList;

    public SimpleIntegerProperty specificLevel;

    public SimpleIntegerProperty specificBoundary;

    public SimpleDoubleProperty specificDiscount;

    public MemberLevelVO() {
    }

    public MemberLevelVO(String memberLevelID, String framerName, Timestamp frameDate, int num, int[] creditBoundaries, List<String> discountList) {
        this.memberLevelID = memberLevelID;
        this.framerName = framerName;
        this.frameDate = frameDate;
        this.num = num;
        this.creditBoundaries = creditBoundaries;
        this.discountList = discountList;
    }


    public MemberLevelVO(int num, int[] creditBoundaries, List<String> discountList) {
        this.num = num;
        this.creditBoundaries = creditBoundaries;
        this.discountList = discountList;
    }

    /**
     * 显示会员等级制度时使用的构造方法
     *
     * @param specificLevel
     * @param specificBoundary
     * @param specificDiscount
     */
    public MemberLevelVO(int specificLevel, int specificBoundary, double specificDiscount) {
        this.specificLevel = new SimpleIntegerProperty(specificLevel);
        this.specificBoundary = new SimpleIntegerProperty(specificBoundary);
        this.specificDiscount = new SimpleDoubleProperty(specificDiscount);
    }

    public int getSpecificLevel() {
        return specificLevel.get();
    }

    public int getSpecificBoundary() {
        return specificBoundary.get();
    }

    public double getSpecificDiscount() {
        return specificDiscount.get();
    }
}
