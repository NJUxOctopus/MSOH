package data.promotion_dataserviceImpl;

import DataHelper.DataFactory;
import DataHelper.promotionDataHelper.PromotionDataHelper;
import DataHelperImpl.DataFactoryImpl;
import dataservice.promotion_dataservice.Promotion_DataService;
import po.PromotionPO;
import util.CopyUtil;
import util.PromotionType;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zqh on 2016/12/1.
 */
public class Promotion_DataServiceImpl implements Promotion_DataService {
    private PromotionDataHelper promotionDataHelper;

    private DataFactory dataFactory;

    private static Promotion_DataServiceImpl promotion_dataService;

    /**
     * 提供给外界获取实例的方法，采用单例模式使该类构造方法私有化
     *
     * @return promotionDataService的实例
     */
    public static Promotion_DataServiceImpl getInstance() {
        if (null == promotion_dataService) {
            promotion_dataService = new Promotion_DataServiceImpl();
        }
        return promotion_dataService;
    }

    private Promotion_DataServiceImpl() {
        dataFactory = new DataFactoryImpl();
        promotionDataHelper = dataFactory.getPromotionDataHelper();
    }

    /**
     * 新增促销策略
     *
     * @param po
     * @throws RemoteException
     */
    public boolean addPromotion(PromotionPO po) throws RemoteException {
        return promotionDataHelper.addPromotion(po);
    }

    /**
     * 根据促销策略ID获得促销策略
     *
     * @param promotionID
     * @return 与ID匹配的促销策略
     * @throws RemoteException
     */
    public PromotionPO getPromotion(int promotionID) throws RemoteException {
        PromotionPO promotionPO = promotionDataHelper.getPromotion(promotionID);

        if (promotionPO == null) {
            return null;
        }

        return (PromotionPO) promotionPO.clone();
    }

    /**
     * 获得所有网站营销策略列表
     *
     * @return 所有网站营销策略的列表
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public List<PromotionPO> getAllWebPromotions() throws IOException, ClassNotFoundException {
        List<PromotionPO> promotionList = getAllPromotions();

        if (promotionList.isEmpty() || promotionList == null) {
            return promotionList;
        }

        List<PromotionPO> webPromotionList = new ArrayList<PromotionPO>();

        for (PromotionPO promotionPO : promotionList) {
            if (promotionPO.getPromotionType().equals(PromotionType.WebPromotion_Holiday) || promotionPO.getPromotionType().equals(PromotionType.WebPromotion_VIP)) {
                webPromotionList.add(promotionPO);
            }
        }

        if (webPromotionList.isEmpty() || webPromotionList == null) {
            return webPromotionList;
        }

        return webPromotionList;
    }

    /**
     * 获得适用于某酒店的促销策略
     *
     * @param hotelID
     * @return 适用于该酒店的促销策略
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public List<PromotionPO> getPromotionByHotelID(String hotelID) throws IOException, ClassNotFoundException {
        List<PromotionPO> promotionList = getAllPromotions();

        if (promotionList.isEmpty() || promotionList == null) {
            return promotionList;
        }

        List<PromotionPO> hotelPromotionList = new ArrayList<PromotionPO>();

        for (PromotionPO promotion : promotionList) {
            // 促销策略适用于所有酒店，或适用酒店中包含该酒店
            if (promotion.getTargetHotel().equals("All") || promotion.getTargetHotel().contains(hotelID)) {
                hotelPromotionList.add(promotion);
            }
        }

        if (hotelPromotionList.isEmpty() || hotelPromotionList == null) {
            return hotelPromotionList;
        }

        return hotelPromotionList;
    }

    /**
     * 删除促销策略
     *
     * @param promotionPO
     * @throws RemoteException
     */
    public boolean deletePromotion(PromotionPO promotionPO) throws RemoteException {
        return promotionDataHelper.deletePromotion(promotionPO);
    }

    /**
     * 更新促销策略
     *
     * @param promotionPO
     * @throws RemoteException
     */
    public boolean modifyPromotion(PromotionPO promotionPO) throws RemoteException {
        return promotionDataHelper.modifyPromotion(promotionPO);
    }

    /**
     * 获得所有促销策略
     * 私有方法
     *
     * @return 所有促销策略组成的列表
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private List<PromotionPO> getAllPromotions() throws IOException, ClassNotFoundException {
        List<PromotionPO> list = promotionDataHelper.getAllPromotions();

        if (list == null || list.isEmpty()) {
            return list;
        }

        List<PromotionPO> returnPromotionList = CopyUtil.deepCopy(list);

        return returnPromotionList;
    }

    /**
     * 根据促销策略类型获得促销策略
     *
     * @param promotionType
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public List<PromotionPO> getPromotionByPromotionType(PromotionType promotionType) throws IOException, ClassNotFoundException {
        List<PromotionPO> list = promotionDataHelper.getAllPromotions();

        if (list == null || list.isEmpty()) {
            return list;
        }

        List<PromotionPO> certainTypePromotionList = new ArrayList<PromotionPO>();

        for (PromotionPO promotion : list) {
            if (promotion.getPromotionType().equals(promotionType)) {
                certainTypePromotionList.add(promotion);
            }
        }

        if (certainTypePromotionList.isEmpty()) {
            return certainTypePromotionList;
        } else {
            return CopyUtil.deepCopy(certainTypePromotionList);
        }
    }
}
