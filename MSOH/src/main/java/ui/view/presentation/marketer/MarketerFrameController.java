package ui.view.presentation.marketer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import ui.controller.UserAdminController;
import ui.view.controllerservice.UserAdmin;
import ui.view.presentation.PaneAdder;
import ui.view.presentation.util.ConfirmExitController;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;
import vo.MarketerVO;

import java.io.IOException;
import java.rmi.RemoteException;

/**
 * Created by ST on 2016/12/1.
 */
public class MarketerFrameController implements ControlledStage {

    private StageController stageController;

    @FXML
    private Pane marketerFramePane;
    @FXML
    private Label marketerName;

    private PaneAdder paneAdder;
    private String marketerID;
    private MarketerVO marketerVO;
    private UserAdmin userAdmin;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * initial方法，初始化界面
     */
    public void initial(String ID) throws RemoteException {
        userAdmin = new UserAdminController();
        paneAdder = new PaneAdder();
        marketerID = ID;
        marketerVO = userAdmin.findMarketerByID(marketerID);
        marketerName.setText(marketerVO.name);
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
        MarketerCheckOrderListController marketerCheckOrderListController = (MarketerCheckOrderListController)paneAdder.getController();
        marketerCheckOrderListController.initial(marketerID);
    }

    /**
     * 促销策略按钮结果，显示网站促销策略列表
     */
    @FXML
    private void showWebPromotion() throws IOException, ClassNotFoundException {
        // Close the previous panel
        marketerFramePane.getChildren().clear();

        paneAdder.addPane(marketerFramePane, "marketer/MarketerWebPromotion.fxml", 0, 0);
        MarketerWebPromotionController marketerWebPromotionController = (MarketerWebPromotionController)paneAdder.getController();
        marketerWebPromotionController.initial(marketerID);
    }

    /**
     * 会员制度按钮结果，显示会员等级制度界面
     */
    @FXML
    private void showMemberLevel() throws IOException {
        // Close the previous panel
        marketerFramePane.getChildren().clear();

        paneAdder.addPane(marketerFramePane, "marketer/MarketerMemberLevel.fxml", 0, 0);
    }

    /**
     * 信用充值按钮结果，显示信用充值界面
     */
    @FXML
    private void showCreditCharge() throws IOException {
        // Close the previous panel
        marketerFramePane.getChildren().clear();

        paneAdder.addPane(marketerFramePane, "marketer/MarketerChargeCredit.fxml", 0, 0);
        MarketerChargeCreditController marketerChargeCreditController = (MarketerChargeCreditController)paneAdder.getController();
        marketerChargeCreditController.initial(marketerID);
    }

    /**
     * 个人信息按钮结果，显示修改个人信息界面
     */
    @FXML
    private void showModifyMarketerInfo() throws RemoteException {
        stageController = new StageController();
        stageController.loadStage("marketer/MarketerModifyPersonalInfo.fxml", 1);
        MarketerModifyPersonalInfoController marketerModifyPersonalInfoController = (MarketerModifyPersonalInfoController) stageController.getController();
        marketerModifyPersonalInfoController.initial(marketerID);
    }


}
