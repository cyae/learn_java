package com.learn.microservice.service_1;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import com.learn.microservice.service_1.service.UserService;
import com.learn.microservice.service_1.service.UserServiceImpl;
import com.learn.microservice.service_2.service.PersonService;
import com.learn.microservice.service_2.service.PersonServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Server {
    
    private static boolean isRunning = true;
    private static final Map<String, Object> REGISTRY_MAP = new HashMap<>();

    public static void start() throws Exception {

        REGISTRY_MAP.putIfAbsent(UserService.class.getCanonicalName(), new UserServiceImpl());
        REGISTRY_MAP.putIfAbsent(PersonService.class.getCanonicalName(), new PersonServiceImpl());

        ServerSocket serverSocket = new ServerSocket(8888);
        while (isRunning) {
            log.info("等待 client 连接");
            Socket client = serverSocket.accept();
            log.info("client 已连接");
            handle(client);
            client.close();
        }
        serverSocket.close();
    }

    private static void handle(Socket client) throws Exception {
        InputStream is = client.getInputStream();
        OutputStream os = client.getOutputStream();
        ObjectInputStream ois = new ObjectInputStream(is);
        ObjectOutputStream oos = new ObjectOutputStream(os);

        String seriveName = ois.readUTF();
        log.info("client 请求 服务: " + seriveName);

        String methodName = ois.readUTF();
        Class<?>[] parameterTypes = (Class<?>[]) ois.readObject();
        Object[] args = (Object[]) ois.readObject();
        log.info("client 传输 参数 = " + args.toString());

        Object service = REGISTRY_MAP.get(seriveName);
        Method method = service.getClass().getMethod(methodName, parameterTypes);
        Object obj = method.invoke(service, args);

        oos.writeObject(obj);
        oos.flush();

        ois.close();
        oos.close();
    }
}