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

    @FXML
    private void showHotelManageView(){
        shadeImage.setY(-604);
        contentPane.getChildren().clear();
        PaneAdder paneAdder = new PaneAdder();
        paneAdder.addPane(contentPane,"manager/ManagerHotelManageView.fxml", 0, 0);
        managerHotelManageViewController = (ManagerHotelManageViewController) paneAdder.getController();
        managerHotelManageViewController.init();
    }

    @FXML
    private void showStaffManageView(){
        shadeImage.setY(-356);
        contentPane.getChildren().clear();
        PaneAdder paneAdder = new PaneAdder();
        paneAdder.addPane(contentPane,"manager/ManagerHotelManageView.fxml", 0, 0);
    }

}