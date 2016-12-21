package init;/**
 * Created by ST on 2016/11/15.
 */

import businesslogic.hotel_bl.HotelUtil;
import javafx.application.Application;
import javafx.stage.Stage;
import rmi.RemoteHelper;
import ui.view.presentation.StageController;
import ui.view.presentation.login.LoginViewController;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoginView extends Application {
    private RemoteHelper remoteHelper;

    private static String resource = "login/LoginView.fxml";

    private StageController stageController;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        linkServer();

        initGUI();
    }

    private void linkServer() {
        try {
            remoteHelper = RemoteHelper.getInstance();
            remoteHelper.setRemote(Naming.lookup("rmi://114.212.42.35:1099/DataRemoteObject"));
            System.out.println("Octopus: 连接服务器成功");
//            HotelUtil hotelUtil = new HotelUtil();
//            Date date = new Date();
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
//            Timestamp timestamp1 = Timestamp.valueOf(sdf.format(date));
//            System.out.print(hotelUtil.getDailyRoomInfo("10000006",timestamp1).room.size());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }


    private void initGUI() {
        stageController = new StageController();
        stageController.loadStage(resource, 1);
        LoginViewController loginViewController = (LoginViewController)stageController.getController();
        loginViewController.initial();
    }
}
