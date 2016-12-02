package ui.view.presentation.manager;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ConfirmExitController;
import ui.view.presentation.util.ControlledStage;

/**
 * Created by island on 2016/12/2.
 */
public class ManagerHotelInfoViewController implements ControlledStage {
    StageController stageController = new StageController();

    private String resource = "manager/ManagerHotelInfoView.fxml";

    @FXML
    private Button addPictureButton;

    @FXML
    private ChoiceBox cityChoiceBox;

    @FXML
    private ChoiceBox areaChoiceBox;

    @FXML
    private ChoiceBox starChoiceBox;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField addressTextField;

    @FXML
    private TextField licenceTextField;

    @FXML
    private TextField facilityTextField;

    @FXML
    private Button addButton;

    @FXML
    private CheckBox depotCheckBox;

    @FXML
    private CheckBox swimmingPoolCheckBox;

    @FXML
    private CheckBox gymCheckBox;

    @FXML
    private CheckBox otherCheckBox;

    @FXML
    private ImageView pictureImage;

    @FXML
    private Pane modofyHotelPane;

    @FXML
    private Pane addHotelPane;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * 关闭当前界面
     */
    @FXML
    private void closeStage() {
        stageController = new StageController();
        stageController.loadStage("util/ConfirmExit.fxml", 0.75);
        ConfirmExitController controller = (ConfirmExitController) stageController.getController();
        controller.setToBeClosed(resource);
    }

    @FXML
    private void addPicture(){

    }

    @FXML
    private void confirmInfo(){
        if(addButton.getText().equals("添加"))
            System.out.print("1");
        if(addButton.getText().equals("修改"))
            System.out.print("2");
    }

    public void setModifyVer(){
        addButton.setText("修改");
        addHotelPane.setOpacity(0);
        modofyHotelPane.setOpacity(1);
    }

    public void setAddVer(){
        addButton.setText("添加");
        addHotelPane.setOpacity(1);
        modofyHotelPane.setOpacity(0);
    }

}
