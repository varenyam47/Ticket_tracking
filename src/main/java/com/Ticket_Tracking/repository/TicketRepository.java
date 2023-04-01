package com.Ticket_Tracking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Ticket_Tracking.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

	// Custom Query
	@Query(value="select * from tickets t where t.ticket_title like %:keyword% or t.ticket_created_on like %:keyword% ", nativeQuery=true)

	List<Ticket> findByKeyword(@Param("keyword") String keyword);

}
