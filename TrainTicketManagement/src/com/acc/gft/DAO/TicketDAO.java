package com.acc.gft.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.transaction.annotation.Transactional;

import com.acc.gft.entity.TicketEntity;

@RepositoryDefinition(idClass = Integer.class, domainClass = TicketEntity.class)
@Transactional
public interface TicketDAO{

	TicketEntity save(TicketEntity entity); 
	
	@Query(name = "getTickets")
	List<TicketEntity> getTickets();
	 
}
