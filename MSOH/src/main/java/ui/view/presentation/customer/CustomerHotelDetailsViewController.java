package ui.view.presentation.customer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
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
import ui.view.presentation.PaneAdder;
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
    private Label areaLabel;

    @FXML
    private Label addressLabel;

    @FXML
    private Label starLabel;

    @FXML
    private TextArea briefInfoTextArea;

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

    /**
     * 关闭酒店详情界面，返回上一界面
     */
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
        checkInTimeTextField.setText(checkInTime);
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

    /**
     * 修改日期信息，重新显示房间信息
     */
    @FXML
    private void resetRoomInfo(){
        if(!checkInTimeTextField.getText().equals("") && !checkOutTimeTextField.getText().equals("")) {
            roomInfoScrollPane.getChildren().clear();
            addRoomTypePane();
        }
    }

    /**
     * 预定酒店酒店方法，跳转至预订界面
     */
    @FXML
    private void reserve(){
        HotelVO newHotelVO = new HotelVO(hotelID, hotelVO.hotelName, checkInTimeTextField.getText(), checkOutTimeTextField.getText());
        stageController = new StageController();
        stageController.loadStage("customer/CustomerReserveView.fxml", 1);
        CustomerReserveViewController customerReserveViewController = (CustomerReserveViewController) stageController.getController();
        customerReserveViewController.init(customerID, newHotelVO);

    }

    /**
     * 酒店详情界面初始化方法
     * @param customerID
     * @param hotelID
     */
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
        checkInTimeTextField.setText(hotelVO.checkInTime);
        checkOutTimeTextField.setText(hotelVO.checkOutTime);
        if(checkInTimeTextField.getText() != null && checkOutTimeTextField != null) {
            addRoomTypePane();
        }
        addPromotionPane();
        addCommentPane();
        addOrderPane();
    }

    /**
     * 设置酒店信息
     */
    private void setInfo(){
        hotelNameLabel.setText(hotelVO.hotelName);
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
        briefInfoTextArea.setText(hotelVO.intro);
        scoreLabel.setText(hotelVO.score + "");

        }

    /**
     * 初始化酒店评论面板
     */
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

    /**
     * 初始化酒店促销策略面板
     */
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

    /**
     * 初始化酒店房间信息面板
     */
    private void addRoomTypePane() {
        Timestamp checkInTime = Timestamp.valueOf(checkInTimeTextField.getText() + " 00:00:00");
        Timestamp checkOutTime = Timestamp.valueOf(checkOutTimeTextField.getText() + " 00:00:00");

        //// TODO: 2016/12/10  获得选择区间内房间类型的数量和价格
        try {
            HotelAdmin hotelAdmin = new HotelAdminController();
            hotelVO = hotelAdmin.findByID(hotelID);
            List<RoomVO> roomVOs = hotelAdmin.getDailyRoomInfo(hotelID, checkInTime).room;
            int roomTypes = roomVOs.size();
            int count = 0;

            HotelInfo hotelInfo = new HotelInfoController();
            for (int i = 0; i < roomTypes; i++) {
                RoomVO roomVO = hotelInfo.getBewteenDate(hotelID, roomVOs.get(i).roomType, checkInTime, checkOutTime);
                addPane("CustomerSingleRoomTypeView.fxml", 45, 75 + 35 * count, roomInfoScrollPane, 3, null, null, roomVO, null);
                count++;
            }
        }catch (RemoteException e){
            e.printStackTrace();
        }
    }

    /**
     *初始化历史订单面板
     */
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

    /**
     * 添加单个面板
     * @param resource 需要添加的面板类型
     * @param x 横坐标
     * @param y 纵坐标
     * @param sourcePane 添加面板的目标pane
     * @param type 类型标识
     * @param commentVO
     * @param promotionID
     * @param roomVO
     * @param orderID
     */
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

    /**
     * 退出系统
     */
    @FXML
    private void exit() {
        System.exit(0);
    }
}
