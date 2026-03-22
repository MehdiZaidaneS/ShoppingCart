import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {


    @Test
    void testCalculateTotal(){
        int total = 0;
        assertEquals(35,ShoppingCart.calculateTotal(7,5, total));
    }

    @Test
    void testGetLocale(){
        assertEquals(new Locale("fi", "FI"), ShoppingCart.getLocale("finnish"));
    }
}