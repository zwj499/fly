package com.mygo.fly.module.role.controller;

import com.mygo.fly.module.role.model.Role;
import org.assertj.core.util.Lists;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zwj * @since 1.0
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/list")
    public List<Role> listRole() {
        List<Role> roleList = Lists.newArrayList();
        roleList.add(new Role("啊就是你把", "毁灭者"));
        roleList.add(new Role("啊就是你把", "毁灭者"));
        roleList.add(new Role("啊就是你把", "毁灭者"));
        roleList.add(new Role("啊就是你把", "毁灭者"));
        roleList.add(new Role("啊就是你把", "毁灭者"));
        return roleList;
    }



}
