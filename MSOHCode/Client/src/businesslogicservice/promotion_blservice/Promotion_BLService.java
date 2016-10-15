package businesslogicservice.promotion_blservice;

import java.util.Date;
import java.util.List;

import util.ResultMessage;

public interface Promotion_BLService {
	public ResultMessage Add(PromtionVO vo);
	
	public void End(Date date, PromotionVO vo);
	
	public void Execute(Date date, PromotionVO vo);
	
	public List<PromotionVo> getAll (Date date);
	
	public PromotionVo getSingle(Date date, String name);
}
