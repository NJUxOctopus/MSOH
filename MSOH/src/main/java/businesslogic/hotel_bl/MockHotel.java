package businesslogic.hotel_bl;

/**
 * Created by Pxr on 16/11/13.
 */
public class MockHotel extends Hotel{
    double number;
    double price;

    public MockHotel(double price,double number){
        this.price = price;
        this.number=number;
    }

    public double getPrice(){
        return price*number;
    }
}
