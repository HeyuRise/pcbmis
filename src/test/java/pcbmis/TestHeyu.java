package pcbmis;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.pcbwx.pcbmis.model.ColSizeWarpingItem;
import com.pcbwx.pcbmis.utils.DateCalcUtil;
import com.pcbwx.pcbmis.utils.JsonUtil;
import com.pcbwx.pcbmis.utils.PcbmisUtil;

public class TestHeyu {
	public static void main(String[] args) {
		Date startTime = DateCalcUtil.subTime(new Date(), Calendar.MONTH, 6);
		System.out.println(startTime);
	}
	
	public static String stringDouble(String str) {
		BigDecimal doublel = null;
		try {
			doublel =  new BigDecimal(str);
		} catch (Exception e) {
			return str;
		}
		doublel = doublel.setScale(2, RoundingMode.HALF_UP);
		
		return doublel.toString();
	}
	
	/**
	 * @param boardLong
	 * @param boardWide
	 * @param boardply
	 * @param size
	 * @param colSizeWarpinpId
	 * @param colReportId
	 */
	public static void generateSizeWarping(BigDecimal boardLong, BigDecimal boardWide, BigDecimal boardply, Integer size,
			Integer colSizeWarpinpId, Integer colReportId) {
		BigDecimal tolerance = new BigDecimal("0.3");
		BigDecimal percent = new BigDecimal("0.1");
		BigDecimal warpingMax = new BigDecimal("0.749");
		BigDecimal warpingMin = new BigDecimal("0.009");
		BigDecimal boardLongMax = boardLong.add(tolerance);
		BigDecimal boardLongMin = boardLong.subtract(tolerance);
		BigDecimal boardWideMax = boardWide.add(tolerance);
		BigDecimal boardWideMin = boardWide.subtract(tolerance);
		BigDecimal boardplyMax = boardply.add(boardply.multiply(percent));
		BigDecimal boardplyMin = boardply.subtract(boardply.multiply(percent));
		List<ColSizeWarpingItem> colSizeWarpingItems = new ArrayList<>();
		ColSizeWarpingItem colSizeWarpingItem = null;
		for (int i = 0; i < size; i++) {
			colSizeWarpingItem = new ColSizeWarpingItem();
			colSizeWarpingItem.setBoardLong(PcbmisUtil.nextBigDecimal(boardLongMax, boardLongMin, 3));
			colSizeWarpingItem.setBoardWide(PcbmisUtil.nextBigDecimal(boardWideMax, boardWideMin, 3));
			colSizeWarpingItem.setBoardPly(PcbmisUtil.nextBigDecimal(boardplyMax, boardplyMin, 3));
			colSizeWarpingItem.setWarping(PcbmisUtil.nextBigDecimal(warpingMax, warpingMin, 3));
			colSizeWarpingItem.setColSizeWarpingId(colSizeWarpinpId);
			colSizeWarpingItem.setColReportId(colReportId);
			colSizeWarpingItems.add(colSizeWarpingItem);
		}
		System.out.println(JsonUtil.obj2json(colSizeWarpingItems));
	}
}
