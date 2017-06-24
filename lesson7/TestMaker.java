//  Это класс, который может выполнять «тесты»...

package lesson7;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TestMaker {
    static void start(Class cl) throws Exception {
        run(cl);
    }

    static void start(String name) throws Exception {
        run(Class.forName(name));
    }

    static void run(Class cl) throws Exception {
        Method beforeSuite = null;
        Method afterSuite = null;
        ArrayList<Method> tests = new ArrayList<>();
        boolean isBefore = false;
        boolean isAfter = false;
        // Разбираем все методы, полученные от класса Main
        for (Method met : cl.getDeclaredMethods()) {
            // Метод с аннотацией @BeforeSuite запоминаем в beforeSuite
            if (met.isAnnotationPresent(BeforeSuite.class)) {
                if (!isBefore) {
                    beforeSuite = met;
                    isBefore = true;
                } else
                    // Если более одного метода с такой аннотацией бросаем исключение
                    throw new RuntimeException("More one @BeforeSuite");
            }
            // Закидываем методы с аннтоцией @Test в список
            if (met.isAnnotationPresent(Test.class)) {
                tests.add(met);
            }
            // Метод с аннотацией @AfterSuite запоминаем в afterSuite
            if (met.isAnnotationPresent(AfterSuite.class)) {
                if (!isAfter) {
                    afterSuite = met;
                    isAfter = true;
                } else
                    // Если более одного метода с такой аннотацией бросаем исключение
                    throw new RuntimeException("More one @AfterSuite");
            }
        }
        Collections.sort(tests, new Comparator<Method>() {
            @Override
            public int compare(Method o1, Method o2) {
                int p1 = o1.getAnnotation(Test.class).priority();
                int p2 = o2.getAnnotation(Test.class).priority();
                return Integer.compare(p2, p1);
            }
        });
        if (isBefore && isAfter) {
            beforeSuite.invoke(null);
            for (int i = 0; i < tests.size(); i++)
                tests.get(i).invoke(null);
            afterSuite.invoke(null);
        } else
            throw new RuntimeException("No one @BeforeSuite or @AfterSuite");
    }

    public static void main(String[] args) throws Exception {
        try {
            TestMaker.start(Main.class);
            TestMaker.start("lesson7.Main");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
