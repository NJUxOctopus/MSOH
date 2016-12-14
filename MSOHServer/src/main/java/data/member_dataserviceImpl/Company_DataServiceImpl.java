package data.member_dataserviceImpl;

import DataHelper.memberDataHelper.CompanyDataHelper;
import DataHelper.DataFactory;
import DataHelperImpl.DataFactoryImpl;
import dataservice.member_dataservice.Company_DataService;
import po.Company;
import util.DataUtil.CopyUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zqh on 2016/12/8.
 */
public class Company_DataServiceImpl implements Company_DataService {
    private CompanyDataHelper companyDataHelper;

    private DataFactory dataFactory;

    private static Company_DataServiceImpl company_dataService;

    /**
     * 提供给外界获取实例的方法，采用单例模式使该类构造方法私有化
     *
     * @return companyDataService的实例
     */
    public static Company_DataServiceImpl getInstance() {
        if (company_dataService == null) {
            company_dataService = new Company_DataServiceImpl();
        }
        return company_dataService;
    }

    private Company_DataServiceImpl() {
        dataFactory = new DataFactoryImpl();
        companyDataHelper = dataFactory.getCompanyDataHelper();
    }

    /**
     * 获得所有企业的名称
     *
     * @return 所有企业的名称
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public List<String> getAllCompanies() throws IOException, ClassNotFoundException {
        List<Company> list = companyDataHelper.getAllCompanies();

        List<String> companyList = new ArrayList<String>();

        for (Company company : list) {
            companyList.add(company.getCompanyName());
        }

        return CopyUtil.deepCopy(companyList);
    }
}
