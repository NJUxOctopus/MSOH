package ui.view.presentation.customer;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import ui.controller.HotelInfoController;
import ui.controller.ReservedHotelController;
import ui.controller.UserAdminController;
import ui.view.controllerservice.HotelInfo;
import ui.view.controllerservice.ReservedHotel;
import ui.view.controllerservice.UserAdmin;
import ui.view.presentation.PaneAdder;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;
import ui.view.presentation.util.SelectTimeViewController;
import vo.HotelVO;

import java.io.IOException;
import java.rmi.RemoteException;
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
    private CheckBox reservedCheckBox;

    @FXML
    private AnchorPane hotelListScrollPane;

    @FXML
    private Label emptyHotelLabel;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * 确认修改搜索条件，重新搜索
     */
    @FXML
    private void research() {
        String city = (String) cityChoiceBox.getSelectionModel().getSelectedItem();
        String area = (String) areaChoiceBox.getSelectionModel().getSelectedItem();
        int star = starChoiceBox.getSelectionModel().selectedIndexProperty().intValue();
        int score = scoreChoiceBox.getSelectionModel().selectedIndexProperty().intValue();
        String checkInTime = checkInTimeTextField.getText();
        String checkOutTime = checkOutTimeTextField.getText();
        HotelVO hotelVO = new HotelVO(city, area, star, score, checkInTime, checkOutTime);

    }

    /**
     * 关闭酒店列表界面
     */
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

    private void addHotelPane(List<HotelVO> hotelList){

        if(!hotelList.isEmpty()) {
            int num = hotelList.size();
            hotelListScrollPane.setPrefWidth(270 * num);
            PaneAdder paneAdder = new PaneAdder();
            for (int i = 0; i < num; i++) {
                System.out.print("!");
                paneAdder.addPane(hotelListScrollPane, "customer/CustomerSingleHotelView.fxml", 5 + 270 * i, 10);
                customerSingleHotelViewController = (CustomerSingleHotelViewController) paneAdder.getController();
                customerSingleHotelViewController.init(customerID, hotelList.get(num).hotelID);
            }
        }else{
            emptyHotelLabel.setOpacity(1);
        }

    }

    /**
     * 酒店列表界面初始化方法
     * @param hotelVO
     * @param customerId
     */
    public void init(HotelVO hotelVO, String customerId){
        this.customerID = customerId;
        try {

            HotelInfo hotelInfo = new HotelInfoController();
            List<String> city = hotelInfo.getAllCities();
            ObservableList<String> citys = FXCollections.observableArrayList();
            for(int i = 0; i < city.size(); i++)
                citys.add(city.get(i));
            cityChoiceBox.setItems(citys);

            cityChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener(){
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
                    }catch(RemoteException e){
                        e.printStackTrace();
                    }
                }
            });
        }catch(RemoteException e){
            e.printStackTrace();
        }

        String[] star = {"任意星级","★", "★★", "★★★", "★★★★", "★★★★★"};
        starChoiceBox.setItems(FXCollections.observableArrayList(
                "任意星级","★", "★★", "★★★", "★★★★", "★★★★★"));

        String[] score = {"任意分数","1分以上", "2分以上", "3分以上", "4分以上", "5分以上"};
        scoreChoiceBox.setItems(FXCollections.observableArrayList(
                "任意分数","1分以上", "2分以上", "3分以上", "4分以上", "5分以上"));


        sortChoiceBox.setItems(FXCollections.observableArrayList(
                "星级↑","星级↓","评分↑","评分↓"));


        cityChoiceBox.setValue(hotelVO.city);
        areaChoiceBox.setValue(hotelVO.area);
        if(hotelVO.star != -1)
            starChoiceBox.setValue(star[hotelVO.star]);
        if(hotelVO.score != -1)
            scoreChoiceBox.setValue(score[(int)hotelVO.score]);
        checkInTimeTextField.setText(hotelVO.checkInTime);
        checkOutTimeTextField.setText(hotelVO.checkOutTime);

        getAllHotel(hotelVO);
    }



    private void getAllHotel(HotelVO hotelVO){
        HotelInfo hotelInfo = new HotelInfoController();
        try {
            List<HotelVO> hotelList = hotelInfo.searchHotel(hotelVO);
            System.out.print(hotelList.size());

            addHotelPane(hotelList);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
