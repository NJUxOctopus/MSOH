package businesslogicservice.hotelUtil_blservice;

import vo.CommentVO;
import vo.DailyRoomInfoVO;
import vo.HotelVO;
import vo.RoomVO;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by apple on 16/11/10.
 */
public interface HotelUtil_BLService {
    public List<HotelVO> getAll()throws RemoteException;

    public List<HotelVO> sortByPrice(List<HotelVO> list)throws RemoteException;

    public List<HotelVO> sortByStar(List<HotelVO> list)throws RemoteException;

    public List<HotelVO> sortByScore(List<HotelVO> list)throws RemoteException;

    public HotelVO getByID (String ID)throws RemoteException;

    public List<HotelVO> getByName (String name)throws RemoteException;

    public List<HotelVO> getByArea (String area)throws RemoteException;

    public RoomVO getRoomByName(String hotelID, String roomName)throws RemoteException;

    public List<CommentVO> getComment(String hotelID)throws RemoteException;

    public List<DailyRoomInfoVO> getDailyRoomInfo(String hotelID)throws RemoteException;

}
