package Test;
import org.junit.Test;
import static org.junit.Assert.*;

import com.pos.barcodescanner.Barcodescanner;
import com.pos.barcodescanner.Observercart;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class BarcodescannerTest {
    
    @Test
    public void testRegisterObserver() {
        Observercart cart = new Observercart();
        Barcodescanner scanner = new Barcodescanner();
        scanner.registerObserver(cart);
        assertEquals(cart, scanner.observer);
    }
    
    @Test
    public void testNotifyObserver() {
        Observercart cart = new Observercart();
        Barcodescanner scanner = new Barcodescanner();
        scanner.registerObserver(cart);
        scanner.notifyObserver(1234);
        assertEquals(1234, cart.getproductCode());
    }
    
    @Test
    public void testUpdate() {
        Observercart cart = new Observercart();
        cart.update(5678);
        assertEquals(5678, cart.getproductCode());
    }
    
    @Test
    public void testGetproductCode() {
        Observercart cart = new Observercart();
        cart.update(9012);
        assertEquals(9012, cart.getproductCode());
    }
}