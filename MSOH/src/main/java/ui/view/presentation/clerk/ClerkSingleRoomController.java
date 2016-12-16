package ui.view.presentation.clerk;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ControlledStage;
import vo.RoomVO;

import java.rmi.RemoteException;

/**
 * Created by ST on 2016/12/8.
 */
public class ClerkSingleRoomController implements ControlledStage {

    private StageController stageController;

    @FXML
    private Label roomTypeLabel;
    @FXML
    private Label roomLeftLabel;
    @FXML
    private Label roomNumLabel;
    @FXML
    private Label roomPriceLabel;

    private RoomVO roomVO;
    private int numOfLeftRooms = 0;

    /**
     * initial方法，初始化界面
     */
    public void initial(RoomVO roomVO) throws RemoteException {
        this.roomVO = roomVO;
        roomTypeLabel.setText(roomVO.roomType);
        roomLeftLabel.setText(String.valueOf(roomVO.leftRooms));
        roomPriceLabel.setText(String.valueOf(roomVO.price));
        numOfLeftRooms = roomVO.leftRooms;
    }


    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * 加号按钮结果，房间数量+1
     */
    @FXML
    private void addRoomNum() {
        int roomNum = Integer.parseInt(roomNumLabel.getText());
        if (roomNum < numOfLeftRooms) {
            roomNumLabel.setText(String.valueOf(roomNum + 1));
        }
    }

    /**
     * 减号按钮结果，房间数量-1
     */
    @FXML
    private void minusRoomNum() {
        int roomNum = Integer.parseInt(roomNumLabel.getText());
        if (roomNum > 0) {
            roomNumLabel.setText(String.valueOf(roomNum - 1));
        }
    }

    /**
     * get方法，返回房间类型
     *
     * @return
     */
    public String getRoomType() {
        return roomTypeLabel.getText();
    }

    /**
     * get方法，返回房间价格
     *
     * @return
     */
    public double getPrice() {
//        System.out.println(Double.parseDouble(roomPriceLabel.getText()));
//        System.out.println(Integer.parseInt(roomNumLabel.getText()));
        return Double.parseDouble(roomPriceLabel.getText());
    }

    /**
     * get方法，返回房间数量
     *
     * @return
     */
    public int getRoomNum() {
        return Integer.parseInt(roomNumLabel.getText());
    }
}
