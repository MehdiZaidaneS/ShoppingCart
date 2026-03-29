import javafx.application.Application;

import java.util.*;

public class ShoppingCart {

    private int sumTotalCart;


    public int getTotal(){
        return sumTotalCart;
    }

    public void setSumTotalCart(){
        sumTotalCart = 0;
    }
    public int calculateTotal(int price, int quantity) {
        int sum = price * quantity;
        return sumTotalCart += sum;
    }



    public Locale getLocale(String language) {
        switch (language.toLowerCase()) {
            case "swedish":
                return new Locale("sv", "SE");
            case "finnish":
                return new Locale("fi", "FI");
            case "japanese":
                return new Locale("ja", "JP");
            case "arabic":
                return new Locale("ar", "AR");
            default:
                return new Locale("en", "US");
        }
    }

}
