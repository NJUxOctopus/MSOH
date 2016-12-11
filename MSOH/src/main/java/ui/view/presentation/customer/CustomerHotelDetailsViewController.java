package ui.view.presentation.customer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import ui.controller.EditPromotionController;
import ui.controller.HotelAdminController;
import ui.controller.HotelInfoController;
import ui.controller.ProcessOrderController;
import ui.view.controllerservice.EditPromotion;
import ui.view.controllerservice.HotelAdmin;
import ui.view.controllerservice.HotelInfo;
import ui.view.controllerservice.ProcessOrder;
import ui.view.presentation.clerk.ClerkChooseRoomController;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;
import ui.view.presentation.util.SelectTimeViewController;
import vo.*;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by island on 2016/11/24.
 */
public class CustomerHotelDetailsViewController implements ControlledStage {
    StageController stageController;

    private String resource = "customer/CustomerHotelDetailsView.fxml";

    private String customerID;

    private String hotelID;

    private HotelVO hotelVO;

    private CustomerSingleCommentViewController customerSingleCommentViewController;

    private CustomerSinglePromotionViewController customerSinglePromotionViewController;

    private CustomerSingleRoomTypeViewController customerSingleRoomTypeViewController;

    private CustomerSingleHotelOrderViewController customerSingleHotelOrderViewController;

    @FXML
    private ImageView background;

    @FXML
    private Button backButton;

    @FXML
    private Button checkInTimeButton;

    @FXML
    private Button checkOutTimeButton;

    @FXML
    private TextField checkInTimeTextField;

    @FXML
    private TextField checkOutTimeTextField;

    @FXML
    private Button confirmButton;

    @FXML
    private Button reserveButton;

    @FXML
    private AnchorPane promotionScrollPane;

    @FXML
    private AnchorPane commentScrollPane;

    @FXML
    private AnchorPane historyOrderScrollPane;

    @FXML
    private AnchorPane roomInfoScrollPane;

    @FXML
    private Label hotelNameLabel;

    @FXML
    private Label cityLabel;

    @FXML
    private Label areaLabel;

    @FXML
    private Label addressLabel;

    @FXML
    private Label starLabel;

    @FXML
    private Label briefInfoLabel;

    @FXML
    private Label facilityLabel;

    @FXML
    private Label scoreLabel;

    @FXML
    private Label emptyRoomLabel;

    @FXML
    private Label emptyPromotionLabel;

    @FXML
    private Label emptyCommentLabel;

    @FXML
    private Label emptyOrderLabel;


    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

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

    @FXML
    private void resetRoomInfo(){
        roomInfoScrollPane.getChildren().clear();
        addRoomTypePane();
    }

    @FXML
    private void reserve(){
        HotelVO newHotelVO = new HotelVO(hotelID, hotelVO.hotelName, checkInTimeTextField.getText(), checkOutTimeTextField.getText());
        stageController = new StageController();
        stageController.loadStage("clerk/ClerkChooseRoom.fxml", 1);
        ClerkChooseRoomController clerkChooseRoomController = (ClerkChooseRoomController)stageController.getController();
        try {
            clerkChooseRoomController.initial(hotelID);
        }catch (RemoteException e){
            e.printStackTrace();
        }
    }

    public void init(String customerID, String hotelID){
        this.customerID = customerID;
        this.hotelID = hotelID;
        HotelAdmin hotelAdmin = new HotelAdminController();
        try {
            hotelVO = hotelAdmin.findByID(hotelID);
        }catch (RemoteException e){
            e.printStackTrace();
        }
        setInfo();
        addRoomTypePane();
        addPromotionPane();
        addCommentPane();
        addOrderPane();
    }

    private void setInfo(){
        hotelNameLabel.setText(hotelVO.hotelName);
        cityLabel.setText(hotelVO.city);
        areaLabel.setText(hotelVO.area);
        addressLabel.setText(hotelVO.hotelAddress);
        String star = "";
        for(int i = 0; i < hotelVO.star; i++){
            star += "★";
        }
        starLabel.setText(star);
        String facility = "";
        for(int i = 0; i < hotelVO.infra.length; i++){
            facility += hotelVO.infra[i] + ";";
        }
        facilityLabel.setText(facility);
        briefInfoLabel.setText(hotelVO.intro);
        scoreLabel.setText(hotelVO.score + "");
    }

