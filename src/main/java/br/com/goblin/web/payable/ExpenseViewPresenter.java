/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.goblin.web.payable;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import javax.faces.bean.ManagedBean;

import lombok.Getter;
import lombok.Setter;
import br.com.goblin.domain.account.AccountPayable;
import br.com.goblin.domain.account.Expense;
import br.com.goblin.domain.supplier.Supplier;
import br.com.goblin.persistence.dao.accounts.AccountsPayableDAO;

/**
 *
 * @author aeloy
 */
@ManagedBean
public class ExpenseViewPresenter {

	@Getter @Setter
	private Long expenseId;

	@Getter @Setter
	private Supplier supplier;

	@Getter @Setter
	private Date dateOfPayment;

	@Getter @Setter
	private BigDecimal paymentValue;

	@Getter @Setter
	private String description;

	@Getter @Setter
	private Long accountPayableId;

	public void edit(final Expense expense) {
		setDateOfPayment(expense.getDateOfPayment().getTime());
		setPaymentValue(expense.getPaymentValue());
		setDescription(expense.getDescription());
	}

	public Expense build() {

		Expense expense = new Expense();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateOfPayment);

		if (expenseId != null) {
			AccountsPayableDAO accountPayableDao = new AccountsPayableDAO();

			expense.setId(expenseId);
			expense.setAccountPayable(accountPayableDao.findBy(getAccountPayableId()));
			expense.getAccountPayable().setSupplier(supplier);
		} else {
			expense.setAccountPayable(new AccountPayable(supplier, calendar, paymentValue));
		}

		expense.setDateOfPayment(calendar);
		expense.setDescription(description);
		expense.setPaymentValue(paymentValue);

		return expense;
	}
}