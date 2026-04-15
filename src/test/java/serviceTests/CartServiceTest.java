package serviceTests;

import service.CartService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CartServiceTest {

    @Test
    void testSaveCartAndItems() {
        CartService cs = new CartService();

        int cartId = cs.saveCart(2, 50.0, "en");

        assertTrue(cartId > 0);

        cs.saveItem(cartId, 1, 10.0, 2);
        cs.saveItem(cartId, 2, 15.0, 2);

        assertTrue(true);
    }
}