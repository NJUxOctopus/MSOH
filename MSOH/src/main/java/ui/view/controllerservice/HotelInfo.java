package ui.view.controllerservice;

import vo.HotelVO;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by apple on 16/11/10.
 */
public interface HotelInfo {
    public List<HotelVO> sortByPrice(List<HotelVO> list);

    public List<HotelVO> sortByStar(List<HotelVO> list);

    public List<HotelVO> sortByScore(List<HotelVO> list);

    public List<HotelVO> searchHotel (HotelVO hotelVO) throws RemoteException;

    public List<String> getAllCities() throws RemoteException;

    public List<String> getAreaByCity(String city) throws RemoteException;

}
