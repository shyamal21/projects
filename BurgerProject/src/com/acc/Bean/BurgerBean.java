package com.acc.Bean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BurgerBean {
	private String burgerName;
	private String toppingName;
	@NotNull
	@Size(min=1,max=10,message="must be between 1 and 10")
	private Integer noOfBurgers;

	public String getBurgerName() {
		return burgerName;
	}

	public void setBurgerName(String burgerName) {
		this.burgerName = burgerName;
	}

	public String getToppingName() {
		return toppingName;
	}

	public void setToppingName(String toppingName) {
		this.toppingName = toppingName;
	}

	public Integer getNoOfBurgers() {
		return noOfBurgers;
	}

	public void setNoOfBurgers(Integer noOfBurgers) {
		this.noOfBurgers = noOfBurgers;
	}

}
