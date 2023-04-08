package Test;
import com.pos.payment.CashPaymentStrategy;
import com.pos.payment.CreditCardPaymentStrategy;
import com.pos.payment.PaymentContext;
import static org.junit.Assert.*;
import org.junit.Test;

public class PaymentContextTest {

    @Test
    public void testCashPaymentStrategy() {
        PaymentContext paymentContext = new PaymentContext(new CashPaymentStrategy());
        String paidWith = paymentContext.pay();
        assertEquals("cash", paidWith);
    }
    
    @Test
    public void testCreditCardPaymentStrategy() {
        CreditCardPaymentStrategy cardpay = new CreditCardPaymentStrategy("1234-5678-9012-3456", "05/24", "123");
        PaymentContext paymentContext = new PaymentContext(cardpay);
        String paidWith = paymentContext.pay();
        assertEquals("credit card", paidWith);
    }
}

