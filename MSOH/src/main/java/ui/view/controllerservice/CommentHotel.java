package ui.view.controllerservice;

import util.ResultMessage;
import vo.CommentVO;
import vo.OrderVO;

/**
 * Created by apple on 16/11/10.
 */
public interface CommentHotel {
    public ResultMessage addComment(CommentVO commentVO, OrderVO orderVO);
}
