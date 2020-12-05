package com.acc.gft.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.acc.gft.bean.TicketBean;
import com.acc.gft.bean.TrainBean;
import com.acc.gft.service.TicketService;

@Controller
public class TicketController {

	@Autowired
	TicketService ticketService;

	@ModelAttribute("trainDetails")
	public Map<Integer, String> populateTrainDetails() throws Exception {
		List<TrainBean> beanList = ticketService.populateTrainDetails();
		Map<Integer, String> map = new HashMap<>();
		for (TrainBean bean : beanList)
			map.put(bean.getTrainId(), bean.getTrainName());
		return map;
	}

	@RequestMapping("bookTicket")
	public ModelAndView loadBookingPage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("ticketBean", new TicketBean());
		modelAndView.setViewName("bookingform");
		return modelAndView;
	}

	@RequestMapping("validateTicket")
	public ModelAndView processBookingForm(@Validated TicketBean ticketbean, BindingResult result) throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		if (result.hasErrors()) {
			modelAndView.setViewName("bookingform");
		} else {
			TicketBean bean = ticketService.addTicket(ticketbean);
			modelAndView.addObject("msg",
					"Your Booking id is " + bean.getBookingId() + " and Total ticket price is " + bean.getTotalPrice());
			modelAndView.setViewName("success");
		}
		return modelAndView;
	}

	@RequestMapping("Report")
	public String processReport(ModelMap map) {
		List<TicketBean> tickets = ticketService.getAllTicketDetails();
		map.addAttribute("AllTicketDetails", tickets);
		return "report";
	}

	@ExceptionHandler(value = Exception.class)
	public ModelAndView handleAllException(Exception exception) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("GeneralizedExceptionHandlerPage");
		modelAndView.addObject("exception", exception.getMessage());
		return modelAndView;
	}
}
