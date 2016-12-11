package ui.view.controllerservice;

import util.ResultMessage;
import vo.MemberLevelVO;

import java.rmi.RemoteException;

/**
 * Created by apple on 16/11/10.
 */
public interface EditMemberLevel {
    public ResultMessage addMemberLevel(MemberLevelVO memberLevelVO);

    public MemberLevelVO getMemberLevel() throws RemoteException;
}