package DataHelper;

import po.ClerkPO;

import java.util.List;

/**
 * Created by zqh on 2016/11/24.
 */
public interface ClerkDataHelper {
    public void addClerk(ClerkPO clerkPO);

    public void modifyClerk(ClerkPO clerkPO);

    public void deleteClerk(ClerkPO clerkPO);

    public ClerkPO getClerkByID(String ID);

    public List<ClerkPO> getClerkByName(String name);

    public List<ClerkPO> getAllClerks();
}
