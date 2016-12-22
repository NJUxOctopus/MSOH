package ui.view.presentation.customer;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import ui.controller.EditPromotionController;
import ui.controller.HotelAdminController;
import ui.controller.ProcessOrderController;
import ui.controller.UserAdminController;
import ui.view.controllerservice.EditPromotion;
import ui.view.controllerservice.HotelAdmin;
import ui.view.controllerservice.ProcessOrder;
import ui.view.controllerservice.UserAdmin;
import ui.view.presentation.util.ConfirmExitController;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ErrorBoxController;
import ui.view.presentation.util.SelectTimeViewController;
import util.ResultMessage;
import vo.*;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by island on 2016/12/1.
 */
public class CustomerReserveViewController implements ControlledStage{
    StageController stageController;

    private String resource = "customer/CustomerReserveView.fxml";

    private String customerID;

    private HotelVO hotelVO;

    private String[] rooms;

    private List<OrderPriceVO> orderPriceVOList;

    @FXML
    private TextField customerNameTextField;

    @FXML
    private TextField phoneTextField;

    @FXML
    private TextField idTextField;

    @FXML
    private TextField checkInTimeTextField;

    @FXML
    private TextField checkOutTimeTextField;

    @FXML
    private TextField hotelNameTextField;

    @FXML
    private TextField hotelIDTextField;

    @FXML
    private TextField prePriceTextField;

    @FXML
    private TextField afterPriceTextField;

    @FXML
    private TextField roomNumTextField;

    @FXML
    private TextField roomTypeTextField;

    @FXML
    private ChoiceBox hasChildChoiceBox;

    @FXML
    private ChoiceBox promotionChoiceBox;

    @FXML
    private Button selectCheckInButton;

    @FXML
    private Button selectCheckOutButton;

    @FXML
    private Button reserveButton;

