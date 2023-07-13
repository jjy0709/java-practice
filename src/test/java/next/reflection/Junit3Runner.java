package next.reflection;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

public class Junit3Runner {
    @Test
    public void runner() throws Exception {
        SoftAssertions s = new SoftAssertions();
        Class clazz = Junit3Test.class;
        Method[] methods = clazz.getDeclaredMethods();

        int testNumber = 0;

        for(Method method: methods) {
            if(method.getName().startsWith("test")) {
                testNumber++;
                method.invoke(clazz.newInstance());
            }
        }

        s.assertThat(testNumber).isEqualTo(2);

    }
}
