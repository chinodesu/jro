package ro;

import org.junit.Test;

public class FakerTest {
    @Test
    public void testF() throws Exception {
        ro.Faker.f("dir/File.java", "" +
                "line" +
                "lin2");
    }
}
