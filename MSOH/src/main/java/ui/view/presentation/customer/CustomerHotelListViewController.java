package ui.view.presentation.customer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import ui.view.presentation.ControlledStage;
import ui.view.presentation.StageController;

import java.io.IOException;

/**
 * Created by island on 2016/11/24.
 */
public class CustomerHotelListViewController implements ControlledStage {
    StageController stageController = new StageController();

    CustomerSingleHotelViewController customerSingleHotelViewController;

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
    private AnchorPane hotelListScrollPane;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    @FXML
    private void research() {

    }

    @FXML
    private void closeStage() {
        stageController.closeStage(resources);
    }

    public void addHotelPane(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("CustomerSingleHotelView.fxml"));
            Pane singleHotel = (Pane) loader.load();
            hotelListScrollPane.getChildren().add(singleHotel);
            singleHotel.setLayoutX(5);
            singleHotel.setLayoutY(10);
            customerSingleHotelViewController = loader.getController();
            customerSingleHotelViewController.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
