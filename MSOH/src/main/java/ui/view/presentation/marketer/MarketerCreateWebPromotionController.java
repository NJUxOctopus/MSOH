package ui.view.presentation.marketer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import ui.view.presentation.PaneAdder;
import ui.view.presentation.util.ConfirmExitController;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;

import java.rmi.RemoteException;

/**
 * Created by ST on 2016/12/1.
 */
public class MarketerCreateWebPromotionController implements ControlledStage {

    private StageController stageController;

    @FXML
    private Rectangle promotionSelectShade;
    @FXML
    private Pane promotionPane;

    private String marketerID;
    private PaneAdder paneAdder;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * initial方法，初始化界面
     */
    public void initial(String marketerID) throws RemoteException {

        this.marketerID = marketerID;
        //默认显示节日特惠
        this.showHolidayPromotion();
    }

    /**
     * 后退按钮结果，显示确认退出提示弹窗
     */
    @FXML
    private void showConfirmExit() {
        stageController = new StageController();
        stageController.loadStage("util/ConfirmExit.fxml", 0.8);
        ConfirmExitController controller = (ConfirmExitController) stageController.getController();
        controller.setToBeClosed("marketer/MarketerCreateWebPromotion.fxml");
    }

    /**
     * 节日特惠按钮结果，显示制定网站节日特惠界面
     */
    @FXML
    private void showHolidayPromotion() throws RemoteException {
        paneAdder = new PaneAdder();
        promotionSelectShade.setHeight(70);
        promotionSelectShade.setY(0);
        promotionPane.getChildren().clear();
        paneAdder.addPane(promotionPane, "marketer/MarketerHolidayPromotion.fxml", 0, 0);
        paneAdder.putIntoLoaders("marketer/MarketerHolidayPromotion.fxml");

        MarketerHolidayPromotionController marketerHolidayPromotionController = (MarketerHolidayPromotionController) paneAdder.getController();
        marketerHolidayPromotionController.initial(marketerID);
    }

    /**
     * VIP特惠按钮结果，显示制定网站VIP特惠界面
     */
    @FXML
    private void showVIPPromotion() throws RemoteException {
        paneAdder = new PaneAdder();
        promotionSelectShade.setHeight(70);
        promotionSelectShade.setY(70);
        promotionPane.getChildren().clear();
        paneAdder.addPane(promotionPane, "marketer/MarketerVIPPromotion.fxml", 0, 0);
        paneAdder.putIntoLoaders("marketer/MarketerVIPPromotion.fxml");

        MarketerVIPPromotionController marketerVIPPromotionController = (MarketerVIPPromotionController) paneAdder.getController();
        marketerVIPPromotionController.initial(marketerID);
    }

    /**
     * 其他特惠按钮结果，显示制定网站其他特惠界面
     */
    @FXML
    private void showOtherPromotion() throws RemoteException {
        paneAdder = new PaneAdder();
        promotionSelectShade.setHeight(67);
        promotionSelectShade.setY(140);
        promotionPane.getChildren().clear();
        paneAdder.addPane(promotionPane, "marketer/MarketerOtherPromotion.fxml", 0, 0);
        paneAdder.putIntoLoaders("marketer/MarketerOtherPromotion.fxml");

        MarketerOtherPromotionController marketerOtherPromotionController = (MarketerOtherPromotionController) paneAdder.getController();
        marketerOtherPromotionController.initial(marketerID);
    }

    /**
     * 退出按钮结果，退出程序
     */
    @FXML
    private void exit() {
        System.exit(0);
    }

}
