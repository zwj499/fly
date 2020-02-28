package com.mygo.fly.module.role.model;

/**
 * @author zwj * @since 1.0
 */
public class Role {

    private Integer id;
    private String name;
    private String career;

    private Integer fightingPower; //战斗力

    public Role() {
    }

    public Role(String name, String career) {
        this.name = name;
        this.career = career;
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

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public Integer getFightingPower() {
        return fightingPower;
    }

    public void setFightingPower(Integer fightingPower) {
        this.fightingPower = fightingPower;
    }
}
