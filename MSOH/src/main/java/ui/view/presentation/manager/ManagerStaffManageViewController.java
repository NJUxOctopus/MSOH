package ui.view.presentation.manager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import ui.view.presentation.PaneAdder;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ControlledStage;

import java.io.IOException;

/**
 * Created by island on 2016/12/1.
 */
public class ManagerStaffManageViewController implements ControlledStage {
    StageController stageController = new StageController();

    ManagerSinglePersonViewController managerSinglePersonViewController;

    @FXML
    private Button searchButton;

    @FXML
    private ChoiceBox staffChoiceBox;

    @FXML
    private TextField textField;

    @FXML
    private Button addMarketerButton;

    @FXML
    private Button addCleckButton;

    @FXML
    private AnchorPane staffListScrollPane;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }


    @FXML
    private void showAddMarketerView(){
        stageController = new StageController();
        stageController.loadStage("manager/ManagerMarketerInfoView.fxml", 1);
        ManagerMarketerInfoViewController managerMarketerInfoViewController = (ManagerMarketerInfoViewController) stageController.getController();
        managerMarketerInfoViewController.setAddVer();
    }

    @FXML
    private void showAddCleckButton(){
        stageController = new StageController();
        stageController.loadStage("manager/ManagerClerkInfoView.fxml", 1);
        ManagerClerkInfoViewController managerClerkInfoViewController = (ManagerClerkInfoViewController) stageController.getController();
        managerClerkInfoViewController.setAddVer();
    }

    @FXML
    private void search(){
        addSinglePersonPane();
    }

    private void addSinglePersonPane(){
        PaneAdder paneAdder = new PaneAdder();
        paneAdder.addPane(staffListScrollPane,"manager/ManagerSinglePersonView.fxml", 10, 10);

    }

}
