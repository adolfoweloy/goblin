package br.com.goblin.web.payable;

import javax.faces.bean.ManagedBean;

import lombok.Getter;
import lombok.Setter;
import br.com.goblin.domain.account.AccountPayable;

@ManagedBean
public class PaymentBean {

	@Getter @Setter
	private PaymentViewPresenter payment = new PaymentViewPresenter();
	
	@Getter @Setter
	private Long accountPayableId;
	
	public String payment(AccountPayable payable) {
		this.payment = new PaymentViewPresenter(payable);
		return "payment";
	}
	
	public String payAccount() {
		// TODO - effective pay the account
		return "/accounts-payable/list.xhtml?faces-redirect=true";
	}
	
	public String cancel() {
		return "/accounts-payable/list.xhtml";
	}
}
