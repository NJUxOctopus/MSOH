package ui.controller;

import businesslogic.member_bl.MemberLevel;
import businesslogicservice.member_blservice.MemberLevel_BLService;
import ui.view.controllerservice.ProcessMember;
import vo.CustomerVO;
import vo.MemberLevelVO;
import vo.MemberVO;

import java.rmi.RemoteException;

/**
 * Created by apple on 16/11/10.
 */
public class ProcessMemberController implements ProcessMember {
    private MemberLevel_BLService memberLevel_blService;

    public ProcessMemberController(){
        memberLevel_blService = new MemberLevel();
    }

    /**
     * 获取会员等级制度
     *
     * @return
     * @throws RemoteException
     */
    public MemberLevelVO getMemberLevel() throws RemoteException {
        return memberLevel_blService.getMemberLevel();
    }
}
