package ui.view.presentation.customer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;

/**
 * Created by island on 2016/11/30.
 */
public class CustomerSingleHotelViewController implements ControlledStage {
    StageController stageController = new StageController();


    @FXML
    private Button hotelButton;

    @FXML
    private ImageView hotelPicture;

    @FXML
    private Label scoreLabel;

    @FXML
    private Label discountLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label numOfCommentLabel;

    @FXML
    private Button normalOrderButton;

    @FXML
    private Button abnormalOrderButton;

    @FXML
    private Button canceledOrderButton;

    @FXML
    private Button reserveButton;

    @FXML
    private Button detailButton;

    @FXML
    private void viewHotelDetails(){
        stageController = new StageController();
        stageController.loadStage("customer/CustomerHotelDetailsView.fxml", 1);
        CustomerHotelDetailsViewController customerHotelDetailsViewController = (CustomerHotelDetailsViewController) stageController.getController();
        customerHotelDetailsViewController.init();
    }

    @FXML
    private void viewNormalOrder(){

    }

    @FXML
    private void viewAbnormalOrder(){

    }

    @FXML
    private void viewCanceledOrderButton(){

    }

    @FXML
    private void reserve(){
        stageController = new StageController();
        stageController.loadStage("customer/CustomerReserveView.fxml", 1);
    }

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    public void init(){
        hotelButton.setText("");
        scoreLabel.setText("");
        discountLabel.setText("");
        priceLabel.setText("");
        numOfCommentLabel.setText("");
        normalOrderButton.setText("");
        abnormalOrderButton.setText("");
        canceledOrderButton.setText("");

    }
}
