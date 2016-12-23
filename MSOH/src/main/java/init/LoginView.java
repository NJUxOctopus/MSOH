package init;/**
 * Created by ST on 2016/11/15.
 */

import javafx.application.Application;
import javafx.stage.Stage;
import rmi.RemoteHelper;
import ui.view.presentation.StageController;
import ui.view.presentation.login.LoginViewController;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Properties;

public class LoginView extends Application {
    private RemoteHelper remoteHelper;

    private StageController stageController;

    private String hostname;

    private String IPAddress;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        initGUI();
//        linkServer();
//        initLogin();
    }

    public void linkServer() {
        try {
            //initProperties();

            remoteHelper = RemoteHelper.getInstance();
//            InetAddress ipv4Address = InetAddress.getByName(hostname);
//            String ipAddress = ipv4Address.getHostAddress();
//            remoteHelper.setRemote(Naming.lookup("rmi://172.28.179.228:1099/DataRemoteObject"));
            remoteHelper.setRemote(Naming.lookup("rmi://" + IPAddress + ":1099/DataRemoteObject"));
            System.out.println("Octopus: 连接服务器成功");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
//        catch (UnknownHostException e) {
//            e.printStackTrace();
//        }
    }

    private void initGUI() {
        stageController = new StageController();
        stageController.loadStage("login/IP.fxml", 1);
    }

    private void initProperties() {
        Properties pro = new Properties();
        try {
            InputStream in = new BufferedInputStream(new FileInputStream("host.properties"));
            pro.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        hostname = pro.getProperty("hostname");
    }

    public void setIPAddress(String address) {
        this.IPAddress = address;
    }

}
