package ui.view.controllerservice;

import vo.HotelVO;
import vo.RoomVO;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by apple on 16/11/10.
 */
public interface HotelInfo {
    public List<HotelVO> sortByPrice(List<HotelVO> list);

    public List<HotelVO> sortByStar(List<HotelVO> list);

    public List<HotelVO> sortByScore(List<HotelVO> list);

    public List<HotelVO> searchHotel (HotelVO hotelVO) throws RemoteException;

    public List<HotelVO> filter(String star, String name, String low, String high, Timestamp timestamp1, Timestamp timestamp2, String roomType, int roomNum, String area) throws RemoteException;

    public List<String> getAllCities() throws RemoteException;

    public List<String> getAreaByCity(String city) throws RemoteException;

    public RoomVO getBetweenDate(String hotelID, String roomName, Timestamp timestamp1, Timestamp timestamp2) throws RemoteException;
}
