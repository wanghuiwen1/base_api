package com.api.base.model;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "sys_role_power")
public class SysRolePower implements Serializable {
    @Id
    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "power_id")
    private Integer powerId;

    private static final long serialVersionUID = 1L;

    /**
     * @return role_id
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * @param roleId
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * @return power_id
     */
    public Integer getPowerId() {
        return powerId;
    }

    /**
     * @param powerId
     */
    public void setPowerId(Integer powerId) {
        this.powerId = powerId;
    }
}