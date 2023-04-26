package com.JNF.stream_functional;
import java.util.List;
import java.util.stream.Collectors;

public class UserService2 {
    
    // @Autowired
    private UserMapper userMapper = new UserMapper();

    public List<UserDTO> getAllUsers() {
        List<UserDTO> dtos = userMapper.finaAll()
                                       .stream()
                                    //    .map(this::toUserDTO)
                                       .map(UserDTO::new)
                                       .collect(Collectors.toList());

        return dtos;
    }

}
