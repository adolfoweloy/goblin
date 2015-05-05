package br.com.goblin.domain.account;

public class Customer {
	private boolean specialClient;
	private Double currentBalance;
	
	public boolean isSpecialClient() {
		return specialClient;
	}
	
	public Double getCurrentBalance() {
		return currentBalance;
	}
	
	public void setAsSpecial() {
		specialClient = true;
	}
	
	public void setAsOrdinary() {
		specialClient = false;
	}
	
	public void setCurrentBalance(Double currentBalance) {
		this.currentBalance = currentBalance;
	}
	
	// TODO - porque retornando boolean????
	public boolean withdraw(Double amount) throws Exception {
		
		if (this.currentBalance < amount) {
			if (isSpecialClient()) {
				updateBalance(amount);
				return true;
			} else {
				//TODO - pelo amor de todos os bytecodes já escritos no mundo
				// que merda é essa?????
				throw new Exception("not enough balance");
			}
		} else {
			updateBalance(amount);
			return true;
		}
		
	}

	private void updateBalance(Double amount) {
		this.currentBalance -= amount;
	}
	
	
}
