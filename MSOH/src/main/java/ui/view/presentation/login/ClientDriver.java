package ui.view.presentation.login;

import rmi.RemoteHelper;
import ui.view.presentation.StageController;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ClientDriver {

	private RemoteHelper remoteHelper;

	public ClientDriver(){
		linkToServer();
		initGUI();
	}

	private void linkToServer(){
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
		//TODO Initialize the main frame
	}

	public static void main(String[] args) {
		StageController stageController = new StageController();
		stageController.loadStage("login/LoginView.fxml", 1);
		ClientDriver dc=new ClientDriver();

//		Clerk clerk=new Clerk();
//
//
//		try{
//		clerk.deleteClerk(new ClerkVO("qky", "13913025325", "123321", "320581190011223111", "",
//				"hn", "123"));}catch(RemoteException e){
//			e.printStackTrace();
//		}

	}

}
