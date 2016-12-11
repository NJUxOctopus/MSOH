package ui.view.presentation.customer;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ControlledStage;
import vo.CreditRecordVO;

/**
 * Created by island on 2016/12/11.
 */
public class CustomerSingleLevelController implements ControlledStage {

    StageController stageController = new StageController();

    @FXML
    private Label levelLabel;

    @FXML
    private Label creditLabel;


    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * 单个信用记录面板初始化方法
     */
    public void init(int level, int credit ){
        levelLabel.setText(level + "");
        creditLabel.setText(credit + "");
    }


}
