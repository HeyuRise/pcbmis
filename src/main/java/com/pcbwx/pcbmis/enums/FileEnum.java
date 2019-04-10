package com.pcbwx.pcbmis.enums;

/**
 * 文件类型枚举类
 *
 * @author 孙贺宇
 */
public enum FileEnum {

    IMAGE(1), FILE(2);

    int type;

    FileEnum(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
