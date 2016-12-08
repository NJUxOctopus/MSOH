package po;

import javax.persistence.*;

/**
 * Created by zqh on 2016/12/8.
 */
@Entity
@Table(name = "companysheet",schema = "msoh_database")
public class Company {
    // 企业名称
    @Column(name = "companyName")
    private String companyName;
    // 企业ID
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_id_seq")
    @SequenceGenerator(name = "company_id_seq", sequenceName = "company_id_seq", allocationSize = 1)
    @Column(name = "companyID")
    private int companyID;

    public Company(){}

    public Company(String companyName){
        this.companyName=companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
