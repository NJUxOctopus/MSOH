package ui.view.presentation.customer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import po.OrderPO;
import ui.controller.ProcessOrderController;
import ui.view.controllerservice.ProcessOrder;
import ui.view.presentation.PaneAdder;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ControlledStage;
import util.OrderStatus;
import vo.HotelVO;
import vo.OrderVO;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by island on 2016/11/23.
 */
public class CustomerOrderListViewController implements ControlledStage {
    StageController stageController;

    CustomerSingleOrderPaneViewController  customerSingleOrderPaneViewController;

    private String resource = "customer/CustomerOrderListView.fxml";

    private String customerID = "";

    private ProcessOrder processOrder;

    @FXML
    private AnchorPane orderListScrollPane;

    @FXML
    private ImageView background;

    @FXML
    private Button backButton;

    @FXML
    private ImageView orderButtonShade;

    @FXML
    private Button allOrderButton;

    @FXML
    private Button normalOrderButton;

    @FXML
    private Button abnormalOrderButton;

    @FXML
    private Button canceledOrderButton;

    @FXML
    private Button evaluateButton;

    @FXML
    private Button nextPageButton;

    @FXML
    private Button lastPageButton;

    @FXML
    private ChoiceBox selectPageBox;

    @FXML
    private Label emptyOrderLabel;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    @FXML
    private void closeStage() {
        stageController.closeStage(resource);
    }

    /**
     * 显示所有订单
     */
    @FXML
    private void showAllOrder(){
        orderButtonShade.setY(0);
        try {
            List<OrderVO> orderVOList = processOrder.getOrderByCustomerID(customerID);
            addOrderPane(orderVOList);
        }catch (RemoteException e){

        }
    }

    /**
     * 显示
     */
    @FXML
    private void showUnexecutedOrder(){
        orderButtonShade.setY(200);
        try {
            List<OrderVO> orderVOList = processOrder.getOrderByIDAndStatus(customerID, OrderStatus.UNEXECUTED);
            addOrderPane(orderVOList);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void showAbnormalOrder(){
        orderButtonShade.setY(100);
        try {
            List<OrderVO> orderVOList = processOrder.getOrderByIDAndStatus(customerID, OrderStatus.ABNORMAL);
            addOrderPane(orderVOList);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showCanceledOrder(){
        orderButtonShade.setY(300);
        try {
            List<OrderVO> orderVOList = processOrder.getOrderByIDAndStatus(customerID, OrderStatus.REVOKED);
            addOrderPane(orderVOList);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showEvaluateOrder(){
        orderButtonShade.setY(400);
        try {
            List<OrderVO> orderVOList = processOrder.getOrderByIDAndStatus(customerID, OrderStatus.FINISHED_UNEVALUATED);
            addOrderPane(orderVOList);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showFinishedOrder(){
        orderButtonShade.setY(500);
        try {
            List<OrderVO> orderVOList = processOrder.getOrderByIDAndStatus(customerID, OrderStatus.FINISHED_EVALUATED);
            addOrderPane(orderVOList);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void addOrderPane(List<OrderVO> orderList){
        orderListScrollPane.getChildren().clear();
        emptyOrderLabel.setOpacity(0);

        int num = 1;
        if (!(orderList.isEmpty())) {
            num = orderList.size();
            for (int i = 0; i < num; i++) {
                String orderID = orderList.get(i).orderID;
                orderListScrollPane.setPrefHeight(160 * num);
                PaneAdder paneAdder = new PaneAdder();
                paneAdder.addPane(orderListScrollPane, "customer/CustomerSingleOrderPaneView.fxml", 5, 160 * i - 155);
                customerSingleOrderPaneViewController = (CustomerSingleOrderPaneViewController) paneAdder.getController();
                customerSingleOrderPaneViewController.init(customerID, orderID);
            }
        }
        else{
            emptyOrderLabel.setOpacity(1);
        }

    }

    public void init(String customerID){
        this.customerID = customerID;
        showAllOrder();
        processOrder = new ProcessOrderController();
    }


}
