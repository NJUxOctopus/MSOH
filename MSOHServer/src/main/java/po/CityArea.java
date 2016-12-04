package po;

import javax.persistence.*;

/**
 * Created by zqh on 2016/12/3.
 */
@Entity
@Table(name = "city_area", schema = "msoh_database")
public class CityArea {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "city_area_id_seq")
    @SequenceGenerator(name = "city_area_id_seq", sequenceName = "city_area_id_seq", allocationSize = 1)
    @Column(name = "city_areaID")
    private int city_areaID;
    @Column(name = "cityname")
    private String city;
    @Column(name = "areaname")
    private String area;

    public CityArea() {
    }

    public CityArea(String city, String area) {
        this.city = city;
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
