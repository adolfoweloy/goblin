package br.com.goblin.web.payable;

import javax.faces.bean.ManagedBean;

import br.com.goblin.domain.account.AccountPayable;

@ManagedBean
public class ExpensesBean {
	private AccountPayable payable;
	
	public String payment(AccountPayable payable) {
		this.payable = payable;
		return "payment";
	}
	
	public AccountPayable getPayable() {
		return payable;
	}
}
