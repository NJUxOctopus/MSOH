package ui.view.presentation.customer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import ui.controller.ProcessOrderController;
import ui.controller.UserAdminController;
import ui.view.controllerservice.ProcessOrder;
import ui.view.controllerservice.UserAdmin;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ErrorBoxController;
import util.OrderStatus;
import util.ResultMessage;
import vo.CustomerVO;
import vo.OrderVO;

import java.rmi.RemoteException;

/**
 * Created by island on 2016/11/24.
 */
public class CustomerOrderDetailViewController implements ControlledStage {
    StageController stageController;

    private String resource = "customer/CustomerOrderDetailView.fxml";

    private String customerID;

    private String orderID;

    private String hotelID;

    @FXML
    private Label customerNameLabel;

    @FXML
    private ImageView background;

    @FXML
    private Button backButton;

    @FXML
    private Button processOrderButton;

    @FXML
    private Label customerIDLabel;

    @FXML
    private Label phoneNumberLabel;

    @FXML
    private Label orderIDLabel;

    @FXML
    private Label orderStatusLabel;

    @FXML
    private Label estimatedCheckInTimeLabel;

    @FXML
    private Label estimatedCheckOutTimeLabel;

    @FXML
    private Label actualCheckInTimeLabel;

    @FXML
    private Label actualCheckOutTimeLabel;

    @FXML
    private Label extraLabel;

    @FXML
    private Label hotelNameLabel;

    @FXML
    private Label hotelIdLabel;

    @FXML
    private Label roomTypeLabel;

    @FXML
    private Label numberOfPeopleLabel;

    @FXML
    private Label childLabel;

    @FXML
    private Label promotionLabel;

    @FXML
    private Label originalPriceLabel;

    @FXML
    private Label discountedPriceLabel;


    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    @FXML
    private void closeStage() {
        stageController.closeStage(resource);
    }

    /**
     * 按钮响应
     */
    @FXML
    private void processOrder(){
        if(processOrderButton.getText().equals("撤销订单")) {
            cancelOrder();
        }
        if(processOrderButton.getText().equals("评价酒店")){
            evaluateHotel();
        }
    }

    /**
     * 撤销订单
     */
    private void cancelOrder(){
        ProcessOrder processOrder = new ProcessOrderController();
        try {
            ResultMessage resultMessage = processOrder.cancelOrder(processOrder.getSingle(orderID));
            stageController = new StageController();
            stageController.loadStage("util/ErrorBoxView.fxml", 0.8);
            ErrorBoxController errorBoxController = (ErrorBoxController) stageController.getController();
            if(resultMessage ==  ResultMessage.Order_CancelOrderSuccess){
                errorBoxController.setLabel("撤销成功！");
                hideButton();
            }
            if(resultMessage ==  ResultMessage.Fail){
                errorBoxController.setLabel("撤销失败！");
            }
        }catch (RemoteException e){
            e.printStackTrace();
        }
    }

    /**
     * 跳转至评价订单界面
     */
    private void evaluateHotel(){
        stageController = new StageController();
        stageController.loadStage("customer/CustomerEvaluateView.fxml", 1);
        CustomerEvaluateViewController customerEvaluateViewController = (CustomerEvaluateViewController) stageController.getController();
        customerEvaluateViewController.init(customerID, hotelID);
    }

    /**
     * 隐藏按钮
     */
    public void hideButton(){
        processOrderButton.setOpacity(0);
    }

    /**
     * 订单详情界面初始化方法
     * @param customerID
     * @param orderID
     */
    public void init(String customerID, String orderID){
        this.customerID = customerID;
        this.orderID = orderID;
        System.out.print(customerID);
        setCustomerInfo();
        setOrderInfo();
    }

    /**
     * 设置客户相关信息
     */
    private void setCustomerInfo(){
        try {
            UserAdmin userAdmin = new UserAdminController();
            CustomerVO customerVO = userAdmin.findCustomerByID(customerID);

            customerNameLabel.setText(customerVO.name);
            phoneNumberLabel.setText(customerVO.phone);
            customerIDLabel.setText(customerID);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置订单相关信息
     */
    private void setOrderInfo(){
        try {
            ProcessOrder processOrder = new ProcessOrderController();
            OrderVO orderVO = processOrder.getSingle(orderID);
            //订单号
            hotelID = orderVO.orderID;
            orderIDLabel.setText(orderVO.orderID);
            //时间
            estimatedCheckInTimeLabel.setText(orderVO.estimatedCheckinTime.toString().substring(0,19));
            estimatedCheckOutTimeLabel.setText(orderVO.estimatedCheckoutTime.toString().substring(0,19));
            if(orderVO.actualCheckinTime != null) {
                actualCheckInTimeLabel.setText(orderVO.actualCheckinTime.toString().substring(0,19));
            }else{
                actualCheckInTimeLabel.setText("暂 无");
            }
            if(orderVO.actualCheckinTime != null) {
                actualCheckOutTimeLabel.setText(orderVO.actualCheckinTime.toString().substring(0,19));
            }else{
                actualCheckOutTimeLabel.setText("暂 无");
            }
            //订单状态
            OrderStatus orderStatus = orderVO.orderType;
            if(orderStatus == OrderStatus.FINISHED_UNEVALUATED) {
                orderStatusLabel.setText("未评价订单");
                processOrderButton.setOpacity(1);
                processOrderButton.setText("评价酒店");
            }
            if(orderStatus == OrderStatus.UNEXECUTED){
                orderStatusLabel.setText("未执行订单");
                processOrderButton.setOpacity(1);
                processOrderButton.setText("撤销订单");
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
            //备注
            extraLabel.setText(orderVO.remarks);

            //酒店信息
            hotelNameLabel.setText(orderVO.hotelID);
            hotelIdLabel.setText(orderVO.hotelName);
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
                    roomType += room[i] + "*" + roomNum[i] + '\n';
                }
            }
            roomTypeLabel.setText(roomType);
            numberOfPeopleLabel.setText(orderVO.numOfCustomers + "");
            if(orderVO.haveChildren){
                childLabel.setText("有");
            }else{
                childLabel.setText("无");
            }
            promotionLabel.setText(orderVO.promotionName);
            originalPriceLabel.setText(orderVO.initialPrice + "");
            discountedPriceLabel.setText(orderVO.finalPrice + "");

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * 退出系统
     */
    @FXML
    private void exit() {
        System.exit(0);
    }

}
