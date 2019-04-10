package com.pcbwx.pcbmis.model;

import java.math.BigDecimal;
import java.util.Date;

public class ColSizeWarpingItem {
    private Integer id;

    private Integer colReportId;

    private Integer colSizeWarpingId;

    private BigDecimal boardLong;

    private BigDecimal boardWide;

    private BigDecimal boardPly;

    private BigDecimal warping;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getColReportId() {
        return colReportId;
    }

    public void setColReportId(Integer colReportId) {
        this.colReportId = colReportId;
    }

    public Integer getColSizeWarpingId() {
        return colSizeWarpingId;
    }

    public void setColSizeWarpingId(Integer colSizeWarpingId) {
        this.colSizeWarpingId = colSizeWarpingId;
    }

    public BigDecimal getBoardLong() {
        return boardLong;
    }

    public void setBoardLong(BigDecimal boardLong) {
        this.boardLong = boardLong;
    }

    public BigDecimal getBoardWide() {
        return boardWide;
    }

    public void setBoardWide(BigDecimal boardWide) {
        this.boardWide = boardWide;
    }

    public BigDecimal getBoardPly() {
        return boardPly;
    }

    public void setBoardPly(BigDecimal boardPly) {
        this.boardPly = boardPly;
    }

    public BigDecimal getWarping() {
        return warping;
    }

    public void setWarping(BigDecimal warping) {
        this.warping = warping;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}