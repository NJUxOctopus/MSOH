package ui.view.presentation.customer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import ui.controller.EditPromotionController;
import ui.controller.HotelAdminController;
import ui.view.controllerservice.EditPromotion;
import ui.view.controllerservice.HotelAdmin;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;
import vo.DailyRoomInfoVO;
import vo.HotelVO;
import vo.PromotionVO;
import vo.RoomVO;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.DateFormat;
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
    private Label discountLabel;

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
    private void viewHotelDetails(){
        stageController = new StageController();
        stageController.loadStage("customer/CustomerHotelDetailsView.fxml", 1);
        CustomerHotelDetailsViewController customerHotelDetailsViewController = (CustomerHotelDetailsViewController) stageController.getController();
        customerHotelDetailsViewController.init();
    }

    @FXML
    private void viewNormalOrder(){

    }

    @FXML
    private void viewAbnormalOrder(){

    }

    @FXML
    private void viewCanceledOrderButton(){

    }

    @FXML
    private void reserve(){
        stageController = new StageController();
        stageController.loadStage("customer/CustomerReserveView.fxml", 1);
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
        setHotelInfo(hotelID);
        setHotelPromotion(hotelID);
        setOrderInfo(customerID, hotelID);
        hotelButton.setText("");
        scoreLabel.setText("");
        discountLabel.setText("");
        priceLabel.setText("");
        numOfCommentLabel.setText("");
        normalOrderButton.setText("");
        abnormalOrderButton.setText("");
        canceledOrderButton.setText("");
        //setHotelInfo(hotelID);
        //setHotelPromotion(hotelID);
        //setOrderInfo(customerID, hotelID);
    }

    /**
     * 设置酒店相关信息
     * @param hotelID
     */
    private void setHotelInfo(String hotelID){
        HotelAdmin hotelAdmin = new HotelAdminController();
        try {
            HotelVO hotelVO = hotelAdmin.getByID(hotelID);
            hotelButton.setText(hotelVO.hotelName);
            scoreLabel.setText(hotelVO.score + "");


            List<RoomVO> roomVOList = hotelVO.dailyRoomInfo.room;
            double price = roomVOList.get(0).price;;
            for(int i = 1; i < roomVOList.size(); i++){
                if(price > roomVOList.get(i).price)
                    price = roomVOList.get(i).price;
            }
            priceLabel.setText(price + "");

            numOfCommentLabel.setText(hotelVO.comment.size() + "");
            normalOrderButton.setText("");
            abnormalOrderButton.setText("");
            canceledOrderButton.setText("");
        } catch (RemoteException e){
            e.printStackTrace();
        }
    }

    /**
     * 设置促销相关信息
     * @param hotelID
     */
    private void setHotelPromotion(String hotelID){
        EditPromotion editPromotion = new EditPromotionController();
        try {
            List<PromotionVO> promotionVOList = editPromotion.getPromotionByHotelID(hotelID);
            double minDiscount = promotionVOList.get(0).discount;
            for(int i = 1; i < promotionVOList.size(); i++){
                if(minDiscount > promotionVOList.get(i).discount)
                    minDiscount = promotionVOList.get(i).discount;
            }
            discountLabel.setText(minDiscount + "");

        }catch (RemoteException e1) {
            e1.printStackTrace();
        }catch (IOException e2){
            e2.printStackTrace();
        }catch (ClassNotFoundException e3){
            e3.printStackTrace();
        }
    }

    /**
     * 设置该用户的酒店订单信息
     * @param customerID
     * @param hotelID
     */
    private void setOrderInfo(String customerID, String hotelID){

    }
}
