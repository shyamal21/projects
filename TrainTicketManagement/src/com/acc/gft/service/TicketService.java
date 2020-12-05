package com.acc.gft.service;

import java.util.List;

import com.acc.gft.bean.TicketBean;
import com.acc.gft.bean.TrainBean;

public interface TicketService {

	TicketBean addTicket(TicketBean bean) throws Exception;

	List<TrainBean> populateTrainDetails() throws Exception;

	List<TicketBean> getAllTicketDetails();

	long countTicket(int trainId) throws Exception;

	int getTicketPrice(int trainId);
}
