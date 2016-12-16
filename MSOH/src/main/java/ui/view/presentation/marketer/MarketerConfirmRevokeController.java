package ui.view.presentation.marketer;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import ui.controller.ProcessOrderController;
import ui.view.controllerservice.ProcessOrder;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.util.ErrorBoxController;
import util.ResultMessage;
import vo.OrderVO;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ST on 2016/12/16.
 */
public class MarketerConfirmRevokeController implements ControlledStage {

    private StageController stageController;

    @FXML
    private ChoiceBox revokeCreditChoiceBox;

    private OrderVO orderVO;
    private String marketerID;
    private ProcessOrder processOrder;
    private boolean isOrderDetails;

    private String resource = "marketer/MarketerConfirmRevoke.fxml";

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * 初始化方法，初始界面
     *
     * @param orderVO
     */
    public void initial(OrderVO orderVO, String marketerID) {
        this.isOrderDetails = false;
        stageController = new StageController();
        processOrder = new ProcessOrderController();
        this.orderVO = orderVO;
        this.marketerID = marketerID;
        revokeCreditChoiceBox.setItems(FXCollections.observableArrayList("全部信用值", "一半信用值"));
        revokeCreditChoiceBox.getSelectionModel().select("全部信用值");
    }

    /**
     * 重载初始化方法，从订单详情操作时初始界面
     *
     * @param orderVO
     */
    public void initial(OrderVO orderVO, String marketerID, boolean isOrderDetails) {
        this.initial(orderVO, marketerID);
        this.isOrderDetails = isOrderDetails;
    }

    /**
     * 确认按钮结果，撤销该异常订单
     */
    @FXML
    private void confirmRevoke() throws RemoteException {
        //获取当前时间
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Timestamp time = Timestamp.valueOf(dateFormat.format(date));

        String toBeRevoked = (String) revokeCreditChoiceBox.getSelectionModel().getSelectedItem();

        ResultMessage resultMessage = processOrder.renewOrder(orderVO);
        if (resultMessage.equals(ResultMessage.Order_RenewOrderSuccess)) {
            stageController = this.returnMessage("撤销成功！");
            renew();
        } else {
            returnMessage("未知错误！");
        }
    }

    /**
     * 取消按钮结果，关闭弹窗
     */
    @FXML
    private void cancelRevoke() {
        stageController.closeStage(resource);
    }

    /**
     * 返回提示信息
     *
     * @param error
     * @return
     */

    private StageController returnMessage(String error) {
        stageController = new StageController();
        stageController.loadStage("util/ErrorBoxView.fxml", 0.8);
        ErrorBoxController errorBoxController = (ErrorBoxController) stageController.getController();
        errorBoxController.setLabel(error);
        return stageController;
    }

    /**
     * 办理成功后刷新订单列表页面
     */
    private void renew() throws RemoteException {
        if (isOrderDetails)
            //如果从订单详情页面操作，成功后要关闭订单详情页面
            stageController.closeStage("marketer/MarketerOrderDetails.fxml");
        stageController.closeStage(resource);
        MarketerCheckOrderListController marketerCheckOrderListController = (MarketerCheckOrderListController) stageController.getController();
        marketerCheckOrderListController.initial(marketerID);
    }


}

