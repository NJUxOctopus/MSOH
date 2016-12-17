package ui.view.presentation.marketer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import ui.controller.UserAdminController;
import ui.view.controllerservice.UserAdmin;
import ui.view.presentation.PaneAdder;
import ui.view.presentation.util.ConfirmExitController;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;
import util.PromotionType;
import vo.PromotionVO;

import java.rmi.RemoteException;

/**
 * Created by ST on 2016/12/1.
 */
public class MarketerModifyPromotionController implements ControlledStage {

    private StageController stageController;

    @FXML
    private Pane promotionPane;

    private PromotionVO promotionVO;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * initial方法，初始化界面
     *
     * @param promotionVO
     */
    public void initial(PromotionVO promotionVO) throws RemoteException {
        this.promotionVO = promotionVO;
        PaneAdder paneAdder = new PaneAdder();
        switch ((promotionVO.promotionType)) {
            case WebPromotion_Holiday:
                promotionPane.getChildren().clear();
                paneAdder.addPane(promotionPane, "marketer/MarketerHolidayPromotion.fxml", 0, 0);
                MarketerHolidayPromotionController marketerHolidayPromotionController = (MarketerHolidayPromotionController) paneAdder.getController();
                marketerHolidayPromotionController.initial(promotionVO);
                break;
            case WebPromotion_VIP:
                promotionPane.getChildren().clear();
                paneAdder.addPane(promotionPane, "marketer/MarketerVIPPromotion.fxml", 0, 0);
                MarketerVIPPromotionController marketerVIPPromotionController = (MarketerVIPPromotionController)paneAdder.getController();
                marketerVIPPromotionController.initial(promotionVO);
                break;
            case WebPromotion_Other:
                promotionPane.getChildren().clear();
                paneAdder.addPane(promotionPane,"marketer/MarketerOtherPromotion.fxml",0,0);
                MarketerOtherPromotionController marketerOtherPromotionController = (MarketerOtherPromotionController)paneAdder.getController();
                marketerOtherPromotionController.initial(promotionVO);
                break;
        }
    }

    /**
     * 后退按钮结果，显示确认退出提示弹窗
     */
    @FXML
    private void showConfirmExit() {
        stageController = new StageController();
        stageController.loadStage("util/ConfirmExit.fxml", 0.8);
        ConfirmExitController controller = (ConfirmExitController) stageController.getController();
        controller.setToBeClosed("marketer/MarketerModifyPromotion.fxml");
    }

    /**
     * 退出按钮结果，退出程序
     */
    @FXML
    private void exit() {
        System.exit(0);
    }
}
