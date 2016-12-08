package ui.controller;

import businesslogic.member_bl.Member;
import businesslogicservice.member_blservice.Member_BLService;
import ui.view.controllerservice.MemberRegister;
import util.ResultMessage;
import vo.CustomerVO;
import vo.MemberVO;

import java.rmi.RemoteException;
import java.sql.Timestamp;

/**
 * Created by apple on 16/11/10.
 */
public class MemberRegisterController implements MemberRegister {
    public ResultMessage signUp(MemberVO memberVO) throws RemoteException {
        Member_BLService member_blService = new Member();
        return member_blService.signUp(memberVO);
    }
}
