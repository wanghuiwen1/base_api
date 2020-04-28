package com.api.base.config;

public enum RoleEnum {
    MENU(2),
    API(1);

    int type;

    RoleEnum(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
