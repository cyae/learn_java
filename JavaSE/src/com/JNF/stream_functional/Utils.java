package com.JNF.stream_functional;
import java.util.Collection;

/**
 * Utils
 */
public class Utils {

    static String getGroup(Integer age) {
        return switch (age) {
            case 18 -> "teenager";
            case 30 -> "youngman";
            case 50 -> "adult";
            case 80 -> "elder";
            default -> "alien";
        };
    }

    static  void printUser(Collection<UserDTO> users) {
        users.forEach(System.out::println);
    }
}