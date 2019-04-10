package com.pcbwx.pcbmis.enums;

public enum UnqualifiedSourceEnum {
	
    OUTSOURCED_PRODUCTS(1, "采购/外包产品"), 
    process_product(2, "过程产品"), 
    finished_product(3, "成品"), 
    Customer_product(4, "顾客产品");

    private int index;
    private String desc;

    UnqualifiedSourceEnum(int index, String desc) {
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
