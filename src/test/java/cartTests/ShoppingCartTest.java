package cartTests;

import cart.ShoppingCart;
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
        assertEquals(Locale.of("fi", "FI"), sc.getLocale("finnish"));
        assertEquals(Locale.of("sv", "SE"), sc.getLocale("swedish"));
        assertEquals(Locale.of("ja", "JP"), sc.getLocale("japanese"));
        assertEquals(Locale.of("ar", "AR"), sc.getLocale("arabic"));
        assertEquals(Locale.of("en", "US"), sc.getLocale("english"));
        assertEquals(Locale.of("en", "US"), sc.getLocale("unknown"));
    }

    @Test
    void testGetTotalInitial() {
        assertEquals(0, sc.getTotal());
    }
}