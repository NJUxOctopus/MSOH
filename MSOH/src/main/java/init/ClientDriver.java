package init;

import businesslogic.clerk_bl.Clerk;
import dataservice.clerk_dataservice.Clerk_DataService;
import rmi.RemoteHelper;
import util.ResultMessage;
import vo.ClerkVO;

import javax.swing.*;
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
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(200, 50, 1000, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
