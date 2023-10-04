package suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.*;
@RunWith(Suite.class)

@Suite.SuiteClasses({
        MyListsTests.class,
        SearchTests.class,
        ArticleTests.class
        })
public class TestSuite {
}
