package ro.ex;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

class InvocationHandler implements java.lang.reflect.InvocationHandler {
    public InvocationHandler(Object o) {

    }

    public Object invoke(Object o, Method m, Object[] args) {
        return null;
    }
}

interface ExIt {
    public void m(Object arg, Object arg2);
}

class Ex implements ExIt {
    public void m(Object arg, Object arg2) {

    }
}

class Ex2 implements ExIt {
    public void m(Object arg, Object arg2) {

    }
}

class Ex3 {
    public void m(Object arg, Object arg2) {
        return;
    }

    public static void main(String[] args) {
        ExIt i = (ExIt) Proxy.newProxyInstance(Ex.class.getClassLoader(),
                Ex.class.getInterfaces(),
                new InvocationHandler(new Ex()));

        i.m(1, 2);
    }
}
