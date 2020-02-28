package com.mygo.fly.module.home.service;

import org.assertj.core.util.Lists;

import java.util.List;

/**
 * @author zwj * @since 1.0
 */
public class Info {
    private String name;
    private List<String> roles = Lists.newArrayList();
    private String intriducec;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getIntriducec() {
        return intriducec;
    }

    public void setIntriducec(String intriducec) {
        this.intriducec = intriducec;
    }
}
