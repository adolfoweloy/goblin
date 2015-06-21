package br.com.goblin.domain.account;

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

import lombok.Getter;
import lombok.Setter;
import br.com.goblin.domain.supplier.Supplier;

import com.google.common.base.MoreObjects;

@Entity
@Table(name = "account_payable")
public class AccountPayable {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "supplier_id")
	@Getter @Setter private Supplier supplier;

	@Temporal(TemporalType.DATE)
	@Column(name = "due_date")
	private Calendar dueDate;

	@Column(name = "value")
	private BigDecimal value;

	@Column(name = "flag_payed")
	private Boolean payed = false;

	@Deprecated // JPA purposes only
	public AccountPayable() { }

	public AccountPayable(final Supplier supplier, final Calendar dueDate, final BigDecimal value) {
		this.supplier = supplier;
		this.dueDate = dueDate;
		this.value = value;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public Calendar getDueDate() {
		return dueDate;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(final BigDecimal value) {
		this.value = value;
	}

	public void setPayed(final Boolean payed) {
		this.payed = payed;
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
				.add("payed", payed)
				.toString();
	}
}
