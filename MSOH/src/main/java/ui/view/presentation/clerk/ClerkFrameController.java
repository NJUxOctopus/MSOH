package ui.view.presentation.clerk;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import ui.controller.HotelAdminController;
import ui.controller.UserAdminController;
import ui.view.controllerservice.HotelAdmin;
import ui.view.controllerservice.UserAdmin;
import ui.view.presentation.PaneAdder;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ControlledStage;

import java.io.IOException;
import java.rmi.RemoteException;

/**
 * Created by ST on 2016/11/22.
 */
public class ClerkFrameController implements ControlledStage {

    private StageController stageController;

    private String resource = "clerk/ClerkFrame.fxml";

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

    private String clerkID;

    private HotelAdmin hotelAdmin;

    private UserAdmin userAdmin;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * initial方法，初始化界面
     */
    public void initial(String ID) throws RemoteException {

        clerkID = ID;

        hotelAdmin = new HotelAdminController();
        hotelName.setText(hotelAdmin.findByClerkID(clerkID).hotelName);

        userAdmin = new UserAdminController();
        clerkName.setText(userAdmin.findClerkByID(clerkID).name);

    }

    /**
     * 酒店按钮结果，显示酒店信息
     */
    @FXML
    private void showHotelInfo() throws RemoteException {
        // Close the previous panel
        clerkFramePane.getChildren().clear();

        PaneAdder paneAdder = new PaneAdder();
        paneAdder.addPane(clerkFramePane, "clerk/ClerkHotelInfo.fxml", 0, 0);

        //初始化界面
        ClerkHotelInfoController clerkHotelInfoController = (ClerkHotelInfoController) paneAdder.getController();
        clerkHotelInfoController.initial(clerkID);
    }

    /**
     * 订单按钮结果，显示酒店订单列表
     */
    @FXML
    private void showHotelOrderList() throws RemoteException {
        // Close the previous panel
        clerkFramePane.getChildren().clear();

        PaneAdder paneAdder = new PaneAdder();
        paneAdder.addPane(clerkFramePane, "clerk/ClerkCheckOrderList.fxml", 0, 0);

        //初始化界面
        ClerkCheckOrderListController clerkCheckOrderListController = (ClerkCheckOrderListController) paneAdder.getController();
        clerkCheckOrderListController.initial(clerkID);
    }

    /**
     * 酒店促销按钮结果，显示酒店促销页面
     */
    @FXML
    private void showHotelPromotionList() throws IOException, ClassNotFoundException {
        // Close the previous panel
        clerkFramePane.getChildren().clear();

        PaneAdder paneAdder = new PaneAdder();
        paneAdder.addPane(clerkFramePane, "clerk/ClerkHotelPromotion.fxml", 0, 0);

        //初始化界面
        ClerkHotelPromotionController clerkHotelPromotionController = (ClerkHotelPromotionController)paneAdder.getController();
        clerkHotelPromotionController.initial(clerkID);
    }

    /**
     * 个人信息按钮结果，显示修改个人信息界面
     */
    @FXML
    private void showModifyClerkInfo() throws IOException {
        stageController = new StageController();
        stageController.loadStage("clerk/ClerkModifyPersonalInfo.fxml", 1);

        //初始化界面
        ClerkModifyPersonalInfoController clerkModifyPersonalInfoController = (ClerkModifyPersonalInfoController) stageController.getController();
        clerkModifyPersonalInfoController.initial(clerkID);
    }

    /**
     * 切换账号
     */
    @FXML
    private void switchAccount() {
        stageController = new StageController();
        stageController.closeStage(resource);
        stageController.loadStage("login/LoginView.fxml", 1);
    }

    /**
     * 退出系统
     */
    @FXML
    private void exit() {
        System.exit(0);
    }

//    /**
//     * 系统设置按钮结果，显示选择具体设置弹框
//     */
//    @FXML
//    private void showSpecificSettings(){
//
//    }


}
