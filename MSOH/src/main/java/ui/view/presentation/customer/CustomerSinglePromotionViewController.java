package ui.view.presentation.customer;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ui.controller.EditPromotionController;
import ui.view.controllerservice.EditPromotion;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;
import util.MemberType;
import vo.PromotionVO;

import java.rmi.RemoteException;

/**
 * Created by island on 2016/11/30.
 */
public class CustomerSinglePromotionViewController implements ControlledStage {
    StageController stageController = new StageController();

    @FXML
    private Label promotionNameLabel;

    @FXML
    private Label discountLabel;

    @FXML
    private Label staffLabel;

    @FXML
    private Label timeLabel;


    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    public void init(String promotionID){
        try{
            EditPromotion editPromotion = new EditPromotionController();
            PromotionVO promotionVO = editPromotion.getSingle(promotionID);
            promotionNameLabel.setText(promotionVO.promotionName);
            discountLabel.setText(promotionVO.discount + "");
            if(promotionVO.targetUser == MemberType.ENTREPRISE) {
                staffLabel.setText("企业会员");
            }
            if(promotionVO.targetUser == MemberType.NONMEMBER) {
                staffLabel.setText("非会员");
            }
            if(promotionVO.targetUser == MemberType.NORMAL) {
                staffLabel.setText("普通会员");
            }

            timeLabel.setText(promotionVO.startTime.toString().substring(0,10) + "~" + promotionVO.endTime.toString().substring(0, 10));
        }catch (RemoteException e){
            e.printStackTrace();
        }
    }

}