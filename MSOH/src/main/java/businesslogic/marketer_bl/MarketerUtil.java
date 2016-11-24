package businesslogic.marketer_bl;

import businesslogicservice.marketerUtil_blservice.MarketerUtil_BLService;
import dataservice.marketer_dataservice.Marketer_DataService_Stub;
import po.MarketerPO;
import vo.MarketerVO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Pxr on 16/11/23.
 */
public class MarketerUtil implements MarketerUtil_BLService {
    Marketer_DataService_Stub marketer_dataService_stub = new Marketer_DataService_Stub();

    public MarketerVO getSingle(String marketerID) throws RemoteException {
        if (marketerID.equals(""))
            return null;
        if (marketer_dataService_stub.findMarketerByID(marketerID) == null)
            return null;
        MarketerPO marketerPO = marketer_dataService_stub.findMarketerByID(marketerID);
        return new MarketerVO(marketerPO.getName(), marketerPO.getPhone(), marketerPO.getPassword(), marketerPO.getID(), marketerPO.getPic());
    }

    public List<MarketerVO> getByName(String name) throws RemoteException {
        if(name.equals(""))
            return null;
        List<MarketerPO> marketerPOList = marketer_dataService_stub.findMarketerByName(name);
        if(marketerPOList==null)
            return null;
        List<MarketerVO> marketerVOList = new ArrayList<MarketerVO>();
        Iterator iterator = marketerPOList.iterator();
        while(iterator.hasNext()){
            MarketerPO marketerPO = (MarketerPO)iterator.next();
            marketerVOList.add(new MarketerVO(marketerPO.getName(), marketerPO.getPhone(), marketerPO.getPassword(),
                    marketerPO.getID(), marketerPO.getPic()));

        }
        return marketerVOList;
    }

    public List<MarketerVO> getAll() throws RemoteException {
        List<MarketerPO> marketerPOList = marketer_dataService_stub.findAllMarketers();
        if(marketerPOList==null)
            return null;
        List<MarketerVO> marketerVOList = new ArrayList<MarketerVO>();
        Iterator iterator = marketerPOList.iterator();
        while(iterator.hasNext()){
            MarketerPO marketerPO = (MarketerPO)iterator.next();
            marketerVOList.add(new MarketerVO(marketerPO.getName(), marketerPO.getPhone(), marketerPO.getPassword(),
                    marketerPO.getID(), marketerPO.getPic()));

        }
        return marketerVOList;
    }
}
