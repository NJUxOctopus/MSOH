package ui.view.presentation.marketer;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ControlledStage;
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

    private String resource = "marketer/MarketerSelectCharge,fxml";

    /**
     * initial方法，初始化界面
     */
    public void initial() {

    }

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * 确认按钮结果，充值信用
     */
    @FXML
    private void confirmCharge() {
        //获取当前时间
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Timestamp time = Timestamp.valueOf(dateFormat.format(date));


    }

    /**
     * 取消按钮结果，关闭弹窗
     */
    @FXML
    private void cancelCharge() {
        stageController = new StageController();
        stageController.closeStage(resource);
    }

}
