package ui.view.presentation.customer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import ui.controller.*;
import ui.view.controllerservice.*;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.util.ErrorBoxController;
import util.CreditChangeReason;
import util.ResultMessage;
import vo.CreditRecordVO;
import vo.CustomerVO;
import vo.OrderVO;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by island on 2016/12/8.
 */
public class CustomerConfirmCancelOrderViewController implements ControlledStage {

    StageController stageController;

    private static String resource = "customer/CustomerConfirmCancelOrderView.fxml";

    @FXML
    private Button exitButton;

    @FXML
    private Button cancelButton;

    private String orderID;

    private Label orderStatusLabel;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * 确认退出按钮结果，显示上级修改个人信息界面
     */
    @FXML
    private void confirm() {

        ProcessOrder processOrder = new ProcessOrderController();
        try {
            OrderVO orderVO = processOrder.getSingle(orderID);
            ResultMessage resultMessage = processOrder.cancelOrder(orderVO);
            if(resultMessage == ResultMessage.Fail){
                stageController.loadStage("util/ErrorBox.fxml", 0.8);
                ErrorBoxController errorBoxController = (ErrorBoxController) stageController.getController("util/ErrorBox.fxml");
                errorBoxController.setLabel("撤销失败！");
            }else{
                stageController = new StageController();
                stageController.closeStage(resource);
                CustomerOrderListViewController customerOrderListViewController = (CustomerOrderListViewController) stageController.getController("customer/CustomerOrderListView.fxml");
                customerOrderListViewController.init(orderVO.customerID);
                HotelAdmin hotelAdmin = new HotelAdminController();
                hotelAdmin.changeAvailableRoom(orderVO, 1);
                hotelAdmin.changeReservedRoom(orderVO, -1);
                if(resultMessage == ResultMessage.Order_CancelOrderBetweenSixHour){
                    //获取当前时间
                    Date date = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Timestamp time = Timestamp.valueOf(dateFormat.format(date));
                    UserAdmin userAdmin = new UserAdminController();
                    CustomerVO customerVO = userAdmin.findCustomerByID(orderVO.customerID);
                    CreditRecordVO creditRecordVO = new CreditRecordVO((int) - orderVO.finalPrice, time, orderVO.customerName, orderVO.customerID,
                            (int) (customerVO.credit + orderVO.finalPrice), orderVO.orderID, "", CreditChangeReason.Order_Executed);
                    CreditRecord creditRecord = new CreditRecordController();
                    creditRecord.addCreditRecord(orderVO.customerID, creditRecordVO);
                    EditMemberLevel editMemberLevel = new EditMemberLevelController();
                    editMemberLevel.changeGrade(orderVO.customerID);
                    stageController.loadStage("util/ErrorBox.fxml", 0.8);
                    ErrorBoxController errorBoxController = (ErrorBoxController) stageController.getController("util/ErrorBox.fxml");
                    errorBoxController.setLabel("已扣除订单价值一半的信用值！");
                }else{
                    stageController.loadStage("util/ErrorBox.fxml", 0.8);
                    ErrorBoxController errorBoxController = (ErrorBoxController) stageController.getController("util/ErrorBox.fxml");
                    errorBoxController.setLabel("撤销成功！");
                }
            }
        }catch (RemoteException e){
            e.printStackTrace();
        }

    }

    /**
     * 取消退出按钮结果，关闭弹窗
     */
    @FXML
    private void cancel(){
        stageController = new StageController();
        stageController.closeStage(resource);
    }

    public void init(String orderID, Label orderStatusLabel){
        this.orderID = orderID;
        this.orderStatusLabel = orderStatusLabel;
    }
}


