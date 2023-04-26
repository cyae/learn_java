package com.learn.microservice.service_2.rpc;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

public class Stub {

    public static Object getStub(Class<?> clazz) {
        Object obj = Proxy.newProxyInstance(
            clazz.getClassLoader(), 
            new Class[]{clazz}, 
            new NetInvocationHandler(clazz)
        );
        return obj;
    }

    static class NetInvocationHandler implements InvocationHandler {

        private Class<?> clazz;

        public NetInvocationHandler(Class<?> clazz) {
            this.clazz = clazz;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            Socket socket = new Socket("127.0.0.1", 8888);

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeUTF(clazz.getName());

            oos.writeUTF(method.getName());
            Class<?>[] parameterTypes = method.getParameterTypes();
            oos.writeObject(parameterTypes);
            oos.writeObject(args);

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Object obj = ois.readObject();

            oos.close();
            ois.close();
            socket.close();

            return obj;
        }
        
    }
}
