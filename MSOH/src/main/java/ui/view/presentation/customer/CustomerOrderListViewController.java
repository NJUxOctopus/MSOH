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
            ProcessOrder processOrder = new ProcessOrderController();
            List<OrderVO> orderVOList = processOrder.getOrderByCustomerID(customerID);
            if(orderVOList.size() == 0)
                emptyOrderLabel.setOpacity(1);
            else {
                addOrderPane(orderVOList);
                emptyOrderLabel.setOpacity(0);

            }
        }catch (RemoteException e){

        }
    }

    /**
     * 查看未执行订单
     */
    @FXML
    private void showUnexecutedOrder(){
        orderButtonShade.setY(200);
        try {
            ProcessOrder processOrder = new ProcessOrderController();
            List<OrderVO> orderVOList = processOrder.getOrderByIDAndStatus(customerID, OrderStatus.UNEXECUTED);
            addOrderPane(orderVOList);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    /**
     * 查看异常订单
     */
    @FXML
    private void showAbnormalOrder(){
        orderButtonShade.setY(100);
        try {
            ProcessOrder processOrder = new ProcessOrderController();
            List<OrderVO> orderVOList = processOrder.getOrderByIDAndStatus(customerID, OrderStatus.ABNORMAL);
            addOrderPane(orderVOList);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查看已撤销订单
     */
    @FXML
    private void showCanceledOrder(){
        orderButtonShade.setY(300);
        try {
            ProcessOrder processOrder = new ProcessOrderController();
            List<OrderVO> orderVOList = processOrder.getOrderByIDAndStatus(customerID, OrderStatus.REVOKED);
            addOrderPane(orderVOList);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查看未评价订单
     */
    @FXML
    private void showEvaluateOrder(){
        orderButtonShade.setY(400);
        try {
            ProcessOrder processOrder = new ProcessOrderController();
            List<OrderVO> orderVOList = processOrder.getOrderByIDAndStatus(customerID, OrderStatus.FINISHED_UNEVALUATED);
            addOrderPane(orderVOList);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查看已完成订单
     */
    @FXML
    private void showFinishedOrder(){
        orderButtonShade.setY(500);
        try {
            ProcessOrder processOrder = new ProcessOrderController();
            List<OrderVO> orderVOList = processOrder.getOrderByIDAndStatus(customerID, OrderStatus.FINISHED_EVALUATED);
            addOrderPane(orderVOList);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化订单列表面板
     * @param orderList
     */
    public void addOrderPane(List<OrderVO> orderList){
        orderListScrollPane.getChildren().clear();
        emptyOrderLabel.setOpacity(1);

        int num = 1;
        if (orderList != null) {
            ProcessOrder processOrder = new ProcessOrderController();
            if (orderList.isEmpty()) {
                emptyOrderLabel.setOpacity(1);
            } else {
                try {
                    orderList = processOrder.sortByTime(orderList);
                    num = orderList.size();
                    orderListScrollPane.setPrefHeight(160 * num);
                    for (int i = 0; i < num; i++) {
                        String orderID = orderList.get(i).orderID;
                        PaneAdder paneAdder = new PaneAdder();
                        paneAdder.addPane(orderListScrollPane, "customer/CustomerSingleOrderPaneView.fxml", 5, 160 * i);
                        customerSingleOrderPaneViewController = (CustomerSingleOrderPaneViewController) paneAdder.getController();
                        customerSingleOrderPaneViewController.init(customerID, orderID);
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                emptyOrderLabel.setOpacity(0);

            }

        }
    }

    /**
     * 订单列表界面初始化方法
     * @param customerID
     */
    public void init(String customerID){
        this.customerID = customerID;
        showAllOrder();
    }

    /**
     * 退出系统
     */
    @FXML
    private void exit() {
        System.exit(0);
    }


}
