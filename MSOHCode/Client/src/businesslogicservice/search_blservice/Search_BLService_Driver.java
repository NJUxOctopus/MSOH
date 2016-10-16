package businesslogicservice.search_blservice;

import util.OrderStatus;

public class Search_BLService_Driver {
	public void drive(Search_BLService search_BLService){
		WorkerPosition position;
		search_BLService.searchByPosition(position);
		search_BLService.searchCustomer("3202XXXXXXXXXXXXXX");
		search_BLService.searchHotelAddress("NJU");
		search_BLService.searchHotelByID("123456");
		search_BLService.searchHotelByName("���");
		search_BLService.searchOrderByCustomerName("pxr");
		search_BLService.searchOrderByHotelName("���");
		search_BLService.searchOrderByCustomerName("st");
		search_BLService.searchOrderByStatus(OrderStatus.ABNORMAL);
		search_BLService.searchWorkerByID("3202XXXXXXXXXXXXXX");
		search_BLService.searchWorkerByName("zqh");
		
	}
}
