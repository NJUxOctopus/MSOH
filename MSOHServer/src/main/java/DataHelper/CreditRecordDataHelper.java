package DataHelper;

import po.CreditRecordPO;

import java.util.List;

/**
 * Created by zqh on 2016/12/1.
 */
public interface CreditRecordDataHelper {
    public boolean addCreditRecord(CreditRecordPO creditRecordPO);

    public boolean deleteCreditRecord(CreditRecordPO creditRecordPO);

    public List<CreditRecordPO> findCreditRecordByID(String ID);
}
