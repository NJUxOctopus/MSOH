package dataservice.member_dataservice;

import java.io.IOException;
import java.util.List;

/**
 * Created by zqh on 2016/12/8.
 */
public interface Company_DataService {
    public List<String> getAllCompanies() throws IOException,ClassNotFoundException;
}
