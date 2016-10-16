package businesslogicservice.order_blservice;

import java.util.List;

import util.OrderStatus;
import util.ResultMessage;
import vo.CreditRecordVO;
import vo.OrderVO;
import vo.PromotionVO;
import vo.RoomVO;

public interface Order_BLService {
	public ResultMessage createOrder(OrderVO orderVO);

	public ResultMessage cancelOrder(OrderVO orderVO);

	public ResultMessage executeOrder(OrderVO orderVO);

	public ResultMessage endOrder(OrderVO orderVO);

	public void setAbnormal(OrderVO orderVO);

	public void renewOrder(OrderVO orderVO);

	public OrderVO getSingle(String orderID);

	public List<OrderVO> getAll();

	public double getDiscount(PromotionVO promotionVO, OrderVO orderVO);

	public void addCreditRecord(OrderVO orderVO,CreditRecordVO creditRecordVO);

	public void changeStatus(OrderVO orderVO, OrderStatus orderType);

	public double getPrice(OrderVO orderVO,RoomVO roomVO);
}
