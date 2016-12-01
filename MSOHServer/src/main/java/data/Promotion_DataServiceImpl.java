package data;

import DataHelper.DataFactory;
import DataHelper.PromotionDataHelper;
import DataHelperImpl.DataFactoryImpl;
import dataservice.promotion_dataservice.Promotion_DataService;
import po.PromotionPO;
import util.CopyUtil;

import java.io.IOException;
import java.rmi.RemoteException;
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
     * 获得所有促销策略
     *
     * @return 所有促销策略组成的列表
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public List<PromotionPO> getAllPromotions() throws IOException, ClassNotFoundException {
        List<PromotionPO> list = promotionDataHelper.getAllPromotions();

        if (list == null) {
            return null;
        }

        List<PromotionPO> returnPromotionList = CopyUtil.deepCopy(list);

        return returnPromotionList;
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
}
