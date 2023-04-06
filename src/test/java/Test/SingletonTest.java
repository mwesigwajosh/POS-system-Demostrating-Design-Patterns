package Test;
import com.pos.item.Product;
import com.pos.item.ProductSingleton;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
public class SingletonTest {
    private ProductSingleton productSingleton;
    
    @Before
    public void setUp() {
        productSingleton = ProductSingleton.getInstance();
    }
    
    @Test
    public void testGetInstance() {
        assertNotNull(productSingleton);
    }
    
    @Test
    public void testGetProducts() {
        ArrayList<Product> products = productSingleton.getProducts();
        assertNotNull(products);
        assertEquals(8, products.size());
    }
    
    @Test
    public void testProductDetails() {
        ArrayList<Product> products = productSingleton.getProducts();
        Product firstProduct = products.get(0);
        assertEquals("TV", firstProduct.getName());
    }
}
