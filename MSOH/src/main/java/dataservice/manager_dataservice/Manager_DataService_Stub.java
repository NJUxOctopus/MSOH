package dataservice.manager_dataservice;

import po.ManagerPO;
import util.ResultMessage;
import util.WorkerPosition;

import javax.swing.*;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

/**
 * 
 * @author 钱柯宇
 *
 */
public class Manager_DataService_Stub implements Manager_DataService{
	public boolean modifyManager(ManagerPO po) throws RemoteException {
		return false;
	}

	public ManagerPO findManagerByID(String ID) throws RemoteException {
		if(ID.equals("320200000000000000"))
			return new ManagerPO("pxr", ID, "12345678910", "123456",  "", WorkerPosition.Manager);
		else
			return null;
	}

	@Override
	public List<ManagerPO> findAllManagers() throws IOException, ClassNotFoundException {
		return null;
	}

	@Override
	public List<ManagerPO> findManagerByName(String name) throws IOException, ClassNotFoundException {
		return null;
	}
}
