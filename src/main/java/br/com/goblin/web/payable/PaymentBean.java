package br.com.goblin.web.payable;

import javax.faces.bean.ManagedBean;

import lombok.Getter;
import lombok.Setter;
import br.com.goblin.domain.account.AccountPayable;

@ManagedBean
public class PaymentBean {

	@Getter @Setter
	private PaymentViewPresenter payment;
	
	public String payment(AccountPayable payable) {
		this.payment = new PaymentViewPresenter(payable);
		return "payment";
	}
	
}
