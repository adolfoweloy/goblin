package br.com.goblin.web.payable;

import java.math.BigDecimal;
import java.util.Calendar;

import lombok.Getter;
import lombok.Setter;
import br.com.goblin.domain.account.AccountPayable;

import com.google.common.base.MoreObjects;

/**
 * Class which represents payment view content.
 *  
 * @author aeloy
 */
public class PaymentViewPresenter {

	@Getter
	private AccountPayable payable;

	@Getter @Setter
	private BigDecimal paymentValue;
	
	@Getter @Setter
	private Calendar dateOfPayment;
	
	@Deprecated
	public PaymentViewPresenter() { }
	
	public PaymentViewPresenter(AccountPayable payable) {
		this.payable = payable;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
			.add("payable", payable)
			.add("paymentValue", paymentValue)
			.add("dateOfPayment", dateOfPayment)
			.toString();
	}
	
}