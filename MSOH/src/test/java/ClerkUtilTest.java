import businesslogic.clerk_bl.ClerkUtil;
import junit.framework.TestCase;
import util.ResultMessage;
import vo.ClerkVO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pxr on 16/11/19.
 */
public class ClerkUtilTest extends TestCase{
    ClerkUtil clerkUtil = new ClerkUtil();
    public void testGetAll()throws RemoteException{
        List<ClerkVO> clerkVOList = clerkUtil.getAll();
        List<ClerkVO> clerkVOList1 = new ArrayList<ClerkVO>();
        clerkVOList1.add(new ClerkVO("pxr","12345678910","123456","320200000000000000",
                "","RUJIA","123"));
        assertEquals(clerkVOList.get(0).hotelID,clerkVOList1.get(0).hotelID);
    }

    public void testGetSingle()throws RemoteException{
        ClerkVO clerkVO = clerkUtil.getSingle("320200000000000000");
        ClerkVO clerkVO1 = new ClerkVO("pxr","12345678910","123456","320200000000000000",
                "","RUJIA","123");
        ClerkVO clerkVO2 = clerkUtil.getSingle("123");
        assertEquals(clerkVO.hotelID,clerkVO1.hotelID);
        assertEquals(null,clerkVO2);
    }

    public void testGetByName()throws RemoteException{
        List<ClerkVO> clerkVOList = clerkUtil.getByName("pxr");
        List<ClerkVO> clerkVOList1 = new ArrayList<ClerkVO>();
        clerkVOList1.add(new ClerkVO("pxr","12345678910","123456","320200000000000000",
               "","RUJIA","123"));
        clerkVOList1.add(new ClerkVO("pxr","12345678910","1234567","320200000000000001",
               "","RUJIA","123"));
        List<ClerkVO> clerkVOList2 = clerkUtil.getByName("zqh");
        assertEquals(clerkVOList.get(0).hotelID,clerkVOList1.get(0).hotelID);
        assertEquals(null,clerkVOList2);
    }
}
