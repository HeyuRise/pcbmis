package com.pcbwx.pcbmis.bean.response;

public class ColSizeWarpingItemBean {
	
	private String itemName;

	private String boardLong;

    private String boardWide;

    private String boardPly;

    private String warping;

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getBoardLong() {
		return boardLong;
	}

	public void setBoardLong(String boardLong) {
		this.boardLong = boardLong;
	}

	public String getBoardWide() {
		return boardWide;
	}

	public void setBoardWide(String boardWide) {
		this.boardWide = boardWide;
	}

	public String getBoardPly() {
		return boardPly;
	}

	public void setBoardPly(String boardPly) {
		this.boardPly = boardPly;
	}

	public String getWarping() {
		return warping;
	}

	public void setWarping(String warping) {
		this.warping = warping;
	}

	@Override
	public String toString() {
		return "ColSizeWarpingItemBean [itemName=" + itemName + ", boardLong=" + boardLong + ", boardWide=" + boardWide
				+ ", boardPly=" + boardPly + ", warping=" + warping + "]";
	}
  
}
