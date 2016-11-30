package ui.view.presentation.customer;

/**
 * Created by island on 2016/11/22.
 */


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import ui.view.presentation.ControlledStage;
import ui.view.presentation.StageController;



public class CustomerMainViewController implements ControlledStage {
    StageController stageController;

    @FXML
    private ImageView background;

    @FXML
    private Button selectAddressButton;

    @FXML
    private Button selectAreaButton;

    @FXML
    private Button selectCheckInTimeButton;

    @FXML
    private Button selectCheckOutTimeButton;

    @FXML
    private Button selectStarButton;

    @FXML
    private Button selectScoreButton;

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
        FXMLLoader loader = stageController.getLoader();
        CustomerOrderListViewController customerOrderListViewController = loader.getController();
        customerOrderListViewController.addOrderPane();
    }

    /**
     * 酒店按钮结果，跳出我的酒店界面
     */
    @FXML
    private void showCustomerHotelListView() {
        stageController = new StageController();
        stageController.loadStage("customer/CustomerHotelListView.fxml", 1);
        FXMLLoader loader = stageController.getLoader();
        CustomerHotelListViewController customerHotelListViewController = loader.getController();
        customerHotelListViewController.addHotelPane();
    }

    /**
     * 信用按钮结果，跳出我的信用界面
     */
    @FXML
    private void showCustomerCreditRecordView() {
        stageController = new StageController();
        stageController.loadStage("customer/CustomerCreditRecordView.fxml", 1);
        FXMLLoader loader = stageController.getLoader();
        CustomerCreditRecordViewController customerCreditRecordViewController = loader.getController();
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

    /**
     * 地址选择按钮结果，跳出地址选择界面
     */
    @FXML
    private void showCitySelectView(){

    }

    @FXML
    private void showAreaSelectView(){

    }

    @FXML
    private void showCheckInTimeSelectView(){

    }

    @FXML
    private void showCheckOutTimeSelectView(){

    }

    @FXML
    private void showStarSelectView(){

    }

    @FXML
    private void showScoreSelectView(){

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

    }
}
