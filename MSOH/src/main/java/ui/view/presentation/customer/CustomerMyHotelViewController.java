package ui.view.presentation.customer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import ui.controller.ReservedHotelController;
import ui.view.controllerservice.ReservedHotel;
import ui.view.presentation.PaneAdder;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;
import vo.HotelVO;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by island on 2016/11/24.
 */
public class CustomerMyHotelViewController implements ControlledStage {
    StageController stageController;

    private String resource = "customer/CustomerMyHotelView.fxml";

    private CustomerSingleHotelViewController customerSingleHotelViewController;

    private String customerID;

    private String hotelID;

    @FXML
    private ImageView background;

    @FXML
    private Button backButton;

    @FXML
    private AnchorPane hotelListScrollPane;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    @FXML
    private void closeStage() {
        stageController.closeStage(resource);
    }

    /**
     * 酒店列表面板初始化方法
     */
    public void addHotelPane(){
        ReservedHotel reservedHotel = new ReservedHotelController();
        try {
            List<HotelVO> hotelVOList = reservedHotel.getHistoryHotel(customerID);
            int num = hotelVOList.size();
            hotelListScrollPane.setPrefWidth(260*num - 5);
            PaneAdder paneAdder = new PaneAdder();
            for(int i =0; i < num; i++) {
                paneAdder.addPane(hotelListScrollPane, "customer/CustomerSingleHotelView.fxml", 5 + 270 * i, 10);
                customerSingleHotelViewController = (CustomerSingleHotelViewController) stageController.getController();
                customerSingleHotelViewController.init(customerID, hotelID);
            }
        }catch (RemoteException e){
            e.printStackTrace();
        }
    }

    /**
     * 我的酒店界面初始化方法
     * @param customerID
     */
    public void init(String customerID){
        this.customerID = customerID;

        addHotelPane();
    }

    /**
     * 退出系统
     */
    @FXML
    private void exit() {
        System.exit(0);
    }
}
