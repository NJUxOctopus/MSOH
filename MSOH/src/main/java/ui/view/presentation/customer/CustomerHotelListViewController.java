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
import ui.controller.HotelAdminController;
import ui.controller.HotelInfoController;
import ui.controller.ReservedHotelController;
import ui.controller.UserAdminController;
import ui.view.controllerservice.HotelAdmin;
import ui.view.controllerservice.HotelInfo;
import ui.view.controllerservice.ReservedHotel;
import ui.view.controllerservice.UserAdmin;
import ui.view.presentation.PaneAdder;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ErrorBoxController;
import ui.view.presentation.util.SelectTimeViewController;
import vo.HotelVO;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by island on 2016/11/24.
 */
public class CustomerHotelListViewController implements ControlledStage {
    StageController stageController = new StageController();

    CustomerSingleHotelViewController customerSingleHotelViewController;

    private String resource = "customer/CustomerHotelListView.fxml";

    private String customerID;

    private List<HotelVO> hotelList = new ArrayList<HotelVO>();

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
    private ChoiceBox typeOfRoomChoiceBox;

    @FXML
    private AnchorPane hotelListScrollPane;

    @FXML
    private Label emptyHotelLabel;

    @FXML
    private TextField roomNumTextField;

    @FXML
    private Button reservedButton;

