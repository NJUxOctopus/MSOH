package init;/**
 * Created by ST on 2016/11/15.
 */

import javafx.application.Application;
import javafx.stage.Stage;
import rmi.RemoteHelper;
import ui.view.presentation.StageController;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

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

    private void linkServer(){
        try{
            remoteHelper=RemoteHelper.getInstance();
            remoteHelper.setRemote(Naming.lookup("rmi://localhost:8888/DataRemoteObject"));
            System.out.println("linked");
        }catch(MalformedURLException e){
            e.printStackTrace();
        }catch(RemoteException e){
            e.printStackTrace();
        }catch(NotBoundException e){
            e.printStackTrace();
        }
    }

    private void initGUI(){
        stageController = new StageController();
        stageController.loadStage(resource, 1);
    }
}
