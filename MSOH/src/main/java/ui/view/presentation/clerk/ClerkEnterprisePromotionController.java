package ui.view.presentation.clerk;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import ui.controller.EditPromotionController;
import ui.controller.HotelAdminController;
import ui.controller.UserAdminController;
import ui.view.controllerservice.EditPromotion;
import ui.view.controllerservice.HotelAdmin;
import ui.view.controllerservice.UserAdmin;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.util.ErrorBoxController;
import ui.view.presentation.util.SelectTimeViewController;
import util.MemberType;
import util.PromotionType;
import util.ResultMessage;
import vo.ClerkVO;
import vo.HotelVO;
import vo.PromotionVO;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by ST on 2016/12/11.
 */
public class ClerkEnterprisePromotionController implements ControlledStage {

    private StageController stageController;

    private String resource = "clerk/ClerkEnterprisePromotion.fxml";

    @FXML
    private Label discountLabel;
    @FXML
    private ChoiceBox enterpriseChoiceBox;
    @FXML
    private Button startTimeButton;
    @FXML
    private Button endTimeButton;
    @FXML
    private Button confirmButton;


    private String clerkID;
    private String clerkName;
    private ClerkVO clerkVO;
    private HotelVO hotelVO;
    private String hotelID;
    private UserAdmin userAdmin;
    private HotelAdmin hotelAdmin;
    private EditPromotion editPromotion;

    //获取当前时间
    private Date date = new Date();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    private Timestamp time = Timestamp.valueOf(dateFormat.format(date));

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * initial方法，初始化界面
     */
    public void initial(String clerkID) throws RemoteException {
        userAdmin = new UserAdminController();
        hotelAdmin = new HotelAdminController();
        editPromotion = new EditPromotionController();
        this.clerkID = clerkID;
        this.clerkVO = userAdmin.findClerkByID(clerkID);
        this.clerkName = clerkVO.name;
        this.hotelVO = hotelAdmin.findByClerkID(clerkID);
        this.hotelID = hotelVO.hotelID;

        //加载合作企业列表
        List<String> enterprise = editPromotion.getCompany();
        ObservableList<String> toAdd = FXCollections.observableArrayList();
        for (int i = 0; i < enterprise.size(); i++) {
            toAdd.add(enterprise.get(i));
        }
        enterpriseChoiceBox.setItems(toAdd);
    }

    /**
     * 重载initial方法，用于修改策略时初始化界面
     */
    public void initial(PromotionVO promotionVO) throws RemoteException {
        confirmButton.setText("修改");
        discountLabel.setText(String.valueOf(promotionVO.discount));
        startTimeButton.setText(String.valueOf(promotionVO.startTime));
        endTimeButton.setText(String.valueOf(promotionVO.endTime));
        enterpriseChoiceBox.getSelectionModel().select(promotionVO.companyName);
    }

    /**
     * 加号按钮结果，增加折扣数字
     */
    @FXML
    private void addDiscount() {
        double discount = Double.parseDouble(discountLabel.getText());
        if (discount != 9.9) {
            discountLabel.setText(String.valueOf((discount + 0.1)));
        }
    }

    /**
     * 减号按钮结果，减少折扣数字
     */
    @FXML
    private void minusDiscount() {
        double discount = Double.parseDouble(discountLabel.getText());
        if (discount != 0.1) {
            discountLabel.setText(String.valueOf((discount - 0.1)));
        }
    }

    /**
     * 开始时间按钮结果，显示选择开始时间弹窗
     */
    @FXML
    private void showBeginTime() {
        stageController = new StageController();
        stageController.loadStage("util/SelectTimeView.fxml", 0.8);
        SelectTimeViewController selectTimeViewController = (SelectTimeViewController) stageController.getController();
        selectTimeViewController.setToBeSet(resource, "start");
        selectTimeViewController.init();
    }

    /**
     * 结束时间按钮结果，显示选择结束时间弹窗
     */
    @FXML
    private void showEndTime() {
        stageController = new StageController();
        stageController.loadStage("util/SelectTimeView.fxml", 0.8);
        SelectTimeViewController selectTimeViewController = (SelectTimeViewController) stageController.getController();
        selectTimeViewController.setToBeSet(resource, "end");
        selectTimeViewController.init();
    }

    /**
     * 确定按钮结果，创建订单
     */
    @FXML
    private void confirmCreate() throws IOException, ClassNotFoundException {

        editPromotion = new EditPromotionController();

        String[] targetHotel = new String[1];
        targetHotel[0] = hotelID;
        Timestamp startTime = Timestamp.valueOf(startTimeButton.getText() + " 00:00:00");
        Timestamp endTime = Timestamp.valueOf(endTimeButton.getText() + " 00:00:00");
        double discount = Double.parseDouble(discountLabel.getText());
        String enterpriseName = (String) enterpriseChoiceBox.getSelectionModel().getSelectedItem();

        if (confirmButton.getText().equals("制定")) {
            //制定策略
            ResultMessage resultMessage = editPromotion.addHotelPromotion(new PromotionVO(clerkName, time, "合作企业优惠", MemberType.ENTREPRISE
                    , targetHotel, startTime, endTime, discount, 0, PromotionType.HotelPromotion_Company, enterpriseName));
            if (resultMessage.equals(ResultMessage.Blank)) {
                this.returnMessage("信息未填写完整！");
            } else if (resultMessage.equals(ResultMessage.Promotion_AddPromotionSuccess)) {
                stageController = this.returnMessage("创建成功！");
                stageController.closeStage(resource);
            } else {
                this.returnMessage("未知错误！");
            }
        } else if (confirmButton.getText().equals("修改")) {
            //修改策略
            ResultMessage resultMessage = editPromotion.addHotelPromotion(new PromotionVO(clerkName, time, "合作企业优惠", MemberType.ENTREPRISE
                    , targetHotel, startTime, endTime, discount, 0, PromotionType.HotelPromotion_Company, enterpriseName));
            if (resultMessage.equals(ResultMessage.Blank)) {
                this.returnMessage("信息未填写完整！");
            } else if (resultMessage.equals(ResultMessage.Promotion_ModifyPromotionSuccess)) {
                stageController = this.returnMessage("修改成功！");
                stageController.closeStage(resource);
            } else {
                this.returnMessage("未知错误！");
            }
        }
    }

    /**
     * 显示提示信息
     *
     * @param error
     * @return
     */
    private StageController returnMessage(String error) {
        stageController = new StageController();
        stageController.loadStage("util/ErrorBoxView.fxml", 0.8);
        ErrorBoxController errorBoxController = (ErrorBoxController) stageController.getController();
        errorBoxController.setLabel(error);
        return stageController;
    }

    /**
     * 回显选择的开始时间
     * @param time
     */
    public void setStartTime(String time){
        startTimeButton.setText(time);
    }

    /**
     * 回显选择的结束时间
     * @param time
     */
    public void setEndTime(String time){
        endTimeButton.setText(time);
    }
}
