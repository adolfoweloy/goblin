package br.com.goblin.domain.account;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

@Entity
@Table(name="customer")
public class Customer {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name", length=60, nullable=false)
	private String name;
	
	@Column(name = "tax_payer_number", nullable=true)
	private String taxPayerNumber;
	
	@Column(name = "tax_payer_type", nullable=false)
	private TaxPayerType taxPayerType;

	@Temporal(TemporalType.DATE)
	private Calendar registryDate;

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

	public String getTaxPayerNumber() {
		return taxPayerNumber;
	}

	public void setTaxPayerNumber(String taxPayerNumber) {
		this.taxPayerNumber = taxPayerNumber;
	}

	public TaxPayerType getTaxPayerType() {
		return taxPayerType;
	}

	public void setTaxPayerType(TaxPayerType taxPayerType) {
		this.taxPayerType = taxPayerType;
	}

	public Calendar getRegistryDate() {
		return registryDate;
	}

	public void setRegistryDate(Calendar registryDate) {
		this.registryDate = registryDate;
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}

	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof Customer) {
			Customer other = (Customer) obj;
			return Objects.equal(other.id, id);
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
			.add("id", this.id)
			.add("name", this.name)
			.add("taxPayerNumber", this.taxPayerNumber)
			.add("taxPayerType", this.taxPayerType)
			.add("registryDate", registryDate)
			.toString();
	}	
}
