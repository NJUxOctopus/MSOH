package ui.view.presentation.customer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import ui.view.presentation.ControlledStage;
import ui.view.presentation.StageController;

import java.io.IOException;

/**
 * Created by island on 2016/11/23.
 */
public class CustomerCreditRecordViewController implements ControlledStage {
    StageController stageController;

    private String resources = "customer/CustomerCreditRecordView.fxml";

    CustomerSingleCreditRecordViewController customerSingleCreditRecordViewController;

    @FXML
    private ImageView background;

    @FXML
    private Label creditLabel;

    @FXML
    private AnchorPane creditListScrollPane;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    @FXML
    private void closeStage() {
        stageController.closeStage(resources);
    }


    public void addCreditPane(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("CustomerSingleCreditRecordView.fxml"));
            Pane singleHotel = (Pane) loader.load();
            creditListScrollPane.getChildren().add(singleHotel);
            singleHotel.setLayoutX(3);
            singleHotel.setLayoutY(10);
            customerSingleCreditRecordViewController = loader.getController();
            customerSingleCreditRecordViewController.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
