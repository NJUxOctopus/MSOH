package ui.view.presentation.marketer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import ui.controller.ProcessOrderController;
import ui.view.controllerservice.ProcessOrder;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ControlledStage;
import util.OrderStatus;
import vo.OrderVO;

import java.rmi.RemoteException;

/**
 * Created by ST on 2016/12/16.
 */
public class MarketerOrderDetailsController implements ControlledStage {

    private StageController stageController;

    private String resource = "marketer/MarketerOrderDetails.fxml";

    @FXML
    private Label customerNameLabel;
    @FXML
    private Label customerPhoneLabel;
    @FXML
    private Label customerIDLabel;
    @FXML
    private Label orderIDLabel;
    @FXML
    private Label orderStatusLabel;
    @FXML
    private Label estimatedCheckinTimeLabel;
    @FXML
    private Label actualCheckinTimeLabel;
    @FXML
    private Label estimatedCheckoutTimeLabel;
    @FXML
    private Label actualCheckoutTimeLabel;
    @FXML
    private Label hotelNameLabel;
    @FXML
    private Label hotelIDLabel;
    @FXML
    private Label numOfCustomersLabel;
    @FXML
    private TextArea roomTypeTextArea;
    @FXML
    private Label haveChildrenLabel;
    @FXML
    private TextArea promotionTextArea;
    @FXML
    private Label initialPriceLabel;
    @FXML
    private Label finalPriceLabel;
    @FXML
    private Label remarksLabel;
    @FXML
    private Button checkButton;
    @FXML
    private Pane orderDetailsPane;

    private String marketerID;
    private String orderID;
    private OrderVO orderVO;
    private ProcessOrder processOrder;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * initial方法，初始化界面
     */
    public void initial(String orderID, String marketerID) throws RemoteException {
        this.marketerID = marketerID;
        //根据订单号得到订单
        this.orderID = orderID;
        processOrder = new ProcessOrderController();
        orderVO = processOrder.getSingle(orderID);

        //显示订单各项信息
        orderIDLabel.setText(orderID);
        customerIDLabel.setText(orderVO.customerID);
        customerNameLabel.setText(orderVO.customerName);
        customerPhoneLabel.setText(orderVO.phone);
        orderStatusLabel.setText(orderVO.orderType.toString());
        estimatedCheckinTimeLabel.setText(orderVO.estimatedCheckinTime == null ? "" : orderVO.estimatedCheckinTime.toString().substring(0, 10));
        estimatedCheckoutTimeLabel.setText(orderVO.estimatedCheckoutTime == null ? "" : orderVO.estimatedCheckoutTime.toString().substring(0, 10));
        actualCheckinTimeLabel.setText(orderVO.actualCheckinTime == null ? "" : orderVO.actualCheckinTime.toString().substring(0, 10));
        actualCheckoutTimeLabel.setText(orderVO.actualCheckoutTime == null ? "" : orderVO.actualCheckoutTime.toString().substring(0, 10));
        hotelNameLabel.setText(orderVO.hotelName);
        hotelIDLabel.setText(orderVO.hotelID);
        remarksLabel.setText(orderVO.remarks == null ? "无" : orderVO.remarks);
        haveChildrenLabel.setText(orderVO.haveChildren ? "有" : "无");
        numOfCustomersLabel.setText(String.valueOf(orderVO.numOfCustomers));
        String roomTypes = "";
        for (String types : orderVO.rooms) {
            roomTypes += types + "; ";
        }
        roomTypeTextArea.setText(roomTypes);
        promotionTextArea.setText(orderVO.promotionName);
        initialPriceLabel.setText(String.valueOf(orderVO.initialPrice));
        finalPriceLabel.setText(String.valueOf(orderVO.finalPrice));
        //根据订单状态设置按钮内容
        if (orderVO.orderType.equals(OrderStatus.ABNORMAL)) {
            checkButton.setText("撤销");
        } else {
            orderDetailsPane.getChildren().remove(checkButton);
        }
    }

    /**
     * 后退按钮结果，显示上级订单列表界面
     */
    @FXML
    private void showOrderList() {
        stageController = new StageController();
        stageController.closeStage(resource);
    }

    /**
     * 撤销按钮结果，显示确认撤销弹窗
     */
    @FXML
    private void revokeOrder() throws RemoteException {
        stageController = new StageController();
        stageController.loadStage("marketer/MarketerConfirmRevoke.fxml", 0.8);
        MarketerConfirmRevokeController marketerConfirmRevokeController = (MarketerConfirmRevokeController) stageController.getController();
        marketerConfirmRevokeController.initial(orderVO, marketerID, true);
    }

    /**
     * 退出按钮结果，退出程序
     */
    @FXML
    private void exit() {
        System.exit(0);
    }


}
