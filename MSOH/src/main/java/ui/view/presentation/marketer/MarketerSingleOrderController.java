package ui.view.presentation.marketer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;
import util.OrderStatus;
import vo.OrderVO;

import java.rmi.RemoteException;

/**
 * Created by ST on 2016/12/1.
 */
public class MarketerSingleOrderController implements ControlledStage {

    private StageController stageController;

    @FXML
    private Pane singleOrderPane;
    @FXML
    private Label orderIDLabel;
    @FXML
    private Label orderStatusLabel;
    @FXML
    private Label roomTypeLabel;
    @FXML
    private Label numOfPeopleLabel;
    @FXML
    private Label priceLabel;
    @FXML
    private Button checkButton;

    private String marketerID;
    private OrderVO orderVO;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * initial方法，初始化界面
     */
    public void initial(OrderVO orderVO, String marketerID) throws RemoteException {
        this.marketerID = marketerID;
        this.orderVO = orderVO;
        orderIDLabel.setText(orderVO.orderID);
        orderStatusLabel.setText(orderVO.orderType.toString());
        String roomTypes = "";
        for (String types : orderVO.rooms) {
            roomTypes += types + "; ";
        }
        roomTypeLabel.setText(roomTypes);
        numOfPeopleLabel.setText(String.valueOf(orderVO.numOfCustomers));
        priceLabel.setText(String.valueOf(orderVO.finalPrice));

        if (orderVO.orderType.equals(OrderStatus.ABNORMAL)) {
            //异常订单，添加撤销按钮
            checkButton.setText("撤销");
        } else {
            singleOrderPane.getChildren().remove(checkButton);
        }
    }

    /**
     * 撤销按钮结果，显示确认撤销弹窗
     */
    @FXML
    private void confirmRevoke() {
        stageController = new StageController();
        stageController.loadStage("marketer/MarketerConfirmRevoke.fxml", 0.8);

        MarketerConfirmRevokeController marketerConfirmRevokeController = (MarketerConfirmRevokeController) stageController.getController();
        marketerConfirmRevokeController.initial(orderVO, marketerID);
    }

    /**
     * 详情按钮结果，显示订单详情
     */
    @FXML
    private void showOrderDetails() throws RemoteException {
        stageController = new StageController();
        stageController.loadStage("marketer/MarketerOrderDetails.fxml", 1);

        MarketerOrderDetailsController marketerOrderDetailsController = (MarketerOrderDetailsController) stageController.getController();
        marketerOrderDetailsController.initial(orderIDLabel.getText(), marketerID);
    }
}
