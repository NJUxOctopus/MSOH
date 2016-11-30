package ui.view.presentation.customer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import ui.view.presentation.ControlledStage;
import ui.view.presentation.StageController;

/**
 * Created by island on 2016/11/24.
 */
public class CustomerHotelDetailsViewController implements ControlledStage {
    StageController stageController;

    private String resources = "customer/CustomerHotelDetailsView.fxml";

    @FXML
    private ImageView background;

    @FXML
    private Button backButton;

    @FXML
    private ChoiceBox checkInYearChoiceBox;

    @FXML
    private ChoiceBox checkInMonthChoiceBox;

    @FXML
    private ChoiceBox checkInDayChoiceBox;

    @FXML
    private ChoiceBox checkOutYearChoiceBox;

    @FXML
    private ChoiceBox checkOutMonthChoiceBox;

    @FXML
    private ChoiceBox checkOutDayChoiceBox;

    @FXML
    private Button confirmButton;

    @FXML
    private AnchorPane promotionScrollPane;

    @FXML
    private AnchorPane evaluationScrollPane;

    @FXML
    private AnchorPane historyOrderScrollPane;

    @FXML
    private AnchorPane roomInfoScrollPane;

    @FXML
    private Label cityLabel;

    @FXML
    private Label areaLabel;

    @FXML
    private Label addressLabel;

    @FXML
    private Label starLabel;

    @FXML
    private Label briefInfoLabel;

    @FXML
    private Label scoreLabel;


    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    @FXML
    private void closeStage() {
        stageController.closeStage(resources);
    }
}
