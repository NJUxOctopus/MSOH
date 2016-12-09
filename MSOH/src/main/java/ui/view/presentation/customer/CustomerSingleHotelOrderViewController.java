package ui.view.presentation.customer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import ui.controller.ProcessOrderController;
import ui.view.controllerservice.ProcessOrder;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;
import util.OrderStatus;
import vo.OrderVO;

import java.rmi.RemoteException;

/**
 * Created by island on 2016/12/1.
 */
public class CustomerSingleHotelOrderViewController implements ControlledStage {
    StageController stageController = new StageController();

    @FXML
    private Button orderDetailButton;

    @FXML
    private Label orderIDLabel;

    @FXML
    private Label peopleLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label roomTypeLabel;

    @FXML
    private Label orderStatusLabel;

    @FXML
    private Label childLabel;

    private String customerID;

    private String orderID;


    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    @FXML
    private void viewDetails(){
        stageController = new StageController();
        stageController.loadStage("customer/CustomerSingleHotelOrderView.fxml", 1);
    }

    public void init(String customerID, String orderID){
        this.customerID = customerID;
        this.orderID = orderID;
        ProcessOrder processOrder = new ProcessOrderController();
        try{
            OrderVO orderVO = processOrder.getSingle(orderID);
            orderIDLabel.setText(orderID);
            peopleLabel.setText(orderVO.numOfCustomers + "");
            priceLabel.setText(orderVO.finalPrice + "");
            String roomType = "";
            for(int i = 0; i < orderVO.rooms.length; i++){
                roomType = orderVO.rooms[i] + "*" + orderVO.orderType;
            }
            roomTypeLabel.setText(orderVO.rooms[0]);
            OrderStatus orderStatus = orderVO.orderType;
            if(orderStatus == OrderStatus.FINISHED_UNEVALUATED) {
                orderStatusLabel.setText("未评价订单");
            }
            if(orderStatus == OrderStatus.UNEXECUTED){
                orderStatusLabel.setText("未执行订单");
            }
            if(orderStatus == OrderStatus.REVOKED){
                orderStatusLabel.setText("已撤销订单");
            }
            if(orderStatus == OrderStatus.ABNORMAL){
                orderStatusLabel.setText("异常订单");
            }
            if(orderStatus == OrderStatus.FINISHED_EVALUATED){
                orderStatusLabel.setText("已完成订单");
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }



}
