package ui.view.presentation.clerk;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ChoiceBoxListCell;
import ui.controller.HotelAdminController;
import ui.view.controllerservice.HotelAdmin;
import ui.view.presentation.util.ConfirmExitController;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ErrorBoxController;
import util.ResultMessage;
import vo.HotelVO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ST on 2016/11/30.
 */
public class ClerkModifyHotelInfoController implements ControlledStage {

    private StageController stageController;

    @FXML
    private TextField hotelNameTextField;
    @FXML
    private TextField hotelAddressTextField;
    @FXML
    private ChoiceBox cityChoiceBox;
    @FXML
    private ChoiceBox businessAreaChoiceBox;
    @FXML
    private ChoiceBox starLevelChoiceBox;
    @FXML
    private CheckBox haveWIFICheckBox;
    @FXML
    private CheckBox haveParkingLotCheckBox;
    @FXML
    private CheckBox haveSwimmingPoolCheckBox;
    @FXML
    private CheckBox haveGymCheckBox;
    @FXML
    private TextArea hotelIntroductionTextArea;

    private String resource = "clerk/ClerkModifyHotelInfoController.fxml";

    private String hotelID;
    private HotelVO hotelVO;
    private HotelAdmin hotelAdmin;
    private List<String> infras = new ArrayList<String>();

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * initial方法，初始化界面
     */
    public void initial(String hotelID) throws RemoteException {
        hotelAdmin = new HotelAdminController();
        this.hotelID = hotelID;
        hotelVO = hotelAdmin.findByID(hotelID);
        hotelNameTextField.setText(hotelVO.hotelName);
        hotelAddressTextField.setText(hotelVO.hotelAddress);
        hotelIntroductionTextArea.setText(hotelVO.intro);

        ObservableList<String> star = FXCollections.observableArrayList("★", "★★", "★★★", "★★★★", "★★★★★");
        starLevelChoiceBox.setItems(star);
        starLevelChoiceBox.setValue(star.get(hotelVO.star - 1));

        ObservableList<String> city = FXCollections.observableArrayList();
        city.addAll(hotelAdmin.getAllCities());
        cityChoiceBox.setItems(city);
        cityChoiceBox.setValue(hotelVO.city);

        ObservableList<String> businessArea = FXCollections.observableArrayList();
        businessArea.addAll(hotelAdmin.getAreaByCity(cityChoiceBox.getSelectionModel().selectedItemProperty().toString()));
        businessAreaChoiceBox.setItems(businessArea);
        businessAreaChoiceBox.setValue(hotelVO.area);

        List<String> infra = new ArrayList<String>();
        for (String s : hotelVO.infra) {
            infra.add(s);
        }
        haveWIFICheckBox.setSelected(infra.contains("WIFI"));
        haveParkingLotCheckBox.setSelected(infra.contains("停车场"));
        haveSwimmingPoolCheckBox.setSelected(infra.contains("游泳池"));
        haveGymCheckBox.setSelected(infra.contains("健身房"));

    }

    @FXML
    private void showConfirmExit() {
        stageController = new StageController();
        if (!this.isModified()) {
            //信息未修改
            stageController.closeStage(resource);
        }
        stageController.loadStage("util/ConfirmExit.fxml", 0.8);
        ConfirmExitController controller = (ConfirmExitController) stageController.getController();
        controller.setToBeClosed("clerk/ClerkModifyHotelInfo.fxml");
    }

    /**
     * 确认修改按钮结果
     */
    @FXML
    private void confirmModifyHotelInfo() throws RemoteException {

        if (!this.isModified()) {
            //信息未修改
            this.returnMessage("信息未修改！");
        } else {
            hotelAdmin = new HotelAdminController();
            ResultMessage resultMessage = hotelAdmin.updateHotelInfo(new HotelVO(hotelNameTextField.getText(),
                    hotelAddressTextField.getText(), cityChoiceBox.getSelectionModel().selectedItemProperty().toString(),
                    businessAreaChoiceBox.getSelectionModel().selectedItemProperty().toString(),
                    hotelIntroductionTextArea.getText(),
                    (String[]) infras.toArray(), starLevelChoiceBox.getSelectionModel().selectedIndexProperty().intValue() + 1,
                    hotelID));
            if (resultMessage.equals(ResultMessage.Blank)) {
                this.returnMessage("信息填写不完整！");
            } else if (resultMessage.equals(ResultMessage.Hotel_modifyHotelInfoSuccess)) {
                stageController = this.returnMessage("修改成功！");
                stageController.closeStage(resource);
            }
        }
    }

    /**
     * 显示错误提示的方法
     *
     * @param error
     */
    public StageController returnMessage(String error) {
        stageController = new StageController();
        stageController.loadStage("util/ErrorBoxView.fxml", 0.8);
        ErrorBoxController errorBoxController = (ErrorBoxController) stageController.getController();
        errorBoxController.setLabel(error);
        return stageController;
    }

    /**
     * 返回信息是否被修改
     *
     * @return
     */
    public boolean isModified() {
        if (haveWIFICheckBox.isSelected()) {
            infras.add("WIFI");
        }
        if (haveSwimmingPoolCheckBox.isSelected()) {
            infras.add("游泳池");
        }
        if (haveParkingLotCheckBox.isSelected()) {
            infras.add("停车场");
        }
        if (haveGymCheckBox.isSelected()) {
            infras.add("健身房");
        }

        if (hotelNameTextField.getText().equals(hotelVO.hotelName)
                && starLevelChoiceBox.getSelectionModel().selectedIndexProperty().intValue() + 1 == hotelVO.star
                && cityChoiceBox.getSelectionModel().selectedItemProperty().toString().equals(hotelVO.city)
                && businessAreaChoiceBox.getSelectionModel().selectedItemProperty().toString().equals(hotelVO.area)
                && hotelAddressTextField.getText().equals(hotelVO.hotelAddress)
                && infras.toArray().equals(hotelVO.infra)
                && hotelIntroductionTextArea.getText().equals(hotelVO.intro)) {
            //信息未修改
            return false;
        } else {
            return true;
        }
    }

}
