package cn.com.egova.bean;

public class CreatorDTO {
	private String accountId;
	private String accountOrgId;
	private String accountOrgName;
	private float money;
	private Integer year;

	public CreatorDTO() {
	}

	public CreatorDTO(String accountId, String accountOrgId, String accountOrgName) {
		this.accountId = accountId;
		this.accountOrgId = accountOrgId;
		this.accountOrgName = accountOrgName;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAccountOrgId() {
		return accountOrgId;
	}

	public void setAccountOrgId(String accountOrgId) {
		this.accountOrgId = accountOrgId;
	}

	public String getAccountOrgName() {
		return accountOrgName;
	}

	public void setAccountOrgName(String accountOrgName) {
		this.accountOrgName = accountOrgName;
	}

	public float getMoney() {
		return money;
	}

	public void setMoney(float money) {
		this.money = money;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}
}
