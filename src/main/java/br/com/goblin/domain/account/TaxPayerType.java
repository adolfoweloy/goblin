package br.com.goblin.domain.account;

/**
 * Identifies a registration tax payer for Brazilian services known as CPF and CPNJ.
 * These registration tax payer numbers applies to corporate or individuals.
 * 
 * @author aeloy
 */
public enum TaxPayerType {
	INDIVIDUAL("PF", "Pessoa Física", 11), 
	CORPORATE("PJ", "Pessoa Jurídica", 14);
	
	private String acronym;
	private String description;
	private int size;
	
	private TaxPayerType(String acronym, String description, int size) { 
		this.acronym = acronym;
		this.description = description;
		this.size = size;
	}
	
	public String getAcronym() {
		return acronym;
	}

	public String getDescription() {
		return description;
	}
	
	public int getSize() {
		return size;
	}
}
