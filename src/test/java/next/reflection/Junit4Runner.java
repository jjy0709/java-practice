package next.reflection;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

public class Junit4Runner {
    @Test
    public void run() throws Exception {
        SoftAssertions s = new SoftAssertions();
        Class clazz = Junit4Test.class;
        Method[] methods = clazz.getDeclaredMethods();

        for(Method method: methods) {
            if(method.isAnnotationPresent(MyTest.class)) {
                method.invoke(clazz.newInstance());
            }
        }

    }
}


