package com.ltgame.protobufdemo.controller;

import com.ltgame.protobufdemo.proto.UserProto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    // 返回 Protobuf 格式
    @GetMapping(value = "/{id}", produces = "application/x-protobuf")
    public UserProto.User getUser(@PathVariable Long id) {
        System.out.println("查询用户: " + id);
        return UserProto.User.newBuilder()
                .setId(id)
                .setName("张三")
                .setEmail("zhangsan@example.com")
                .build();
    }

    // 接收 Protobuf 请求体
    @PostMapping(consumes = "application/x-protobuf", produces = "application/x-protobuf")
    public UserProto.User createUser(@RequestBody UserProto.User user) {
        System.out.println("用户ID: " + user.getId());
        System.out.println("收到用户: " + user.getName());
        System.out.println("用户邮箱: " + user.getEmail());
        // 业务处理后返回
        return user.toBuilder().setId(100L).build();
    }

    // 接收 Protobuf 请求体
    @PostMapping(value = "check")
    public void getUserByBin(@RequestBody UserProto.User user) {
        System.out.println("用户ID: " + user.getId());
        System.out.println("收到用户: " + user.getName());
        System.out.println("用户邮箱: " + user.getEmail());
    }
}
