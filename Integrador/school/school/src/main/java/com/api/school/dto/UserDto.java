package com.api.school.dto;

public class UserDto {

    private Long id;

    private String name;

    private int age;

    private int tell;

    public UserDto(Long id, String name, int age, int tell) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.tell = tell;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Integer getTell() {
        return tell;
    }
    public void setTell(int tell) {
        this.tell = tell;
    }
}
