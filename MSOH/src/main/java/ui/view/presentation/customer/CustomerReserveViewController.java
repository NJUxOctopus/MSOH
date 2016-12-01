package ui.view.presentation.customer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;

/**
 * Created by island on 2016/12/1.
 */
public class CustomerReserveViewController implements ControlledStage{
    StageController stageController;

    private String resources = "customer/CustomerReserveView.fxml";

    @FXML
    private TextField customerNameTextField;

    @FXML
    private TextField phoneTextField;

    @FXML
    private TextField idTextField;

    @FXML
    private TextField checkInTimeTextField;

    @FXML
    private TextField checkOutTimeTextField1;

    @FXML
    private TextField hotelNameTextField;

    @FXML
    private TextField hotelIDTextField;

    @FXML
    private TextField prePriceTextField;

    @FXML
    private TextField afterPriceTextField;

    @FXML
    private ChoiceBox staffChoiceBox;

    @FXML
    private ChoiceBox hasChildChoiceBox;

    @FXML
    private ChoiceBox promotionChoiceBox;

    @FXML
    private Button selectCheckInButton;

    @FXML
    private Button selectCheckOutButton;

    @FXML
    private Button reserveButton;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    @FXML
    private void reserve(){

    }

    @FXML
    private void closeStage() {
        stageController.closeStage(resources);
    }

    @FXML
    private void selectCheckInTime(){

    }

    @FXML
    private void selectCheckOutTime(){

    }


    private void init(){

    }
}
