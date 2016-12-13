package ui.view.presentation.customer;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import ui.controller.HotelAdminController;
import ui.view.controllerservice.HotelAdmin;
import ui.view.presentation.PaneAdder;
import ui.view.presentation.StageController;
import ui.view.presentation.clerk.ClerkCreateOfflineOrderController;
import ui.view.presentation.clerk.ClerkSingleRoomController;
import ui.view.presentation.util.ControlledStage;
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
public class CustomerChooseRoomController implements ControlledStage {

    private StageController stageController;

    private String resource = "customer/CustomerChooseRoom.fxml";

    @FXML
    private Pane chooseRoomPane;


    //获取当前时间
    private Date date = new Date();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private Timestamp time = Timestamp.valueOf(dateFormat.format(date));

    private String hotelID;

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
    public void initial(String hotelID){
        this.hotelID = hotelID;
        hotelAdmin = new HotelAdminController();
        try {
            hotelVO = hotelAdmin.findByID(hotelID);
            List<RoomVO> roomVOs = hotelAdmin.getDailyRoomInfo(hotelID, time).room;
            int roomTypes = roomVOs.size();
            for (int i = 0; i < roomTypes; i++) {
                PaneAdder paneAdder = new PaneAdder();
                paneAdders.add(paneAdder);
                paneAdder.addPane(chooseRoomPane, "customer/CustomerSingleRoom.fxml", 0, 108 + i * 40);
                CustomerSingleRoomController customerSingleRoomController = (CustomerSingleRoomController) paneAdder.getController();
                customerSingleRoomController.initial(roomVOs.get(i));
            }
        }catch (RemoteException e){
            e.printStackTrace();
        }
    }

    /**
     * 确认按钮结果，选择对应的房间，回显至预订界面
     */
    @FXML
    private void confirmSelect() throws RemoteException {
        List<String> rooms = new ArrayList<String>();
        double totalPrice = 0;
        for (int i = 0; i < paneAdders.size(); i++) {
            CustomerSingleRoomController customerSingleRoomController = (CustomerSingleRoomController) paneAdders.get(i).getController();
            for (int j = 0; j < customerSingleRoomController.getRoomNum(); j++) {
                rooms.add(customerSingleRoomController.getRoomType());
                totalPrice += customerSingleRoomController.getPrice();
            }
        }

        stageController.closeStage(resource);
        CustomerReserveViewController customerReserveViewController = (CustomerReserveViewController) stageController.getController("customer/CustomerReserveView.fxml");
        customerReserveViewController.setRoomAndPrice(rooms.toArray(new String[rooms.size()]), totalPrice);
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
