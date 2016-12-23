package ui.view.presentation.manager;

import businesslogic.manager_bl.Manager;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import ui.controller.HotelAdminController;
import ui.view.controllerservice.HotelAdmin;
import ui.view.presentation.PaneAdder;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.util.ErrorBoxController;
import vo.HotelVO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by island on 2016/12/1.
 */
public class ManagerHotelManageViewController implements ControlledStage {
    StageController stageController = new StageController();

    @FXML
    private Button searchButton;

    @FXML
    private ChoiceBox typeChoiceBox;

    @FXML
    private TextField textField;

    @FXML
    private Button addHotelButton;

    @FXML
    private AnchorPane hotelListScrollPane;

    @FXML
    private Label emptyLabel;


    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * 搜索酒店方法
     */
    @FXML
    private void search(){
        String type = (String)typeChoiceBox.getSelectionModel().getSelectedItem();
        String input = textField.getText();
        List<HotelVO> hotelVOList = new ArrayList<HotelVO>();
        HotelVO hotelVO;
        try {
            HotelAdmin hotelAdmin = new HotelAdminController();
            if (type == null){
                stageController = new StageController();
                stageController.loadStage("util/ErrorBoxView.fxml", 0.75);
                ErrorBoxController errorBoxController = (ErrorBoxController) stageController.getController();
                errorBoxController.setLabel("请先选择搜索条件！");
            }
            else {
                if (!input.equals("")){
                    if (type.equals("酒店ID")) {
                        hotelVO = hotelAdmin.findByID(input);
                        if(hotelVO != null)
                            hotelVOList.add(hotelVO);
                    }
                    else if (type.equals("酒店名称")) {
                        hotelVOList = hotelAdmin.findByName(input);
                    }
                    else if (type.equals("所属商圈")) {
                        hotelVOList = hotelAdmin.findByArea(input);
                    }
                    addHotelPane(hotelVOList);
                }

            }
        }catch (RemoteException e){
            e.printStackTrace();
        }
    }

    /**
     * 点击添加酒店按钮，跳转至添加酒店界面
     */
    @FXML
    private void addHotel(){
            stageController = new StageController();
            stageController.loadStage("manager/ManagerHotelInfoView.fxml", 1);
            ManagerHotelInfoViewController managerHotelInfoViewController = (ManagerHotelInfoViewController) stageController.getController();
            managerHotelInfoViewController.setAddVer();
    }

    /**
     * 添加单个酒店面板
     * @param hotelVOs
     */
    public void addHotelPane(List<HotelVO> hotelVOs){
        hotelListScrollPane.getChildren().clear();

        if(!hotelVOs.isEmpty()) {
            emptyLabel.setOpacity(0);
            int num = hotelVOs.size();
            hotelListScrollPane.setPrefWidth(250 * num);
            for(int i = 0; i < num; i++) {
                PaneAdder paneAdder = new PaneAdder();
                paneAdder.addPane(hotelListScrollPane, "manager/ManagerSingleHotelView.fxml", 10 + 250*i, 10);
                ManagerSingleHotelViewController managerSingleHotelViewController = (ManagerSingleHotelViewController) paneAdder.getController();
                managerSingleHotelViewController.init(hotelVOs.get(i).hotelID);
            }
        }else{
            emptyLabel.setOpacity(1);
        }
    }

    /**
     * 初始化选择框
     */
    public void init(){
        typeChoiceBox.setItems(FXCollections.observableArrayList(
                "酒店ID", "酒店名称", "所属商圈"));
    }
}
