package com.JNF.Sneaky_Exception;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.function.Consumer;

interface OrderMapper {
    List<Order> findByActive();
}

interface UserMapper {
    List<User> findByActive();
}

/**
 * OrderExporter
 */
public class Exporter {

    public File exportFile(String fileName, Consumer<Writer> contentWriter) {
        File file = new File("export/" + fileName);
        try (Writer writer = new FileWriter(file)) {
            contentWriter.accept(writer);
            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }
    
}

class UserExporter {

    private UserMapper userMapper;

    // 等价于UserExporter implements Consumer override accept
    // Lombok
    // @SneakyThrow   
    protected void writeContent(Writer writer) throws IOException {
        writer.write("user:");
        userMapper.findByActive()
                .stream()
                .map(user -> "")
                .forEach(userInfo -> {
                    try {
                        writer.write(userInfo);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }
}

class OrderExporter{

    private OrderMapper orderMapper;
    
    // 等价于Consumer
    // Lombok
    // @SneakyThrow   
    protected void writeContent(Writer writer) throws IOException {
        writer.write("order:");
        orderMapper.findByActive()
                   .stream()
                   .map(order -> order.getId() + ":" + order.getDate())
                   .forEach(orderInfo -> {
                    try {
                        writer.write(orderInfo);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }
}