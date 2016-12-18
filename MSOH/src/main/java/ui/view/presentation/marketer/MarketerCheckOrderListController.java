package ui.view.presentation.marketer;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import ui.controller.ProcessOrderController;
import ui.view.controllerservice.ProcessOrder;
import ui.view.presentation.PaneAdder;
import ui.view.presentation.clerk.ClerkSingleOrderController;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ErrorBoxController;
import util.OrderStatus;
import vo.OrderVO;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ST on 2016/12/1.
 */
public class MarketerCheckOrderListController implements ControlledStage {

    private StageController stageController;

    @FXML
    private Rectangle orderSelectShade;
    @FXML
    private TextField searchTextField;
    @FXML
    private AnchorPane orderListPane;

    private String marketerID;
    private ProcessOrder processOrder;
    private OrderVO orderVO;
    private List<OrderVO> orderVOs = new ArrayList<OrderVO>();
    private PaneAdder paneAdder;

    //获取当前日期
    Date date = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
    Timestamp time = Timestamp.valueOf(sdf.format(date));

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * initial方法，初始化界面
     */
    public void initial(String ID) throws RemoteException {
        this.marketerID = ID;
        processOrder = new ProcessOrderController();
        paneAdder = new PaneAdder();

        //默认显示所有的未执行订单
        this.showUnexecutedOrder();
    }

    /**
     * 未执行订单结果，显示当日的所有未执行订单
     */
    @FXML
    private void showUnexecutedOrder() throws RemoteException {
        orderSelectShade.setX(0);
        orderSelectShade.setWidth(334);
        orderVOs = processOrder.getOrderByStatusAndDate(time, OrderStatus.UNEXECUTED);
        if (!orderVOs.isEmpty()) {
            orderVOs = processOrder.sortByTime(orderVOs);
            this.addOrder(orderVOs);
        }
    }

    /**
     * 异常订单结果，显示所有异常订单
     */
    @FXML
    private void showAbnormalOrder() throws RemoteException {
        orderSelectShade.setX(334);
        orderSelectShade.setWidth(356);
        orderVOs = processOrder.getOrderByStatus(OrderStatus.ABNORMAL);
        if (!orderVOs.isEmpty()) {
            orderVOs = processOrder.sortByTime(orderVOs);
            this.addOrder(orderVOs);
        }
    }

    /**
     * 把singleOrder加到Pane上的方法
     *
     * @param orderVOList
     */
    private void addOrder(List<OrderVO> orderVOList) throws RemoteException {
        orderListPane.getChildren().clear();
        int numOfOrder = orderVOList.size();
        orderListPane.setPrefHeight(numOfOrder * 160);

        for (int i = 0; i < numOfOrder; i++) {
            orderVO = orderVOList.get(i);
            paneAdder.addPane(orderListPane, "marketer/MarketerSingleOrder.fxml", 0, i * 160);
            MarketerSingleOrderController marketerSingleOrderController = (MarketerSingleOrderController) paneAdder.getController();
            marketerSingleOrderController.initial(orderVO, marketerID);
        }
    }

    /**
     * 搜索按钮结果，显示搜索订单结果
     */
    @FXML
    private void searchOrder() throws RemoteException {
        String searchInfo = searchTextField.getText();
        orderListPane.getChildren().clear();
        if (searchInfo.length() == 18) {
            //输入的是顾客ID
            processOrder = new ProcessOrderController();
            List<OrderVO> orderVOList = processOrder.getOrderByCustomerID(searchTextField.getText());
            this.addOrder(orderVOList);
        } else if (searchInfo.length() == 9) {
            //输入的是订单号
            processOrder = new ProcessOrderController();
            orderVO = processOrder.getSingle(searchTextField.getText());
            PaneAdder paneAdder = new PaneAdder();
            paneAdder.addPane(orderListPane, "clerk/ClerkSingleOrder.fxml", 0, 0);
            MarketerSingleOrderController marketerSingleOrderController = (MarketerSingleOrderController) paneAdder.getController();
            marketerSingleOrderController.initial(orderVO, marketerID);
        } else {
            //输入的信息格式错误
            stageController = new StageController();
            stageController.loadStage("util/ErrorBoxView.fxml", 0.8);
            ErrorBoxController errorBoxController = (ErrorBoxController) stageController.getController();
            errorBoxController.setLabel("格式错误！");
        }
    }
}
