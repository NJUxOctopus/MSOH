package dataservice.hotel_dataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by zqh on 2016/12/3.
 */
public interface City_DataService extends Remote {
    public List<String> getAllCities() throws RemoteException;

    public List<String> getAreaByCity(String city) throws RemoteException;
}
