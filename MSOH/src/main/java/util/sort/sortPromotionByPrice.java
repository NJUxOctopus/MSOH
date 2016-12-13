package util.sort;

import vo.OrderPriceVO;

import java.util.Comparator;

/**
 * Created by Pxr on 16/12/13.
 */
public class sortPromotionByPrice implements Comparator<OrderPriceVO> {
    @Override
    public int compare(OrderPriceVO o1, OrderPriceVO o2) {
        return (int) (o1.finalPrice - o2.finalPrice);
    }
}
