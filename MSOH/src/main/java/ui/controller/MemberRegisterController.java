package ui.controller;

import businesslogic.member_bl.Member;
import businesslogic.member_bl.MemberUtil;
import businesslogicservice.member_blservice.MemberUtil_BLService;
import businesslogicservice.member_blservice.Member_BLService;
import ui.view.controllerservice.MemberRegister;
import util.ResultMessage;
import vo.CustomerVO;
import vo.MemberVO;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by apple on 16/11/10.
 */
public class MemberRegisterController implements MemberRegister {
    private Member_BLService member_blService;

    private MemberUtil_BLService memberUtil_blService;
    public MemberRegisterController(){
        member_blService = new Member();
        memberUtil_blService = new MemberUtil();

    }
    public ResultMessage signUp(MemberVO memberVO) throws RemoteException {
        return member_blService.signUp(memberVO);
    }

    public List<String> getCompany() throws RemoteException{
        return memberUtil_blService.getCompany();
    }

}
