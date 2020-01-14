package com.wallet.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;


public class WalletDTO {
	
	private Long id;
	@Length(min=3)
	@NotNull
	private String name;
	@NotNull
	private BigDecimal value;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "WalletDTO [id=" + id + ", name=" + name + ", value=" + value + "]";
	}
	
	

}
