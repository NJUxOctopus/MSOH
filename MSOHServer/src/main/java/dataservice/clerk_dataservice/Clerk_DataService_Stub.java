package dataservice.clerk_dataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import po.ClerkPO;
import util.ResultMessage;
import util.WorkerPosition;

import javax.swing.*;

/**
 * @author ÷‹«ﬂ∫≠
 */
public class Clerk_DataService_Stub implements Clerk_DataService {


    @Override
    public boolean addClerk(ClerkPO clerkPO) throws RemoteException {
        return false;
    }

    @Override
    public boolean modifyClerk(ClerkPO clerkPO) throws RemoteException {
        return false;
    }

    @Override
    public List<ClerkPO> findClerkByName(String name) throws RemoteException {
        return null;
    }

    @Override
    public ClerkPO findClerkByID(String id) throws RemoteException {
        if(id=="320200000000000000")
            return new ClerkPO("pxr","12345678910","123456","320200000000000000","RUJIA","123",
                    WorkerPosition.Clerk,new ImageIcon().getImage());
        else
            return null;
    }

    @Override
    public List<ClerkPO> findAllClerks() throws RemoteException {
        return null;
    }

    @Override
    public boolean deleteClerk(ClerkPO clerkPO) throws RemoteException {
        return false;
    }


}
