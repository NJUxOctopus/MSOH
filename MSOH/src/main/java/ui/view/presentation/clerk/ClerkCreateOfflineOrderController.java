package ui.view.presentation.clerk;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ui.controller.HotelAdminController;
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
import util.OrderStatus;
import util.ResultMessage;
import vo.ClerkVO;
import vo.OrderVO;
import vo.RoomVO;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by ST on 2016/11/30.
 */
public class ClerkCreateOfflineOrderController implements ControlledStage {

    private StageController stageController;

    private static String resource = "clerk/ClerkCreateOfflineOrder.fxml";

    @FXML
    private TextField peopleNumTextField;
    @FXML
    private Button checkInTimeButton;
    @FXML
    private Button estimatedCheckOutTimeButton;
    @FXML
    private CheckBox childCheckBox;
    @FXML
    private TextField customerNameTextField;
    @FXML
    private TextField customerIDTextField;
    @FXML
    private TextField customerPhoneTextField;
    @FXML
    private Label priceLabel;

    private String clerkID;
    private ClerkVO clerkVO;
    private UserAdmin userAdmin;
    private HotelAdmin hotelAdmin;
    private OrderVO orderVO;
    private String[] rooms;
    private double price;
    private ProcessOrder processOrder;

    //获取当前日期
    Date date = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String initialTime = sdf.format(date);

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * initial方法，初始化界面
     */
    public void initial(String ID, String[] rooms, double price) throws RemoteException {
        userAdmin = new UserAdminController();
        this.clerkID = ID;
        clerkVO = userAdmin.findClerkByID(clerkID);
        this.rooms = rooms;
        this.price = price;
        priceLabel.setText("￥" + String.valueOf(price));
        checkInTimeButton.setText(initialTime);
        estimatedCheckOutTimeButton.setText(initialTime);
    }

    /**
     * 减号按钮结果，房客人数减一
     */
    @FXML
    private void minusPeopleNum() {
        int peopleNum = Integer.parseInt(peopleNumTextField.getText());
        if (peopleNum != 1) {
            peopleNumTextField.setText(String.valueOf((peopleNum - 1)));
        }
    }

    /**
     * 加号按钮结果，房客人数加一
     */
    @FXML
    private void addPeopleNum() {
        int peopleNum = Integer.parseInt(peopleNumTextField.getText());
        peopleNumTextField.setText(String.valueOf((peopleNum + 1)));
    }

    /**
     * 后退按钮结果，显示确认退出提示弹框
     */
    @FXML
    private void showConfirmExit() {
        stageController = new StageController();
        stageController.loadStage("util/ConfirmExit.fxml", 1);
        ConfirmExitController controller = (ConfirmExitController) stageController.getController();
        controller.setToBeClosed(resource);
    }

    /**
     * 入住时间按钮结果，显示日期选择弹窗
     */
    @FXML
    private void showCheckInDate() {
        stageController = new StageController();
        stageController.loadStage("util/SelectTimeView.fxml", 0.8);
        SelectTimeViewController selectTimeViewController = (SelectTimeViewController) stageController.getController();
        selectTimeViewController.setToBeSet(resource, "checkIn");
        selectTimeViewController.init();
    }

    /**
     * 预计离开时间按钮结果，显示日期选择弹窗
     */
    @FXML
    private void showExpectedCheckOutDate() {
        stageController = new StageController();
        stageController.loadStage("util/SelectTimeView.fxml", 0.8);
        SelectTimeViewController selectTimeViewController = (SelectTimeViewController) stageController.getController();
        selectTimeViewController.setToBeSet(resource, "checkOut");
        selectTimeViewController.init();
    }


    /**
     * 根据选择的日期回显入住时间
     *
     * @param time
     */
    public void setCheckInTime(String time) {
        checkInTimeButton.setText(time);
    }

    /**
     * 根据选择的日期回显预计离开时间
     *
     * @param time
     */
    public void setExpectedCheckOutTime(String time) {
        estimatedCheckOutTimeButton.setText(time);
        Timestamp estimatedLeaveTime = Timestamp.valueOf(time + " 00:00:00");
        Timestamp checkInTime = Timestamp.valueOf(checkInTimeButton.getText() + " 00:00:00");
        long oneDay = 1000 * 60 * 60 * 24;
        long days = (estimatedLeaveTime.getTime() - checkInTime.getTime()) / oneDay;
        if (days < 0) {
            priceLabel.setText("请选择正确日期！");
        } else {
            priceLabel.setText("￥" + String.valueOf(price * days));
        }
    }

    /**
     * 创建按钮结果，创建线下订单
     */
    @FXML
    private void createOrder() throws RemoteException {

        if (customerNameTextField.getText().equals("") || customerIDTextField.getText().equals("") || customerPhoneTextField.getText().equals("")) {
            //信息填写不完整
            this.returnMessage("信息填写不完整！");
        } else {
            processOrder = new ProcessOrderController();
            String hotelName = clerkVO.hotelName;
            String hotelID = clerkVO.hotelID;
            String customerName = customerNameTextField.getText();
            String customerID = customerIDTextField.getText();
            String phone = customerPhoneTextField.getText();
            int numOfCustomers = Integer.parseInt(peopleNumTextField.getText());
            boolean haveChildren = childCheckBox.isSelected();
            double actualPrice = Double.parseDouble(priceLabel.getText().substring(1));
            Timestamp checkInTime = Timestamp.valueOf(checkInTimeButton.getText() + " 00:00:00");

            //System.out.print(checkInTime);

            Timestamp estimatedCheckOutTime = Timestamp.valueOf(estimatedCheckOutTimeButton.getText() + " 00:00:00");

            OrderVO newOrder = new OrderVO(customerName, phone, customerID, hotelID, hotelName,
                    checkInTime, checkInTime, estimatedCheckOutTime, rooms, numOfCustomers,
                    haveChildren, actualPrice, actualPrice, OrderStatus.EXECUTED);
            ResultMessage resultMessage = processOrder.createOrderOffline(newOrder);

            if (resultMessage.equals(ResultMessage.Order_CreateOrderSuccess)) {
                //创建订单成功
                hotelAdmin = new HotelAdminController();
                if (hotelAdmin.changeAvailableRoom(newOrder, -1).equals(ResultMessage.Hotel_changeAvailableRoomSuccess)
                        && hotelAdmin.changeOccupiedRoom(newOrder, 1).equals(ResultMessage.Hotel_changeOccupiedRoomSuccess)) {
                    stageController = this.returnMessage("创建订单成功！");
                    stageController.closeStage(resource);
                }
            } else if (resultMessage.equals(ResultMessage.DataFormatWrong)) {
                this.returnMessage("ID格式错误！");
            } else if (resultMessage.equals(ResultMessage.phoneFormatWrong)) {
                this.returnMessage("手机号格式错误！");
            } else if (resultMessage.equals(ResultMessage.Blank)) {
                this.returnMessage("信息填写不完整！");
            } else {
                this.returnMessage("未知错误！");
            }
        }


    }

    /**
     * 返回提示信息
     *
     * @param error
     * @return
     */
    private StageController returnMessage(String error) {
        stageController = new StageController();
        stageController.loadStage("util/ErrorBoxView.fxml", 0.8);
        ErrorBoxController errorBoxController = (ErrorBoxController) stageController.getController();
        errorBoxController.setLabel(error);
        return stageController;
    }

    /**
     * 退出按钮结果，退出程序
     */
    @FXML
    private void exit() {
        System.exit(0);
    }

}
