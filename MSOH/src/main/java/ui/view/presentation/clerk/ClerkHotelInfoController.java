package ui.view.presentation.clerk;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import ui.controller.HotelAdminController;
import ui.view.controllerservice.HotelAdmin;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;
import vo.HotelVO;
import vo.RoomVO;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;

/**
 * Created by ST on 2016/11/28.
 */
public class ClerkHotelInfoController implements ControlledStage {

    private StageController stageController;

    @FXML
    private Button checkCommentsButton;
    @FXML
    private Button addRoomButton;
    @FXML
    private Label hotelName;
    @FXML
    private Label roomNum;
    @FXML
    private Label hotelScore;
    @FXML
    private ChoiceBox roomTypeChoiceBox;

    private String clerkID;
    private HotelAdmin hotelAdmin;
    private HotelVO hotelVO;
    private DecimalFormat df = new DecimalFormat("0.0");

    //获取当前时间
    private Date date = new Date();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
    private Timestamp time = Timestamp.valueOf(dateFormat.format(date));

    //当前酒店房间信息
    private ObservableList<String> roomType = FXCollections.observableArrayList();
    private List<String> leftRooms = new ArrayList<String>();

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * initial方法，初始化界面
     */
    public void initial(String ID) throws RemoteException {
        clerkID = ID;
        //显示酒店名称
        hotelAdmin = new HotelAdminController();
        hotelVO = hotelAdmin.findByClerkID(clerkID);
        hotelName.setText(hotelVO.hotelName);
        //显示酒店评分
        hotelScore.setText(df.format(hotelVO.score) + "分");
        //加载酒店房间类型列表
        for (RoomVO room : hotelAdmin.getDailyRoomInfo(hotelVO.hotelID, time).room) {
            roomType.add(room.roomType);
            leftRooms.add(String.valueOf(room.leftRooms));
        }
        roomTypeChoiceBox.setItems(roomType);
        //设置提示信息
        roomTypeChoiceBox.setTooltip(new Tooltip("选择房间类型"));
        //设置ChoicebBox监听
        roomTypeChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                int index = roomTypeChoiceBox.getSelectionModel().selectedIndexProperty().intValue();
                roomNum.setText(leftRooms.get(index));
            }
        });
    }

    /**
     * 查看详细评价按钮结果，显示酒店详细评价页面
     */
    @FXML
    private void showHotelComments() throws RemoteException {
        stageController = new StageController();
        stageController.loadStage("clerk/ClerkHotelComments.fxml", 1);
        ClerkHotelCommentsController clerkHotelCommentsController = (ClerkHotelCommentsController) stageController.getController();
        clerkHotelCommentsController.initial(hotelVO.hotelID);
    }

    /**
     * 录入客房按钮结果，显示录入可用客房界面
     */
    @FXML
    private void showModifyAvailableRooms() throws RemoteException {
        stageController = new StageController();
        stageController.loadStage("clerk/ClerkModifyAvailableRooms.fxml", 1);

        ClerkModifyAvailableRoomsController clerkModifyAvailableRoomsController = (ClerkModifyAvailableRoomsController)stageController.getController();
        clerkModifyAvailableRoomsController.initial(hotelVO.hotelID);
    }

    /**
     * 修改信息按钮结果，显示修改酒店信息界面
     */
    @FXML
    private void showModifyHotelInfo() throws RemoteException {
        stageController = new StageController();
        stageController.loadStage("clerk/ClerkModifyHotelInfo.fxml", 1);
        ClerkModifyHotelInfoController clerkModifyHotelInfoController = (ClerkModifyHotelInfoController)stageController.getController();
        clerkModifyHotelInfoController.initial(hotelVO.hotelID);
    }

}
