package init;/**
 * Created by ST on 2016/11/15.
 */

import businesslogic.hotel_bl.HotelUtil;
import javafx.application.Application;
import javafx.stage.Stage;
import rmi.RemoteHelper;
import ui.view.presentation.StageController;
import ui.view.presentation.login.LoginViewController;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
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
            InetAddress ipv4Address = InetAddress.getByName("HiPC");
            String ipAddress=ipv4Address.getHostAddress();
//            remoteHelper.setRemote(Naming.lookup("rmi://172.28.179.228:1099/DataRemoteObject"));
            remoteHelper.setRemote(Naming.lookup("rmi://"+ipAddress+":1099/DataRemoteObject"));
            System.out.println("Octopus: 连接服务器成功");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }catch (UnknownHostException e){
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
