package ui.view.controllerservice;

import util.ResultMessage;
import vo.OrderVO;

/**
 * Created by apple on 16/11/10.
 */
public interface CheckOut {
    public ResultMessage endOrder(OrderVO orderVO);
}
