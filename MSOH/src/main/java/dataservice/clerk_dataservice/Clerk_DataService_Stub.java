package dataservice.clerk_dataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import po.ClerkPO;
import util.ResultMessage;
import util.WorkerPosition;

import javax.swing.*;

/**
 * @author ���ߺ�
 */
public class Clerk_DataService_Stub implements Clerk_DataService {

    public boolean addClerk(ClerkPO clerkPO) throws RemoteException {
        return false;
    }

    public boolean modifyClerk(ClerkPO clerkPO) throws RemoteException {
        return false;
    }

    public List<ClerkPO> findClerkByName(String name) throws RemoteException {
        List<ClerkPO> clerkPOList = new ArrayList<ClerkPO>();
        clerkPOList.add(new ClerkPO("pxr", "12345678910", "123456", "320200000000000000", "RUJIA", "123",
                WorkerPosition.Clerk, ""));
        clerkPOList.add(new ClerkPO("pxr", "12345678910", "1234567", "320200000000000001", "RUJIA", "123",
                WorkerPosition.Clerk, ""));
        if (name.equals("pxr"))
            return clerkPOList;
        else
            return null;
    }

    public ClerkPO findClerkByID(String id) throws RemoteException {
        if (id.equals("320200000000000000"))
            return new ClerkPO("pxr", "12345678910", "123456", "320200000000000000", "RUJIA", "123",
                    WorkerPosition.Clerk, "");
        else
            return null;
    }

    public List<ClerkPO> findAllClerks() throws RemoteException {
        List<ClerkPO> clerkPOList = new ArrayList<ClerkPO>();
        clerkPOList.add(new ClerkPO("pxr", "12345678910", "123456", "320200000000000000", "RUJIA", "123",
                WorkerPosition.Clerk, ""));
        return clerkPOList;
    }

    public boolean deleteClerk(ClerkPO clerkPO) throws RemoteException {
        return false;
    }
}
