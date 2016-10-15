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
	 * �½�����
	 */
	@Override
	public ResultMessage createOrder(OrderVO orderVO) {
		// TODO Auto-generated method stub
		if (orderVO.estimatedCheckinTime == null || orderVO.estimatedCheckoutTime == null || orderVO.phone.equals("")
				|| orderVO.rooms == null) {
			return ResultMessage.Blank;
		} else if (orderVO.haveChildren == false && orderVO.numOfCustomers == 2 && orderVO.rooms.equals("���")) {
			return ResultMessage.Order_CreateOrderSuccess;
		}

	}

	/**
	 * ��������
	 */
	@Override
	public ResultMessage cancelOrder(OrderVO orderVO) {
		changeStatus(orderVO, OrderStatus.REVOKED);
		return ResultMessage.Order_CancelOrderSuccess;
	}

	/**
	 * ִ�ж���
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
	 * ��������
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
	 * ����������Ϊ�쳣
	 */
	@Override
	public void setAbnormal(OrderVO orderVO) {
		changeStatus(orderVO, OrderStatus.ABNORMAL);
	}

	/**
	 * ���쳣�����ָ�
	 */
	@Override
	public void renewOrder(OrderVO orderVO) {
		changeStatus(orderVO, OrderStatus.REVOKED);
	}

	/**
	 * ����ID��ȡ����
	 */
	@Override
	public OrderVO getSingle(String orderID) {
		return orderVO;
	}
	
	/**
	 * ��ȡ���ж���
	 */
	@Override
	public List<OrderVO> getAll() {
		return list;
	}
	
	/**
	 * �õ��ۿ�
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
	 * ������ü�¼
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
	 * �ı䶩��״̬
	 */
	@Override
	public void changeStatus(OrderVO orderVO, OrderStatus orderType) {
		orderVO.orderType = orderType;
	}
	/**
	 * ��ȡ�۸�
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
