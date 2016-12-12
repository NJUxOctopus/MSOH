package ui.view.presentation.clerk;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import ui.controller.HotelAdminController;
import ui.controller.ProcessOrderController;
import ui.view.controllerservice.HotelAdmin;
import ui.view.controllerservice.ProcessOrder;
import ui.view.presentation.PaneAdder;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ErrorBoxController;
import util.OrderStatus;
import vo.HotelVO;
import vo.OrderVO;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by ST on 2016/11/30.
 */
public class ClerkCheckOrderListController implements ControlledStage {

    private StageController stageController;

    @FXML
    private AnchorPane orderListPane;
    @FXML
    private TextField searchTextField;
    @FXML
    private Rectangle orderSelectShade;

    private String clerkID;
    private String hotelID;
    private HotelAdmin hotelAdmin;
    private HotelVO hotelVO;
    private OrderVO orderVO;
    private ProcessOrder processOrder;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * initial方法，初始化界面
     */
    public void initial(String ID) throws RemoteException {
        this.clerkID = ID;
        hotelAdmin = new HotelAdminController();
        hotelVO = hotelAdmin.findByClerkID(clerkID);
        hotelID = hotelVO.hotelID;

        //默认显示所有未执行订单
        this.showUnexecutedOrders();
//        processOrder = new ProcessOrderController();
//        List<OrderVO> orderVOList = processOrder.getOrderByHotelAndStatus(hotelID, OrderStatus.UNEXECUTED);
//        if (!orderVOList.isEmpty()) {
//            this.addOrder(orderVOList);
//        }

    }

    /**
     * 线下入住按钮结果，显示线下入住界面
     */
    @FXML
    private void showCreateOfflineOrder() throws RemoteException {
        stageController = new StageController();
        stageController.loadStage("clerk/ClerkChooseRoom.fxml", 1);
        ClerkChooseRoomController clerkChooseRoomController = (ClerkChooseRoomController)stageController.getController();
        clerkChooseRoomController.initial(hotelID);
    }

    /**
     * 搜索按钮结果，显示搜索订单结果
     */
    @FXML
    private void searchOrder() throws RemoteException {
        orderListPane.getChildren().remove(orderSelectShade);
        String searchInfo = searchTextField.getText();
        if (searchInfo.length() == 18) {
            //输入的是顾客ID
            orderListPane.getChildren().clear();
            processOrder = new ProcessOrderController();
            List<OrderVO> orderVOList = processOrder.getOrderByCustomerID(searchTextField.getText());
            this.addOrder(orderVOList);
        } else if (searchInfo.length() == 9) {
            //输入的是订单号
            orderListPane.getChildren().clear();
            processOrder = new ProcessOrderController();
            orderVO = processOrder.getSingle(searchTextField.getText());
            PaneAdder paneAdder = new PaneAdder();
            ClerkSingleOrderController clerkSingleOrderController = (ClerkSingleOrderController) paneAdder.getController();
            clerkSingleOrderController.initial(orderVO);
            paneAdder.addPane(orderListPane, "clerk/ClerkSingleOrder.fxml", 0, 0);
        } else {
            //输入的信息格式错误
            stageController = new StageController();
            stageController.loadStage("util/ErrorBoxView.fxml", 0.8);
            ErrorBoxController errorBoxController = (ErrorBoxController) stageController.getController();
            errorBoxController.setLabel("格式错误！");
        }
    }

    /**
     * 把singleOrder加到Pane上的方法
     *
     * @param orderVOList
     */
    private void addOrder(List<OrderVO> orderVOList) throws RemoteException {
        PaneAdder paneAdder = new PaneAdder();
        int numOfOrder = orderVOList.size();
        orderListPane.setPrefHeight(numOfOrder * 160);

        for (int i = 0; i < numOfOrder; i++) {
            orderVO = orderVOList.get(i);
            paneAdder.addPane(orderListPane, "clerk/ClerkSingleOrder.fxml", 0, i * 160);
            ClerkSingleOrderController clerkSingleOrderController = (ClerkSingleOrderController) paneAdder.getController();
            clerkSingleOrderController.initial(orderVO);
        }
    }

    /**
     * 未执行订单按钮结果，显示该酒店的所有未执行订单
     */
    @FXML
    private void showUnexecutedOrders() throws RemoteException {
        orderSelectShade.setX(0);
        processOrder = new ProcessOrderController();
        List<OrderVO> orderVOList = processOrder.getOrderByHotelAndStatus(hotelID, OrderStatus.UNEXECUTED);
        orderVOList = processOrder.sortByTime(orderVOList);
        if (!orderVOList.isEmpty()) {
            this.addOrder(orderVOList);
        }
    }

    /**
     * 已执行订单按钮结果，显示该酒店的所有已执行订单
     */
    @FXML
    private void showExecutedOrders() throws RemoteException {
        orderSelectShade.setX(137);
        processOrder = new ProcessOrderController();
        List<OrderVO> orderVOList = processOrder.getOrderByHotelAndStatus(hotelID, OrderStatus.EXECUTED);
        orderVOList = processOrder.sortByTime(orderVOList);
        if (!orderVOList.isEmpty()) {
            this.addOrder(orderVOList);
        }
    }

    /**
     * 已结束订单按钮结果，显示该酒店的所有已结束订单
     */
    @FXML
    private void showFinishedOrders() throws RemoteException {
        orderSelectShade.setX(274);
        processOrder = new ProcessOrderController();
        List<OrderVO> orderVOList = processOrder.getOrderByHotelAndStatus(hotelID, OrderStatus.FINISHED_EVALUATED);//已结束且已评价的所有订单
        orderVOList.addAll(processOrder.getOrderByHotelAndStatus(hotelID, OrderStatus.FINISHED_UNEVALUATED));//已结束且未评价的所有订单
        orderVOList = processOrder.sortByTime(orderVOList);
        if (!orderVOList.isEmpty()) {
            this.addOrder(orderVOList);
        }
    }

    /**
     * 已撤销订单按钮结果，显示该酒店的所有已撤销订单
     */
    @FXML
    private void showRevokedOrders() throws RemoteException {
        orderSelectShade.setX(412);
        processOrder = new ProcessOrderController();
        List<OrderVO> orderVOList = processOrder.getOrderByHotelAndStatus(hotelID, OrderStatus.REVOKED);
        orderVOList = processOrder.sortByTime(orderVOList);
        if (!orderVOList.isEmpty()) {
            this.addOrder(orderVOList);
        }
    }

    /**
     * 异常订单按钮结果，显示该酒店的所有异常订单
     */
    @FXML
    private void showAbnormalOrders() throws RemoteException {
        orderSelectShade.setX(550);
        processOrder = new ProcessOrderController();
        List<OrderVO> orderVOList = processOrder.getOrderByHotelAndStatus(hotelID, OrderStatus.ABNORMAL);
        orderVOList = processOrder.sortByTime(orderVOList);
        if (!orderVOList.isEmpty()) {
            this.addOrder(orderVOList);
        }
    }
}
