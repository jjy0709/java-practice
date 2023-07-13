package next.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import next.optional.User;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.assertj.core.api.Assertions.*;

public class ReflectionTest {
    private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);

    @Test
    @DisplayName("테스트1: 리플렉션을 이용해서 클래스와 메소드의 정보를 정확하게 출력해야 한다.")
    public void showClass() {
        SoftAssertions s = new SoftAssertions();
        Class<Question> clazz = Question.class;

        Field[] fields = clazz.getDeclaredFields();
        Constructor[] constructors = clazz.getDeclaredConstructors();
        Method[] methods = clazz.getDeclaredMethods();

        logger.debug("Class Name {}", clazz.getName());
        logger.debug("Field Name {}", Arrays.toString(fields));
        logger.debug("Constructor Name {}", Arrays.toString(constructors));
        logger.debug("Method Name {}", Arrays.toString(methods));

        s.assertThat(fields.length).isEqualTo(6);
        s.assertThat(constructors.length).isEqualTo(2);
        s.assertThat(methods.length).isEqualTo(11);
    }

    @Test
    public void constructor() throws Exception {
        Class<Question> clazz = Question.class;
        Constructor[] constructors = clazz.getConstructors();
        for (Constructor constructor : constructors) {
            Class[] parameterTypes = constructor.getParameterTypes();
            logger.debug("paramer length : {}", parameterTypes.length);
            for (Class paramType : parameterTypes) {
                logger.debug("param type : {}", paramType);
            }
        }
    }

    @Test
    public void privateFieldAccess() throws NoSuchFieldException, IllegalAccessException {
        SoftAssertions s = new SoftAssertions();
        Class<Student> clazz = Student.class;
        logger.debug(clazz.getName());

        Field[] fields = clazz.getDeclaredFields();
        logger.debug(Arrays.toString(fields));

        Field name = clazz.getDeclaredField("name");
        Field age = clazz.getDeclaredField("age");

        name.setAccessible(true);
        age.setAccessible(true);

        Student student = new Student();

        name.set(student, "명수");
        age.set(student, 12);

        s.assertThat(student.getName()).isEqualTo("명수");
        s.assertThat(student.getAge()).isEqualTo(12);
    }

    @Test
    void createInstanceWithParameters() throws InvocationTargetException, InstantiationException, IllegalAccessException {
        SoftAssertions s = new SoftAssertions();
        Class<User> clazz = User.class;

        Constructor[] constructors = clazz.getDeclaredConstructors();
        logger.debug(Arrays.toString(constructors));

        Object[] parameters = new Object[2];
        parameters[0] = "명수";
        parameters[1] = 12;

        User user = (User) constructors[0].newInstance(parameters);

        s.assertThat(user.getName()).isEqualTo("명수");
        s.assertThat(user.getAge()).isEqualTo(12);
    }
}
