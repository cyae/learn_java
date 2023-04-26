package com.JNF.Sneaky_Exception;

import java.io.IOException;

public class Main {
    
    public static void main(String[] args) {
        Exporter exporter = new Exporter();
        OrderExporter orderExporter = new OrderExporter();
        exporter.exportFile("order.csv", writer -> {
            try {
                orderExporter.writeContent(writer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
