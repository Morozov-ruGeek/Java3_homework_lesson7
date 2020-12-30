package ru.geekbrains.lessons;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StartTest {
    public static void start (Class c){
        List<Method> methods = new ArrayList<>();
        Method[] classMethods = c.getDeclaredMethods();

        for (Method m : classMethods) {
            if (m.isAnnotationPresent(Test.class)){
                methods.add(m);
            }
        }

        methods.sort(Comparator.comparingInt((Method i) -> i.getAnnotation(Test.class).priority()).reversed());

        for (Method beforeMethod : classMethods) {
            if (beforeMethod.isAnnotationPresent(BeforeSuite.class)){
                if(methods.size() > 0 && methods.get(0).isAnnotationPresent(BeforeSuite.class)){
                    throw new  RuntimeException("@BeforeSuite annotation method > 1");
                }
                methods.add(0,beforeMethod);
            }
        }

        for (Method afterMethod : classMethods) {
            if (afterMethod.isAnnotationPresent(AfterSuite.class)){
                if(methods.size() > 0 && methods.get(0).isAnnotationPresent(AfterSuite.class)){
                    throw new RuntimeException("@AfterSuite annotation method > 1");
                }
                methods.add(afterMethod);
            }
        }

        for (Method m : classMethods) {
            try {
                m.invoke(null);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
