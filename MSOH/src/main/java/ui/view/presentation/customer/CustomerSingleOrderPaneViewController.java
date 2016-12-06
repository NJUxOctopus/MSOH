package ui.view.presentation.customer;

import businesslogic.order_bl.OrderUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import ui.controller.ProcessOrderController;
import ui.view.controllerservice.ProcessOrder;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;
import util.OrderStatus;
import vo.OrderVO;

import java.rmi.RemoteException;

/**
 * Created by island on 2016/11/26.
 */
public class CustomerSingleOrderPaneViewController implements ControlledStage {
    StageController stageController = new StageController();

    private String customerID;

    private String orderID;

    @FXML
    private Pane singleOrderPane;

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

    @FXML
    private void processOrder(){
        if(processOrderButton.getText().equals("撤销订单"))
            cancelOrder();
        if(processOrderButton.getText().equals("评价酒店"))
            showEvaluateView();

    }

    private void cancelOrder(){

    }

    private void showEvaluateView(){
        stageController = new StageController();
        stageController.loadStage("customer/CustomerEvaluateView.fxml", 1);
        CustomerEvaluateViewController customerEvaluateViewController = (CustomerEvaluateViewController) stageController.getController();
        customerEvaluateViewController.init(customerID, orderID);
    }

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
        /*
        ProcessOrder processOrder = new ProcessOrderController();
        try{
             OrderVO orderVO = processOrder.getSingle(orderID);
            orderIDLabel.setText(orderID);
            peopleLabel.setText(orderVO.numOfCustomers + "");
            checkOutTimeLabel.setText(orderVO.estimatedCheckinTime.toString());
            checkInTimeLabel.setText(orderVO.estimatedCheckoutTime.toString());
            priceLabel.setText(orderVO.finalPrice + "");
            roomTypeLabel.setText(orderVO.rooms[0]);
            orderStatusLabel.setText(orderVO.orderType.toString());
            hotelButton.setText(orderVO.hotelName);
            processOrderButton.setOpacity(0);
            if(orderVO.orderType == OrderStatus.FINISHED_UNEVALUATED) {
                processOrderButton.setText("评价酒店");
                processOrderButton.setOpacity(1);
            }
            if(orderVO.orderType == OrderStatus.UNEXECUTED){
                processOrderButton.setText("撤销订单");
                processOrderButton.setOpacity(1);
            }
        } catch (RemoteException e) {
             e.printStackTrace();
        }
        */
        orderIDLabel.setText(orderID);
        peopleLabel.setText("");
        checkOutTimeLabel.setText("");
        checkInTimeLabel.setText("");
        priceLabel.setText("");
        roomTypeLabel.setText("");
        orderStatusLabel.setText("");
        hotelButton.setText("");
        processOrderButton.setText("评价酒店");

    }


}
