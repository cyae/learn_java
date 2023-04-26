package com.JNF.stream_functional;

import java.util.List;

public class UserMapper {

    public List<User> finaAll() {
        // select * from tbl
        return List.of(
                new User("john", "lee", 12, "qw123123e@qq.com"),
                new User("harry", "poter", 29, "23234qwe@gmail.com"),
                new User("gordan", "ramsy", 37, "qw234224e@hotmail.com"),
                new User("donald", "trump", 46, "q23434we@microsoft.com")

        );
    }
}