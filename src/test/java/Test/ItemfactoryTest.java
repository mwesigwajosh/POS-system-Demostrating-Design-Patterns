package Test;
import com.pos.item.Item;
import com.pos.item.Itemfactory;
import org.junit.Test;
import static org.junit.Assert.*;

public class ItemfactoryTest {

    @Test
    public void testGetItemCategory() {
        Itemfactory itemFactory = new Itemfactory();

        // Test getting a beverage item
        Item beverageItem = itemFactory.getItemCategory("Beverage");
        assertEquals("Beverage", beverageItem.getCategory());

        // Test getting an electronic item
        Item electronicItem = itemFactory.getItemCategory("Electronic");
        assertEquals("Electronic", electronicItem.getCategory());

        // Test getting a grocery item
        Item groceryItem = itemFactory.getItemCategory("Grocery");
        assertEquals("Grocery", groceryItem.getCategory());

        // Test getting an unknown item category
        Item unknownItem = itemFactory.getItemCategory("Unknown");
        assertNull(unknownItem);
    }
}
