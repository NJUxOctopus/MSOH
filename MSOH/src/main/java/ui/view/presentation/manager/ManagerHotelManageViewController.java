package ui.view.presentation.manager;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import ui.view.presentation.PaneAdder;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ControlledStage;

/**
 * Created by island on 2016/12/1.
 */
public class ManagerHotelManageViewController implements ControlledStage {
    StageController stageController = new StageController();

    @FXML
    private Button searchButton;

    @FXML
    private ChoiceBox typeChoiceBox;

    @FXML
    private TextField textField;

    @FXML
    private Button addHotelButton;

    @FXML
    private AnchorPane hotelListScrollPane;


    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    @FXML
    private void search(){
        addSingleHotelPane();
    }

    /**
     * 点击添加酒店按钮，跳转至添加酒店界面
     */
    @FXML
    private void addHotel(){
        stageController = new StageController();
        stageController.loadStage("manager/ManagerHotelInfoView.fxml", 1);
        ManagerHotelInfoViewController managerHotelInfoViewController = (ManagerHotelInfoViewController) stageController.getController();
        managerHotelInfoViewController.setAddVer();
    }

    public void addSingleHotelPane(){
        PaneAdder paneAdder = new PaneAdder();
        paneAdder.addPane(hotelListScrollPane,"manager/ManagerSingleHotelView.fxml", 10, 10);
    }
}
