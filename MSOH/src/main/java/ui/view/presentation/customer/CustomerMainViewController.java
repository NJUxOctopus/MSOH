package ui.view.presentation.customer;

/**
 * Created by island on 2016/11/22.
 */


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;
import ui.view.presentation.util.SelectTimeViewController;


public class CustomerMainViewController implements ControlledStage {
    StageController stageController;

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
    private Label addressLabel;

    @FXML
    private Label areaLabel;

    @FXML
    private Label checkInTimeLabel;

    @FXML
    private Label checkOutTimeLabel;

    @FXML
    private Label starLabel;

    @FXML
    private Label scoreLabel;

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
        customerOrderListViewController.addOrderPane();
    }

    /**
     * 酒店按钮结果，跳出我的酒店界面
     */
    @FXML
    private void showCustomerHotelListView() {
        stageController = new StageController();
        stageController.loadStage("customer/CustomerMyHotelView.fxml", 1);
        CustomerMyHotelViewController customerMyHotelViewController = (CustomerMyHotelViewController) stageController.getController();
        customerMyHotelViewController.addHotelPane();
    }

    /**
     * 信用按钮结果，跳出我的信用界面
     */
    @FXML
    private void showCustomerCreditRecordView() {
        stageController = new StageController();
        stageController.loadStage("customer/CustomerCreditRecordView.fxml", 1);
        CustomerCreditRecordViewController customerCreditRecordViewController = (CustomerCreditRecordViewController)stageController.getController();
        customerCreditRecordViewController.addCreditPane();

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
    }


    @FXML
    private void showCheckInTimeSelectView(){
        stageController = new StageController();
        stageController.loadStage("util/SelectTimeView.fxml",0.8);
        SelectTimeViewController selectTimeViewController = (SelectTimeViewController) stageController.getController();
        selectTimeViewController.init();
    }

    @FXML
    private void showCheckOutTimeSelectView(){

    }

    @FXML
    private void showCustomerSettingView(){

    }

    @FXML
    private void showFeedbackView(){

    }

    @FXML
    private void showAboutUsView(){

    }

    @FXML
    private void search(){
        stageController = new StageController();
        stageController.loadStage("customer/CustomerHotelListView.fxml", 1);
        CustomerHotelListViewController customerHotelListViewController = (CustomerHotelListViewController)stageController.getController();
        customerHotelListViewController.addHotelPane();
    }
}
