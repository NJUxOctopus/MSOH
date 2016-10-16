package dataservice.datafactory_dataservice;

import po.CreditRecordPO;
import po.CustomerPO;
import util.ResultMessage;
/**
 * 
 * @author ÷‹«ﬂ∫≠
 *
 */
public class Datafactory_DataService_Stub implements Datafactory_DataService{

	@Override
	public ResultMessage addCredit(CustomerPO customerPO, int increase) {
		// TODO Auto-generated method stub
		customerPO.setCredit(customerPO.getCredit()+increase);
		return ResultMessage.Datafactory_CreditChangeSuccess;
	}

	@Override
	public ResultMessage decreaseCredit(CustomerPO customerPO, int decrease) {
		// TODO Auto-generated method stub
		customerPO.setCredit(customerPO.getCredit()-decrease);
		return ResultMessage.Datafactory_CreditChangeSuccess;
	}

	@Override
	public ResultMessage addCreditRecord(CreditRecordPO crPO) {
		// TODO Auto-generated method stub
		return ResultMessage.Datafactory_AddCreditRecordSuccess;
	}

}
