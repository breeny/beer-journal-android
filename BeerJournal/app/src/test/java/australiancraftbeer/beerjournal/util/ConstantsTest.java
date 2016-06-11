package australiancraftbeer.beerjournal.util;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import australiancraftbeer.beerjournal.util.Constants;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ConstantsTest {
    @Test
    public void TestConstantsAreUnique() throws Exception {
        Field[] constantFields = Constants.class.getDeclaredFields();
        List<Field> staticFields = new ArrayList<>();
        for (Field field : constantFields) {
            if (Modifier.isStatic(field.getModifiers())) {
                staticFields.add(field);
            }
        }

        for (Field field : staticFields) {
            for (Field comparisonField : staticFields) {
                if (comparisonField.equals(field)) {
                    continue;
                }
                assertNotEquals(field.getName() + " was equal to " + comparisonField.getName(), field.getInt(null), comparisonField.getInt(null));
            }
        }
    }
}