package com.pcbwx.pcbmis.enums;

public enum DisposalMethodEnum {
    REWORK(1, "返工"), REPAIR(2, "返修"), SCRAP(3, "报废"), RETURN_GOODS(4, "退货"), CONCESSION_RECEPTION(5, "让步接收");

    int index;
    String desc;

    DisposalMethodEnum(int index, String desc) {
        this.index = index;
        this.desc = desc;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}


