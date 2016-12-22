package ui.view.presentation.manager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import ui.controller.UserAdminController;
import ui.view.controllerservice.UserAdmin;
import ui.view.presentation.PaneAdder;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ImageController;
import vo.ManagerVO;

import java.io.IOException;
import java.rmi.RemoteException;

/**
 * Created by island on 2016/11/30.
 */
public class ManagerMainViewController implements ControlledStage {
    StageController stageController = new StageController();

    private String resource = "manager/ManagerMainView.fxml";

    private String managerID;

    @FXML
    private Button hotelManageButton;

    @FXML
    private Button staffManageButton;

    @FXML
    private Button infoButton;

    @FXML
    private Button settingButton;

    @FXML
    private Button feedbackButton;

    @FXML
    private Button aboutUsButton;

    @FXML
    private ImageView iconImage;

    @FXML
    private Label managerNameLabel;

    @FXML
    private ImageView shadeImage;

    @FXML
    private Pane contentPane;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * 点击酒店管理按钮，框架中出现酒店管理面板
     */
    @FXML
    private void showHotelManageView(){
        shadeImage.setY(-604);
        contentPane.getChildren().clear();
        PaneAdder paneAdder = new PaneAdder();
        paneAdder.addPane(contentPane,"manager/ManagerHotelManageView.fxml", 0, 0);
        ManagerHotelManageViewController managerHotelManageViewController = (ManagerHotelManageViewController) paneAdder.getController();
        managerHotelManageViewController.init();
    }

    /**
     * 点击人员管理页面，框架中出现人员管理面板
     */
    @FXML
    private void showStaffManageView(){
        shadeImage.setY(-356);
        contentPane.getChildren().clear();
        PaneAdder paneAdder = new PaneAdder();
        paneAdder.addPane(contentPane,"manager/ManagerStaffManageView.fxml", 0, 0);
        ManagerStaffManageViewController managerStaffManageViewController = (ManagerStaffManageViewController) paneAdder.getController();
        managerStaffManageViewController.init();
    }

    /**
     * 点击个人信息按钮，跳转至个人信息修改界面
     */
    @FXML
    private void showModifyInfoView(){
        stageController = new StageController();
        stageController.loadStage("manager/ManagerModifyInfoView.fxml", 1);
        ManagerModifyInfoViewController managerModifyInfoViewController = (ManagerModifyInfoViewController) stageController.getController();
        managerModifyInfoViewController.init(managerID);
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

    @FXML
    private void showAboutUsView() {

    }

    /**
     * 初始化网管id
     * @param managerID
     */
    public void init(String managerID){
        this.managerID = managerID;
        UserAdmin userAdmin = new UserAdminController();
        try {
            ManagerVO managerVO = userAdmin.findManagerByID(managerID);
            managerNameLabel.setText(managerVO.name);
            if(!managerVO.picUrl.equals("")) {
                ImageController imageController = new ImageController();
                WritableImage wr = imageController.loadImage(managerVO.picUrl, 145, 145);
                iconImage.setImage(wr);
            }
        }catch (RemoteException e){
            e.printStackTrace();
        }

    }

}