package ui.controller;

import businesslogic.member_bl.Member;
import businesslogic.member_bl.MemberLevel;
import businesslogicservice.member_blservice.MemberLevel_BLService;
import businesslogicservice.member_blservice.Member_BLService;
import ui.view.controllerservice.EditMemberLevel;
import util.ResultMessage;
import vo.MemberLevelVO;

import java.rmi.RemoteException;

/**
 * Created by apple on 16/11/10.
 */
public class EditMemberLevelController implements EditMemberLevel {
    private MemberLevel_BLService memberLevel_blService;
    private Member_BLService member_blService;

    public EditMemberLevelController(){
        memberLevel_blService = new MemberLevel();
        member_blService = new Member();
    }

    /**
     * 修改会员等级制度
     *
     * @param memberLevelVO
     * @return
     * @throws RemoteException
     */
    public ResultMessage modifyMemberLevel(MemberLevelVO memberLevelVO) throws RemoteException {
        return memberLevel_blService.modifyMemberLevel(memberLevelVO);
    }

    public MemberLevelVO getMemberLevel() throws RemoteException{
        return memberLevel_blService.getMemberLevel();
    }

    /**
     * 会员等级的变化
     *
     * @param customerID
     * @return
     * @throws RemoteException
     */
    public ResultMessage changeGrade(String customerID) throws RemoteException {
        return member_blService.changeGrade(customerID);
    }
}
