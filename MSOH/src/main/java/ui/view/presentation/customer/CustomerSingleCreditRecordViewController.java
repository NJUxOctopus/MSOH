package ui.view.presentation.customer;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;
import vo.CreditRecordVO;

/**
 * Created by island on 2016/11/30.
 */
public class CustomerSingleCreditRecordViewController implements ControlledStage {
    StageController stageController = new StageController();

    private String customerID;

    @FXML
    private Label timeLabel;

    @FXML
    private Label actionLabel;

    @FXML
    private Label orderLabel;

    @FXML
    private Label changeNumberLabel;

    @FXML
    private Label creditResultLabel;


    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * 单个信用记录面板初始化方法
     */
    public void init(CreditRecordVO creditRecordVO ){
        timeLabel.setText("");
        actionLabel.setText("");
        orderLabel.setText("");
        changeNumberLabel.setText("");
        creditResultLabel.setText("");
        setCreditRecordInfo(creditRecordVO);
    }

    public void setCreditRecordInfo(CreditRecordVO creditRecordVO){
        timeLabel.setText(creditRecordVO.changeTime + "");
        changeNumberLabel.setText(creditRecordVO.variation + "");
        creditResultLabel.setText(creditRecordVO.afterChangeCredit + "");
        if(creditRecordVO.orderID.equals("")){
            orderLabel.setText("无");
        }
        else{
            orderLabel.setText(creditRecordVO.orderID);
            actionLabel.setText("完成订单");
        }
        if(!creditRecordVO.marketerName.equals("")){
            actionLabel.setText("信用充值");
            orderLabel.setText("无");
        }
    }

}
