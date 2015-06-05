package br.com.goblin.domain.supplier;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.goblin.domain.account.TaxPayerType;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

@Entity
@Table(name = "supplier")
public class Supplier implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@Column(name = "regular_day_of_payment")
	private Integer regularDayOfPayment;
	
	@Column(name = "tax_payer_number")
	private String taxPayerNumber;
	
	@Column(name = "tax_payer_type")
	private TaxPayerType taxPayerType;
	
	// JPA purposes only
	@Deprecated
	Supplier() { }
	
	public Supplier(String name, String taxPayerNumber, TaxPayerType taxPayerType) {
		this.name = name;
		this.taxPayerNumber = taxPayerNumber;
		this.taxPayerType = taxPayerType;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getRegularDayOfPayment() {
		return regularDayOfPayment;
	}

	public void setRegularDayOfPayment(Integer regularDayOfPayment) {
		this.regularDayOfPayment = regularDayOfPayment;
	}

	public String getTaxPayerNumber() {
		return taxPayerNumber;
	}

	public void setTaxPayerNumber(String taxPayerNumber) {
		this.taxPayerNumber = taxPayerNumber;
	}

	public TaxPayerType getTaxPayerType() {
		
		if (taxPayerType == null) {
			return TaxPayerType.INDIVIDUAL;
		}
		
		return taxPayerType;
	}

	public void setTaxPayerType(TaxPayerType taxPayerType) {
		this.taxPayerType = taxPayerType;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}

	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof Supplier) {
			Supplier other = (Supplier) obj;
			return Objects.equal(other.id, id);
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
			.add("id", this.id)
			.add("name", this.name)
			.add("regularDayOfPayment", this.regularDayOfPayment)
			.add("taxPayerNumber", this.taxPayerNumber)
			.add("taxPayerType", this.taxPayerType)
			.toString();
	}
}
