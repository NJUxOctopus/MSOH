package ui.view.presentation.manager;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import rmi.RemoteHelper;
import ui.controller.HotelAdminController;
import ui.view.controllerservice.HotelAdmin;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.util.ImageController;
import util.ImageHelper;
import vo.HotelVO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.rmi.RemoteException;

/**
 * Created by island on 2016/12/2.
 */
public class ManagerSingleHotelViewController implements ControlledStage {
    StageController stageController = new StageController();

    private String hotelID;

    private HotelVO hotelVO;

    @FXML
    private Label hotelIDLabel;

    private String type;

    @FXML
    private Label hotelNameLabel;

    @FXML
    private Label addressLabel;

    @FXML
    private Label areaLabel;

    @FXML
    private Label cityLabel;

    @FXML
    private Button hotelInfoButton;

    @FXML
    private Button cleckButton;

    @FXML
    private ImageView hotelImage;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * 显示酒店信息界面
     */
    @FXML
    private void showHotelInfoView(){
        stageController = new StageController();
        stageController.loadStage("manager/ManagerHotelInfoView.fxml", 1);
        ManagerHotelInfoViewController managerHotelInfoViewController = (ManagerHotelInfoViewController) stageController.getController();
        managerHotelInfoViewController.setModifyVer(hotelID);

    }

    /**
     * 显示工作人员信息界面
     * 如果该酒店已存在工作人员，进入修改信息界面
     * 如果该酒店无工作人员，进入添加人员界面
     */
    @FXML
    private void showClerkInfoView(){
        stageController = new StageController();
        stageController.loadStage("manager/ManagerClerkInfoView.fxml", 1);
        ManagerClerkInfoViewController managerClerkInfoViewController = (ManagerClerkInfoViewController) stageController.getController();
        if(!hotelVO.clerkID.equals("")) {
            managerClerkInfoViewController.setModifyVer(hotelVO.clerkID);
        }
        else{
            managerClerkInfoViewController.setAddVer(hotelID);
        }

    }

    /**
     * 初始化酒店面板
     * @param hotelID
     */
    public void init(String hotelID){
        this.hotelID = hotelID;
        HotelAdmin hotelAdmin = new HotelAdminController();
        try {
            hotelVO = hotelAdmin.findByID(hotelID);
            hotelNameLabel.setText(hotelVO.hotelName);
            hotelIDLabel.setText(hotelVO.hotelID);
            cityLabel.setText(hotelVO.city);
            areaLabel.setText(hotelVO.area);
            addressLabel.setText(hotelVO.hotelAddress);
            //酒店图片
            ImageController imageController = new ImageController();
            WritableImage wr = imageController.loadImage(hotelVO.picUrls[0], 200, 150);
            hotelImage.setImage(wr);
        }catch (RemoteException e){
            e.printStackTrace();
        }
    }
}
