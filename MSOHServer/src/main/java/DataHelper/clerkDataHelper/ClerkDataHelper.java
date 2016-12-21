package DataHelper.clerkDataHelper;

import po.ClerkPO;

import java.util.List;

/**
 * Created by zqh on 2016/11/24.
 */
public interface ClerkDataHelper {
    public boolean addClerk(ClerkPO clerkPO);

    public boolean modifyClerk(ClerkPO clerkPO);

    public boolean deleteClerk(ClerkPO clerkPO);

    public ClerkPO getClerkByID(String ID);

    public List<ClerkPO> getClerkByName(String name);

    public List<ClerkPO> getAllClerks();

    public ClerkPO getClerkByHotelID(String hotelID);
}
