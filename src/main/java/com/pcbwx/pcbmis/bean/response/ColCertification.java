package com.pcbwx.pcbmis.bean.response;

public class ColCertification {

	private String companyName;
	
	private String name;
	
	private String productDate;
	
	private String productNum;
	
	private String inspector;
    
    private String inspectDate;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProductDate() {
		return productDate;
	}

	public void setProductDate(String productDate) {
		this.productDate = productDate;
	}

	public String getProductNum() {
		return productNum;
	}

	public void setProductNum(String productNum) {
		this.productNum = productNum;
	}

	public String getInspector() {
		return inspector;
	}

	public void setInspector(String inspector) {
		this.inspector = inspector;
	}

	public String getInspectDate() {
		return inspectDate;
	}

	public void setInspectDate(String inspectDate) {
		this.inspectDate = inspectDate;
	}

	@Override
	public String toString() {
		return "ColCertification [companyName=" + companyName + ", name=" + name + ", productDate=" + productDate
				+ ", productNum=" + productNum + ", inspector=" + inspector + ", inspectDate=" + inspectDate + "]";
	}
    
}
