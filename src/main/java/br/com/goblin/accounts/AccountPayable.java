package br.com.goblin.accounts;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.common.base.MoreObjects;

@Entity
@Table(name = "account_payable")
public class AccountPayable {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "supplier_id")
	private Supplier supplier;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "due_date")
	private Calendar dueDate;
	
	@Column(name = "value")
	private BigDecimal value;
	
	@Column(name = "flag_payed")
	private Boolean payed;
	
	@Deprecated // JPA purposes only
	AccountPayable() { }

	public AccountPayable(Supplier supplier, Calendar dueDate, BigDecimal value) {
		this.supplier = supplier;
		this.dueDate = dueDate;
		this.value = value;
	}

	public Long getId() {
		return id;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public Calendar getDueDate() {
		return dueDate;
	}

	public BigDecimal getValue() {
		return value;
	}

	public Boolean getPayed() {
		return payed;
	}
	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
			.add("id", id)
			.add("supplier", supplier)
			.add("dueDate", dueDate)
			.add("value", value)
			.toString();
	}
}
