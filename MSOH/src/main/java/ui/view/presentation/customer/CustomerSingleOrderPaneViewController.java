package ui.view.presentation.customer;

import businesslogic.order_bl.OrderUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import ui.controller.ProcessOrderController;
import ui.view.controllerservice.ProcessOrder;
import ui.view.presentation.util.ConfirmExitController;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;
import util.OrderStatus;
import vo.OrderVO;

import java.rmi.RemoteException;
import java.text.DecimalFormat;

/**
 * Created by island on 2016/11/26.
 */
public class CustomerSingleOrderPaneViewController implements ControlledStage {
    StageController stageController = new StageController();

    private String customerID;

    private String orderID;

    @FXML
    private Button processOrderButton;

    @FXML
    private Button orderDetailButton;

    @FXML
    private Label orderIDLabel;

    @FXML
    private Label peopleLabel;

    @FXML
    private Label checkOutTimeLabel;

    @FXML
    private Label checkInTimeLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label roomTypeLabel;

    @FXML
    private Label orderStatusLabel;

    @FXML
    private Button hotelButton;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * 修改按钮种类
     */
    @FXML
    private void processOrder(){
        if(processOrderButton.getText().equals("撤销订单"))
            cancelOrder();
        if(processOrderButton.getText().equals("评价酒店"))
            showEvaluateView();

    }

    /**
     * 跳出确认撤销订单窗口
     */
    private void cancelOrder(){
        stageController = new StageController();
        stageController.loadStage("customer/CustomerConfirmCancelOrderView.fxml", 1);
        CustomerConfirmCancelOrderViewController customerConfirmCancelOrderViewController = (CustomerConfirmCancelOrderViewController) stageController.getController();
        customerConfirmCancelOrderViewController.init(orderID, orderStatusLabel);
    }

    /**
     * 跳转至评价订单页面
     */
    private void showEvaluateView(){
        stageController = new StageController();
        stageController.loadStage("customer/CustomerEvaluateView.fxml", 1);
        CustomerEvaluateViewController customerEvaluateViewController = (CustomerEvaluateViewController) stageController.getController();
        customerEvaluateViewController.init(customerID, orderID);
    }

    /**
     * 跳转至订单详情页面
     */
    @FXML
    private void viewDetails(){
        stageController = new StageController();
        stageController.loadStage("customer/CustomerOrderDetailView.fxml", 1);
        CustomerOrderDetailViewController customerOrderDetailViewController = (CustomerOrderDetailViewController) stageController.getController();
        customerOrderDetailViewController.init(customerID, orderID);
    }

    /**
     * 单个订单面板初始化
     * @param customerID
     * @param orderID
     */
    public void init(String customerID, String orderID){
        this.customerID = customerID;
        this.orderID = orderID;
        ProcessOrder processOrder = new ProcessOrderController();
        try{
            OrderVO orderVO = processOrder.getSingle(orderID);
            orderIDLabel.setText(orderID);
            peopleLabel.setText(orderVO.numOfCustomers + "");
            checkInTimeLabel.setText(orderVO.estimatedCheckinTime.toString().substring(0,10));
            checkOutTimeLabel.setText(orderVO.estimatedCheckoutTime.toString().substring(0,10));
            DecimalFormat df = new DecimalFormat("0.0");
            priceLabel.setText(df.format(orderVO.finalPrice) + "");

            String[] room = {"单人房", "标间", "大床房"};
            int[] roomNum = {0, 0, 0};
            for(int i = 0; i < orderVO.rooms.length; i++) {
                if(orderVO.rooms[i].equals(room[0])){
                    roomNum[0]++;
                }else if(orderVO.rooms[i].equals(room[1])){
                    roomNum[1]++;
                }else if(orderVO.rooms[i].equals(room[2])){
                    roomNum[2]++;
                }
            }
            String roomType = "";
            for(int i = 0; i < 3; i++){
                if(roomNum[i] != 0){
                    roomType += room[i] + "*" + roomNum[i];
                }
            }
            roomTypeLabel.setText(roomType);
            hotelButton.setText(orderVO.hotelName);
            processOrderButton.setOpacity(0);
            OrderStatus orderStatus = orderVO.orderType;
            if(orderStatus == OrderStatus.FINISHED_UNEVALUATED) {
                orderStatusLabel.setText("未评价订单");
                processOrderButton.setText("评价酒店");
                processOrderButton.setOpacity(1);
            }
            if(orderStatus == OrderStatus.UNEXECUTED){
                orderStatusLabel.setText("未执行订单");
                processOrderButton.setOpacity(1);
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
