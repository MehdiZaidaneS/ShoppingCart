import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {

    private ShoppingCart sc;

    @BeforeEach
    void setUp() {
        sc = new ShoppingCart();
    }

    @Test
    void testCalculateTotal() {
        assertEquals(35, sc.calculateTotal(7, 5));

        sc.setSumTotalCart();
        assertEquals(0, sc.calculateTotal(0, 5));

        sc.setSumTotalCart();
        assertEquals(0, sc.calculateTotal(7, 0));

        sc.setSumTotalCart();
        sc.calculateTotal(3, 2); // 6
        sc.calculateTotal(4, 1); // +4 = 10
        assertEquals(10, sc.getTotal());
    }

    @Test
    void testSetSumTotalCart() {
        sc.calculateTotal(5, 5); // sum = 25
        sc.setSumTotalCart();
        assertEquals(0, sc.getTotal());
    }

    @Test
    void testGetLocale() {
        assertEquals(new Locale("fi", "FI"), sc.getLocale("finnish"));
        assertEquals(new Locale("sv", "SE"), sc.getLocale("swedish"));
        assertEquals(new Locale("ja", "JP"), sc.getLocale("japanese"));
        assertEquals(new Locale("ar", "AR"), sc.getLocale("arabic"));
        assertEquals(new Locale("en", "US"), sc.getLocale("english"));
        assertEquals(new Locale("en", "US"), sc.getLocale("unknown"));
    }

    @Test
    void testGetTotalInitial() {
        assertEquals(0, sc.getTotal());
    }
}