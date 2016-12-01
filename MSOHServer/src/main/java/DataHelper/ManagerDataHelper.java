package DataHelper;

import po.ManagerPO;

import java.util.List;

/**
 * Created by zqh on 2016/11/24.
 */
public interface ManagerDataHelper {
    public boolean modifyManager(ManagerPO managerPO);

    public ManagerPO findManagerByID(String ID);

    public List<ManagerPO> findAllManagers();

    public List<ManagerPO> findManagerByName(String name);
}
