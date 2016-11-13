package init;

import rmi.RemoteHelper;

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
//		JFrame frame = new JFrame();
//		frame.setVisible(true);
//		frame.setBounds(200, 50, 1000, 800);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ClientDriver cd=new ClientDriver();

	}

}
