package com.pcbwx.pcbmis.enums;

public enum ProductTypeEnum {
    PCB(1, "PCB"), SMT(2, "SMT"), COL(3, "COL"), EXPRESS_INTERFACE(4, "快速接口"), METAL_STRUCTURAL_PARTS(5, "金属结构件"), ELECTRICAL_CONNECTOR(6, "电连接器");

    int index;
    String desc;

    ProductTypeEnum(int index, String desc) {
        this.index = index;
        this.desc = desc;
    }

    public int getIndex() {
        return index;
    }

    public String getDesc() {
        return desc;
    }
}
