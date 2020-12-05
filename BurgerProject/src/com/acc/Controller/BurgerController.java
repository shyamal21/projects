package com.acc.Controller;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.acc.Bean.BurgerBean;
import com.acc.Service.BurgerService;

@Controller
public class BurgerController {
	@Autowired
	BurgerService burgerService;

	@ModelAttribute("burgerList")
	public Set<String> getBurgers() {
		Map<String, Double> burgerMap = burgerService.getBurgerDetails();
		Set<String> burgerSet = burgerMap.keySet();
		System.out.println(burgerSet);
		return burgerSet;
	}

	@ModelAttribute("toppingList")
	public Set<String> getToppings() {
		Map<String, Double> toppingMap = burgerService.getToppingDetails();
		Set<String> toppingSet = toppingMap.keySet();
		return toppingSet;
	}

	@RequestMapping("orderpage")
	public ModelAndView showPage() {
		ModelAndView mView = new ModelAndView();
		System.out.println("INside orderpage");
		mView.addObject("burgerBean", new BurgerBean());//sending empty ref of bean class to spring form to collect the data
		mView.setViewName("orderpage");
		return mView;
	}

	@RequestMapping("val")
	public ModelAndView orderBurger(@Validated BurgerBean burgerBean, BindingResult result) {
		ModelAndView modelView = new ModelAndView();
		if (result.hasErrors()) {
			modelView.setViewName("orderpage");
		} else {
			double cost =burgerService.calculateTotalCost(burgerBean);
			modelView.addObject("msg", cost );
			modelView.setViewName("successorder");
		}
		return modelView;
	}

}
