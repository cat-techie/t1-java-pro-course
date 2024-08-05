package ru.t1.pmorozov;

import ru.t1.pmorozov.annotations.*;
import ru.t1.pmorozov.exceptions.TestException;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Stream;

public class TestRunner {
    public static void runTests(Class c) throws Exception {

        MyClass testEntity = new MyClass();
        Method[] declaredMethods = c.getDeclaredMethods();

        long beforeSuiteDeclaredMethods = getAnnotatedMethodsStream(declaredMethods, BeforeSuite.class).count();
        long afterSuiteDeclaredMethods = getAnnotatedMethodsStream(declaredMethods, AfterSuite.class).count();

        if (beforeSuiteDeclaredMethods > 1) {
            throw new TestException("More than one method with annotation @BeforeSuite");
        } else if (afterSuiteDeclaredMethods > 1) {
            throw new TestException("More than one method with annotation @AfterSuite");
        }

        // выполнение @BeforeSuite
        if (beforeSuiteDeclaredMethods == 1) {
            Method beforeSuiteMethod = getAnnotatedMethodsStream(declaredMethods, BeforeSuite.class).findAny().get();
            if (!Modifier.isStatic(beforeSuiteMethod.getModifiers())) {
                throw new TestException("Method with annotation @BeforeSuite is not static");
            }
            beforeSuiteMethod.invoke(testEntity, "ᓚᘏᗢ");

        }

        // выполнение тестов @Test в порядке приоритетности
        var prioritySortedTestMethods = new java.util.ArrayList<>(Arrays.stream(declaredMethods)
                .filter(m -> m.isAnnotationPresent(Test.class))
                .sorted(Comparator.comparingInt(m -> m.getAnnotation(Test.class).priority()))
                .toList());
        Collections.reverse(prioritySortedTestMethods);

        for (var testMethod : prioritySortedTestMethods) {

            var beforeTestMethods = getAnnotatedMethodsStream(declaredMethods, BeforeTest.class).toList();
            for (Method beforeTestMethod : beforeTestMethods) {
                beforeTestMethod.invoke(testEntity);
            }

            int testPriority = testMethod.getAnnotation(Test.class).priority();
            if (testPriority < 1 || testPriority > 10) {
                throw new TestException("Test priority not in range 1 to 10");
            }

            if (testMethod.isAnnotationPresent(CsvSource.class)) {
                testMethod.invoke(testEntity, parseCsvSourceToList(testMethod).toArray());
            } else {
                testMethod.invoke(testEntity);
            }

            var afterTestMethods = getAnnotatedMethodsStream(declaredMethods, AfterTest.class).toList();
            for (Method afterTestMethod : afterTestMethods) {
                afterTestMethod.invoke(testEntity);
            }
        }

        // выполнение @AfterSuite
        if (afterSuiteDeclaredMethods == 1) {
            Method afterSuiteMethod = getAnnotatedMethodsStream(declaredMethods, AfterSuite.class).findAny().get();
            if (!Modifier.isStatic(afterSuiteMethod.getModifiers())) {
                throw new TestException("Method with annotation @AfterSuite is not static");
            }
            afterSuiteMethod.invoke(testEntity, 42);

        }
    }

    private static Stream<Method> getAnnotatedMethodsStream(Method[] declaredMethods, Class annotation) {
        return Arrays.stream(declaredMethods).filter(m -> m.isAnnotationPresent(annotation));
    }

    private static List<Object> parseCsvSourceToList(Method testMethod) throws TestException {
        var args = testMethod.getAnnotation(CsvSource.class).source().split(", ");

        List<Object> result = new ArrayList<>();
        for (int i = 0; i < args.length; i++) {
//            result.add(testMethod.getParameterTypes()[i].cast(args[i]));
            if (testMethod.getParameterTypes()[i] == String.class) {
                result.add(args[i]);
            } else if (testMethod.getParameterTypes()[i] == int.class) {
                result.add(Integer.parseInt(args[i]));
            } else if (testMethod.getParameterTypes()[i] == boolean.class) {
                result.add(Boolean.parseBoolean(args[i]));
            } else {
                throw new TestException("Unhandled method parameter type: " + testMethod.getParameterTypes()[i]);
            }
        }
        return result;
    }
}
