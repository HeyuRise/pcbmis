package pcbmis;

import java.util.List;

public class DataEnvelope<T> {
	List<T> rows;

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	
}
