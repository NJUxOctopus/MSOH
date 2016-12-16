package ui.view.presentation.clerk;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import ui.view.presentation.PaneAdder;
import ui.view.presentation.util.ConfirmExitController;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;
import ui.view.presentation.util.SelectTimeViewController;

import java.rmi.RemoteException;

/**
 * Created by ST on 2016/11/30.
 */
public class ClerkCreateHotelPromotionController implements ControlledStage {

    private StageController stageController;

    @FXML
    private Pane promotionPane;
    @FXML
    private Rectangle promotionSelectShade;

    private String resource = "clerk/ClerkCreateHotelPromotion.fxml";
    private String clerkID;
    private PaneAdder paneAdder;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * initial方法，初始化界面
     */
    public void initial(String clerkID) throws RemoteException {
        paneAdder = new PaneAdder();
        this.clerkID = clerkID;
        //默认显示生日特惠
        this.showBirthdayPromotion();
    }

    /**
     * 生日特惠按钮结果，显示制定生日特惠界面
     */
    @FXML
    private void showBirthdayPromotion() throws RemoteException {
        promotionSelectShade.setHeight(70);
        promotionSelectShade.setY(0);
        promotionPane.getChildren().clear();

        paneAdder.addPane(promotionPane, "clerk/ClerkBirthdayPromotion.fxml", 0, 0);
        paneAdder.putIntoLoaders("clerk/ClerkBirthdayPromotion.fxml");
        ClerkBirthdayPromotionController clerkBirthdayPromotionController = (ClerkBirthdayPromotionController) paneAdder.getController();
        clerkBirthdayPromotionController.initial(clerkID);
    }

    /**
     * 节日特惠按钮结果，显示制定节日特惠界面
     */
    @FXML
    private void showHolidayPromotion() throws RemoteException {
        promotionSelectShade.setHeight(70);
        promotionSelectShade.setY(70);
        promotionPane.getChildren().clear();

        paneAdder.addPane(promotionPane, "clerk/ClerkHolidayPromotion.fxml", 0, 0);
        ClerkHolidayPromotionController clerkHolidayPromotionController = (ClerkHolidayPromotionController) paneAdder.getController();
        clerkHolidayPromotionController.initial(clerkID);
    }

    /**
     * 多订多惠按钮结果，显示制定多订多惠界面
     */
    @FXML
    private void showReservePromotion() throws RemoteException {
        promotionSelectShade.setHeight(68);
        promotionSelectShade.setY(140);
        promotionPane.getChildren().clear();

        paneAdder.addPane(promotionPane, "clerk/ClerkReservePromotion.fxml", 0, 0);
        ClerkReservePromotionController clerkReservePromotionController = (ClerkReservePromotionController) paneAdder.getController();
        clerkReservePromotionController.initial(clerkID);
    }

    /**
     * 合作企业优惠按钮结果，显示制定合作企业优惠界面
     */
    @FXML
    private void showEnterprisePromotion() throws RemoteException {
        promotionSelectShade.setHeight(67);
        promotionSelectShade.setY(208);
        promotionPane.getChildren().clear();

        paneAdder.addPane(promotionPane, "clerk/ClerkEnterprisePromotion.fxml", 0, 0);
        ClerkEnterprisePromotionController clerkEnterprisePromotionController = (ClerkEnterprisePromotionController) paneAdder.getController();
        clerkEnterprisePromotionController.initial(clerkID);
    }

    /**
     * 其他优惠按钮结果，显示制定其他优惠界面
     */
    @FXML
    private void showOtherPromotion() throws RemoteException {
        promotionSelectShade.setHeight(67);
        promotionSelectShade.setY(275);
        promotionPane.getChildren().clear();

        paneAdder.addPane(promotionPane, "clerk/ClerkOtherPromotion.fxml", 0, 0);
        ClerkOtherPromotionController clerkOtherPromotionController = (ClerkOtherPromotionController) paneAdder.getController();
        clerkOtherPromotionController.initial(clerkID);
    }

    /**
     * 后退按钮结果，显示确认退出提示弹窗
     */
    @FXML
    private void showConfirmExit() {
        stageController = new StageController();
        stageController.loadStage("util/ConfirmExit.fxml", 0.8);
        ConfirmExitController controller = (ConfirmExitController) stageController.getController();
        controller.setToBeClosed("clerk/ClerkCreateHotelPromotion.fxml");
    }

}
