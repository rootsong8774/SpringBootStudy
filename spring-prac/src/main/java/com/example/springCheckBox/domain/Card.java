package com.example.springCheckBox.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Card implements Serializable{

	private static final long serialVersionUID = 1433342994761080681L;
	
	private String no;
	
	@DateTimeFormat(pattern="yyyyMMdd")
	private Date validMonth;

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public Date getValidMonth() {
		return validMonth;
	}

	public void setValidMonth(Date validMonth) {
		this.validMonth = validMonth;
	}

}
