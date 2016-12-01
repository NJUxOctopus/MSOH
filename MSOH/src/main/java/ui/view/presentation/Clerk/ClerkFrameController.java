package ui.view.presentation.Clerk;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ui.view.presentation.StageController;
import ui.view.presentation.ControlledStage;

import java.io.IOException;

/**
 * Created by ST on 2016/11/22.
 */
public class ClerkFrameController implements ControlledStage {

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
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ClerkFrame.class.getResource("ClerkHotelInfo.fxml"));
            Pane hotelInfoView = (Pane) loader.load();

            // Close the previous panel
            clerkFramePane.getChildren().clear();

            // Set hotelInfoView into the initial pane.
            clerkFramePane.getChildren().add(hotelInfoView);
            hotelInfoView.setLayoutX(0);
            hotelInfoView.setLayoutY(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 订单按钮结果，显示酒店订单列表
     */
    public void showHotelOrderList() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ClerkFrame.class.getResource("ClerkCheckOrderListView.fxml"));
            Pane orderListPane = (Pane) loader.load();

            // Close the previous panel
            clerkFramePane.getChildren().clear();

            // Set hotelInfoView into the initial pane.
            clerkFramePane.getChildren().add(orderListPane);
            orderListPane.setLayoutX(0);
            orderListPane.setLayoutY(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 酒店促销按钮结果，显示酒店促销页面
     */
    public void showHotelPromotionList() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ClerkFrame.class.getResource("ClerkHotelPromotion.fxml"));
            Pane promotionPane = (Pane) loader.load();

            // Close the previous panel
            clerkFramePane.getChildren().clear();

            // Set hotelInfoView into the initial pane.
            clerkFramePane.getChildren().add(promotionPane);
            promotionPane.setLayoutX(0);
            promotionPane.setLayoutY(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 个人信息按钮结果，显示修改个人信息界面
     */
    public void showModifyClerkInfo() throws IOException {
        stageController = new StageController();
        stageController.loadStage("Clerk/ClerkModifyPersonalInfo.fxml", 1);
    }


}
