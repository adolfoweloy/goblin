package br.com.goblin.domain.account;

/**
 * Identifies a registration tax payer for Brazilian services known as CPF and CPNJ.
 * These registration tax payer numbers applies to corporate or individuals.
 * 
 * @author aeloy
 */
public enum TaxPayerType {
	INDIVIDUAL("PF", "Pessoa Física", "domain.taxpayer.individual", 11), 
	CORPORATE("PJ", "Pessoa Jurídica", "domain.taxpayer.corporate", 14);
	
	private String acronym;
	private String description;
	private String i18nMessage;
	
	private int size;
	
	private TaxPayerType(String acronym, String description, String i18nMessage, int size) { 
		this.acronym = acronym;
		this.description = description;
		this.i18nMessage = i18nMessage;
		this.size = size;
	}
	
	public String getAcronym() {
		return acronym;
	}

	public String getDescription() {
		return description;
	}
	
	public String getI18nMessage() {
		return i18nMessage;
	}
	
	public int getSize() {
		return size;
	}
}
