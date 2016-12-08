package businesslogic.member_bl;

import businesslogicservice.member_blservice.MemberLevel_BLService;
import dataservice.memberlevel_dataservice.MemberLevel_DataService_Stub;
import po.MemberLevelPO;
import util.ResultMessage;
import vo.MemberLevelVO;

import java.rmi.RemoteException;

/**
 * Created by apple on 16/11/10.
 */
public class MemberLevel implements MemberLevel_BLService {
    MemberLevel_DataService_Stub memberLevelDataServiceStub = new MemberLevel_DataService_Stub();


    /**
     * 修改会员等级制度
     *
     * @param memberLevelVO
     * @return
     * @throws RemoteException
     */
    public ResultMessage modifyMemberLevel(MemberLevelVO memberLevelVO) throws RemoteException {
        if (memberLevelVO.num < 0)
            //若数量小于0
            return ResultMessage.DataFormatWrong;
        if (memberLevelVO.creditBoundaries == null)
            //若未填写信用界限
            return ResultMessage.Blank;
        else {
            String creditBoundaries = "";
            for (int i = 0; i < memberLevelVO.creditBoundaries.length; i++) {
                if (i != memberLevelVO.creditBoundaries.length - 1)
                    creditBoundaries += memberLevelVO.creditBoundaries[i] + ";";
                else
                    creditBoundaries += memberLevelVO.creditBoundaries[i];
            }
            MemberLevelPO memberLevelPO = memberLevelDataServiceStub.getMemberLevel();
            memberLevelPO.setCreditBoundaries(creditBoundaries);
            memberLevelPO.setFrameDate(memberLevelVO.frameDate);
            memberLevelPO.setNum(memberLevelVO.num);
            if (memberLevelDataServiceStub.updateMemberLevel(memberLevelPO))
                return ResultMessage.MemberLevel_ModifyMemberLevelSuccess;
            else
                return ResultMessage.Fail;
        }
    }
}
