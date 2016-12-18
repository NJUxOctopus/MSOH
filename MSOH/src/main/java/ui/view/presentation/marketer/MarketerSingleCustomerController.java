package ui.view.presentation.marketer;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;
import vo.CustomerVO;

import java.rmi.RemoteException;

/**
 * Created by ST on 2016/12/1.
 */
public class MarketerSingleCustomerController implements ControlledStage {

    private StageController stageController;

    @FXML
    private Label customerNameLabel;
    @FXML
    private Label customerIDLabel;
    @FXML
    private Label customerCreditLabel;

    private CustomerVO customerVO;
    private String marketerID;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * initial方法，初始化界面
     */
    public void initial(CustomerVO customerVO, String marketerID) throws RemoteException {
        this.marketerID = marketerID;
        this.customerVO = customerVO;
        customerNameLabel.setText(customerVO.name);
        customerIDLabel.setText(customerVO.ID);
        customerCreditLabel.setText(String.valueOf(customerVO.credit));
    }

    /**
     * 充值按钮结果，显示充值页面
     */
    @FXML
    private void chargeCredit() throws RemoteException {
        stageController = new StageController();
        stageController.loadStage("marketer/MarketerSelectCharge.fxml", 0.8);

        MarketerSelectChargeController marketerSelectChargeController = (MarketerSelectChargeController) stageController.getController();
        marketerSelectChargeController.initial(customerVO, marketerID);
    }
}
