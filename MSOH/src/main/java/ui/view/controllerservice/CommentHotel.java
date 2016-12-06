package ui.view.controllerservice;

import util.ResultMessage;
import vo.CommentVO;
import vo.OrderVO;

import java.rmi.RemoteException;

/**
 * Created by apple on 16/11/10.
 */
public interface CommentHotel {
    public ResultMessage addComment(CommentVO commentVO, OrderVO orderVO) throws RemoteException;
}
