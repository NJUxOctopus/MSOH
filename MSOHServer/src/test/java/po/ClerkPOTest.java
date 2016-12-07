package po;

import DataHelper.ClerkDataHelper;
import DataHelperImpl.ClerkDataHelperSQLImpl;
import data.Clerk_DataServiceImpl;
import dataservice.clerk_dataservice.Clerk_DataService;
import org.hibernate.Session;
import org.junit.Test;
import util.HibernateUtil;
import util.WorkerPosition;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by zqh on 2016/11/23.
 */
public class ClerkPOTest {
    @Test
    public void testSaveClerkPO() throws RemoteException{
        Clerk_DataService clerk_dataService= Clerk_DataServiceImpl.getInstance();

        ClerkPO clerkPO=new ClerkPO("范冰冰","13501326699","987654","320581190011223111","南京夜泊秦淮君亭酒店","10000001",WorkerPosition.Clerk,"F:/fanbingbing.png");
        ClerkPO clerkPO1=new ClerkPO("郁可唯","13501236969","ykw888520","320581199701016002","南京米可酒店","10000002",WorkerPosition.Clerk,"F:/yukewei.png");
        ClerkPO clerkPO2=new ClerkPO("李晨","13562301555","lclove520fbb","320581199701016003","南京涵田城市酒店","10000003",WorkerPosition.Clerk,"F:/lichen.png");
        ClerkPO clerkPO3=new ClerkPO("张震岳","13013685203","zzyintaibei","320581199701010004","南京新地酒店","10000004",WorkerPosition.Clerk,"F:/zhangzhenyue.png");
        ClerkPO clerkPO4=new ClerkPO("马伊琍","12013014567","myl2016","320581199701010005","南京杏苑宾馆","10000005",WorkerPosition.Clerk,"F:/mayili.png");
        ClerkPO clerkPO5=new ClerkPO("梁静茹","15016978932","ljr2016","320581199701010006","南京古南都饭店","10000006",WorkerPosition.Clerk,"F:/liangjingru.png");
        ClerkPO clerkPO6=new ClerkPO("陈粒","12036974102","chenlichenli","320581199701010007","南京金丝利喜来登酒店","10000007",WorkerPosition.Clerk,"F:/chenli.png");
        ClerkPO clerkPO7=new ClerkPO("莫文蔚","13013952301","momo2016","320581199701010008","南京苏宁环球套房饭店","10000008",WorkerPosition.Clerk,"F:/mowenwei.png");
        ClerkPO clerkPO8=new ClerkPO("杨颖","12036523142","angelababy","320581199701010009","桔子水晶酒店","10000009",WorkerPosition.Clerk,"F:/yangying.png");
        ClerkPO clerkPO9=new ClerkPO("容祖儿","12023659874","rongzuer","320581199701010010","南京金陵饭店","10000010",WorkerPosition.Clerk,"F:/rongzuer.png");
        ClerkPO clerkPO10=new ClerkPO("杨宗纬","12036985236","yzw2016","320581199701011001","杭州友好饭店","10000011",WorkerPosition.Clerk,"F:/yangzongwei.png");
        ClerkPO clerkPO11=new ClerkPO("陈小春","15963201010","chenxc2016","320581199701011002","罗莱夏朵·杭州湖边邨酒店","10000012",WorkerPosition.Clerk,"F:chenxiaochun.png");
        ClerkPO clerkPO12=new ClerkPO("王力宏","15298720036","wanglihong","320581199701011003","杭州马可波罗假日酒店","10000013",WorkerPosition.Clerk,"F:/wanglihong.png");
        ClerkPO clerkPO13=new ClerkPO("那英","12332659874","naying2016","320581199701012001","香港朗廷酒店","10000014",WorkerPosition.Clerk,"F:/naying.png");



        clerk_dataService.addClerk(clerkPO13);
    }
}
