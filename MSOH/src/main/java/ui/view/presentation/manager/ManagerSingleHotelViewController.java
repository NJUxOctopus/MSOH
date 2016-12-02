package ui.view.presentation.manager;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ControlledStage;

/**
 * Created by island on 2016/12/2.
 */
public class ManagerSingleHotelViewController implements ControlledStage {
    StageController stageController = new StageController();

    @FXML
    private Label hotelIDLabel;

    private String type;

    @FXML
    private Label hotelNameLabel;

    @FXML
    private Label addressLabel;

    @FXML
    private Label areaLabel;

    @FXML
    private Label cityLabel;

    @FXML
    private Button hotelInfoButton;

    @FXML
    private Button cleckButton;

    @FXML
    private ImageView hotelImage;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    @FXML
    private void showHotelInfoView(){
        stageController = new StageController();
        stageController.loadStage("manager/ManagerHotelInfoView.fxml", 1);
        ManagerHotelInfoViewController managerHotelInfoViewController = (ManagerHotelInfoViewController) stageController.getController();
        managerHotelInfoViewController.setModifyVer();

    }

    @FXML
    private void showClerkInfoView(){
        stageController = new StageController();
        stageController.loadStage("manager/ManagerClerkInfoView.fxml", 1);
        ManagerClerkInfoViewController managerClerkInfoViewController = (ManagerClerkInfoViewController) stageController.getController();
        managerClerkInfoViewController.setModifyVer();
    }

}
