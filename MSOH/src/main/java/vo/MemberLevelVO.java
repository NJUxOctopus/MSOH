package vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @author pxr
 */
public class MemberLevelVO implements Serializable {

    public String framerName;

    public Timestamp frameDate;

    public int num;

    public int[] creditBoundaries;

    public List<String> discountList;

    public int specificLevel;

    public int specificBoundary;

    public double specificDiscount;

    public MemberLevelVO() {
    }

    public MemberLevelVO(String framerName, Timestamp frameDate, int num, int[] creditBoundaries, List<String> discountList) {
        this.framerName = framerName;
        this.frameDate = frameDate;
        this.num = num;
        this.creditBoundaries = creditBoundaries;
        this.discountList = discountList;
    }

    /**
     * 制定会员等级制度时使用的构造方法
     *
     * @param num
     * @param creditBoundaries
     * @param discountList
     */
    public MemberLevelVO(int num, int[] creditBoundaries, List<String> discountList) {
        this.num = num;
        this.creditBoundaries = creditBoundaries;
        this.discountList = discountList;
    }

    public MemberLevelVO(int specificLevel, int specificBoundary, double specificDiscount) {
        this.specificLevel = specificLevel;
        this.specificBoundary = specificBoundary;
        this.specificDiscount = specificDiscount;
    }
}
