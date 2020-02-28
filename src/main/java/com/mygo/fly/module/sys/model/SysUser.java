package com.mygo.fly.module.sys.model;

/**
 * @author zwj * @since 1.0
 */
public class SysUser {

    private Integer id;
    private String username;
    private String password;

    public SysUser() {
    }

    public SysUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
