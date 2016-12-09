package ui.view.presentation.customer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import ui.controller.ProcessOrderController;
import ui.view.controllerservice.ProcessOrder;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.util.ErrorBoxController;
import util.ResultMessage;
import vo.OrderVO;

import java.rmi.RemoteException;

/**
 * Created by island on 2016/12/8.
 */
public class CustomerConfirmCancelOrderViewController implements ControlledStage {

    StageController stageController;

    private static String resource = "customer/CustomerConfirmCancelOrderView.fxml";

    @FXML
    private Button exitButton;

    @FXML
    private Button cancelButton;

    private String orderID;

    private Label orderStatusLabel;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * 确认退出按钮结果，显示上级修改个人信息界面
     */
    @FXML
    private void confirm() {
        ProcessOrder processOrder = new ProcessOrderController();
        try {
            OrderVO orderVO = processOrder.getSingle(orderID);
            ResultMessage resultMessage = processOrder.cancelOrder(orderVO);
            if(resultMessage == ResultMessage.Order_CancelOrderSuccess){
                stageController = new StageController();
                stageController.closeStage(resource);
                CustomerOrderListViewController customerOrderListViewController = (CustomerOrderListViewController) stageController.getController("customer/CustomerOrderListViewController.fxml");
                customerOrderListViewController.init(orderVO.customerID);
            }else{
                stageController.loadStage("util/ErrorBox.fxml", 0.8);
                ErrorBoxController errorBoxController = (ErrorBoxController) stageController.getController("util/ErrorBox.fxml");
                errorBoxController.setLabel("撤销失败！");
            }
        }catch (RemoteException e){
            e.printStackTrace();
        }

    }

    /**
     * 取消退出按钮结果，关闭弹窗
     */
    @FXML
    private void cancel(){
        stageController = new StageController();
        stageController.closeStage(resource);
    }

    public void init(String orderID, Label orderStatusLabel){
        this.orderID = orderID;
        this.orderStatusLabel = orderStatusLabel;
    }
}


