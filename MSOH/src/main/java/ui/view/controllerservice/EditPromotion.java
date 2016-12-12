package ui.view.controllerservice;

import util.PromotionType;
import util.ResultMessage;
import vo.PromotionVO;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by apple on 16/11/10.
 */
public interface EditPromotion {
    public ResultMessage addHotelPromotion(PromotionVO promotionVO) throws IOException, ClassNotFoundException;

    public ResultMessage addWebPromotion(PromotionVO promotionVO);

    public ResultMessage modifyHotelPromotion(PromotionVO promotionVO) throws IOException, ClassNotFoundException;

    public ResultMessage deletePromotion(String promotionID) throws RemoteException;

    public PromotionVO getSingle(String promotionID) throws RemoteException;

    public List<PromotionVO> getPromotionByHotelID(String hotelID, Timestamp time) throws RemoteException, ClassNotFoundException, IOException ;

    public List<String> getCompany() throws RemoteException;

    public List<PromotionVO> getPromotionByTypeAndHotelID(PromotionType promotionType, String hotelID, Timestamp timestamp) throws IOException, ClassNotFoundException;


    }
