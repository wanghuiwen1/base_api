package com.api.base.model;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "sys_role")
public class SysRole implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String description;

    /**
     * 1:api 2:menu
     */
    private Integer type;

    public SysRole(String name, String description, Integer type) {
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public SysRole() {
    }

    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取1:api 2:menu
     *
     * @return type - 1:api 2:menu
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置1:api 2:menu
     *
     * @param type 1:api 2:menu
     */
    public void setType(Integer type) {
        this.type = type;
    }
}