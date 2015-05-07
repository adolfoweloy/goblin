package br.com.goblin.domain.account;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class AccountReceivable {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "due_date")
	private Calendar dueDate;
	
	@Column(name = "value")
	private BigDecimal value;
	
	@Column(name = "flag_received")
	private Boolean received;
	
}
