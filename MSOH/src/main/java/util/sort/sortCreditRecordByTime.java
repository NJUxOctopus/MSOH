package util.sort;

import vo.CreditRecordVO;

import java.util.Comparator;

/**
 * Created by Pxr on 16/12/19.
 */
public class sortCreditRecordByTime implements Comparator<CreditRecordVO> {
    @Override
    public int compare(CreditRecordVO o1, CreditRecordVO o2) {
        return o2.changeTime.compareTo(o1.changeTime);
    }
}
