package br.com.goblin.web.payable;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import br.com.goblin.domain.account.AccountPayable;
import br.com.goblin.domain.supplier.Supplier;

import com.google.common.base.MoreObjects;

/**
 * Class which represents payment view content.
 *  
 * @author aeloy
 */
public class PaymentViewPresenter {

	private AccountPayable payable;

	@Setter
	private BigDecimal paymentValue;
	
	@Getter @Setter
	private Calendar dateOfPayment = getCurrentCalendar();
	
	@Deprecated
	public PaymentViewPresenter() { }
	
	public PaymentViewPresenter(AccountPayable payable) {
		this.payable = payable;
	}

	public Long getAccountPayableId() {
		return payable.getId();
	}
	
	public Supplier getSupplier() {
		return payable.getSupplier();
	}

	public Calendar getDueDate() {
		return payable.getDueDate();
	}

	public BigDecimal getValue() {
		return payable.getValue();
	}

	public Boolean getPayed() {
		return payable.getPayed();
	}
	
	public BigDecimal getPaymentValue() {
		if (paymentValue == null) {
			paymentValue = payable.getValue();
		}
		
		return paymentValue;
	}
	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
			.add("payable", payable)
			.add("paymentValue", paymentValue)
			.add("dateOfPayment", dateOfPayment)
			.toString();
	}
	
	private static Calendar getCurrentCalendar() {
		Calendar today = Calendar.getInstance();
		today.setTime(new Date());
		return today;
	}
	
}