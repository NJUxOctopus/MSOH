package ui.controller;

import businesslogic.member_bl.MemberUtil;
import businesslogic.promotion_bl.Promotion;
import businesslogic.promotion_bl.PromotionUtil;
import businesslogicservice.member_blservice.MemberUtil_BLService;
import businesslogicservice.promotion_blservice.PromotionUtil_BLService;
import businesslogicservice.promotion_blservice.Promotion_BLService;
import ui.view.controllerservice.EditPromotion;
import util.PromotionType;
import util.ResultMessage;
import vo.OrderPriceVO;
import vo.OrderVO;
import vo.PromotionVO;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by apple on 16/11/10.
 */
public class EditPromotionController implements EditPromotion {
    private PromotionUtil_BLService promotionUtil_blService;
    private Promotion_BLService promotion_blService;
    private MemberUtil_BLService memberUtil_blService;

    public EditPromotionController() {
        promotionUtil_blService = new PromotionUtil();
        promotion_blService = new Promotion();
        memberUtil_blService = new MemberUtil();
    }

    /**
     * 增加酒店促销策略，这里就没有商圈的概念，目标酒店就是酒店本身
     *
     * @param promotionVO
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public ResultMessage addHotelPromotion(PromotionVO promotionVO) throws IOException, ClassNotFoundException {
        return promotion_blService.addHotelPromotion(promotionVO);
    }

    /**
     * 增加网站营销策略，只需要填写目标商圈，自动获得商圈内所有酒店，用分号隔开
     *
     * @param promotionVO
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public ResultMessage addWebPromotion(PromotionVO promotionVO) throws IOException, ClassNotFoundException {
        return promotion_blService.addWebPromotion(promotionVO);
    }

    /**
     * 修改酒店促销策略
     *
     * @param promotionVO
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public ResultMessage modifyHotelPromotion(PromotionVO promotionVO) throws IOException, ClassNotFoundException {
        return promotion_blService.modifyHotelPromotion(promotionVO);
    }

    /**
     * 修改网站营销策略
     *
     * @param promotionVO
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public ResultMessage modifyWebPromotion(PromotionVO promotionVO) throws IOException, ClassNotFoundException {
        return promotion_blService.modifyWebPromotion(promotionVO);
    }

    /**
     * 删除营销策略
     *
     * @param promotionID
     * @return
     * @throws RemoteException
     */
    @Override
    public ResultMessage deletePromotion(String promotionID) throws RemoteException {
        return promotion_blService.deletePromotion(promotionID);
    }

    /**
     * 根据促销策略id获得策略
     *
     * @param promotionID
     * @return
     * @throws RemoteException
     */
    public PromotionVO getSingle(String promotionID) throws RemoteException {
        return promotionUtil_blService.getSingle(promotionID);
    }

    /**
     * 根据酒店id获得促销策略
     *
     * @param hotelID
     * @param time
     * @return
     * @throws RemoteException
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public List<PromotionVO> getPromotionByHotelID(String hotelID, Timestamp time) throws RemoteException, ClassNotFoundException, IOException {
        return promotionUtil_blService.getPromotionByHotelID(hotelID, time);
    }

    /**
     * 得到企业列表
     *
     * @return
     * @throws RemoteException
     */
    public List<String> getCompany() throws RemoteException {
        return memberUtil_blService.getCompany();
    }

    /**
     * 根据促销策略类型和酒店ID获得促销策略
     *
     * @param promotionType
     * @param hotelID
     * @return
     * @throws RemoteException
     */
    public List<PromotionVO> getPromotionByTypeAndHotelID(PromotionType promotionType, String hotelID, Timestamp timestamp) throws IOException, ClassNotFoundException {
        return promotionUtil_blService.getPromotionByTypeAndHotelID(promotionType, hotelID, timestamp);
    }

    /**
     * 根据策略种类得到策略列表
     *
     * @param promotionType
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public List<PromotionVO> getPromotionByType(PromotionType promotionType, Timestamp timestamp) throws IOException, ClassNotFoundException {
        return promotionUtil_blService.getPromotionByType(promotionType, timestamp);
    }


}
