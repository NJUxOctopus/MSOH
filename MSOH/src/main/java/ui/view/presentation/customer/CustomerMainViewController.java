package ui.view.presentation.customer;

/**
 * Created by island on 2016/11/22.
 */


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import ui.controller.HotelInfoController;
import ui.controller.UserAdminController;
import ui.view.controllerservice.HotelInfo;
import ui.view.controllerservice.UserAdmin;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ErrorBoxController;
import ui.view.presentation.util.SelectTimeViewController;
import vo.HotelVO;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

/**
 * 客户主界面控制器
 */
public class CustomerMainViewController implements ControlledStage {
    StageController stageController;

    private String resource = "customer/CustomerMainView.fxml";

    private String customerID;

    @FXML
    private ImageView background;

    @FXML
    private ChoiceBox cityChoiceBox;

    @FXML
    private ChoiceBox areaChoiceBox;

    @FXML
    private Button selectCheckInTimeButton;

    @FXML
    private Button selectCheckOutTimeButton;

    @FXML
    private ChoiceBox starChoiceBox;

    @FXML
    private ChoiceBox scoreChoiceBox;

    @FXML
    private Button hotelButton;

    @FXML
    private Button orderButton;

    @FXML
    private Button creditButton;

    @FXML
    private Button memberButton;

    @FXML
    private Button searchButton;

    @FXML
    private Button infoButton;

    @FXML
    private Button settingButton;

    @FXML
    private Button feedbackButton;

    @FXML
    private Button aboutUsButton;

    @FXML
    private Label checkInTimeLabel;

    @FXML
    private Label checkOutTimeLabel;

    @FXML
    private Label nameLabel;


    private CustomerMainView customerMainView = new CustomerMainView();

    public CustomerMainViewController() {

    }

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * 订单按钮结果，跳出我的订单界面
     */
    @FXML
    private void showCustomerOrderListView() {
        stageController = new StageController();
        stageController.loadStage("customer/CustomerOrderListView.fxml", 1);
        CustomerOrderListViewController customerOrderListViewController = (CustomerOrderListViewController) stageController.getController();
        customerOrderListViewController.init(customerID);

    }

    /**
     * 酒店按钮结果，跳出我的酒店界面
     */
    @FXML
    private void showCustomerHotelListView() {
        stageController = new StageController();
        stageController.loadStage("customer/CustomerMyHotelView.fxml", 1);
        CustomerMyHotelViewController customerMyHotelViewController = (CustomerMyHotelViewController) stageController.getController();
        customerMyHotelViewController.init(customerID);
    }

    /**
     * 信用按钮结果，跳出我的信用界面
     */
    @FXML
    private void showCustomerCreditRecordView() {
        stageController = new StageController();
        stageController.loadStage("customer/CustomerCreditRecordView.fxml", 1);
        CustomerCreditRecordViewController customerCreditRecordViewController = (CustomerCreditRecordViewController) stageController.getController();
        customerCreditRecordViewController.init(customerID);

    }

    /**
     * 会员按钮结果，跳出我的会员界面
     */
    @FXML
    private void showCustomerMemberView() {
        stageController = new StageController();
        stageController.loadStage("customer/CustomerMyMemberView.fxml", 1);
    }

    /**
     * 个人信息按钮结果，跳出个人信息界面
     */
    @FXML
    private void showCustomerInfoView() {
        stageController = new StageController();
        stageController.loadStage("customer/CustomerInfoView.fxml", 1);
        CustomerInfoViewController customerInfoViewController = (CustomerInfoViewController) stageController.getController();
        customerInfoViewController.init(customerID);
    }

    /**
     * 点击选择入住日期，跳出日期选择框
     */
    @FXML
    private void showCheckInTimeSelectView() {
        stageController = new StageController();
        stageController.loadStage("util/SelectTimeView.fxml", 0.8);
        SelectTimeViewController selectTimeViewController = (SelectTimeViewController) stageController.getController();
        selectTimeViewController.setToBeSet(resource, "checkIn");
        selectTimeViewController.init();
    }

    public void setCheckInTime(String checkInTime) {
        checkInTimeLabel.setText(checkInTime);
    }

    /**
     * 点击选择退房日期，跳出日期选择框
     */
    @FXML
    private void showCheckOutTimeSelectView() {
        stageController = new StageController();
        stageController.loadStage("util/SelectTimeView.fxml", 0.8);
        SelectTimeViewController selectTimeViewController = (SelectTimeViewController) stageController.getController();
        selectTimeViewController.setToBeSet(resource, "checkOut");
        selectTimeViewController.init();
    }

    public void setCheckOutTime(String checkOutTime) {
        checkOutTimeLabel.setText(checkOutTime);
    }

    /**
     * 切换账号
     */
    @FXML
    private void switchAccount() {
        stageController = new StageController();
        stageController.closeStage(resource);
        stageController.loadStage("login/LoginView.fxml", 1);
    }

    /**
     * 退出系统
     */
    @FXML
    private void exit() {
        System.exit(0);
    }

    @FXML
    private void showAboutUsView() {

    }

    @FXML
    private void setColor1() {
        searchButton.setStyle("-fx-background-color: #ffffff00");


    }

    @FXML
    private void setColor2() {
        searchButton.setStyle("-fx-background-color: #ffffff30");

    }

    /**
     * 搜索酒店按钮
     */
    @FXML
    private void search() {
        String city = (String) cityChoiceBox.getSelectionModel().getSelectedItem();
        String area = (String) areaChoiceBox.getSelectionModel().getSelectedItem();
        int star = starChoiceBox.getSelectionModel().selectedIndexProperty().intValue();
        int score = scoreChoiceBox.getSelectionModel().selectedIndexProperty().intValue();
        String checkInTime = checkInTimeLabel.getText();
        String checkOutTime = checkOutTimeLabel.getText();

        HotelVO hotelVO = new HotelVO(city, area, star, score, checkInTime, checkOutTime);

        if (city != null && area != null) {
            stageController = new StageController();
            stageController.loadStage("customer/CustomerHotelListView.fxml", 1);
            CustomerHotelListViewController customerHotelListViewController = (CustomerHotelListViewController) stageController.getController();
            customerHotelListViewController.init(hotelVO, customerID);
        } else {
            stageController.loadStage("util/ErrorBoxView.fxml", 0.8);
            ErrorBoxController controller = (ErrorBoxController) stageController.getController();
            controller.setLabel("请先选择城市与商圈！");
        }


    }

    /**
     * 客户主界面初始化方法
     *
     * @param customerID
     */
    public void init(String customerID) {
        this.customerID = customerID;

        String customerName = "";
        try {
            UserAdmin userAdmin = new UserAdminController();
            customerName = userAdmin.findCustomerByID(customerID).name;
            nameLabel.setText(customerName);

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

            starChoiceBox.setItems(FXCollections.observableArrayList(
                    "任意星级", "★", "★★", "★★★", "★★★★", "★★★★★"));

            scoreChoiceBox.setItems(FXCollections.observableArrayList(
                    "任意分数", "1分以上", "2分以上", "3分以上", "4分以上", "5分以上"));
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        checkInTimeLabel.setText("");
        checkOutTimeLabel.setText("");
    }

    public void init2(String customerID) {
        starChoiceBox.setItems(FXCollections.observableArrayList(
                "任意星级", "★", "★★", "★★★", "★★★★", "★★★★★"));
    }
}