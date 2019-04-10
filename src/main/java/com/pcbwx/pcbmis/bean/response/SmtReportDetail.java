package com.pcbwx.pcbmis.bean.response;

public class SmtReportDetail {

	private SmtCheckReport report;

	private SmtCertification certification;

	public SmtCheckReport getReport() {
		return report;
	}

	public void setReport(SmtCheckReport report) {
		this.report = report;
	}

	public SmtCertification getCertification() {
		return certification;
	}

	public void setCertification(SmtCertification certification) {
		this.certification = certification;
	}

	@Override
	public String toString() {
		return "SmtReportDetail [report=" + report.toString() + ", certification=" + certification.toString() + "]";
	}

}
