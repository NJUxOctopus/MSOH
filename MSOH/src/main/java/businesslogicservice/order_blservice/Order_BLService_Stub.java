package businesslogicservice.order_blservice;

import java.util.ArrayList;
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


	/**
	 * 新建订单
	 */
	public ResultMessage createOrder(OrderVO orderVO) {
		// TODO Auto-generated method stub
		if (orderVO.estimatedCheckinTime == null || orderVO.estimatedCheckoutTime == null || orderVO.phone.equals("")
				|| orderVO.rooms == null) {
			return ResultMessage.Blank;
		} else if (orderVO.haveChildren == false && orderVO.numOfCustomers == 2 && orderVO.rooms.equals("标间")) {
			return ResultMessage.Order_CreateOrderSuccess;
		}
		return null;

	}

	/**
	 * 撤销订单
	 */
	public ResultMessage cancelOrder(OrderVO orderVO) {
		orderVO.orderType=OrderStatus.REVOKED
		return ResultMessage.Order_CancelOrderSuccess;
	}

	/**
	 * 执行订单
	 */
	public ResultMessage executeOrder(OrderVO orderVO) {
		if (orderVO.actualCheckoutTime != null || orderVO.estimatedCheckoutTime != null) {
			orderVO.orderType=OrderStatus.EXECUTED;
			return ResultMessage.Order_ExecuteOrderSuccess;
		} else {
			return ResultMessage.Blank;
		}
	}

	/**
	 * 结束订单
	 */
	public ResultMessage endOrder(OrderVO orderVO) {
		if (orderVO.actualCheckoutTime != null) {
			orderVO.orderType= OrderStatus.FINISHED_UNEVALUATED;
			return ResultMessage.Order_EndOrderSuccess;
		} else {
			return ResultMessage.Blank;
		}
	}

	/**
	 * 将订单设置为异常
	 */
	public ResultMessage setAbnormal(OrderVO orderVO) {
		orderVO.orderType=OrderStatus.ABNORMAL;
		return ResultMessage.Order_SetAbnormalSuccess;
	}

	/**
	 * 将异常订单恢复
	 */
	public ResultMessage renewOrder(OrderVO orderVO) {
		orderVO.orderType=OrderStatus.REVOKED;
		return ResultMessage.Order_RenewOrderSuccess;
	}
}
