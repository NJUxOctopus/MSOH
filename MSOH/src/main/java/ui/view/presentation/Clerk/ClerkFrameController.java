package ui.view.presentation.Clerk;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import ui.view.presentation.StageController;
import ui.view.presentation.ControlledStage;

import java.io.IOException;

/**
 * Created by ST on 2016/11/22.
 */
public class ClerkFrameController implements ControlledStage{

    StageController stageController;

    @FXML
    private Label hotelName;
    @FXML
    private Button hotelInfo;
    @FXML
    private Button hotelOrder;
    @FXML
    private Button hotelPromotion;
    @FXML
    private Label clerkName;
    @FXML
    private Button clerkInfo;
    @FXML
    private Button aboutUs;
    @FXML
    private Button settings;
    @FXML
    private Button feedBack;
    @FXML
    private Pane clerkFramePane;

    private ClerkFrame clerkFrame;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public ClerkFrameController() throws IOException {
    }

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * 酒店按钮结果，显示酒店信息
     */
    public void showHotelInfo() {
        try {
            // Load hotelInfoView.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ClerkFrame.class.getResource("ClerkHotelInfo.fxml"));
            Pane hotelInfoView = (Pane) loader.load();

            // Set hotelInfoView into the initial pane.
            clerkFramePane.getChildren().add(hotelInfoView);
            hotelInfoView.setLayoutX(341);
            hotelInfoView.setLayoutY(140);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 订单按钮结果，显示酒店订单列表
     */
    public void showHotelOrderList(){
        try {
            // Load hotelInfoView.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ClerkFrame.class.getResource("ClerkCheckOrderListView.fxml"));
            Pane hotelInfoView = (Pane) loader.load();

            // Set hotelInfoView into the initial pane.
            clerkFramePane.getChildren().add(hotelInfoView);
            hotelInfoView.setLayoutX(341);
            hotelInfoView.setLayoutY(140);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 酒店促销按钮结果，显示酒店促销页面
     */
    public void showHotelPromotionList(){
        try {
            // Load hotelInfoView.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ClerkFrame.class.getResource("ClerkHotelPromotion.fxml"));
            Pane hotelInfoView = (Pane) loader.load();

            //Delete previous panel.

            // Set hotelInfoView into the initial pane.
            clerkFramePane.getChildren().add(hotelInfoView);
            hotelInfoView.setLayoutX(341);
            hotelInfoView.setLayoutY(140);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void modifyClerkInfo(){
        stageController = new StageController();
    }


}
