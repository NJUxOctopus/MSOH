package ui.view.presentation.marketer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import ui.controller.EditMemberLevelController;
import ui.controller.UserAdminController;
import ui.view.controllerservice.EditMemberLevel;
import ui.view.controllerservice.UserAdmin;
import ui.view.presentation.PaneAdder;
import ui.view.presentation.login.LoginViewController;
import ui.view.presentation.util.ConfirmExitController;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;
import vo.MarketerVO;
import vo.MemberLevelVO;

import java.io.IOException;
import java.rmi.RemoteException;

/**
 * Created by ST on 2016/12/1.
 */
public class MarketerFrameController implements ControlledStage {

    private StageController stageController;

    private String resource = "marketer/MarketerFrame.fxml";

    @FXML
    private Pane marketerFramePane;
    @FXML
    private Label marketerName;
    @FXML
    private Rectangle selectShade;

    private PaneAdder paneAdder;
    private String marketerID;
    private MarketerVO marketerVO;
    private UserAdmin userAdmin;
    private EditMemberLevel editMemberLevel;
    private MemberLevelVO memberLevelVO;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * initial方法，初始化界面
     */
    public void initial(String marketerID) throws RemoteException {
        userAdmin = new UserAdminController();
        paneAdder = new PaneAdder();
        this.marketerID = marketerID;
        marketerVO = userAdmin.findMarketerByID(this.marketerID);
        marketerName.setText(marketerVO.name);
        selectShade.setY(1000);
        editMemberLevel = new EditMemberLevelController();
    }

    /**
     * 处理订单按钮结果，显示订单列表
     */
    @FXML
    private void showOrderList() throws IOException {
        // Close the previous panel
        marketerFramePane.getChildren().clear();

        selectShade.setHeight(127);
        selectShade.setY(0);

        PaneAdder paneAdder = new PaneAdder();
        paneAdder.addPane(marketerFramePane, "marketer/MarketerCheckOrderList.fxml", 0, 0);
        paneAdder.putIntoLoaders("marketer/MarketerCheckOrderList.fxml");
        MarketerCheckOrderListController marketerCheckOrderListController = (MarketerCheckOrderListController) paneAdder.getController();
        marketerCheckOrderListController.initial(marketerID);
    }

    /**
     * 促销策略按钮结果，显示网站促销策略列表
     */
    @FXML
    private void showWebPromotion() throws IOException, ClassNotFoundException {
        // Close the previous panel
        marketerFramePane.getChildren().clear();

        selectShade.setHeight(121);
        selectShade.setY(127);

        paneAdder = new PaneAdder();
        paneAdder.addPane(marketerFramePane, "marketer/MarketerWebPromotion.fxml", 0, 0);
        paneAdder.putIntoLoaders("marketer/MarketerWebPromotion.fxml");
        MarketerWebPromotionController marketerWebPromotionController = (MarketerWebPromotionController) paneAdder.getController();
        marketerWebPromotionController.initial(marketerID);
    }

    /**
     * 会员制度按钮结果，显示会员等级制度界面
     */
    @FXML
    private void showMemberLevel() throws IOException {
        // Close the previous panel
        marketerFramePane.getChildren().clear();

        selectShade.setHeight(110);
        selectShade.setY(248);

        memberLevelVO = editMemberLevel.getMemberLevel();
        paneAdder = new PaneAdder();
        paneAdder.addPane(marketerFramePane, "marketer/MarketerMemberLevel.fxml", 0, 0);
        MarketerMemberLevelController marketerMemberLevelController = (MarketerMemberLevelController) paneAdder.getController();
        marketerMemberLevelController.initial(memberLevelVO);
    }

    /**
     * 信用充值按钮结果，显示信用充值界面
     */
    @FXML
    private void showCreditCharge() throws IOException {

        // Close the previous panel
        marketerFramePane.getChildren().clear();

        selectShade.setHeight(116);
        selectShade.setY(358);

        paneAdder = new PaneAdder();
        paneAdder.addPane(marketerFramePane, "marketer/MarketerChargeCredit.fxml", 0, 0);
        paneAdder.putIntoLoaders("marketer/MarketerChargeCredit.fxml");
        MarketerChargeCreditController marketerChargeCreditController = (MarketerChargeCreditController) paneAdder.getController();
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

    /**
     * 切换账号
     */
    @FXML
    private void switchAccount() throws RemoteException {
        stageController = new StageController();
        stageController.closeStage(resource);
        stageController.loadStage("login/LoginView.fxml", 1);
        LoginViewController loginViewController = (LoginViewController)stageController.getController();
        loginViewController.initial();
    }

    /**
     * 关于我们按钮结果，显示关于我们界面
     */
    @FXML
    private void showAboutUs() {
        stageController = new StageController();
        stageController.loadStage("util/AboutUs.fxml", 1);
    }

    /**
     * 退出系统
     */
    @FXML
    private void exit() {
        System.exit(0);
    }


}
