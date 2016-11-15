package dataservice.manager_dataservice;

import po.ManagerPO;
import util.ResultMessage;

/**
 * @author åX¿ÂÓî
 */
public interface Manager_DataService {
    public ResultMessage modify(ManagerPO po);

    public ManagerPO find(String ID);
}
