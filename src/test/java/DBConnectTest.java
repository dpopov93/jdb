import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/*
 * Class for test DBConnect
 * @author dpopov93 (mailto:d.popov93@mail.ru)
 * @since 29.07.17 20:30
 * @version 1.0
 */
public class DBConnectTest {
    @Test
    public void whenConnectToDataBaseReturnSomeData() {
        DBConnect dbc = new DBConnect("jdbc:mysql://localhost/somebase",
                "Persons",
                "User",
                "password");
        String result = dbc.getData();
        String expected = "ID: 1, First: Mike, Last: Johnson, " +
                "Address: Baker st. 12, City: Bakersfield\nID: 2, " +
                "First: Jesse, Last: Kindey, Address: Maxwell st. 1, City: Redmond\n";
        assertThat(result, is(expected));
    }
}