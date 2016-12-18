package ui.view.presentation.marketer;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import ui.controller.CreditRecordController;
import ui.controller.UserAdminController;
import ui.view.controllerservice.CreditRecord;
import ui.view.controllerservice.UserAdmin;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.util.ErrorBoxController;
import util.CreditChangeReason;
import util.ResultMessage;
import vo.CreditRecordVO;
import vo.CustomerVO;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ST on 2016/12/16.
 */
public class MarketerSelectChargeController implements ControlledStage {

    private StageController stageController;

    @FXML
    private TextField chargeLimitTextField;

    private CustomerVO customerVO;
    private UserAdmin userAdmin;
    private CreditRecord creditRecord;
    private String marketerID;
    private String marketerName;

    private String resource = "marketer/MarketerSelectCharge.fxml";

    /**
     * initial方法，初始化界面
     */
    public void initial(CustomerVO customerVO, String marketerID) throws RemoteException {
        userAdmin = new UserAdminController();
        this.customerVO = customerVO;
        this.marketerID = marketerID;
        marketerName = userAdmin.findMarketerByID(marketerID).name;
    }

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * 确认按钮结果，充值信用
     */
    @FXML
    private void confirmCharge() throws RemoteException {
        //获取当前时间
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Timestamp time = Timestamp.valueOf(dateFormat.format(date));

        int credit = (int) (Double.parseDouble(chargeLimitTextField.getText()) * 100);

        CreditRecordVO creditRecordVO = new CreditRecordVO(credit, time, customerVO.name, customerVO.ID,
                customerVO.credit + credit, "", marketerName, CreditChangeReason.Charge);
        creditRecord = new CreditRecordController();
        if (creditRecord.addCreditRecord(customerVO.ID, creditRecordVO).equals(ResultMessage.Customer_AddCreditRecordSuccess)) {
            stageController = this.returnMessage("充值成功！");
            stageController.closeStage(resource);
        } else {
            this.returnMessage("充值失败！");
        }

    }

    /**
     * 取消按钮结果，关闭弹窗
     */
    @FXML
    private void cancelCharge() {
        stageController = new StageController();
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

}
