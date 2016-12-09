package dataservice.member_dataservice;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by zqh on 2016/12/8.
 */
public interface Company_DataService extends Remote{
    public List<String> getAllCompanies() throws RemoteException;
}
