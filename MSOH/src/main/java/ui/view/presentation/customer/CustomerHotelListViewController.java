package ui.view.presentation.customer;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import ui.view.presentation.PaneAdder;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;
import vo.HotelVO;

import java.io.IOException;
import java.util.List;

/**
 * Created by island on 2016/11/24.
 */
public class CustomerHotelListViewController implements ControlledStage {
    StageController stageController = new StageController();

    CustomerSingleHotelViewController customerSingleHotelViewController;

    private String resource = "customer/CustomerHotelListView.fxml";

    private String customerID;

    @FXML
    private ImageView background;

    @FXML
    private Button backButton;

    @FXML
    private ChoiceBox cityChoiceBox;

    @FXML
    private ChoiceBox areaChoiceBox;

    @FXML
    private ChoiceBox starChoiceBox;

    @FXML
    private ChoiceBox numOfRoomChoiceBox;

    @FXML
    private ChoiceBox preScoreChoiceBox;

    @FXML
    private ChoiceBox postScoreChoiceBox;

    @FXML
    private TextField nameTextField;

    @FXML
    private Button checkInTimeButton;

    @FXML
    private Button checkOutTimeButton;

    @FXML
    private Button confirmModifyButton;

    @FXML
    private ChoiceBox sortChoiceBox;

    @FXML
    private ChoiceBox selcetChoiceBox;

    @FXML
    private CheckBox reservedCheckBox;

    @FXML
    private AnchorPane hotelListScrollPane;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    @FXML
    private void research() {

    }

    @FXML
    private void closeStage() {
        stageController.closeStage(resource);
    }

    public void addHotelPane(List<HotelVO> hotelList){
        PaneAdder paneAdder = new PaneAdder();
        paneAdder.addPane(hotelListScrollPane, "customer/CustomerSingleHotelView.fxml", 5, 10);
        paneAdder.addPane(hotelListScrollPane, "customer/CustomerSingleHotelView.fxml", 250, 10);

    }

    public void init(HotelVO hotelVO, String customerId){
        this.customerID = customerId;
        cityChoiceBox.setItems(FXCollections.observableArrayList(
                "南京"));
        cityChoiceBox.setValue(hotelVO.city);

        System.out.print(hotelVO.city);



    }
}
