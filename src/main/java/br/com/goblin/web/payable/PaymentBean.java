package br.com.goblin.web.payable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import lombok.Getter;
import lombok.Setter;
import br.com.goblin.domain.account.AccountPayable;
import br.com.goblin.persistence.dao.accounts.ExpenseDAO;

@ManagedBean
public class PaymentBean {

    @Getter @Setter
    @ManagedProperty(value = "#{payment}")
    private PaymentViewPresenter payment = new PaymentViewPresenter();

    private final ExpenseDAO expenseDAO = new ExpenseDAO();
    
    public String forward(AccountPayable accountPayable) {
        payment.setPayable(accountPayable);
        return "payment";
    }

    public String payAccount() {
        expenseDAO.save(payment.buildExpense());
        return "/accounts-payable/dashboard.xhtml?faces-redirect=true";
    }

    public String cancel() {
        return "/accounts-payable/dashboard.xhtml";
    }
}
