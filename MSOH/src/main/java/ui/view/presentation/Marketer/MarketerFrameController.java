package ui.view.presentation.Marketer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import ui.view.presentation.ControlledStage;
import ui.view.presentation.StageController;

import java.io.IOException;

/**
 * Created by ST on 2016/12/1.
 */
public class MarketerFrameController implements ControlledStage {

    StageController stageController;

    @FXML
    private Pane marketerFramePane;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * 处理订单按钮结果，显示订单列表
     */
    public void showOrderList() throws IOException {
//        stageController = new StageController();
//        FXMLLoader loader = stageController.getLoader();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MarketerFrame.class.getResource("MarketerCheckOrderList.fxml"));
        Pane orderListPane = (Pane) loader.load();

        // Close the previous panel
        marketerFramePane.getChildren().clear();

        // Set orderListPane into the initial pane.
        marketerFramePane.getChildren().add(orderListPane);
        orderListPane.setLayoutX(0);
        orderListPane.setLayoutY(0);
    }

    /**
     * 促销策略按钮结果，显示网站促销策略列表
     */
    public void showWebPromotion() throws IOException {
//        stageController = new StageController();
//        FXMLLoader loader = stageController.getLoader();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MarketerFrame.class.getResource("MarketerWebPromotion.fxml"));
        Pane promotionPane = (Pane) loader.load();

        // Close the previous panel
        marketerFramePane.getChildren().clear();

        // Set orderListPane into the initial pane.
        marketerFramePane.getChildren().add(promotionPane);
        promotionPane.setLayoutX(0);
        promotionPane.setLayoutY(0);
    }

    /**
     * 会员制度按钮结果，显示会员等级制度界面
     */
    public void showMemberLevel() throws IOException {
//        stageController = new StageController();
//        FXMLLoader loader = stageController.getLoader();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MarketerFrame.class.getResource("MarketerMemberLevel.fxml"));
        Pane memberPane = (Pane) loader.load();

        // Close the previous panel
        marketerFramePane.getChildren().clear();

        // Set orderListPane into the initial pane.
        marketerFramePane.getChildren().add(memberPane);
        memberPane.setLayoutX(0);
        memberPane.setLayoutY(0);
    }

    /**
     * 信用充值按钮结果，显示信用充值界面
     */
    public void showCreditCharge() throws IOException {
//        stageController = new StageController();
//        FXMLLoader loader = stageController.getLoader();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MarketerFrame.class.getResource("MarketerChargeCredit.fxml"));
        Pane creditPane = (Pane) loader.load();

        // Close the previous panel
        marketerFramePane.getChildren().clear();

        // Set orderListPane into the initial pane.
        marketerFramePane.getChildren().add(creditPane);
        creditPane.setLayoutX(0);
        creditPane.setLayoutY(0);
    }

    /**
     * 个人信息按钮结果，显示修改个人信息界面
     */
    public void showModifyMarketerInfo() {
        stageController = new StageController();
        stageController.loadStage("Marketer/MarketerModifyPersonalInfo.fxml", 1);
    }


}
