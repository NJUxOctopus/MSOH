package ui.view.presentation.customer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import rmi.RemoteHelper;
import ui.controller.EditPromotionController;
import ui.controller.HotelAdminController;
import ui.controller.ProcessOrderController;
import ui.view.controllerservice.EditPromotion;
import ui.view.controllerservice.HotelAdmin;
import ui.view.controllerservice.ProcessOrder;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ImageController;
import util.ImageHelper;
import util.OrderStatus;
import vo.DailyRoomInfoVO;
import vo.HotelVO;
import vo.PromotionVO;
import vo.RoomVO;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by island on 2016/11/30.
 */
public class CustomerSingleHotelViewController implements ControlledStage {
    StageController stageController = new StageController();

    private String customerID;

    private String hotelID;

    @FXML
    private Button hotelButton;

    @FXML
    private ImageView hotelPicture;

    @FXML
    private Label scoreLabel;

    @FXML
    private Label starLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label numOfCommentLabel;

    @FXML
    private Button normalOrderButton;

    @FXML
    private Button abnormalOrderButton;

    @FXML
    private Button canceledOrderButton;

    @FXML
    private Button reserveButton;

    @FXML
    private Button detailButton;

    @FXML
    private ImageView reservedImage;

    @FXML
    private ImageView hotelImage;

    @FXML
    private void viewHotelDetails(){
        stageController = new StageController();
        stageController.loadStage("customer/CustomerHotelDetailsView.fxml", 1);
        CustomerHotelDetailsViewController customerHotelDetailsViewController = (CustomerHotelDetailsViewController) stageController.getController();
        customerHotelDetailsViewController.init(customerID, hotelID);
    }

    @FXML
    private void reserve(){
        stageController = new StageController();
        stageController.loadStage("customer/CustomerReserveView.fxml", 1);
        CustomerReserveViewController customerReserveViewController = (CustomerReserveViewController) stageController.getController();
        HotelAdmin hotelAdmin = new HotelAdminController();
        try {
            customerReserveViewController.init(customerID, hotelAdmin.findByID(hotelID));
        }catch (RemoteException e){
            e.printStackTrace();
        }
    }

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * 单个酒店面板的初始化方法
     * @param customerID
     * @param hotelID
     */
    public void init(String customerID, String hotelID){
        this.customerID = customerID;
        this.hotelID = hotelID;

        setOrderInfo(customerID, hotelID);
        setHotelInfo(hotelID);
    }

    /**
     * 设置酒店相关信息
     * @param hotelID
     */
    private void setHotelInfo(String hotelID){
        HotelAdmin hotelAdmin = new HotelAdminController();
        try {
            HotelVO hotelVO = hotelAdmin.findByID(hotelID);
            hotelButton.setText(hotelVO.hotelName);
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Timestamp time = Timestamp.valueOf(dateFormat.format(date) + " 00:00:00");
            //评价信息
            numOfCommentLabel.setText(hotelVO.comment.size() + "");
            DecimalFormat df = new DecimalFormat("0.0");
            scoreLabel.setText(df.format(hotelVO.score) + "");

            //星级信息
            String star = "";
            for(int i = 0; i < hotelVO.star; i++){
                star += "★";
            }
            starLabel.setText(star);

            //房间信息
            List<RoomVO> roomVOList = hotelAdmin.getDailyRoomInfo(hotelID, time).room;
            if(!roomVOList.isEmpty()) {
                double price = roomVOList.get(0).price;
                for (int i = 1; i < roomVOList.size(); i++) {
                    if (price > roomVOList.get(i).price)
                        price = roomVOList.get(i).price;
                }
                priceLabel.setText(price + "");
            }
            else{
                priceLabel.setText("0");
            }

            //预订过的标签
            boolean isReserved = hotelAdmin.hotelIsReserverd(customerID, hotelID);
            if(isReserved){
                reservedImage.setOpacity(1);
            }else{
                reservedImage.setOpacity(0);
            }

            //酒店图片
            ImageController imageController = new ImageController();
            WritableImage wr = imageController.loadImage(hotelVO.picUrls[0], 200, 150);
            hotelImage.setImage(wr);
        } catch (RemoteException e){
            e.printStackTrace();
        }

    }


    /**
     * 设置该用户的酒店订单信息
     * @param customerID
     * @param hotelID
     */
    private void setOrderInfo(String customerID, String hotelID){
        ProcessOrder processOrder = new ProcessOrderController();
        try {
            normalOrderButton.setText(processOrder.getOrderByIDAndHotelIDAndStatus(customerID, hotelID, OrderStatus.FINISHED_EVALUATED).size() + "");
            abnormalOrderButton.setText(processOrder.getOrderByIDAndHotelIDAndStatus(customerID, hotelID, OrderStatus.ABNORMAL).size() + "");
            canceledOrderButton.setText(processOrder.getOrderByIDAndHotelIDAndStatus(customerID, hotelID, OrderStatus.REVOKED).size() + "");
        }catch (RemoteException e){
            e.printStackTrace();
        }
    }
}
