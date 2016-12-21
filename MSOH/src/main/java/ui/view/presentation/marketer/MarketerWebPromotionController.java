package ui.view.presentation.marketer;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import ui.controller.EditPromotionController;
import ui.view.controllerservice.EditPromotion;
import ui.view.presentation.PaneAdder;
import ui.view.presentation.clerk.ClerkSinglePromotionController;
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
public class MarketerWebPromotionController implements ControlledStage {

    private StageController stageController;

    @FXML
    private Rectangle promotionSelectShade;
    @FXML
    private Pane promotionListPane;

    private String marketerID;
    private EditPromotion editPromotion;
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
    public void initial(String marketerID) throws IOException, ClassNotFoundException {
        editPromotion = new EditPromotionController();
        this.marketerID = marketerID;

        //默认显示所有节日特惠
        this.showHolidayPromotion();

    }

    /**
     * 制定按钮结果，显示制定网站促销策略界面
     */
    @FXML
    private void showCreatePromotion() throws RemoteException {
        stageController = new StageController();
        stageController.loadStage("marketer/MarketerCreateWebPromotion.fxml", 1);

        MarketerCreateWebPromotionController marketerCreateWebPromotionController = (MarketerCreateWebPromotionController) stageController.getController();
        marketerCreateWebPromotionController.initial(marketerID);
    }

    /**
     * 节日特惠按钮结果，显示网站所有节日特惠
     */
    @FXML
    private void showHolidayPromotion() throws IOException, ClassNotFoundException {
        promotionListPane.getChildren().clear();
        promotionSelectShade.setWidth(215);
        promotionSelectShade.setX(0);
        promotionVOs = editPromotion.getPromotionByType(PromotionType.WebPromotion_Holiday, time);
        this.addPane(promotionVOs);
    }

    /**
     * VIP特惠按钮结果，显示所有网站VIP特惠
     */
    @FXML
    private void showVIPPromotion() throws IOException, ClassNotFoundException {
        promotionListPane.getChildren().clear();
        promotionSelectShade.setWidth(237);
        promotionSelectShade.setX(215);
        promotionVOs = editPromotion.getPromotionByType(PromotionType.WebPromotion_VIP, time);
        this.addPane(promotionVOs);
    }

    /**
     * 其他特惠按钮结果，显示所有网站其他特惠
     */
    @FXML
    private void showOtherPromotion() throws IOException, ClassNotFoundException {
        promotionListPane.getChildren().clear();
        promotionSelectShade.setWidth(237);
        promotionSelectShade.setX(452);
        promotionVOs = editPromotion.getPromotionByType(PromotionType.WebPromotion_Other, time);
//        System.out.print("其他优惠：" + promotionVOs.size());
        this.addPane(promotionVOs);
    }

    /**
     * 把所有促销策略加到面板中
     *
     * @param promotionVOs
     */
    private void addPane(List<PromotionVO> promotionVOs) throws RemoteException {
        PromotionVO promotionVO;
        PaneAdder paneAdder = new PaneAdder();
        int numOfPromotions = promotionVOs.size();
        promotionListPane.setPrefHeight(215 * numOfPromotions);
        for (int i = 0; i < numOfPromotions; i++) {
            promotionVO = promotionVOs.get(i);
            paneAdder.addPane(promotionListPane, "marketer/MarketerSinglePromotion.fxml", 0, 215 * i);
            MarketerSinglePromotionController marketerSinglePromotionController = (MarketerSinglePromotionController) paneAdder.getController();
            marketerSinglePromotionController.initial(promotionVO);
        }
    }
}
