package com.JNF.stream_functional;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    // @Autowired
    private UserMapper userMapper = new UserMapper();

    public List<UserDTO> getAllUsers() {
        List<User> users = userMapper.finaAll();
        List<UserDTO> dtos = new ArrayList<>();
        for(User user : users) {
            String name = user.getLastName() + "." + user.getFirstName();
            String group = Utils.getGroup(user.getAge());
            String contact = user.getEmail();
            UserDTO userDTO = new UserDTO(name, group, contact);
            dtos.add(userDTO);
        }

        return dtos;
    }
}