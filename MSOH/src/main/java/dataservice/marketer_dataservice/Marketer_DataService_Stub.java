package dataservice.marketer_dataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import po.MarketerPO;
import util.ResultMessage;
import util.WorkerPosition;

import javax.swing.*;

/**
 * 
 * @author Ǯ����
 *
 */
public class Marketer_DataService_Stub implements Marketer_DataService{
	public boolean addMarketer(MarketerPO marketerPO) throws RemoteException {
		return false;
	}

	public boolean modifyMarketer(MarketerPO marketerPO) throws RemoteException {
		return false;
	}

	public List<MarketerPO> findMarketerByName(String name) throws RemoteException {
		return null;
	}

	public MarketerPO findMarketerByID(String id) throws RemoteException {
		if (id .equals( "320200000000000000"))
			return new MarketerPO("pxr", "320200000000000000", "12345678910", "123456", new ImageIcon().getImage(), WorkerPosition.Marketer);
		else
			return null;
	}

	public List<MarketerPO> findAllMarketers() throws RemoteException {
		return null;
	}

	public boolean deleteMarketer(MarketerPO marketerPO) throws RemoteException {
		return false;
	}
}