package cart;

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
        sumTotalCart += sum;
        return sumTotalCart;
    }



    public Locale getLocale(String language) {
        switch (language.toLowerCase()) {
            case "swedish":
                return Locale.of("sv", "SE");
            case "finnish":
                return Locale.of("fi", "FI");
            case "japanese":
                return Locale.of("ja", "JP");
            case "arabic":
                return Locale.of("ar", "AR");
            default:
                return Locale.of("en", "US");
        }
    }

}