    private boolean showReserved = false;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * 确认修改搜索条件，重新搜索
     */
    @FXML
    private void research() {
        stageController = new StageController();

        String city = (String) cityChoiceBox.getSelectionModel().getSelectedItem();
        String area = (String) areaChoiceBox.getSelectionModel().getSelectedItem();
        String hotelName = nameTextField.getText();
        int star = starChoiceBox.getSelectionModel().selectedIndexProperty().intValue();
        int score = scoreChoiceBox.getSelectionModel().selectedIndexProperty().intValue();

        //房间信息
        int roomNum = Integer.parseInt(roomNumTextField.getText());
        String roomType = (String) typeOfRoomChoiceBox.getSelectionModel().getSelectedItem();
        boolean rightRoom = true;

        //日期信息
        String checkInTime = checkInTimeTextField.getText();
        String checkOutTime = checkOutTimeTextField.getText();
        boolean rightTime = true;
        if((checkInTime != null && checkInTime == null) || (checkInTime == null && checkInTime != null)){
            stageController = new StageController();
            stageController.loadStage("util/ErrorBoxView.fxml", 0.8);
            ErrorBoxController controller = (ErrorBoxController) stageController.getController();
            controller.setLabel("请选择完整的日期信息！");
        }
        Timestamp checkIn;
        Timestamp checkOut;
        if(!checkInTime.equals("") && !checkInTime.equals("")){
            checkIn = Timestamp.valueOf(checkInTime + " 00:00:00");
            checkOut = Timestamp.valueOf(checkOutTime + " 00:00:00");
            if(checkIn.after(checkOut)){
                stageController = new StageController();
                stageController.loadStage("util/ErrorBoxView.fxml", 0.8);
                ErrorBoxController controller = (ErrorBoxController) stageController.getController();
                controller.setLabel("退房日期必须在入住日期之后！");
                rightTime = false;
            }
        }else{
            checkIn = null;
            checkOut = null;
            rightTime = false;
        }

        if(roomType == null && roomNum > 0){
            rightRoom = false;
            stageController = new StageController();
            stageController.loadStage("util/ErrorBoxView.fxml", 0.8);
            ErrorBoxController controller = (ErrorBoxController) stageController.getController();
            controller.setLabel("请先选择房间类型再选择房间数量！");
        }
        HotelInfo hotelInfo = new HotelInfoController();
        try {

            if (rightRoom && rightTime) {
                List<HotelVO> hotelVOList = hotelInfo.filter(star + "", hotelName, score + "", 5 + "", checkIn, checkOut, roomType, roomNum, area);
                addHotelPane(hotelVOList);
            }

        }catch (RemoteException e){
            e.printStackTrace();
        }
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
        checkInTimeTextField.setText(checkInTime);
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

    /**
     * 初始化酒店列表面板
     * @param hotelList
     */
    private void addHotelPane(List<HotelVO> hotelList){
        hotelListScrollPane.getChildren().clear();
        emptyHotelLabel.setOpacity(0);

        if(!hotelList.isEmpty()) {
            int num = hotelList.size();
            hotelListScrollPane.setPrefWidth(270 * num);
            PaneAdder paneAdder = new PaneAdder();
            for (int i = 0; i < num; i++) {
                paneAdder.addPane(hotelListScrollPane, "customer/CustomerSingleHotelView.fxml",  270 * i + 5, 10);
                customerSingleHotelViewController = (CustomerSingleHotelViewController) paneAdder.getController();
                customerSingleHotelViewController.init(customerID, hotelList.get(i).hotelID);
            }
        }else{
            emptyHotelLabel.setOpacity(1);
        }

    }

    /**
     * 加号按钮结果，房间数量+1
     */
    @FXML
    private void addRoomNum() {
        int peopleNum = Integer.parseInt(roomNumTextField.getText());
        roomNumTextField.setText(String.valueOf((peopleNum + 1)));
    }

    /**
     * 减号按钮结果，房间数量-1
     */
    @FXML
    private void minusRoomNum() {
        int peopleNum = Integer.parseInt(roomNumTextField.getText());
        if (peopleNum != 0) {
            roomNumTextField.setText(String.valueOf((peopleNum - 1)));
        }
    }

    /**
     * 只显示预订过的酒店
     */
    @FXML
    private void showReservedHotel(){
        if(!showReserved){
            showReserved = true;
            reservedButton.setText("■");
            List<HotelVO> reservedHotelList = new ArrayList<HotelVO>();
            HotelAdmin hotelAdmin = new HotelAdminController();
            try {
                if (!hotelList.isEmpty()) {
                    for (int i = 0; i < hotelList.size(); i++) {
                        if (hotelAdmin.hotelIsReserverd(customerID, hotelList.get(i).hotelID)) {
                            reservedHotelList.add(hotelList.get(i));
                        }
                    }
                    addHotelPane(reservedHotelList);
                }
            }catch (RemoteException e){
                e.printStackTrace();
            }
        }else{
            showReserved = false;
            reservedButton.setText("□");
            addHotelPane(hotelList);
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
                "星级", "评分"));
        sortChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                String sort = (String) sortChoiceBox.getSelectionModel().getSelectedItem();

                try {
                    if(!hotelList.isEmpty()){
                        if(sort.equals("星级")){
                            HotelAdmin hotelAdmin = new HotelAdminController();
                            hotelList = hotelAdmin.sortByStar(hotelList);
                            addHotelPane(hotelList);
                        }
                        if(sort.equals("评分")){
                            HotelAdmin hotelAdmin = new HotelAdminController();
                            hotelList = hotelAdmin.sortByScore(hotelList);
                            addHotelPane(hotelList);
                        }
                    }
                }catch(RemoteException e){
                    e.printStackTrace();
                }
            }
        });

        cityChoiceBox.setValue(hotelVO.city);
        areaChoiceBox.setValue(hotelVO.area);
        if(hotelVO.star != -1)
            starChoiceBox.setValue(star[hotelVO.star]);
        if(hotelVO.score != -1)
            scoreChoiceBox.setValue(score[(int)hotelVO.score]);
        checkInTimeTextField.setText(hotelVO.checkInTime);
        checkOutTimeTextField.setText(hotelVO.checkOutTime);
        typeOfRoomChoiceBox.setItems(FXCollections.observableArrayList(
                "单人房", "大床房", "标间"));
        getAllHotel(hotelVO);
    }


    /**
     * 获得所有酒店
     * @param hotelVO
     */
    private void getAllHotel(HotelVO hotelVO){
        HotelInfo hotelInfo = new HotelInfoController();
        try {
            hotelList = hotelInfo.searchHotel(hotelVO);

            addHotelPane(hotelList);
        } catch (RemoteException e) {
            e.printStackTrace();
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