    private void addCommentPane(){
        List<CommentVO> commentVOList = hotelVO.comment;
        if(commentVOList.isEmpty()){
            emptyCommentLabel.setOpacity(1);
        }else {
            for(int i = 0; i < commentVOList.size(); i++) {
                addPane("CustomerSingleCommentView.fxml", 45 , 35 + 55 * i, commentScrollPane, 1, commentVOList.get(i), null, null, null);
            }
        }
    }

    private void addPromotionPane(){
        EditPromotion editPromotion = new EditPromotionController();
        Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Timestamp timestamp = Timestamp.valueOf(df.format(today));
        try {
            List<PromotionVO> promotionVOList = editPromotion.getPromotionByHotelID(hotelID, timestamp);
            if(promotionVOList.isEmpty()){
                emptyPromotionLabel.setOpacity(1);
            }else {
                for(int i = 0; i < promotionVOList.size(); i++) {
                    addPane("CustomerSinglePromotionView.fxml", 45 , 70 + 35 * i, promotionScrollPane, 2, null, promotionVOList.get(i).promotionID, null, null);
                }
            }
        }catch (RemoteException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void addRoomTypePane(){
        String[] roomType = hotelVO.roomType;
        //hotelVO.dailyRoomInfo.room
        //// TODO: 2016/12/10  获得选择区间内房间类型的数量和价格
        int singleBed = 0;
        int twoBed = 0;
        int kingBed = 0;
        int[] roomNum = {0, 0, 0};
        String[] room = {"单人房", "标间", "大床房"};
        if(roomType.length == 0){
            emptyRoomLabel.setOpacity(1);
        }else {
            for(int i = 0; i < roomType.length; i++) {
                if(roomType[i].equals(room[0])){
                    roomNum[0]++;
                }else if(roomType[i].equals(room[1])){
                    roomNum[1]++;
                }else if(roomType[i].equals(room[2])){
                    roomNum[2]++;
                }
            }
            int count = 0;
            for(int j = 0; j < 3; j++){
                if(roomNum[j] != 0){
                    addPane("CustomerSingleRoomTypeView.fxml", 45 , 75 + 35 * count, roomInfoScrollPane, 4, null, null, new RoomVO(), null);
                    count++;
                }
            }

        }
    }

    private void addOrderPane(){
        ProcessOrder processOrder = new ProcessOrderController();
        try {
            List<OrderVO> orderVOList = processOrder.getOrderByIDAndHotelID(customerID, hotelID);
            if(orderVOList.isEmpty()){
                emptyOrderLabel.setOpacity(1);
            }else {
                for(int i = 0; i < orderVOList.size(); i++) {
                    addPane("CustomerSingleHotelOrderView.fxml", 45 , 40 + 125 * i, historyOrderScrollPane, 4, null, null, null, orderVOList.get(i).orderID);
                }
            }
        }catch (RemoteException e){
            e.printStackTrace();
        }
    }

    private void addPane(String resource, int x, int y, AnchorPane sourcePane, int type, CommentVO commentVO, String promotionID, RoomVO roomVO, String orderID){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(resource));
            Pane singlePane = (Pane) loader.load();
            sourcePane.getChildren().add(singlePane);
            singlePane.setLayoutX(x);
            singlePane.setLayoutY(y);
            if(type == 1){
                customerSingleCommentViewController = loader.getController();
                customerSingleCommentViewController.init(commentVO);
            }
            if(type == 2){
                customerSinglePromotionViewController = loader.getController();
                customerSinglePromotionViewController.init(promotionID);
            }
            if(type == 3){
                customerSingleRoomTypeViewController = loader.getController();
                customerSingleRoomTypeViewController.init();
            }
            if(type == 4){
                customerSingleHotelOrderViewController = loader.getController();
                customerSingleHotelOrderViewController.init(customerID, orderID);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
