package next.reflection;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ElapsedTimeRunner {

    @Test
    void runner() throws InstantiationException, IllegalAccessException, InvocationTargetException {
        Class<ElapsedTimeTest> clazz = ElapsedTimeTest.class;
        Method[] methods = clazz.getDeclaredMethods();

        for(Method method: methods) {
            if(method.isAnnotationPresent(ElapsedTime.class)) {
                long beforeTime = System.currentTimeMillis();
                method.invoke(clazz.newInstance());
                long afterTime = System.currentTimeMillis();

                System.out.println(String.format("%s 메소드 실행 시간: %d", method.getName(), afterTime-beforeTime));
            }
        }
    }
}
