package ui.view.presentation.clerk;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import ui.view.presentation.util.ConfirmExitController;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;

import java.rmi.RemoteException;

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

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * initial方法，初始化界面
     */
    public void initial(String hotelID) throws RemoteException {
        this.hotelID = hotelID;
    }

    /**
     * 后退按钮结果，显示确认退出弹窗
     */
    @FXML
    private void showConfirmExit() {
        stageController = new StageController();
        if (roomTypeChoiceBox.getSelectionModel().selectedIndexProperty().intValue() == -1
                && roomPriceTextField.getText().equals("")
                && roomNumTextField.getText().equals("1")) {
            //未修改
            stageController.closeStage(resource);
        }
        stageController.loadStage("util/ConfirmExit.fxml", 1);
        ConfirmExitController controller = (ConfirmExitController) stageController.getController();
        controller.setToBeClosed(resource);
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
    private void confirmAddRoom(){

    }
}
