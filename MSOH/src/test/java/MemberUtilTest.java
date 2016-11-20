import businesslogic.member_bl.Member;
import businesslogic.member_bl.MemberUtil;
import junit.framework.TestCase;
import util.MemberType;
import vo.MemberVO;

import java.rmi.RemoteException;

/**
 * Created by Pxr on 16/11/19.
 */
public class MemberUtilTest extends TestCase{
    MemberUtil memberUtil =new MemberUtil();
    public void getSingleTest()throws RemoteException{
        MemberVO memberVO1 = memberUtil.getSingle("320200000000000000");
        MemberVO memberVO2 = memberUtil.getSingle("12345678");
        MemberVO memberVO3 = memberUtil.getSingle("123");
        MemberVO memberVO4 = new MemberVO("320200000000000000", MemberType.NONMEMBER,0,null,null);
        MemberVO memberVO5 = new MemberVO("12345678", MemberType.ENTREPRISE,1,null,"NJU");
        assertEquals(memberVO1,memberVO4);
        assertEquals(memberVO2,memberVO5);
        assertEquals(memberVO3,null);
    }
}
