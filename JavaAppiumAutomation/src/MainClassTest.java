import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {
    @Test
    public void testGetLocalNumber() {
        int expected = 14;
        int actual = MainTest.getLocalNumber();
        Assert.assertEquals("actual value !=14", expected, actual);
    }
}
