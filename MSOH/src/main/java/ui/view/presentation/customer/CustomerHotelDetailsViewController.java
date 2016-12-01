package ui.view.presentation.customer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;

import java.io.IOException;

/**
 * Created by island on 2016/11/24.
 */
public class CustomerHotelDetailsViewController implements ControlledStage {
    StageController stageController;

    private String resources = "customer/CustomerHotelDetailsView.fxml";

    private CustomerSingleCommentViewController customerSingleCommentViewController;

    private CustomerSinglePromotionViewController customerSinglePromotionViewController;

    private CustomerSingleRoomTypeViewController customerSingleRoomTypeViewController;

    private CustomerSingleHotelOrderViewController customerSingleHotelOrderViewController;

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
    private AnchorPane commentScrollPane;

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

    public void init(){
        addRoomTypePane();
        addPromotionPane();
        addCommentPane();
        addOrderPane();
    }

    public void addCommentPane(){
        addPane("CustomerSingleCommentView.fxml", 45 , 35, commentScrollPane, 1);
    }

    public void addPromotionPane(){
        addPane("CustomerSinglePromotionView.fxml", 45 , 70, promotionScrollPane, 2);
    }

    public void addRoomTypePane(){
        addPane("CustomerSingleRoomTypeView.fxml", 45 , 70, roomInfoScrollPane, 3);
    }

    public void addOrderPane(){
        addPane("CustomerSingleHotelOrderView.fxml", 45 , 40, historyOrderScrollPane, 4);
    }

    private void addPane(String resource, int x, int y, AnchorPane sourcePane, int type){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(resource));
            Pane singlePane = (Pane) loader.load();
            sourcePane.getChildren().add(singlePane);
            singlePane.setLayoutX(x);
            singlePane.setLayoutY(y);
            if(type == 1){
                customerSingleCommentViewController = loader.getController();
                customerSingleCommentViewController.init();
            }
            if(type == 2){
                customerSinglePromotionViewController = loader.getController();
                customerSinglePromotionViewController.init();
            }
            if(type == 3){
                customerSingleRoomTypeViewController = loader.getController();
                customerSingleRoomTypeViewController.init();
            }
            if(type == 4){
                customerSingleHotelOrderViewController = loader.getController();
                customerSingleHotelOrderViewController.init();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
