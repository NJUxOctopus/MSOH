package ui.view.controllerservice;

import vo.CustomerVO;
import vo.MemberLevelVO;
import vo.MemberVO;

import java.rmi.RemoteException;

/**
 * Created by apple on 16/11/10.
 */
public interface ProcessMember {
    public void upGrade(MemberVO memberVO, CustomerVO customerVO);

    public void deGrade(MemberVO memberVO, CustomerVO customerVO);

    public MemberLevelVO getMemberLevel() throws RemoteException;
}
