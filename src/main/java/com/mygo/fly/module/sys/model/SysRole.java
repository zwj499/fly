package com.mygo.fly.module.sys.model;

/**
 * @author zwj * @since 1.0
 */
public class SysRole {

    private Integer id;
    private String name;

    public SysRole() {
    }

    public SysRole(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
