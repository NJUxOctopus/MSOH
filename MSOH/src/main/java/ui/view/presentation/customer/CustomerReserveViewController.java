package ui.view.presentation.customer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import ui.view.presentation.util.ConfirmExitController;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;

/**
 * Created by island on 2016/12/1.
 */
public class CustomerReserveViewController implements ControlledStage{
    StageController stageController;

    private String resource = "customer/CustomerReserveView.fxml";

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
    private ChoiceBox roomTypeChoiceBox;

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
        stageController = new StageController();
        stageController.loadStage("util/ConfirmExit.fxml", 0.75);
        ConfirmExitController controller = (ConfirmExitController) stageController.getController();
        controller.setToBeClosed(resource);
    }

    @FXML
    private void selectCheckInTime(){
        stageController = new StageController();
        stageController.loadStage("util/SelectTimeView.fxml",0.8);
    }

    @FXML
    private void selectCheckOutTime(){
        stageController = new StageController();
        stageController.loadStage("util/SelectTimeView.fxml",0.8);
    }


    private void init(){

    }
}
