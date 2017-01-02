package ui.view.presentation.clerk;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.ChoiceBoxListCell;
import ui.controller.HotelAdminController;
import ui.controller.HotelInfoController;
import ui.view.controllerservice.HotelAdmin;
import ui.view.controllerservice.HotelInfo;
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
    @FXML
    private ChoiceBox cityChoiceBox;
    @FXML
    private ChoiceBox areaChoiceBox;

    private String resource = "clerk/ClerkModifyHotelInfoController.fxml";

    private String hotelID;
    private String clerkID;
    private HotelVO hotelVO;
    private HotelAdmin hotelAdmin;
    private HotelInfo hotelInfo;
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
        hotelInfo = new HotelInfoController();
        this.hotelID = hotelID;
        hotelVO = hotelAdmin.findByID(hotelID);
        this.clerkID = hotelVO.clerkID;
        hotelNameTextField.setText(hotelVO.hotelName);
        hotelAddressTextField.setText(hotelVO.hotelAddress);
        hotelIntroductionTextArea.setText(hotelVO.intro);

        ObservableList<String> star = FXCollections.observableArrayList("★", "★★", "★★★", "★★★★", "★★★★★");
        starLevelChoiceBox.setItems(star);
        starLevelChoiceBox.setValue(star.get(hotelVO.star - 1));

        //初始化酒店城市和商圈
        ObservableList<String> cities = FXCollections.observableArrayList();
        List<String> toAdd = hotelInfo.getAllCities();
        for (int i = 0; i < toAdd.size(); i++) {
            cities.add(toAdd.get(i));
        }
        cityChoiceBox.setItems(cities);
        cityChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                ObservableList<String> areas = FXCollections.observableArrayList();
                try {
                    List<String> toAdd = hotelInfo.getAreaByCity((String) cityChoiceBox.getSelectionModel().getSelectedItem());
                    for (int i = 0; i < toAdd.size(); i++) {
                        areas.add(toAdd.get(i));
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        cityChoiceBox.getSelectionModel().select(hotelVO.city);
        areaChoiceBox.getSelectionModel().select(hotelVO.area);


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
                    (String) cityChoiceBox.getSelectionModel().getSelectedItem(), (String) areaChoiceBox.getSelectionModel().getSelectedItem(),
                    hotelAddressTextField.getText(), hotelIntroductionTextArea.getText(),
                    (String[]) infras.toArray(), starLevelChoiceBox.getSelectionModel().selectedIndexProperty().intValue() + 1,
                    hotelID));
            if (resultMessage.equals(ResultMessage.Blank)) {
                this.returnMessage("信息填写不完整！");
            } else if (resultMessage.equals(ResultMessage.Hotel_modifyHotelInfoSuccess)) {
                stageController = this.returnMessage("修改成功！");
                stageController.closeStage(resource);

                ClerkHotelInfoController clerkHotelInfoController = (ClerkHotelInfoController) stageController.getController("clerk/ClerkHotelInfo.fxml");
                clerkHotelInfoController.initial(clerkID);
            }
        }
    }

    /**
     * 显示提示弹窗
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
                && ((String) cityChoiceBox.getSelectionModel().getSelectedItem()).equals(hotelVO.city)
                && ((String) areaChoiceBox.getSelectionModel().getSelectedItem()).equals(hotelVO.area)
                && hotelAddressTextField.getText().equals(hotelVO.hotelAddress)
                && infras.toArray().equals(hotelVO.infra)
                && hotelIntroductionTextArea.getText().equals(hotelVO.intro)) {
            //信息未修改
            return false;
        } else {
            return true;
        }
    }

    /**
     * 退出按钮结果，退出程序
     */
    @FXML
    private void exit() {
        System.exit(0);
    }

}
