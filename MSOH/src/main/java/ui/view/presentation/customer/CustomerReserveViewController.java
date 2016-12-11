package ui.view.presentation.customer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import ui.controller.ProcessOrderController;
import ui.controller.UserAdminController;
import ui.view.controllerservice.HotelAdmin;
import ui.view.controllerservice.ProcessOrder;
import ui.view.controllerservice.UserAdmin;
import ui.view.presentation.util.ConfirmExitController;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ErrorBoxController;
import ui.view.presentation.util.SelectTimeViewController;
import util.ResultMessage;
import vo.CustomerVO;
import vo.HotelVO;
import vo.OrderVO;

import java.rmi.RemoteException;
import java.sql.Timestamp;

/**
 * Created by island on 2016/12/1.
 */
public class CustomerReserveViewController implements ControlledStage{
    StageController stageController;

    private String resource = "customer/CustomerReserveView.fxml";

    private String customerID;

    private HotelVO hotelVO;

    private String[] rooms;

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
    private TextField numOfPeopleTextField;

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
        int numOfCustomers = Integer.parseInt(numOfPeopleTextField.getText());
        boolean haveChildren = false;
        if( ((String)hasChildChoiceBox.getSelectionModel().getSelectedItem()) .equals("有"))
            haveChildren = true;
        double price = Double.parseDouble(afterPriceTextField.getText());

        boolean rightTime = false;
        String checkInTime = checkInTimeTextField.getText();
        String checkOutTime = checkOutTimeTextField.getText();
        Timestamp checkIn = Timestamp.valueOf("0000-00-00 00:00:00");
        Timestamp checkOut= Timestamp.valueOf("0000-00-00 00:00:00");

        if(checkInTime.equals("") && checkInTime.equals("")){
            checkIn = null;
            checkOut = null;
        }
        if(!checkInTime.equals("") && !checkInTime.equals("")){
            checkIn = Timestamp.valueOf(checkInTime + " 00:00:00");
            checkOut = Timestamp.valueOf(checkOutTime + " 00:00:00");
            if(checkIn.after(checkOut)){
                stageController = new StageController();
                stageController.loadStage("util/ErrorBoxView.fxml", 0.8);
                ErrorBoxController controller = (ErrorBoxController) stageController.getController();
                controller.setLabel("退房日期必须在入住日期之后！");
            }else{
                rightTime = true;
            }
        }
        if((checkInTime != null && checkInTime == null) || (checkInTime == null && checkInTime != null)){
            stageController = new StageController();
            stageController.loadStage("util/ErrorBoxView.fxml", 0.8);
            ErrorBoxController controller = (ErrorBoxController) stageController.getController();
            controller.setLabel("请选择完整的日期信息！");
        }

        if(rightTime) {
            OrderVO orderVO = new OrderVO(customerName, phone, customerID,
                    hotelVO.hotelID, hotelVO.hotelName,
                    checkIn, checkOut,
                    rooms, numOfCustomers, haveChildren, price);
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


    public void setRoomAndPrice(String[] rooms,double totalPrice){
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
        prePriceTextField.setText(totalPrice + "");
    }


    public void init(String customerID, HotelVO hotelVO, String[] rooms){
        this.customerID = customerID;
        this.hotelVO = hotelVO;
        this.rooms = rooms;
        setCustomerInfo();
        setHotelInfo();
        setRoomInfo();
    }

    private void setRoomInfo(){
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

    }

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

    private void setHotelInfo(){
        hotelNameTextField.setText(hotelVO.hotelName);
        hotelIDTextField.setText(hotelVO.hotelID);
        checkInTimeTextField.setText(hotelVO.checkInTime);
        checkOutTimeTextField.setText(hotelVO.checkOutTime);
    }
}
