package br.com.goblin.web.payable;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import br.com.goblin.domain.account.AccountPayable;
import br.com.goblin.domain.supplier.Supplier;

public class AccountPayableViewPresenter {

	private Supplier supplier;
	
	private Date dueDate;
	
	private BigDecimal value;

	private Long id;
	
	public AccountPayableViewPresenter() {}
	
	public AccountPayableViewPresenter(Long id) {
		this.id = id;
	}
	
        /**
         * Returns the id of current account payable
         * @return Long
         */
        public Long getId() {
            return id;
        }
        
        public void setId(Long id) {
            this.id = id;
        }
        
	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Date getDueDate() {

		if (id != null) {
			return dueDate;
		}
		
		if (supplier != null) {
			Integer regularDayOfPayment = supplier.getRegularDayOfPayment();
			Calendar instance = Calendar.getInstance();
			instance.setTime(new Date());
			instance.set(Calendar.DAY_OF_MONTH, regularDayOfPayment);
			dueDate = instance.getTime();
		}
		
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public boolean getCanEditSupplier() {
		return id != null;
	}
	
	public AccountPayable build() {
            Calendar due = Calendar.getInstance();
            due.setTime(this.dueDate);
            
            AccountPayable payable = new AccountPayable(supplier, due, value);
            payable.setId(id);
            return payable;
	}
	
}
