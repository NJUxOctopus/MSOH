package po;

import data.marketer_dataserviceImpl.Marketer_DataServiceImpl;
import dataservice.marketer_dataservice.Marketer_DataService;
import org.junit.Test;
import util.WorkerPosition;

import java.rmi.RemoteException;

/**
 * Created by zqh on 2016/12/7.
 */
public class MarketerPOTest {
    @Test
    public void testSaveMarketer() throws RemoteException{
        MarketerPO marketerPO=new MarketerPO("陈营","320522201612082017","12036978541","123456","F:/chenying.png", WorkerPosition.Marketer);

        MarketerPO marketerPO1=new MarketerPO("孙销" ,"320581201612102017","12032115975","123456","F:/sunxiao.png",WorkerPosition.Marketer);

        Marketer_DataService marketer_dataService= Marketer_DataServiceImpl.getInstance();

//        marketer_dataService.addMarketer(marketerPO);

        marketer_dataService.addMarketer(marketerPO1);
    }
}
