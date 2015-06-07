package br.com.goblin.web.payable;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import br.com.goblin.domain.account.AccountPayable;
import br.com.goblin.domain.account.Expense;

import com.google.common.base.MoreObjects;

/**
 * Class which represents payment view content.
 *
 * @author aeloy
 */
public class PaymentViewPresenter {

    @Getter
    @Setter
    private AccountPayable payable;

    @Setter
    private BigDecimal paymentValue;

    @Getter
    @Setter
    private Calendar dateOfPayment = getCurrentCalendar();

    public PaymentViewPresenter(AccountPayable payable) {
        this.payable = payable;
    }

    /**
     * Retrieves a default value for payment value regarding previous
     * configuration through AccountPayable.
     *
     * @return BigDecimal default value
     */
    public BigDecimal getPaymentValue() {
        if (paymentValue == null) {
        	if (payable == null) {
        		return null;
        	}
        	
            return payable.getValue();
        }

        return paymentValue;
    }

    private static Calendar getCurrentCalendar() {
        Calendar today = Calendar.getInstance();
        today.setTime(new Date());
        return today;
    }

    /**
     * Build an expense based on account payable and user sent values.
     * @return 
     */
    public Expense buildExpense() {
        Expense expense = new Expense();
        expense.setAccountPayable(payable);
        expense.setDateOfPayment(dateOfPayment);
        expense.setPaymentValue(paymentValue);
        
        return expense;
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
