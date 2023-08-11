import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {
    @Test
    public void  testGetLocalNumber(){
        int expected = 14;
        int actual = MainClass.getLocalNumber();
        Assert.assertEquals("actual value !=14", expected, actual);

    }

    @Test
    public void testGetClassNumber(){
        int actual = MainClass.getClassNumber();
        int comparativeNumber = 45;
        Assert.assertTrue("actual value is less or equals 45", actual>comparativeNumber );
    }

    @Test
    public void testGetClassString(){
        String actual=MainClass.getClassString();
        Assert.assertTrue("actual string doesn't contain substring \"hello\" or \"Hello\"", actual.contains("Hello")||actual.contains("hello") );
    }
}
