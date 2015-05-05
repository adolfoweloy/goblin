package br.com.goblin.web.supplier;

import br.com.goblin.domain.account.TaxPayerType;
import br.com.goblin.domain.supplier.Supplier;

public class SupplierViewPresenter {
	
	private String name;
	
	private Integer regularDayOfPayment;
	
	private String taxPayerNumber;
	
	private TaxPayerType taxPayerType;

	public SupplierViewPresenter() {
		// setting default values
		taxPayerType = TaxPayerType.INDIVIDUAL;
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
		return taxPayerType;
	}

	public void setTaxPayerType(TaxPayerType taxPayerType) {
		this.taxPayerType = taxPayerType;
	}

	public int getTaxPayerNumberSize() {
		return this.taxPayerType.getSize();
	}
	
	public Supplier buildSupplier() {
		Supplier supplier = new Supplier(name, taxPayerNumber, taxPayerType);
		
		if (regularDayOfPayment != null) {
			supplier.setRegularDayOfPayment(regularDayOfPayment);
		}
		
		return supplier;
	}
}
