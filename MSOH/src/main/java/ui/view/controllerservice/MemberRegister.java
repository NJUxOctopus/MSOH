package ui.view.controllerservice;

import util.ResultMessage;
import vo.CustomerVO;
import vo.MemberVO;

import java.rmi.RemoteException;

/**
 * Created by apple on 16/11/10.
 */
public interface MemberRegister {
    public ResultMessage signUp(MemberVO memberVO) throws RemoteException;
}
