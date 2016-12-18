package ui.view.presentation.clerk;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import ui.controller.HotelAdminController;
import ui.view.controllerservice.HotelAdmin;
import ui.view.presentation.util.ConfirmExitController;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ErrorBoxController;
import util.ResultMessage;
import vo.DailyRoomInfoVO;
import vo.RoomVO;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ST on 2016/11/29.
 */
public class ClerkModifyAvailableRoomsController implements ControlledStage {

    private StageController stageController;

    private static String resource = "clerk/ClerkModifyAvailableRooms.fxml";

    @FXML
    private TextField roomNumTextField;
    @FXML
    private TextField roomPriceTextField;
    @FXML
    private ChoiceBox roomTypeChoiceBox;

    private String hotelID;
    private ObservableList<String> roomType = FXCollections.observableArrayList("单人房", "标间", "大床房");
    private HotelAdmin hotelAdmin;

    //获取当前时间
    private Date date = new Date();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
    private Timestamp time = Timestamp.valueOf(dateFormat.format(date));

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * initial方法，初始化界面
     */
    public void initial(final String hotelID) throws RemoteException {
        this.hotelID = hotelID;
        hotelAdmin = new HotelAdminController();
        roomTypeChoiceBox.setItems(roomType);
        roomTypeChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                //根据选定房间类型，显示房间剩余数量
                String selectedType = (String) roomTypeChoiceBox.getSelectionModel().getSelectedItem();
                try {
                    int leftRoomNum = 0;
                    double roomPrice = 0;
                    if (hotelAdmin.getRoomInfo(hotelID, selectedType, time) != null) {
                        leftRoomNum = hotelAdmin.getRoomInfo(hotelID, selectedType, time).leftRooms;
                        roomPrice = hotelAdmin.getRoomInfo(hotelID, selectedType, time).price;
                    }
                    roomNumTextField.setText(String.valueOf(leftRoomNum));
                    roomPriceTextField.setText(String.valueOf(roomPrice));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 后退按钮结果，显示确认退出弹窗
     */
    @FXML
    private void showConfirmExit() {
        stageController = new StageController();
        if (!this.isModified()) {
            //未修改
            stageController.closeStage(resource);
        } else {
            stageController.loadStage("util/ConfirmExit.fxml", 1);
            ConfirmExitController controller = (ConfirmExitController) stageController.getController();
            controller.setToBeClosed(resource);
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
     * 确认添加按钮结果，添加房间
     */
    @FXML
    private void confirmAddRoom() throws RemoteException {
        if (!this.isModified()) {
            this.returnMessage("信息未修改！");
        } else {
            String selectedType = (String) roomTypeChoiceBox.getSelectionModel().getSelectedItem();
            int roomNum = Integer.parseInt(roomNumTextField.getText());
            double roomPrice = Double.parseDouble(roomPriceTextField.getText());
            List<RoomVO> roomVO = new ArrayList<RoomVO>();
            roomVO.add(new RoomVO(hotelID, selectedType, roomNum, roomPrice));
            DailyRoomInfoVO dailyRoomInfoVO = new DailyRoomInfoVO(hotelID, time, roomVO);

            hotelAdmin = new HotelAdminController();
            ResultMessage resultMessage = hotelAdmin.modifyDailyRoomInfo(dailyRoomInfoVO);
            if (resultMessage.equals(ResultMessage.Hotel_ModifyDailyRoomInfoSuccess)) {
                //修改每日房间信息成功
                stageController = this.returnMessage("修改成功！");
                stageController.closeStage(resource);
            } else {
                this.returnMessage("未知错误！");
            }
        }
    }

    /**
     * 返回信息是否被修改
     *
     * @return
     */
    private boolean isModified() {
        if ((roomTypeChoiceBox.getSelectionModel().selectedIndexProperty().intValue() == -1
                && roomPriceTextField.getText().equals("")
                && roomNumTextField.getText().equals("0"))) {
            //未修改
            return false;
        } else {
            return true;
        }
    }

    /**
     * 显示提示弹窗
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
