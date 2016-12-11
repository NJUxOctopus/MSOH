package ui.controller;

import businesslogic.member_bl.MemberLevel;
import businesslogicservice.member_blservice.MemberLevel_BLService;
import ui.view.controllerservice.EditMemberLevel;
import util.ResultMessage;
import vo.MemberLevelVO;

import java.rmi.RemoteException;

/**
 * Created by apple on 16/11/10.
 */
public class EditMemberLevelController implements EditMemberLevel {
    private MemberLevel_BLService memberLevel_blService;

    public EditMemberLevelController(){
        memberLevel_blService = new MemberLevel();
    }
    public ResultMessage addMemberLevel(MemberLevelVO memberLevelVO) {
        return null;
    }

    public MemberLevelVO getMemberLevel() throws RemoteException{
        return memberLevel_blService.getMemberLevel();
    }
}
