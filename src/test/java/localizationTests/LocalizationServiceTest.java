package localizationTests;

import localization.LocalizationService;
import org.junit.jupiter.api.Test;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class LocalizationServiceTest {

    @Test
    void testGetEnglishStrings() {
        LocalizationService ls = new LocalizationService();

        Map<String, String> map = ls.getStrings("en");

        assertNotNull(map);
        assertTrue(map.containsKey("prompt1"));
        assertEquals("Enter number of items:", map.get("prompt1"));
    }

    @Test
    void testGetFinnishStrings() {
        LocalizationService ls = new LocalizationService();

        Map<String, String> map = ls.getStrings("fi");

        assertEquals("Kokonaishinta:", map.get("prompt4"));
    }
}