package ui.view.presentation.clerk;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import ui.view.presentation.PaneAdder;
import ui.view.presentation.util.ConfirmExitController;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;
import vo.PromotionVO;

import java.rmi.RemoteException;

/**
 * Created by ST on 2016/12/1.
 */
public class ClerkModifyPromotionController implements ControlledStage {

    private StageController stageController;

    @FXML
    private Pane promotionPane;

    private PromotionVO promotionVO;
    private String resource = "clerk/ClerkModifyPromotion.fxml";

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * initial方法，初始化界面
     */
    public void initial(PromotionVO promotionVO) throws RemoteException {
        this.promotionVO = promotionVO;
        PaneAdder paneAdder = new PaneAdder();
        switch (promotionVO.promotionType) {
            case HotelPromotion_Birthday:
                promotionPane.getChildren().clear();
                paneAdder.addPane(promotionPane, "clerk/ClerkBirthdayPromotion.fxml", 0, 0);
                ClerkBirthdayPromotionController clerkBirthdayPromotionController = (ClerkBirthdayPromotionController)paneAdder.getController();
                clerkBirthdayPromotionController.initial(promotionVO);
                break;
            case HotelPromotion_Holiday:
                promotionPane.getChildren().clear();
                paneAdder.addPane(promotionPane, "clerk/ClerkHolidayPromotion.fxml", 0, 0);
                ClerkHolidayPromotionController clerkHolidayPromotionController = (ClerkHolidayPromotionController)paneAdder.getController();
                clerkHolidayPromotionController.initial(promotionVO);
                break;
            case HotelPromotion_Reserve:
                promotionPane.getChildren().clear();
                paneAdder.addPane(promotionPane, "clerk/ClerkReservePromotion.fxml", 0, 0);
                ClerkReservePromotionController clerkReservePromotionController = (ClerkReservePromotionController)paneAdder.getController();
                clerkReservePromotionController.initial(promotionVO);
                break;
            case HotelPromotion_Company:
                promotionPane.getChildren().clear();
                paneAdder.addPane(promotionPane, "clerk/ClerkEnterprisePromotion.fxml", 0, 0);
                ClerkEnterprisePromotionController clerkEnterprisePromotionController = (ClerkEnterprisePromotionController)paneAdder.getController();
                clerkEnterprisePromotionController.initial(promotionVO);
                break;
            case HotelPromotion_Other:
                promotionPane.getChildren().clear();
                paneAdder.addPane(promotionPane, "clerk/ClerkOtherPromotion.fxml", 0, 0);
                ClerkOtherPromotionController clerkOtherPromotionController = (ClerkOtherPromotionController)paneAdder.getController();
                clerkOtherPromotionController.initial(promotionVO);
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
        controller.setToBeClosed(resource);
    }

    /**
     * 退出按钮结果，退出程序
     */
    @FXML
    private void exit() {
        System.exit(0);
    }
}
