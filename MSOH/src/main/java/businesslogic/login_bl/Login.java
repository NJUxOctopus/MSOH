package businesslogic.login_bl;

import businesslogicservice.login_blservice.Login_BLService;
import dataservice.clerk_dataservice.Clerk_DataService;
import dataservice.customer_dataservice.Customer_DataService_Stub;
import dataservice.manager_dataservice.Manager_DataService_Stub;
import dataservice.marketer_dataservice.Marketer_DataService_Stub;
import rmi.RemoteHelper;
import util.ResultMessage;

import java.rmi.RemoteException;

/**
 * Created by pxr on 2016/11/13.
 */
public class Login implements Login_BLService {
    /**
     * 登录
     * @param ID
     * @param password
     * @return
     * @throws RemoteException
     */
    public ResultMessage login(String ID, String password) throws RemoteException {
        Clerk_DataService clerk_dataService_stub = RemoteHelper.getInstance().getClerkDataService();
        Manager_DataService_Stub manager_dataService_stub = new Manager_DataService_Stub();
        Marketer_DataService_Stub marketer_dataService_stub = new Marketer_DataService_Stub();
        Customer_DataService_Stub customer_dataService_stub = new Customer_DataService_Stub();
        if (ID.equals("") || password.equals("")) {
            return ResultMessage.Blank;//若账号密码为空
        } else if (clerk_dataService_stub.findClerkByID(ID) == null &&
                manager_dataService_stub.findManager(ID) == null &&
                customer_dataService_stub.findCustomerByID(ID) == null &&
                marketer_dataService_stub.findMarketerByID(ID) == null) {
            return ResultMessage.Login_NoUser;//若未找到该ID的用户，返回无该用户
        } else if (clerk_dataService_stub.findClerkByID(ID) != null &&
                clerk_dataService_stub.findClerkByID(ID).getPassword().equals(password)) {
            return ResultMessage.Login_ClerkSuccess;//返回工作人员登录成功
        } else if (manager_dataService_stub.findManager(ID) != null &&
                manager_dataService_stub.findManager(ID).getPassword().equals(password)) {
            return ResultMessage.Login_ManagerSuccess;//返回管理人员登录成功
        } else if (marketer_dataService_stub.findMarketerByID(ID) != null &&
                marketer_dataService_stub.findMarketerByID(ID).getPassword().equals(password)) {
            return ResultMessage.Login_MarketerSuccess;//返回营销人员登录成功
        } else if (customer_dataService_stub.findCustomerByID(ID) != null &&
                customer_dataService_stub.findCustomerByID(ID).getPassword().equals(password)) {
            return ResultMessage.Login_CustomerSuccess;//返回用户登录成功
        } else {
            return ResultMessage.Login_WrongPassword;//否则返回密码错误
        }
    }

}
