package ui.view.presentation.manager;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import ui.controller.HotelAdminController;
import ui.controller.HotelInfoController;
import ui.view.controllerservice.HotelAdmin;
import ui.view.controllerservice.HotelInfo;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ConfirmExitController;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.util.ErrorBoxController;
import util.ResultMessage;
import vo.HotelVO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by island on 2016/12/2.
 */
public class ManagerHotelInfoViewController implements ControlledStage {
    StageController stageController = new StageController();

    private String resource = "manager/ManagerHotelInfoView.fxml";

    private String hotelID = "";

    private HotelVO hotelVO;

    private String hotelName = "";

    private String address = "";

    private String city = "";

    private String area = "";

    private int star = 0;

    private String facility = "";

    private String newFacility = "";

    private String[] newFac;

    private String license = "";

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
    private TextField licenseTextField;

    @FXML
    private Button addButton;

    @FXML
    private CheckBox depotCheckBox;

    @FXML
    private CheckBox swimmingPoolCheckBox;

    @FXML
    private CheckBox gymCheckBox;

    @FXML
    private CheckBox wifiCheckBox;

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
        if(isModified()) {
            stageController.loadStage("util/ConfirmExit.fxml", 0.75);
            ConfirmExitController controller = (ConfirmExitController) stageController.getController();
            controller.setToBeClosed(resource);
        }else{
            stageController.closeStage(resource);
        }
    }

    @FXML
    private void addPicture(){

    }

    /**
     * 按钮响应
     */
    @FXML
    private void confirmInfo(){
        if(addButton.getText().equals("添加")) {
            isModified();
            addHotel();
        }
        if(addButton.getText().equals("修改")) {
            if(isModified()){
                modifyHotel();
            }else{
                stageController = new StageController();
                stageController.loadStage("util/ErrorBoxView.fxml", 0.75);
                ErrorBoxController errorBoxController = (ErrorBoxController)stageController.getController();
                errorBoxController.setLabel("未修改信息！");
            }
        }
    }

    /**
     * 添加酒店方法
     */
    private void addHotel(){
        HotelAdmin hotelAdmin = new HotelAdminController();
        try{
            ResultMessage resultMessage = hotelAdmin.addHotel(new HotelVO(
                    hotelName, address, area, "", newFac, star, license));
            stageController = new StageController();
            stageController.loadStage("util/ErrorBoxView.fxml", 0.75);
            ErrorBoxController errorBoxController = (ErrorBoxController)stageController.getController();
            if(resultMessage == ResultMessage.Blank){
                errorBoxController.setLabel("请填写完整的信息！");
            }
            if(resultMessage == ResultMessage.Hotel_addHotelSuccess){
                errorBoxController.setLabel("成功添加酒店信息！");
                stageController.closeStage(resource);
            }
        }catch (RemoteException e){
            e.printStackTrace();
        }
    }
    /**
     * 修改酒店信息方法
     */
    private void modifyHotel(){
        HotelAdmin hotelAdmin = new HotelAdminController();
        try {
            ResultMessage resultMessage = hotelAdmin.updateHotelInfo(new HotelVO(
                    hotelName, address, area, "", newFac, star, license));
            stageController = new StageController();
            stageController.loadStage("util/ErrorBoxView.fxml", 0.75);
            ErrorBoxController errorBoxController = (ErrorBoxController)stageController.getController();
            if(resultMessage == ResultMessage.Blank){
                errorBoxController.setLabel("请填写完整的信息！");
            }
            if(resultMessage == ResultMessage.Hotel_modifyHotelInfoSuccess){
                errorBoxController.setLabel("成功修改酒店信息！");
                stageController.closeStage(resource);
            }
        }catch (RemoteException e){
            e.printStackTrace();
        }
    }

    /**
     * 设置界面为修改酒店信息界面
     * @param hotelID
     */
    public void setModifyVer(String hotelID){
        this.hotelID = hotelID;
        //修改
        addButton.setText("修改");
        addHotelPane.setOpacity(0);
        modofyHotelPane.setOpacity(1);
        init();
        setHotelInfo();
    }

    /**
     * 设置界面为添加酒店界面
     */
    public void setAddVer(){
        addButton.setText("添加");
        addHotelPane.setOpacity(1);
        modofyHotelPane.setOpacity(0);
        nameTextField.setText("");
        addressTextField.setText("");
        licenseTextField.setText("");
        init();
    }

    /**
     * 初始化酒店信息页面
     */
    private void init(){
        starChoiceBox.setItems(FXCollections.observableArrayList(
                "任意星级", "★", "★★", "★★★", "★★★★", "★★★★★"));
        try {
            HotelInfo hotelInfo = new HotelInfoController();
            List<String> city = hotelInfo.getAllCities();
            ObservableList<String> citys = FXCollections.observableArrayList();
            for (int i = 0; i < city.size(); i++)
                citys.add(city.get(i));
            cityChoiceBox.setItems(citys);

            cityChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    String selectedCity = (String) cityChoiceBox.getSelectionModel().getSelectedItem();
                    HotelInfo hotelInfo = new HotelInfoController();
                    try {
                        List<String> area = hotelInfo.getAreaByCity(selectedCity);
                        ObservableList<String> areas = FXCollections.observableArrayList();

                        for (int i = 0; i < area.size(); i++)
                            areas.add(area.get(i));
                        areaChoiceBox.setItems(areas);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            });
        }catch (RemoteException e){
            e.printStackTrace();
        }
    }

    /**
     * 设置酒店
     */
    private void setHotelInfo(){
        HotelAdmin hotelAdmin = new HotelAdminController();
        try{
            hotelVO = hotelAdmin.findByID(hotelID);
            hotelName = hotelVO.hotelName;
            address = hotelVO.hotelAddress;
            city = hotelVO.city;
            area = hotelVO.area;
            String starStr = "";
            for(int i = 0; i < hotelVO.star; i++)
                starStr += "★";
            star = starStr.length();
            nameTextField.setText(hotelName);
            addressTextField.setText(address);
            cityChoiceBox.setValue(city);
            areaChoiceBox.setValue(area);
            starChoiceBox.setValue(star);
            String[] infra = hotelVO.infra;
            for(int i = 0; i < infra.length; i++){
                facility = facility + infra[i] + ";";
                if(infra[i].equals("停车场")){
                    depotCheckBox.setSelected(true);
                }
                if(infra[i].equals("游泳池")){
                    swimmingPoolCheckBox.setSelected(true);
                }
                if(infra[i].equals("健身房")){
                    gymCheckBox.setSelected(true);
                }
                if(infra[i].equals("WIFI")){
                    wifiCheckBox.setSelected(true);
                }
            }
            license = hotelVO.license;
            licenseTextField.setText(license);
        }catch (RemoteException e){
            e.printStackTrace();
        }
    }

    /**
     * 判断信息是否被修改
     * @return
     */
    private boolean isModified(){
        hotelName = nameTextField.getText();
        address = addressTextField.getText();
        area = (String)areaChoiceBox.getSelectionModel().getSelectedItem();
        city = (String)cityChoiceBox.getSelectionModel().getSelectedItem();
        star = starChoiceBox.getSelectionModel().getSelectedIndex() + 1;
        List<String> newInfra = new ArrayList<String>();
        if(depotCheckBox.isSelected()){
            newInfra.add("停车场");
            newFacility = newFacility + "停车场;";
        }
        if(swimmingPoolCheckBox.isSelected()){
            newInfra.add("游泳池");
            newFacility = newFacility + "游泳池;";
        }
        if(gymCheckBox.isSelected()){
            newInfra.add("健身房");
            newFacility = newFacility + "健身房;";
        }
        if(wifiCheckBox.isSelected()){
            newInfra.add("WIFI");
            newFacility = newFacility + "WIFI;";
        }
        newFac = newInfra.toArray(new String[newInfra.size()]);
        license = licenseTextField.getText();
        if(hotelID != "") {
            if (hotelName.equals(hotelVO.hotelName) && address.equals(hotelVO.hotelAddress)
                    && area.equals(hotelVO.area) && city.equals(hotelVO.city)
                    && star == hotelVO.star && facility.equals(newFacility)
                    && license.equals(hotelVO.license)) {
                return false;
            } else {
                return true;
            }
        }else{
            if (!hotelName.equals("") || !address.equals("")
                    || area != null || city != null
                    || star != 0 || !newFacility.equals("")
                    || !license.equals("")) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * 退出系统
     */
    @FXML
    private void exit() {
        System.exit(0);
    }
}
