package ui.view.presentation.clerk;

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
 * Created by ST on 2016/11/30.
 */
public class ClerkSingleOrderController implements ControlledStage {

    private StageController stageController;

    @FXML
    private Pane singleOrderPane;
    @FXML
    private Label orderIDLabel;
    @FXML
    private Label orderStatusLabel;
    @FXML
    private Label hotelNameLabel;
    @FXML
    private Label roomTypeLabel;
    @FXML
    private Label numOfPeopleLabel;
    @FXML
    private Label priceLabel;
    @FXML
    private Button checkButton;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * initial方法，初始化界面
     */
    public void initial(OrderVO orderVO) throws RemoteException {
        orderIDLabel.setText(orderVO.orderID);
        orderStatusLabel.setText(orderVO.orderType.toString());
        hotelNameLabel.setText(orderVO.hotelName);
        String roomTypes = "";
        for (String types : orderVO.rooms) {
            roomTypes += types + "; ";
        }
        roomTypeLabel.setText(roomTypes);
        numOfPeopleLabel.setText(String.valueOf(orderVO.numOfCustomers));
        priceLabel.setText(String.valueOf(orderVO.finalPrice));

        if (orderVO.orderType.equals(OrderStatus.UNEXECUTED)) {
            //未执行订单，添加入住按钮
            checkButton.setText("入住");
        } else if (orderVO.orderType.equals(OrderStatus.EXECUTED)) {
            //已执行订单，添加退房按钮
            checkButton.setText("退房");
        } else {
            singleOrderPane.getChildren().remove(checkButton);
        }
    }

    /**
     * 详情按钮结果，显示订单详情界面
     */
    @FXML
    private void showOrderDetails() throws RemoteException {
        stageController = new StageController();
        stageController.loadStage("clerk/ClerkOrderDetails.fxml", 1);
        ClerkOrderDetailsController clerkOrderDetailsController = (ClerkOrderDetailsController) stageController.getController();
        clerkOrderDetailsController.initial(orderIDLabel.getText());
    }

    /**
     * 入住或退房按钮结果，把订单转换成已入住或已结束订单
     */
    @FXML
    private void confirmCheck() {
        stageController = new StageController();
        stageController.loadStage("clerk/ClerkConfirmCheck.fxml", 0.8);
        ClerkConfirmCheckController clerkConfirmCheckController = (ClerkConfirmCheckController) stageController.getController();
        if (checkButton.getText().equals("入住")) {
            clerkConfirmCheckController.initial("checkIn");
        } else if (checkButton.getText().equals("退房")) {
            clerkConfirmCheckController.initial("checkOut");
        }
    }


}
