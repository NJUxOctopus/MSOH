package ui.view.presentation.manager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import ui.view.presentation.PaneAdder;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;

import java.io.IOException;

/**
 * Created by island on 2016/11/30.
 */
public class ManagerMainViewController implements ControlledStage {
    StageController stageController = new StageController();

    ManagerHotelManageViewController managerHotelManageViewController;

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
    }

    /**
     * 点击个人信息按钮，跳转至个人信息修改界面
     */
    @FXML
    private void showModifyInfoView(){
        stageController = new StageController();
        stageController.loadStage("manager/ManagerModifyInfoView.fxml", 1);

    }

    @FXML
    private void showSettingView(){

    }

    @FXML
    private void showFeedbackView(){

    }

    @FXML
    private void showAboutUsView(){

    }


}