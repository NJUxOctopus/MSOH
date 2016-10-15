package dataservice.hotel_dataservice;

import java.util.Date;
import java.util.List;

import po.HotelPO;
import util.ResultMessage;

public interface Hotel_DataService {
	public ResultMessage add(HotelPO po);
	
	public ResultMessage modify (HotelPO po);
	
	public ResultMessage delete (HotelPO po);
	
	public List<HotelPO> find(String address, String area, Date expected_date_of_arrival, Date expected_date_of_departure, int star, double score);
	
	public HotelPO getHotel(String id);


}
