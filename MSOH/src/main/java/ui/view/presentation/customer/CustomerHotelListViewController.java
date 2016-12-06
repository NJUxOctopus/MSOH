package ui.view.presentation.customer;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import ui.controller.HotelInfoController;
import ui.view.controllerservice.HotelInfo;
import ui.view.presentation.PaneAdder;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;
import ui.view.presentation.util.SelectTimeViewController;
import vo.HotelVO;

import java.io.IOException;
import java.util.List;

/**
 * Created by island on 2016/11/24.
 */
public class CustomerHotelListViewController implements ControlledStage {
    StageController stageController = new StageController();

    CustomerSingleHotelViewController customerSingleHotelViewController;

    private String resource = "customer/CustomerHotelListView.fxml";

    private String customerID;

    @FXML
    private ImageView background;

    @FXML
    private Button backButton;

    @FXML
    private ChoiceBox cityChoiceBox;

    @FXML
    private ChoiceBox areaChoiceBox;

    @FXML
    private ChoiceBox starChoiceBox;

    @FXML
    private ChoiceBox numOfRoomChoiceBox;

    @FXML
    private ChoiceBox scoreChoiceBox;

    @FXML
    private TextField nameTextField;

    @FXML
    private Button checkInTimeButton;

    @FXML
    private Button checkOutTimeButton;

    @FXML
    private Button confirmModifyButton;

    @FXML
    private TextField checkInTimeTextField;

    @FXML
    private TextField checkOutTimeTextField;

    @FXML
    private ChoiceBox sortChoiceBox;

    @FXML
    private ChoiceBox selcetChoiceBox;

    @FXML
    private CheckBox reservedCheckBox;

    @FXML
    private AnchorPane hotelListScrollPane;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    @FXML
    private void research() {

    }

    @FXML
    private void closeStage() {
        stageController.closeStage(resource);
    }

    /**
     * 点击选择入住日期，跳出日期选择框
     */
    @FXML
    private void showCheckInTimeSelectView(){
        stageController = new StageController();
        stageController.loadStage("util/SelectTimeView.fxml",0.8);
        SelectTimeViewController selectTimeViewController = (SelectTimeViewController) stageController.getController();
        selectTimeViewController.setToBeSet(resource, "checkIn");
        selectTimeViewController.init();
    }

    public void setCheckInTime(String checkInTime){
        checkOutTimeTextField.setText(checkInTime);
    }

    /**
     * 点击选择退房日期，跳出日期选择框
     */
    @FXML
    private void showCheckOutTimeSelectView(){
        stageController = new StageController();
        stageController.loadStage("util/SelectTimeView.fxml",0.8);
        SelectTimeViewController selectTimeViewController = (SelectTimeViewController) stageController.getController();
        selectTimeViewController.setToBeSet(resource, "checkOut");
        selectTimeViewController.init();
    }

    public void setCheckOutTime(String checkOutTime){
        checkOutTimeTextField.setText(checkOutTime);
    }

    public void addHotelPane(List<HotelVO> hotelList){
        PaneAdder paneAdder = new PaneAdder();
        paneAdder.addPane(hotelListScrollPane, "customer/CustomerSingleHotelView.fxml", 5, 10);
        paneAdder.addPane(hotelListScrollPane, "customer/CustomerSingleHotelView.fxml", 250, 10);

    }

    public void init(HotelVO hotelVO, String customerId){
        getAllHotel(hotelVO);
        this.customerID = customerId;
        cityChoiceBox.setItems(FXCollections.observableArrayList(
                "南京"));

        areaChoiceBox.setItems(FXCollections.observableArrayList(
                "新街口"));

        String[] star = {"任意星级","★", "★★", "★★★", "★★★★", "★★★★★"};
        starChoiceBox.setItems(FXCollections.observableArrayList(
                "任意星级","★", "★★", "★★★", "★★★★", "★★★★★"));

        String[] score = {"任意分数","1分以上", "2分以上", "3分以上", "4分以上", "5分以上"};
        scoreChoiceBox.setItems(FXCollections.observableArrayList(
                "任意分数","1分以上", "2分以上", "3分以上", "4分以上", "5分以上"));


        cityChoiceBox.setValue(hotelVO.city);
        areaChoiceBox.setValue(hotelVO.area);
        if(hotelVO.star != -1)
            starChoiceBox.setValue(star[hotelVO.star]);
        if(hotelVO.score != -1)
            scoreChoiceBox.setValue(score[(int)hotelVO.score]);
        checkInTimeTextField.setText(hotelVO.checkInTime);
        checkOutTimeTextField.setText(hotelVO.checkOutTime);
    }

    private void getAllHotel(HotelVO hotelVO){
        HotelInfo hotelInfo = new HotelInfoController();
            /*
            try {
                //List<HotelVO> hotelList = hotelInfo.searchHotel(hotelVO);
                CustomerHotelListViewController customerHotelListViewController = (CustomerHotelListViewController)stageController.getController();
                //customerHotelListViewController.addHotelPane(hotelList);
                customerHotelListViewController.init(hotelVO);
            } catch (RemoteException e) {
            e.printStackTrace();
            }
            */
    }
}
