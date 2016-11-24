package dataservice.marketer_dataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import po.MarketerPO;
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
		if(name.equals("pxr")) {
			List<MarketerPO> marketerPOList = new ArrayList<MarketerPO>();
			marketerPOList.add(new MarketerPO("pxr","12345678910","123456","1234",null,WorkerPosition.Marketer));
			return marketerPOList;
		}
		return null;
	}

	public MarketerPO findMarketerByID(String id) throws RemoteException {
		if (id.equals("1234"))
			return new MarketerPO("pxr",  "12345678910", "123456", id,new ImageIcon().getImage(), WorkerPosition.Marketer);
		else
			return null;
	}

	public List<MarketerPO> findAllMarketers() throws RemoteException {
		List<MarketerPO> marketerPOList = new ArrayList<MarketerPO>();
		marketerPOList.add(new MarketerPO("pxr","12345678910","123456","1234",null,WorkerPosition.Marketer));
		return marketerPOList;
	}

	public boolean deleteMarketer(MarketerPO marketerPO) throws RemoteException {
		return false;
	}
}