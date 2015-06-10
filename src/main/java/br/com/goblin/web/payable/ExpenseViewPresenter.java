/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.goblin.web.payable;

import br.com.goblin.domain.account.AccountPayable;
import br.com.goblin.domain.account.Expense;
import br.com.goblin.domain.supplier.Supplier;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import lombok.Getter;
import lombok.Setter;

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
 
    public void edit(Expense expense) {
        setDateOfPayment(expense.getDateOfPayment().getTime());
        setPaymentValue(expense.getPaymentValue());
    }
    
    public Expense build() {
        Expense expense = new Expense();
        if (expenseId != null) {
            expense.setId(expenseId);
        }
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateOfPayment);
        
        AccountPayable account = new AccountPayable(supplier, calendar, paymentValue);
        
        expense.setAccountPayable(account);
        expense.setDateOfPayment(calendar);
        expense.setPaymentValue(paymentValue);
        
        return expense;
    }
}