    @FXML
    private Button selectRoomButton;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    @FXML
    private void reserve(){
        String customerName = customerNameTextField.getText();
        String phone = phoneTextField.getText();
        int numOfCustomers = Integer.parseInt(roomNumTextField.getText());

        boolean rightTime = false;
        String checkInTime = checkInTimeTextField.getText();
        String checkOutTime = checkOutTimeTextField.getText();

        OrderVO orderVO = new OrderVO();
        if(checkInTime != null && checkInTime != null){
            Timestamp checkIn = Timestamp.valueOf(checkInTime + " 00:00:00.0");
            Timestamp checkOut = Timestamp.valueOf(checkOutTime + " 00:00:00.0");
            if(checkIn.after(checkOut)){
                stageController = new StageController();
                stageController.loadStage("util/ErrorBoxView.fxml", 0.8);
                ErrorBoxController controller = (ErrorBoxController) stageController.getController();
                controller.setLabel("退房日期必须在入住日期之后！");
            }else{
                if(!roomTypeTextField.getText().equals("")) {
                    boolean haveChildren = false;
                    if ((hasChildChoiceBox.getSelectionModel().getSelectedItem()) != null) {
                        if (hasChildChoiceBox.getSelectionModel().getSelectedItem().equals("有"))
                            haveChildren = true;
                        double initPrice = Double.parseDouble(prePriceTextField.getText());
                        double finalPrice = Double.parseDouble(afterPriceTextField.getText());
                        String promotion = (String) promotionChoiceBox.getSelectionModel().getSelectedItem();
                        orderVO = new OrderVO(customerName, phone, customerID,
                                hotelVO.hotelID, hotelVO.hotelName,
                                checkIn, checkOut,
                                rooms, numOfCustomers, haveChildren, initPrice, finalPrice, promotion);
                        rightTime = true;
                    } else {
                        stageController = new StageController();
                        stageController.loadStage("util/ErrorBoxView.fxml", 0.8);
                        ErrorBoxController controller = (ErrorBoxController) stageController.getController();
                        controller.setLabel("请先选择是否有儿童！");
                    }
                }else{
                    stageController = new StageController();
                    stageController.loadStage("util/ErrorBoxView.fxml", 0.8);
                    ErrorBoxController controller = (ErrorBoxController) stageController.getController();
                    controller.setLabel("请先选择房间类型！");
                }

            }
        }
        else{

            stageController = new StageController();
            stageController.loadStage("util/ErrorBoxView.fxml", 0.8);
            ErrorBoxController controller = (ErrorBoxController) stageController.getController();
            controller.setLabel("请选择完整的日期信息！");
        }
        if(rightTime) {

            ProcessOrder processOrder = new ProcessOrderController();
            try {
                ResultMessage resultMessage = processOrder.createOrder(orderVO);
                stageController = new StageController();
                stageController.loadStage("util/ErrorBoxView.fxml", 0.8);
                ErrorBoxController controller = (ErrorBoxController) stageController.getController();
                if (resultMessage == ResultMessage.Blank) {
                    controller.setLabel("请选择完整的预订信息！");
                }

                if (resultMessage == ResultMessage.Fail) {
                    controller.setLabel("预订失败！");
                }
                if (resultMessage == ResultMessage.Order_CreateOrderSuccess) {
                    controller.setLabel("预定成功！");
                    stageController = new StageController();
                    stageController.closeStage(resource);

                    HotelAdmin hotelAdmin = new HotelAdminController();
                    hotelAdmin.changeAvailableRoom(orderVO, -1);
                    hotelAdmin.changeReservedRoom(orderVO, 1);
                }

            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void closeStage() {
        stageController = new StageController();
        stageController.loadStage("util/ConfirmExit.fxml", 0.75);
        ConfirmExitController controller = (ConfirmExitController) stageController.getController();
        controller.setToBeClosed(resource);
        controller.setLabel("您还未确认订单");
    }

    /**
     * 点击选择入住日期，跳出日期选择框
     */
    @FXML
    private void selectCheckInTime(){
        stageController = new StageController();
        stageController.loadStage("util/SelectTimeView.fxml",0.8);
        SelectTimeViewController selectTimeViewController = (SelectTimeViewController) stageController.getController();
        selectTimeViewController.setToBeSet(resource, "checkIn");
        selectTimeViewController.init();
    }

    public void setCheckInTime(String scheckInTime){
        checkInTimeTextField.setText(scheckInTime);
        roomTypeTextField.setText("");
    }

    /**
     * 点击选择退房日期，跳出日期选择框
     */
    @FXML
    private void selectCheckOutTime(){
        stageController = new StageController();
        stageController.loadStage("util/SelectTimeView.fxml",0.8);
        SelectTimeViewController selectTimeViewController = (SelectTimeViewController) stageController.getController();
        selectTimeViewController.setToBeSet(resource, "checkOut");
        selectTimeViewController.init();
    }

    public void setCheckOutTime(String scheckOutTime){
        checkOutTimeTextField.setText(scheckOutTime);
        roomTypeTextField.setText("");
    }


    /**
     * 跳出房间选择框
     */
    @FXML
    private void selectRoom(){
        if(checkInTimeTextField.getText() == null || checkOutTimeTextField.getText() == null){
            stageController = new StageController();
            stageController.loadStage("util/ErrorBoxView.fxml", 0.75);
            ErrorBoxController errorBoxController = (ErrorBoxController) stageController.getController();
            errorBoxController.setLabel("请先选择入住日期与退房日期！");
        }else {
            stageController = new StageController();
            stageController.loadStage("customer/CustomerChooseRoom.fxml", 1);
            CustomerChooseRoomController customerChooseRoomController = (CustomerChooseRoomController) stageController.getController();
            customerChooseRoomController.initial(hotelVO.hotelID);
        }
    }

    public void setRoomAndPrice(String[] rooms){
        this.rooms = rooms;
        String[] room = {"单人房", "标间", "大床房"};
        int[] roomNum = {0, 0, 0};
        for(int i = 0; i < rooms.length; i++) {
            if(rooms[i].equals(room[0])){
                roomNum[0]++;
            }else if(rooms[i].equals(room[1])){
                roomNum[1]++;
            }else if(rooms[i].equals(room[2])){
                roomNum[2]++;
            }
        }
        String roomType = "";
        for(int i = 0; i < 3; i++){
            if(roomNum[i] != 0){
                roomType += room[i] + "*" + roomNum[i] + '\n';
            }
        }
        roomTypeTextField.setText(roomType);

        setPriceAndPromotion();
    }

    /**
     * 设置价格和促销策略
     */
    private void setPriceAndPromotion(){
        Timestamp checkInTime = Timestamp.valueOf(checkInTimeTextField.getText() + " 00:00:00");
        Timestamp checkOutTime = Timestamp.valueOf(checkOutTimeTextField.getText() + " 00:00:00");
        OrderVO orderVO = new OrderVO(customerID, hotelIDTextField.getText(), checkInTime, checkOutTime, rooms);
        ProcessOrder processOrder = new ProcessOrderController();
        try {
            //初始化促销策略选择下拉框
            orderPriceVOList = processOrder.usePromotion(orderVO);
            final ObservableList<String> promotion = FXCollections.observableArrayList();
            for(int i = 0; i < orderPriceVOList.size(); i++){
                if(orderPriceVOList.get(i).promotionName != null) {
                    promotion.add(orderPriceVOList.get(i).promotionName);
                }else{
                    promotion.add("无");
                }
            }
            promotionChoiceBox.setItems(promotion);

            //获得最低价格
            OrderPriceVO lowestOrderPrice = processOrder.getLowestPrice(orderPriceVOList);
            if(lowestOrderPrice.promotionName != null) {
                promotionChoiceBox.setValue(lowestOrderPrice.promotionName);
            }else{
                promotionChoiceBox.setValue("无");
            }
            DecimalFormat df = new DecimalFormat("0.0");
            afterPriceTextField.setText(df.format(lowestOrderPrice.finalPrice) + "");
            prePriceTextField.setText(df.format(lowestOrderPrice.initPrice) + "");

        }catch (RemoteException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        modifyPromotion();
    }

    private void modifyPromotion(){
        promotionChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                int select = promotionChoiceBox.getSelectionModel().getSelectedIndex();
                afterPriceTextField.setText(orderPriceVOList.get(select).finalPrice + "");
            }
        });
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

    public void init(String customerID, HotelVO hotelVO) {
        this.customerID = customerID;
        this.hotelVO = hotelVO;
        setCustomerInfo();
        setHotelInfo();
        hasChildChoiceBox.setItems(FXCollections.observableArrayList(
                "有", "无"));
    }

    /**
     * 设置客户相关信息
     */
    private void setCustomerInfo(){
        UserAdmin userAdmin = new UserAdminController();
        try {
            CustomerVO customerVO = userAdmin.findCustomerByID(customerID);
            customerNameTextField.setText(customerVO.name);
            phoneTextField.setText(customerVO.phone);
            idTextField.setText(customerVO.ID);
        }catch (RemoteException e){
            e.printStackTrace();
        }
    }

    /**
     * 设置酒店相关信息
     */
    private void setHotelInfo(){
        hotelNameTextField.setText(hotelVO.hotelName);
        hotelIDTextField.setText(hotelVO.hotelID);
        checkInTimeTextField.setText(hotelVO.checkInTime);
        checkOutTimeTextField.setText(hotelVO.checkOutTime);
    }


    /**
     * 退出系统
     */
    @FXML
    private void exit() {
        System.exit(0);
    }


}
