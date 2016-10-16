package businesslogicservice.order_blservice;

import util.OrderStatus;
import util.ResultMessage;
import vo.CreditRecordVO;
import vo.OrderVO;
import vo.PromotionVO;
import vo.RoomVO;

public class Order_BLServie_Driver {
	public void drive(Order_BLService order_BLService) {
		OrderVO orderVO = new OrderVO();
		OrderStatus orderType;
		CreditRecordVO creditRecordVO = new CreditRecordVO();
		ResultMessage result1 = order_BLService.createOrder(orderVO);
		if (result1.equals(ResultMessage.Blank))
			System.out.println("Order's information is blank");
		if (result1.equals(ResultMessage.Order_CreateOrderSuccess))
			System.out.println("Order creates successfully");
		ResultMessage result2 = order_BLService.cancelOrder(orderVO);
		if (result2.equals(Order_CancelOrderSuccess))
			System.out.println("Order cancels successfully");
		ResultMessage result3 = order_BLService.executeOrder(orderVO);
		if (result3.equals(ResultMessage.Blank))
			System.out.println("Order's information is blank");
		if (result3.equals(ResultMessage.Order_ExecuteOrderSuccess))
			System.out.println("Order executes successfully");
		ResultMessage result4 = order_BLService.endOrder(orderVO);
		if (result4.equals(ResultMessage.Order_EndOrderSuccess))
			System.out.println("Order ends successfully");
		if (result4.equals(ResultMessage.Blank))
			System.out.println("Order's information is blank");
		order_BLService.addCreditRecord(orderVO, creditRecordVO);
		order_BLService.changeStatus(orderVO, orderType);
		PromotionVO promotionVO;
		order_BLService.getDiscount(promotionVO, orderVO);
		RoomVO roomVO;
		order_BLService.getPrice(orderVO, roomVO);
		order_BLService.getSingle("123456789");
		order_BLService.setAbnormal(orderVO);
		order_BLService.renewOrder(orderVO);
	}
}
