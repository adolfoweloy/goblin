package br.com.goblin.accounts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

@Entity
@Table(name = "supplier")
public class Supplier {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@Column(name = "regular_day_of_payment")
	private int regularDayOfPayment;
	
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
	
	public String getName() {
		return name;
	}
	
	public String getTaxPayerNumber() {
		return taxPayerNumber;
	}
	
	public TaxPayerType getTaxPayerType() {
		return taxPayerType;
	}

	public int getRegularDayOfPayment() {
		return regularDayOfPayment;
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
