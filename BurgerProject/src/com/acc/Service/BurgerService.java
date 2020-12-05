package com.acc.Service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.acc.Bean.BurgerBean;

@Service
public class BurgerService {

	Map<String, Double> burgers = new HashMap<>();
	Map<String, Double> toppings = new HashMap<>();

	public Map<String, Double> getBurgerDetails() {

		burgers.put("Veg-Burgers", 100.00);
		burgers.put("Chicken-Burgers", 200.00);
		burgers.put("Veg-CrunchBurgers", 150.00);
		burgers.put("Chicken-CrunchBurgers", 250.00);
		return burgers;
	}

	public Map<String, Double> getToppingDetails() {

		toppings.put("cheese", 70.00);
		toppings.put("Dip", 50.00);
		toppings.put("Sauce", 30.00);

		return toppings;
	}

	public Double calculateTotalCost(BurgerBean burgerbean) {
	double burgerCost = burgers.get(burgerbean.getBurgerName());
	double toppingCost = toppings.get(burgerbean.getToppingName())	;
		int noOfBurgers = burgerbean.getNoOfBurgers();
		return ((burgerCost + toppingCost)*noOfBurgers);
	}

}
