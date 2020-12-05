package com.acc.gft.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.acc.gft.bean.TicketBean;
import com.acc.gft.bean.TrainBean;
import com.acc.gft.entity.TicketEntity;
import com.acc.gft.entity.TrainEntity;

@Repository
@Transactional
public class TicketDAOWrapper {
	@Autowired
	TicketDAO ticketDAO;

	@PersistenceContext
	EntityManager entityManager;

	public List<TicketBean> getAllTicketDetails() {
		List<TicketEntity> ticketDetails = ticketDAO.getTickets();
		List<TicketBean> ticketDetailsBean = new ArrayList<TicketBean>();
		TicketBean bean = new TicketBean();

		for (TicketEntity entity : ticketDetails) {
			bean = convertEntityToBean(entity);
			ticketDetailsBean.add(bean);
		}
		return ticketDetailsBean;
	}

	public TicketBean addTicket(TicketBean bean) throws Exception {
		TicketEntity entity = new TicketEntity();
		entity = convertBeanToEntity(bean);
		entity = ticketDAO.save(entity);
		bean = convertEntityToBean(entity);
		return bean;
	}

	@SuppressWarnings("unchecked")
	public List<TrainBean> populateTrainDetails() throws Exception {
		Query query = entityManager.createQuery("select k from TrainEntity k");
		List<TrainEntity> trainEntityList = query.getResultList();
		List<TrainBean> trainBeanList = new ArrayList<TrainBean>();
		for (TrainEntity trainEntity : trainEntityList) {
			TrainBean tempBean = new TrainBean();
			tempBean.setTrainId(trainEntity.getTrainId());
			tempBean.setTrainName(trainEntity.getTrainName());
			tempBean.setTicketPrice(trainEntity.getTicketPrice());
			trainBeanList.add(tempBean);
		}
		return trainBeanList;
	}

	@SuppressWarnings("unchecked")
	public int countTicket(int trainId) throws Exception {
		/*
		 * int i = 1; int count = 0; TicketEntity ticketDetails;
		 * 
		 * while (entityManager.find(TicketEntity.class, i) != null) { ticketDetails =
		 * entityManager.find(TicketEntity.class, i); if (ticketDetails.getTrainId() ==
		 * trainId) { count = count + ticketDetails.getTicketBooked(); } i++; }
		 * 
		 * return count;
		 */
		int count = 0;
		Query query = entityManager.createQuery("select k from TicketEntity k where k.trainId=?1");
		query.setParameter(1, trainId);
		List<TicketEntity> listTicketEntity = query.getResultList();
		for (TicketEntity ticketEntity : listTicketEntity) {
			count = count + ticketEntity.getTicketBooked();
		}
		return count;
	}

	public int getTicketPrice(int trainId) {
		int price = 0;
		TrainEntity trainDetails = entityManager.find(TrainEntity.class, trainId);
		price = trainDetails.getTicketPrice();
		return price;
	}

	public TicketBean convertEntityToBean(TicketEntity entity) {
		TicketBean bean = new TicketBean();
		BeanUtils.copyProperties(entity, bean);
		return bean;
	}

	public TicketEntity convertBeanToEntity(TicketBean bean) {
		TicketEntity entity = new TicketEntity();
		BeanUtils.copyProperties(bean, entity);
		return entity;
	}
}
