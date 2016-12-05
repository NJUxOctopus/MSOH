package dataservice.hotel_dataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by zqh on 2016/12/3.
 */
public interface City_DataService extends Remote {
    // 获得所有城市
    public List<String> getAllCities() throws RemoteException;

    // 根据城市获得该城市中商圈
    public List<String> getAreaByCity(String city) throws RemoteException;
}
