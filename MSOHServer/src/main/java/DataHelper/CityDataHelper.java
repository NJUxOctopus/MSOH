package DataHelper;

import po.CityArea;

import java.util.List;

/**
 * Created by zqh on 2016/12/3.
 */
public interface CityDataHelper {
    public List<CityArea> getAllAreas();

    public List<CityArea> getAreaByCity(String city);
}
