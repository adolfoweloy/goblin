package br.com.goblin.domain.account;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

@Entity
@Table(name = "expense")
public class Expense {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne // maybe it could be ManyToOne regarding parts of payments
	@JoinColumn(name = "account_payable_id", nullable = false)
	private AccountPayable accountPayable;
	
	@Column(name = "payment_value", nullable = false)
	private BigDecimal paymentValue;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date_of_payment", nullable = false)
	private Calendar dateOfPayment;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AccountPayable getAccountPayable() {
		return accountPayable;
	}

	public void setAccountPayable(AccountPayable accountPayable) {
		this.accountPayable = accountPayable;
	}

	public BigDecimal getPaymentValue() {
		return paymentValue;
	}

	public void setPaymentValue(BigDecimal paymentValue) {
		this.paymentValue = paymentValue;
	}

	public Calendar getDateOfPayment() {
		return dateOfPayment;
	}

	public void setDateOfPayment(Calendar dateOfPayment) {
		this.dateOfPayment = dateOfPayment;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}

	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof Expense) {
			Expense other = (Expense) obj;
			return Objects.equal(other.id, id);
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
			.add("id", this.id)
			.add("accountPayable", this.accountPayable)
			.add("paymentValue", this.paymentValue)
			.add("dateOfPayment", this.dateOfPayment)
			.toString();
	}	
}