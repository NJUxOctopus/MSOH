package businesslogicservice.order_blservice;

import java.util.List;

import util.OrderStatus;
import util.ResultMessage;
import vo.CreditRecordVO;
import vo.OrderVO;
import vo.PromotionVO;
import vo.RoomVO;

/**
 * 
 * @author Pxr created:10.15 latest modify:10.15
 *
 */
public class Order_BLService_Stub implements Order_BLService {

	OrderVO orderVO;

	PromotionVO promotionVO;

	RoomVO roomVO;

	List<OrderVO> list;

	CreditRecordVO creditRecordVO;

	/**
	 * 新建订单
	 */
	@Override
	public ResultMessage createOrder(OrderVO orderVO) {
		// TODO Auto-generated method stub
		if (orderVO.estimatedCheckinTime == null || orderVO.estimatedCheckoutTime == null || orderVO.phone.equals("")
				|| orderVO.rooms == null) {
			return ResultMessage.Blank;
		} else if (orderVO.haveChildren == false && orderVO.numOfCustomers == 2 && orderVO.rooms.equals("标间")) {
			return ResultMessage.Order_CreateOrderSuccess;
		}

	}

	/**
	 * 撤销订单
	 */
	@Override
	public ResultMessage cancelOrder(OrderVO orderVO) {
		changeStatus(orderVO, OrderStatus.REVOKED);
		return ResultMessage.Order_CancelOrderSuccess;
	}

	/**
	 * 执行订单
	 */
	@Override
	public ResultMessage executeOrder(OrderVO orderVO) {
		if (orderVO.actualCheckoutTime != null || orderVO.estimatedCheckoutTime != null) {
			changeStatus(orderVO, OrderStatus.EXECUTED);
			return ResultMessage.Order_ExecuteOrderSuccess;
		} else {
			return ResultMessage.Blank;
		}
	}

	/**
	 * 结束订单
	 */
	@Override
	public ResultMessage endOrder(OrderVO orderVO) {
		if (orderVO.actualCheckoutTime != null) {
			changeStatus(orderVO, OrderStatus.ENDED);
			return ResultMessage.Order_EndOrderSuccess;
		} else {
			return ResultMessage.Blank;
		}
	}

	/**
	 * 将订单设置为异常
	 */
	@Override
	public void setAbnormal(OrderVO orderVO) {
		changeStatus(orderVO, OrderStatus.ABNORMAL);
	}

	/**
	 * 将异常订单恢复
	 */
	@Override
	public void renewOrder(OrderVO orderVO) {
		changeStatus(orderVO, OrderStatus.REVOKED);
	}

	/**
	 * 按照ID获取订单
	 */
	@Override
	public OrderVO getSingle(String orderID) {
		return orderVO;
	}
	
	/**
	 * 获取所有订单
	 */
	@Override
	public List<OrderVO> getAll() {
		return list;
	}
	
	/**
	 * 得到折扣
	 */
	@Override
	public double getDiscount(PromotionVO promotionVO, OrderVO orderVO) {
		if (orderVO.rooms.size() >= promotionVO.minRoom) {
			return 9.5;
		} else {
			return 10;
		}
	}
	
	/**
	 * 添加信用记录
	 */
	@Override
	public void addCreditRecord(OrderVO orderVO, CreditRecordVO creditRecordVO) {
		if (orderVO.orderType.equals(OrderStatus.EXECUTED)) {
			creditRecordVO.variation = (int) orderVO.finalPrice;
		} else if (orderVO.orderType.equals(OrderStatus.ABNORMAL)) {
			creditRecordVO.variation = -(int) orderVO.finalPrice;
		}
		creditRecordVO.afterChangeCredit += creditRecordVO.variation;
	}
	/**
	 * 改变订单状态
	 */
	@Override
	public void changeStatus(OrderVO orderVO, OrderStatus orderType) {
		orderVO.orderType = orderType;
	}
	/**
	 * 获取价格
	 */
	@Override
	public double getPrice(OrderVO orderVO, RoomVO roomVO) {
		for (String roomVO : orderVO.rooms) {
			orderVO.initialPrice += roomVO.price;
		}
		orderVO.finalPrice = orderVO.initialPrice * getDiscount(promotionVO, orderVO) * 0.1;
		return orderVO.finalPrice;
	}

}
