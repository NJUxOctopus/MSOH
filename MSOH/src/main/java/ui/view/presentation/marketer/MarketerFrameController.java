package ui.view.presentation.marketer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import ui.view.presentation.PaneAdder;
import ui.view.presentation.util.ConfirmExitController;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;

import java.io.IOException;

/**
 * Created by ST on 2016/12/1.
 */
public class MarketerFrameController implements ControlledStage {

    private StageController stageController;

    @FXML
    private Pane marketerFramePane;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * 处理订单按钮结果，显示订单列表
     */
    @FXML
    private void showOrderList() throws IOException {
        // Close the previous panel
        marketerFramePane.getChildren().clear();

        PaneAdder paneAdder = new PaneAdder();
        paneAdder.addPane(marketerFramePane, "marketer/MarketerCheckOrderList.fxml", 0, 0);
    }

    /**
     * 促销策略按钮结果，显示网站促销策略列表
     */
    @FXML
    private void showWebPromotion() throws IOException {
        // Close the previous panel
        marketerFramePane.getChildren().clear();

        PaneAdder paneAdder = new PaneAdder();
        paneAdder.addPane(marketerFramePane, "marketer/MarketerWebPromotion.fxml", 0, 0);
    }

    /**
     * 会员制度按钮结果，显示会员等级制度界面
     */
    @FXML
    private void showMemberLevel() throws IOException {
        // Close the previous panel
        marketerFramePane.getChildren().clear();

        PaneAdder paneAdder = new PaneAdder();
        paneAdder.addPane(marketerFramePane, "marketer/MarketerMemberLevel.fxml", 0, 0);
    }

    /**
     * 信用充值按钮结果，显示信用充值界面
     */
    @FXML
    private void showCreditCharge() throws IOException {
        // Close the previous panel
        marketerFramePane.getChildren().clear();

        PaneAdder paneAdder = new PaneAdder();
        paneAdder.addPane(marketerFramePane, "marketer/MarketerChargeCredit.fxml", 0, 0);
    }

    /**
     * 个人信息按钮结果，显示修改个人信息界面
     */
    @FXML
    private void showModifyMarketerInfo() {
        stageController = new StageController();
        stageController.loadStage("marketer/MarketerModifyPersonalInfo.fxml", 1);
    }


}
