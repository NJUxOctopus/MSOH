package ui.view.presentation.customer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import ui.view.presentation.StageController;

/**
 * Created by island on 2016/11/24.
 */
public class CustomerHotelListViewController implements ControlledStage{
    StageController stageController = new StageController();

    private String resources = "customer/CustomerHotelListView.fxml";

    @FXML
    private ImageView background;

    @FXML
    private Button backButton;

    @FXML
    private ChoiceBox addressChoiceBox;

    @FXML
    private ChoiceBox areaChoiceBox;

    @FXML
    private ChoiceBox typeOfRoomChoiceBox;

    @FXML
    private ChoiceBox numOfRoomChoiceBox;

    @FXML
    private ChoiceBox preScoreChoiceBox;

    @FXML
    private ChoiceBox postScoreChoiceBox;

    @FXML
    private TextField nameTextField;

    @FXML
    private Button checkInTimeButton;

    @FXML
    private Button checkOutTimeButton;

    @FXML
    private Button confirmModifyButton;

    @FXML
    private ChoiceBox sortChoiceBox;

    @FXML
    private ChoiceBox selcetChoiceBox;

    @FXML
    private CheckBox reservedCheckBox;

    @FXML
    private Button nextPageButton;

    @FXML
    private Button lastPageButton;

    @FXML
    private ChoiceBox selectPageBox;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    @FXML
    private void research(){

    }

    @FXML
    private void closeStage() {
        stageController.closeStage(resources);
    }


}
