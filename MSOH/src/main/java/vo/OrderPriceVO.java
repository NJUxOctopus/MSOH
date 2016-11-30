package vo;

/**
 * Created by Pxr on 16/11/30.
 */
public class OrderPriceVO {
    //策略名称
    public String promotionName;
    //订单原价格
    public double initPrice;
    //打折后的价格
    public double finalPrice;

    public OrderPriceVO(String promotionName, double initPrice, double finalPrice) {
        this.promotionName = promotionName;
        this.initPrice = initPrice;
        this.finalPrice = finalPrice;
    }
}
