package data.hotel_dataserviceImpl;

import DataHelper.hotelDataHelper.CityDataHelper;
import DataHelper.DataFactory;
import DataHelperImpl.DataFactoryImpl;
import dataservice.hotel_dataservice.City_DataService;
import po.CityArea;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zqh on 2016/12/3.
 */
public class City_DataServiceImpl implements City_DataService {
    private CityDataHelper cityDataHelper;

    private DataFactory dataFactory;

    private static City_DataServiceImpl city_dataService;

    /**
     * 提供给外界获取实例的方法，采用单例模式使该类构造方法私有化
     *
     * @return cityDataService的实例
     */
    public static City_DataServiceImpl getInstance() {
        if (city_dataService == null) {
            city_dataService = new City_DataServiceImpl();
        }
        return city_dataService;
    }

    private City_DataServiceImpl() {
        dataFactory = new DataFactoryImpl();
        cityDataHelper = dataFactory.getCityDataHelper();
    }

    /**
     * 获得所有城市
     *
     * @return 所有城市名字组成的列表
     * @throws RemoteException
     */
    public List<String> getAllCities() throws RemoteException {
        List<CityArea> cityAreaList = cityDataHelper.getAllAreas();

        List<String> cityList = new ArrayList<String>();

        for (CityArea cityArea : cityAreaList) {
            if (!cityList.contains(cityArea.getCity())) {
                cityList.add(cityArea.getCity());
            } else {
                continue;
            }
        }

        return cityList;
    }

    /**
     * 根据城市名获得商圈名
     *
     * @param city
     * @return 对应城市中的所有商圈名
     * @throws RemoteException
     */
    public List<String> getAreaByCity(String city) throws RemoteException {
        List<CityArea> cityAreaList = cityDataHelper.getAreaByCity(city);

        List<String> areaList = new ArrayList<String>();

        for (CityArea cityArea : cityAreaList) {
            areaList.add(cityArea.getArea());
        }

        return areaList;
    }
}
