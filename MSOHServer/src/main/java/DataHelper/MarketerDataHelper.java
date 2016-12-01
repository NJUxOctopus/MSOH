package DataHelper;

import po.MarketerPO;

import java.util.List;

/**
 * Created by zqh on 2016/11/24.
 */
public interface MarketerDataHelper {
    public boolean addMarketer(MarketerPO marketerPO);

    public boolean modifyMarketer(MarketerPO marketerPO);

    public boolean deleteMarketer(MarketerPO marketerPO);

    public List<MarketerPO> getMarketerByName(String name);

    public MarketerPO getMarketerByID(String ID);

    public List<MarketerPO> getAllMarketers();
}
