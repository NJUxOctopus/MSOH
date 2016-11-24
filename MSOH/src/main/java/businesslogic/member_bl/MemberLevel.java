package businesslogic.member_bl;

import businesslogicservice.memberLevel_blservice.MemberLevel_BLService;
import businesslogicservice.member_blservice.Member_BLService;
import dataservice.member_dataservice.Member_DataService_Stub;
import dataservice.memberlevel_dataservice.MemberLevel_DataService;
import dataservice.memberlevel_dataservice.MemberLevel_DataService_Stub;
import po.MemberLevelPO;
import po.MemberPO;
import rmi.RemoteHelper;
import util.ResultMessage;
import vo.MemberLevelVO;

import java.rmi.RemoteException;

/**
 * Created by apple on 16/11/10.
 */
public class MemberLevel implements MemberLevel_BLService {
    MemberLevel_DataService_Stub memberLevelDataServiceStub = new MemberLevel_DataService_Stub();
    public ResultMessage addMemberLevel(MemberLevelVO memberLevelVO) throws RemoteException {
        if (memberLevelVO.num < 0)
            return ResultMessage.DataFormatWrong;
        if (memberLevelVO.creditBoundaries == null)
            return ResultMessage.Blank;
        else {
            memberLevelDataServiceStub.addMemberLevel(new MemberLevelPO(memberLevelVO.framerName
                    , memberLevelVO.frameDate, memberLevelVO.num, memberLevelVO.creditBoundaries));
            return ResultMessage.MemberLevel_AddMemberLevelSuccess;
        }
    }

    public ResultMessage modifyMemberLevel(MemberLevelVO memberLevelVO) throws RemoteException {
        if (memberLevelVO.num < 0)
            return ResultMessage.DataFormatWrong;
        if (memberLevelVO.creditBoundaries == null)
            return ResultMessage.Blank;
        else {
            MemberLevelPO memberLevelPO = memberLevelDataServiceStub.getMemberLevel();
            memberLevelPO.setCreditBoundaries(memberLevelVO.creditBoundaries);
            memberLevelPO.setFrameDate(memberLevelVO.frameDate);
            memberLevelPO.setNum(memberLevelVO.num);
            memberLevelDataServiceStub.updateMemberLevel(memberLevelPO);
            return ResultMessage.MemberLevel_ModifyMemberLevelSuccess;
        }
    }
}
