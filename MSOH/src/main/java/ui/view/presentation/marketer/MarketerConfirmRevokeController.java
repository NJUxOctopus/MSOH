package ui.view.presentation.marketer;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import ui.controller.CreditRecordController;
import ui.controller.ProcessOrderController;
import ui.controller.UserAdminController;
import ui.view.controllerservice.CreditRecord;
import ui.view.controllerservice.ProcessOrder;
import ui.view.controllerservice.UserAdmin;
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
 * Created by ST on 2016/12/16.
 */
public class MarketerConfirmRevokeController implements ControlledStage {

    private StageController stageController;

    @FXML
    private ChoiceBox revokeCreditChoiceBox;

    private OrderVO orderVO;
    private String marketerID;
    private ProcessOrder processOrder;
    private CreditRecord creditRecord;
    private CustomerVO customerVO;
    private UserAdmin userAdmin;
    private String marketerName;
    private boolean isOrderDetails;

    private String resource = "marketer/MarketerConfirmRevoke.fxml";

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * 初始化方法，初始界面
     *
     * @param orderVO
     */
    public void initial(OrderVO orderVO, String marketerID) throws RemoteException {
        userAdmin = new UserAdminController();
        this.isOrderDetails = false;
        stageController = new StageController();

        this.orderVO = orderVO;
        this.marketerID = marketerID;
        this.customerVO = userAdmin.findCustomerByID(orderVO.customerID);
        this.marketerName = userAdmin.findMarketerByID(marketerID).name;

        revokeCreditChoiceBox.setItems(FXCollections.observableArrayList("全部信用值", "一半信用值"));
        revokeCreditChoiceBox.getSelectionModel().select("全部信用值");
    }

    /**
     * 重载初始化方法，从订单详情操作时初始界面
     *
     * @param orderVO
     */
    public void initial(OrderVO orderVO, String marketerID, boolean isOrderDetails) throws RemoteException {
        this.initial(orderVO, marketerID);
        this.isOrderDetails = isOrderDetails;
    }

    /**
     * 确认按钮结果，撤销该异常订单
     */
    @FXML
    private void confirmRevoke() throws RemoteException {
        processOrder = new ProcessOrderController();
        creditRecord = new CreditRecordController();
        //获取当前时间
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Timestamp time = Timestamp.valueOf(dateFormat.format(date));

        String toBeRevoked = (String) revokeCreditChoiceBox.getSelectionModel().getSelectedItem();
        int credit = 0;
        if (toBeRevoked.equals("全部信用值")) {
            credit = (int) orderVO.finalPrice;
        } else if (toBeRevoked.equals("一半信用值")) {
            credit = (int) orderVO.finalPrice / 2;
        }
        CreditRecordVO creditRecordVO = new CreditRecordVO(credit, time, orderVO.customerName, orderVO.customerID,
                customerVO.credit + credit, orderVO.orderID, marketerName, CreditChangeReason.Order_Revoked);

        if (processOrder.renewOrder(orderVO).equals(ResultMessage.Order_RenewOrderSuccess)) {
            if (creditRecord.addCreditRecord(orderVO.customerID, creditRecordVO).equals(ResultMessage.Customer_AddCreditRecordSuccess)) {
                stageController = this.returnMessage("撤销成功！");
                renew();
            } else {
                this.returnMessage("更改信用值失败！");
            }
        } else {
            returnMessage("未知错误！");
        }
    }

    /**
     * 取消按钮结果，关闭弹窗
     */
    @FXML
    private void cancelRevoke() {
        stageController.closeStage(resource);
    }

    /**
     * 返回提示信息
     *
     * @param error
     * @return
     */

    private StageController returnMessage(String error) {
        stageController = new StageController();
        stageController.loadStage("util/ErrorBoxView.fxml", 0.8);
        ErrorBoxController errorBoxController = (ErrorBoxController) stageController.getController();
        errorBoxController.setLabel(error);
        return stageController;
    }

    /**
     * 办理成功后刷新订单列表页面
     */
    private void renew() throws RemoteException {
        if (isOrderDetails)
            //如果从订单详情页面操作，成功后要关闭订单详情页面
            stageController.closeStage("marketer/MarketerOrderDetails.fxml");
        stageController.closeStage(resource);
        MarketerCheckOrderListController marketerCheckOrderListController = (MarketerCheckOrderListController) stageController.getController();
        marketerCheckOrderListController.initial(marketerID);
    }


}

