package businesslogic.login_bl;

import businesslogicservice.login_blservice.Login_BLService;
import dataservice.clerk_dataservice.Clerk_DataService;
import dataservice.customer_dataservice.Customer_DataService;
import dataservice.customer_dataservice.Customer_DataService_Stub;
import dataservice.manager_dataservice.Manager_DataService;
import dataservice.manager_dataservice.Manager_DataService_Stub;
import dataservice.marketer_dataservice.Marketer_DataService;
import dataservice.marketer_dataservice.Marketer_DataService_Stub;
import rmi.RemoteHelper;
import util.DataFormat;
import util.ResultMessage;

import java.io.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pxr on 2016/11/13.
 */
public class Login implements Login_BLService {
    /**
     * 登录
     *
     * @param ID
     * @param password
     * @return
     * @throws RemoteException
     */
    public ResultMessage login(String ID, String password) throws RemoteException {
        Clerk_DataService clerk_dataService = RemoteHelper.getInstance().getClerkDataService();
        Manager_DataService manager_dataService = RemoteHelper.getInstance().getManagerDataService();
        Marketer_DataService marketer_dataService = RemoteHelper.getInstance().getMarketerDataService();
        Customer_DataService customer_dataService = RemoteHelper.getInstance().getCustomerDataService();
        if (ID.equals("") || password.equals("")) {
            return ResultMessage.Blank;//若账号密码为空
        } else if (clerk_dataService.findClerkByID(ID) == null &&
                manager_dataService.findManager(ID) == null &&
                customer_dataService.findCustomerByID(ID) == null &&
                marketer_dataService.findMarketerByID(ID) == null) {
            return ResultMessage.Login_NoUser;//若未找到该ID的用户，返回无该用户
        } else if (clerk_dataService.findClerkByID(ID) != null &&
                clerk_dataService.findClerkByID(ID).getPassword().equals(password)) {
            return ResultMessage.Login_ClerkSuccess;//返回工作人员登录成功
        } else if (manager_dataService.findManager(ID) != null &&
                manager_dataService.findManager(ID).getPassword().equals(password)) {
            return ResultMessage.Login_ManagerSuccess;//返回管理人员登录成功
        } else if (marketer_dataService.findMarketerByID(ID) != null &&
                marketer_dataService.findMarketerByID(ID).getPassword().equals(password)) {
            return ResultMessage.Login_MarketerSuccess;//返回营销人员登录成功
        } else if (customer_dataService.findCustomerByID(ID) != null &&
                customer_dataService.findCustomerByID(ID).getPassword().equals(password)) {
            return ResultMessage.Login_CustomerSuccess;//返回用户登录成功
        } else {
            return ResultMessage.Login_WrongPassword;//否则返回密码错误
        }
    }

    /**
     * 记住密码
     *
     * @param ID
     * @param password
     * @return
     * @throws RemoteException
     */
    public ResultMessage rememberPassword(String ID, String password) throws RemoteException {
        File file = new File("password.txt");

        try {
            if(!file.exists())
                file.createNewFile();
            //记住密码时先判断是否已经记住该密码
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                String idFile = str.split(" ")[0];//文件中存储的账号
                String idDecode = DataFormat.reCode(idFile); //文件中的账号解码后
                if (idDecode.equals(ID)) {//若输入的账号等于解码后的账号
                    return ResultMessage.Password_isRemembered;
                }
            }
            //若账号未被记住
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,true)));
            String idCode = DataFormat.code(ID);//加密的ID
            String passwordCode = DataFormat.code(password);//加密的密码
            bufferedWriter.write(idCode + " ");//存入文件用空格分开账号密码
            bufferedWriter.write(passwordCode + "\n");
            bufferedWriter.close();
            return ResultMessage.Password_rememberSuccess;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return ResultMessage.Fail;
    }

    /**
     * 取消记住密码
     *
     * @param ID
     * @return
     * @throws RemoteException
     */
    public ResultMessage cancelRemPassword(String ID) throws RemoteException {
        File file = new File("password.txt");
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = null;
            List<String> list = new ArrayList<String>();
            while ((line = bufferedReader.readLine()) != null) {
                if (!DataFormat.reCode(line.split(" ")[0]).equals(ID))
                    list.add(line);
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file,false);
            for(String s:list){
                fileOutputStream.write(s.getBytes());
            }
            return ResultMessage.Password_CancelRememberSuccess;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return ResultMessage.Fail;
    }

    /**
     * 若账号已被记住，选择账号后直接显示密码，若未被记住返回空
     * @param ID
     * @return
     * @throws RemoteException
     */
    public String autoLogin(String ID) throws RemoteException {
        File file = new File("password.txt");
        try {
            //记住密码时先判断是否已经记住该密码
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                String idFile = str.split(" ")[0];//文件中存储的账号
                String idDecode = DataFormat.reCode(idFile); //文件中的账号解码后
                if (idDecode.equals(ID)) {//若输入的账号等于解码后的账号
                    return DataFormat.reCode(str.split(" ")[1]);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
