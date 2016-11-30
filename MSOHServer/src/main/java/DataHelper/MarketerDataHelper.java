package DataHelper;

import po.MarketerPO;

import java.util.List;

/**
 * Created by zqh on 2016/11/24.
 */
public interface MarketerDataHelper {
    public void addMarketer(MarketerPO marketerPO);

    public void modifyMarketer(MarketerPO marketerPO);

    public void deleteMarketer(MarketerPO marketerPO);

    public List<MarketerPO> getMarketerByName(String name);

    public MarketerPO getMarketerByID(String ID);

    public List<MarketerPO> getAllMarketers();
}
