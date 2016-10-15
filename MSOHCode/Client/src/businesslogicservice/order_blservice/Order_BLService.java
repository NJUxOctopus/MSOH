package businesslogicservice.order_blservice;

import java.util.List;

import util.ResultMessage;

public interface Order_BLService {
	public ResultMessage Create(OrderVO vo);
	
	public ResultMessage Cancel(OrderVO vo);
	
	public ResultMessage Execute(OrderVO vo);
	
	public ResultMessage End(OrderVO vo);
	
	public void Abnormal(OrderVO vo);
	
	public void Renew(OrderVO vo);
	
	public OrderVO GetSingle(OrderVO vo);
	
	public List<OrderVO> getAll();
	
	public int Discount(List<PromotionVO> list,OrderVO vo);
	
	public  void addCreditRecord (OrderVO vo);
	
	public  void changeStatus (OrderVO vo);
	
	public  double getPrice (OrderVO vo);
}
