package businesslogic.marketer_bl;

import businesslogicservice.marketer_blservice.MarketerUtil_BLService;
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
    private Marketer_DataService_Stub marketer_dataService_stub = new Marketer_DataService_Stub();

    /**
     * 根据营销人员ID得到营销人员
     * @param marketerID
     * @return
     * @throws RemoteException
     */
    public MarketerVO getSingle(String marketerID) throws RemoteException {
        if (marketerID.equals(""))
            //若ID为空
            return null;
        if (marketer_dataService_stub.findMarketerByID(marketerID) == null)
            //若无该营销人员
            return null;
        MarketerPO marketerPO = marketer_dataService_stub.findMarketerByID(marketerID);
        return new MarketerVO(marketerPO.getName(), marketerPO.getPhone(), marketerPO.getPassword(), marketerPO.getID(), marketerPO.getPicUrl());
    }

    /**
     * 根据名字得到营销人员的列表
     * @param name
     * @return
     * @throws RemoteException
     */
    public List<MarketerVO> getByName(String name) throws RemoteException {
        if(name.equals(""))
            //若名字为空
            return null;
        List<MarketerPO> marketerPOList = marketer_dataService_stub.findMarketerByName(name);
        if(marketerPOList==null||marketerPOList.isEmpty())
            //若列表为空
            return new ArrayList<MarketerVO>();
        List<MarketerVO> marketerVOList = new ArrayList<MarketerVO>();
        for(MarketerPO marketerPO:marketerPOList){
            marketerVOList.add(new MarketerVO(marketerPO.getName(), marketerPO.getPhone(), marketerPO.getPassword(),
                    marketerPO.getID(), marketerPO.getPicUrl()));

        }
        return marketerVOList;
    }

    /**
     * 得到所有的营销人员
     * @return
     * @throws RemoteException
     */
    public List<MarketerVO> getAll() throws RemoteException {
        List<MarketerPO> marketerPOList = marketer_dataService_stub.findAllMarketers();
        if(marketerPOList==null||marketerPOList.isEmpty())
            //若列表为空
            return new ArrayList<MarketerVO>();
        List<MarketerVO> marketerVOList = new ArrayList<MarketerVO>();
        for(MarketerPO marketerPO:marketerPOList){
            marketerVOList.add(new MarketerVO(marketerPO.getName(), marketerPO.getPhone(), marketerPO.getPassword(),
                    marketerPO.getID(), marketerPO.getPicUrl()));

        }
        return marketerVOList;
    }
}
