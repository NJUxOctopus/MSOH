package ui.view.presentation.customer;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;

/**
 * Created by island on 2016/11/30.
 */
public class CustomerSinglePromotionViewController implements ControlledStage {
    StageController stageController = new StageController();

    @FXML
    private Label promotionNameLabel;

    private String promotionName;

    @FXML
    private Label discountLabel;

    private String discount;

    @FXML
    private Label staffLabel;

    private String staff;

    @FXML
    private Label timeLabel;

    private String time;


    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    public void init(){
        promotionName = "111";
        discount = "2";
        staff = "1111";
        time = "11111";
        promotionNameLabel.setText(promotionName);
        discountLabel.setText(discount);
        staffLabel.setText(staff);
        timeLabel.setText(time);
    }

}