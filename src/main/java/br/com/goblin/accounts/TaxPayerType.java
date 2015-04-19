package br.com.goblin.accounts;

/**
 * Identifies a registration tax payer for Brazilian services known as CPF and CPNJ.
 * These registration tax payer numbers applies to corporate or individuals.
 * 
 * @author aeloy
 */
public enum TaxPayerType {
	INDIVIDUAL("PF"), CORPORATE("PJ");
	
	private String acronym;
	
	private TaxPayerType(String acronym) { this.acronym = acronym; }
	
	public String getAcronym() {
		return acronym;
	}
}
