package ui.view.presentation.clerk;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import ui.controller.HotelAdminController;
import ui.view.controllerservice.HotelAdmin;
import ui.view.presentation.PaneAdder;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ControlledStage;
import vo.DailyRoomInfoVO;
import vo.HotelVO;
import vo.RoomVO;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ST on 2016/12/7.
 */
public class ClerkChooseRoomController implements ControlledStage {

    private StageController stageController;

    //    @FXML
//    private Label singleRoomLeftLabel;
//    @FXML
//    private Label doubleRoomLeftLabel;
//    @FXML
//    private Label kingRoomLeftLabel;
//    @FXML
//    private Label singleRoomNumLabel;
//    @FXML
//    private Label doubleRoomNumLabel;
//    @FXML
//    private Label kingRoomNumLabel;
    @FXML
    private Pane chooseRoomPane;

    private String resource = "clerk/ClerkChooseRoom.fxml";

    //获取当前时间
    private Date date = new Date();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
    private Timestamp time = Timestamp.valueOf(dateFormat.format(date));

    private String hotelID;
    private String clerkID;
    private HotelVO hotelVO;
    private HotelAdmin hotelAdmin;
    private List<PaneAdder> paneAdders = new ArrayList<PaneAdder>();

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * initial方法，初始化界面
     */
    public void initial(String hotelID) throws RemoteException {
        this.hotelID = hotelID;
        hotelAdmin = new HotelAdminController();
        hotelVO = hotelAdmin.findByID(hotelID);
        clerkID = hotelVO.clerkID;
        List<RoomVO> roomVOs = hotelAdmin.getDailyRoomInfo(hotelID, time).room;
        int roomTypes = roomVOs.size();
        for (int i = 0; i < roomTypes; i++) {
            PaneAdder paneAdder = new PaneAdder();
            paneAdders.add(paneAdder);
            paneAdder.addPane(chooseRoomPane, "clerk/ClerkSingleRoom.fxml", 0, 108 + i * 40);
            ClerkSingleRoomController clerkSingleRoomController = (ClerkSingleRoomController) paneAdder.getController();
            clerkSingleRoomController.initial(roomVOs.get(i));
        }
    }

    /**
     * 确认按钮结果，选择对应的房间，进入信息填写页面
     */
    @FXML
    private void confirmSelect() throws RemoteException {
        List<String> rooms = new ArrayList<String>();
        double totalPrice = 0;
//        String[] rooms;
        for (int i = 0; i < paneAdders.size(); i++) {
            ClerkSingleRoomController clerkSingleRoomController = (ClerkSingleRoomController) paneAdders.get(i).getController();
            for (int j = 0; j < clerkSingleRoomController.getRoomNum(); j++) {
                rooms.add(clerkSingleRoomController.getRoomType());
                totalPrice += clerkSingleRoomController.getPrice();
            }
        }
        stageController = new StageController();
        stageController.loadStage("clerk/ClerkCreateOfflineOrder.fxml", 1);
        ClerkCreateOfflineOrderController clerkCreateOfflineOrderController = (ClerkCreateOfflineOrderController) stageController.getController();
        String[] roomList = new String[rooms.size()];
        for (int i = 0; i < rooms.size(); i++) {
            roomList[i] = rooms.get(i);
        }
        clerkCreateOfflineOrderController.initial(clerkID, roomList, totalPrice);
        stageController.closeStage(resource);
    }

    /**
     * 取消按钮结果，关闭弹窗
     */
    @FXML
    private void cancelSelect() {
        stageController = new StageController();
        stageController.closeStage(resource);
    }

}
