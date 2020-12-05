package com.acc.gft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acc.gft.DAO.TicketDAOWrapper;
import com.acc.gft.bean.TicketBean;
import com.acc.gft.bean.TrainBean;
import com.acc.gft.exception.TicketException;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	TicketDAOWrapper ticketDAOWrapper;

	@Override
	public TicketBean addTicket(TicketBean bean) throws Exception {
		long bookedSeats = countTicket(bean.getTrainId());
		TicketBean ticketBean;

		if (bookedSeats + bean.getTicketBooked() >= 100) {
			throw new TicketException("Seats are full,cannot book ticket!!!");
		} else {
			ticketBean = ticketDAOWrapper.addTicket(bean);
		}

		int price = getTicketPrice(bean.getTrainId());
		int ticketBooked = bean.getTicketBooked();
		int total = price * ticketBooked;

		if (bean.getCitizen().equalsIgnoreCase("Senior-Citizen")) {
			total = total - ticketBooked * 500;
		}

		ticketBean.setTotalPrice(total);

		return ticketBean;
	}

	@Override
	public List<TrainBean> populateTrainDetails() throws Exception {
		List<TrainBean> trains = ticketDAOWrapper.populateTrainDetails();
		return trains;
	}

	@Override
	public List<TicketBean> getAllTicketDetails() {
		List<TicketBean> ticketDetails = ticketDAOWrapper.getAllTicketDetails();
		return ticketDetails;
	}

	@Override
	public long countTicket(int trainId) throws Exception {
		long bookedSeats = ticketDAOWrapper.countTicket(trainId);
		return bookedSeats;
	}

	@Override
	public int getTicketPrice(int trainId) {
		int price = ticketDAOWrapper.getTicketPrice(trainId);
		return price;
	}

}
