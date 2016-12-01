package ui.view.presentation.customer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;

/**
 * Created by island on 2016/12/1.
 */
public class CustomerSingleHotelOrderViewController implements ControlledStage {
    StageController stageController = new StageController();

    @FXML
    private Button orderDetailButton;

    @FXML
    private Label orderIDLabel;

    @FXML
    private Label peopleLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label roomTypeLabel;

    @FXML
    private Label orderStatusLabel;

    @FXML
    private Label childLabel;


    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    @FXML
    private void viewDetails(){
        stageController = new StageController();
        stageController.loadStage("customer/CustomerSingleHotelOrderView.fxml", 1);
    }

    public void init(){
        orderIDLabel.setText("222222");
        peopleLabel.setText("");
        childLabel.setText("");
        priceLabel.setText("");
        roomTypeLabel.setText("");
        orderStatusLabel.setText("");
    }


}
