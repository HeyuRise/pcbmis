package com.pcbwx.pcbmis.bean.response;

public class ColReportDetail {

	private ColCheckReport report;
	
	private ColCertification certification;
	
	private ColSizeWarpingBean sizeWarping;

	public ColCheckReport getReport() {
		return report;
	}

	public void setReport(ColCheckReport report) {
		this.report = report;
	}

	public ColCertification getCertification() {
		return certification;
	}

	public void setCertification(ColCertification certification) {
		this.certification = certification;
	}

	public ColSizeWarpingBean getSizeWarping() {
		return sizeWarping;
	}

	public void setSizeWarping(ColSizeWarpingBean sizeWarping) {
		this.sizeWarping = sizeWarping;
	}

	@Override
	public String toString() {
		return "ColReportDetail [report=" + report + ", certification=" + certification + ", sizeWarping=" + sizeWarping
				+ "]";
	}
	
}
