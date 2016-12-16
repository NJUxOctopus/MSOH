package ui.view.presentation.clerk;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import ui.controller.EditPromotionController;
import ui.controller.HotelAdminController;
import ui.view.controllerservice.EditPromotion;
import ui.view.controllerservice.HotelAdmin;
import ui.view.presentation.PaneAdder;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;
import util.PromotionType;
import vo.PromotionVO;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by ST on 2016/12/1.
 */
public class ClerkHotelPromotionController implements ControlledStage {

    private StageController stageController;

    @FXML
    private Rectangle promotionSelectShade;
    @FXML
    private AnchorPane promotionListPane;

    private String resource = "clerk/ClerkHotelPromotion.fxml";

    private String clerkID;
    private String hotelID;
    private EditPromotion editPromotion;
    private HotelAdmin hotelAdmin;
    private List<PromotionVO> promotionVOs;

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
    public void initial(String clerkID) throws IOException, ClassNotFoundException {
        editPromotion = new EditPromotionController();
        hotelAdmin = new HotelAdminController();
        this.clerkID = clerkID;
        this.hotelID = hotelAdmin.findByClerkID(clerkID).hotelID;

        //默认显示所有生日特惠
        this.showBirthdayPromotion();
    }

    /**
     * 制定按钮结果，显示制定酒店促销策略界面
     */
    @FXML
    private void showCreatePromotion() throws RemoteException {
        stageController = new StageController();
        stageController.loadStage("clerk/ClerkCreateHotelPromotion.fxml", 1);
        ClerkCreateHotelPromotionController clerkCreateHotelPromotionController = (ClerkCreateHotelPromotionController) stageController.getController();
        clerkCreateHotelPromotionController.initial(clerkID);
    }

    /**
     * 生日特惠按钮结果，显示该酒店的所有生日特惠
     */
    @FXML
    private void showBirthdayPromotion() throws IOException, ClassNotFoundException {
        promotionSelectShade.setWidth(166);
        promotionSelectShade.setX(0);
        promotionVOs = editPromotion.getPromotionByTypeAndHotelID(PromotionType.HotelPromotion_Birthday, hotelID, time);
        this.addPane(promotionVOs);
    }

    /**
     * 节日特惠按钮结果，显示该酒店的所有节日特惠
     */
    @FXML
    private void showHolidayPromotion() throws IOException, ClassNotFoundException {
        promotionSelectShade.setWidth(148);
        promotionSelectShade.setX(166);
        promotionVOs = editPromotion.getPromotionByTypeAndHotelID(PromotionType.HotelPromotion_Holiday, hotelID, time);
        this.addPane(promotionVOs);

    }

    /**
     * 多订多惠按钮结果，显示该酒店的所有多订多惠
     */
    @FXML
    private void showReservePromotion() throws IOException, ClassNotFoundException {
        promotionSelectShade.setWidth(132);
        promotionSelectShade.setX(314);
        promotionVOs = editPromotion.getPromotionByTypeAndHotelID(PromotionType.HotelPromotion_Reserve, hotelID, time);
        this.addPane(promotionVOs);
    }

    /**
     * 合作企业优惠按钮结果，显示该酒店的所有合作企业优惠
     */
    @FXML
    private void showEnterprisePromotion() throws IOException, ClassNotFoundException {
        promotionSelectShade.setWidth(166);
        promotionSelectShade.setX(446);
        promotionVOs = editPromotion.getPromotionByTypeAndHotelID(PromotionType.HotelPromotion_Company, hotelID, time);
        this.addPane(promotionVOs);
    }

    /**
     * 其他优惠按钮结果，显示该酒店的所有其他优惠
     */
    @FXML
    private void showOtherPromotion() throws IOException, ClassNotFoundException {
        promotionSelectShade.setWidth(76);
        promotionSelectShade.setX(612);
        promotionVOs = editPromotion.getPromotionByTypeAndHotelID(PromotionType.HotelPromotion_Other, hotelID, time);
        this.addPane(promotionVOs);
    }

    /**
     * 把所有促销策略加到面板中
     *
     * @param promotionVOs
     */
    private void addPane(List<PromotionVO> promotionVOs) throws RemoteException {
        PromotionVO promotionVO;
        promotionListPane.getChildren().clear();
        PaneAdder paneAdder = new PaneAdder();
        int numOfPromotions = promotionVOs.size();
        promotionListPane.setPrefHeight(215 * numOfPromotions);
        for (int i = 0; i < numOfPromotions; i++) {
            promotionVO = promotionVOs.get(i);
            paneAdder.addPane(promotionListPane, "clerk/ClerkSinglePromotion.fxml", 0, 215 * i);
            ClerkSinglePromotionController clerkSinglePromotionController = (ClerkSinglePromotionController) paneAdder.getController();
            clerkSinglePromotionController.initial(promotionVO);
        }
    }

}
