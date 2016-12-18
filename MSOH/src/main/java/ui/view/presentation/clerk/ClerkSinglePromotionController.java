package ui.view.presentation.clerk;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ui.controller.UserAdminController;
import ui.view.controllerservice.UserAdmin;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;
import util.PromotionType;
import vo.PromotionVO;

import java.rmi.RemoteException;

/**
 * Created by ST on 2016/11/30.
 */
public class ClerkSinglePromotionController implements ControlledStage {

    private StageController stageController;

    @FXML
    private Label promotionNameLabel;
    @FXML
    private Label targetMemberLabel;
    @FXML
    private Label startTimeLabel;
    @FXML
    private Label endTimeLabel;
    @FXML
    private Label enterpriseNameLabel;
    @FXML
    private Label roomNumLabel;
    @FXML
    private Label discountLabel;

    private PromotionVO promotionVO;
    private PromotionType promotionType;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * initial方法，初始化界面
     */
    public void initial(PromotionVO promotionVO) throws RemoteException {
        this.promotionVO = promotionVO;
        this.promotionType = promotionVO.promotionType;

        promotionNameLabel.setText(promotionVO.promotionName);
        String target = "";
        switch (promotionVO.targetUser) {
            case NONMEMBER:
                target = "所有客户";
                break;
            case NORMAL:
                target = "所有会员";
                break;
            case ENTREPRISE:
                target = "企业会员";
        }
        targetMemberLabel.setText(target);

        startTimeLabel.setText(String.valueOf(promotionVO.startTime).substring(0, 10));
        endTimeLabel.setText(String.valueOf(promotionVO.endTime).substring(0, 10));
        enterpriseNameLabel.setText(promotionVO.companyName == null ? "无" : promotionVO.companyName);
        roomNumLabel.setText(String.valueOf(promotionVO.minRoom));
        discountLabel.setText(String.valueOf(promotionVO.discount));
    }

    /**
     * 修改按钮结果，显示修改酒店促销策略界面
     */
    @FXML
    private void showModifyPromotion() throws RemoteException {
        stageController = new StageController();
        stageController.loadStage("clerk/ClerkModifyPromotion.fxml", 1);
        ClerkModifyPromotionController clerkModifyPromotionController = (ClerkModifyPromotionController) stageController.getController();
        clerkModifyPromotionController.initial(promotionVO);
    }

    /**
     * 删除按钮结果，显示确认删除提示弹窗
     */
    @FXML
    private void deletePromotion() throws RemoteException {
        stageController = new StageController();
        stageController.loadStage("clerk/ClerkConfirmDeletePromotion.fxml", 0.8);
        ClerkConfirmDeletePromotionController clerkConfirmDeletePromotionController = (ClerkConfirmDeletePromotionController) stageController.getController();
        clerkConfirmDeletePromotionController.initial(promotionVO);
    }

}
