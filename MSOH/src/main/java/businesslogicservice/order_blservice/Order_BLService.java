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

	public ResultMessage setAbnormal(OrderVO orderVO);

	public ResultMessage renewOrder(OrderVO orderVO);

	public double getTotal(OrderVO orderVO);

}
