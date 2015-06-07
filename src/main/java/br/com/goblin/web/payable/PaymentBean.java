package br.com.goblin.web.payable;

import javax.faces.bean.ManagedBean;

import lombok.Getter;
import lombok.Setter;
import br.com.goblin.domain.account.AccountPayable;
import br.com.goblin.persistence.dao.accounts.ExpenseDAO;

@ManagedBean
public class PaymentBean {

    @Getter
    @Setter
    private PaymentViewPresenter payment;

    @Getter
    @Setter
    private Long accountPayableId;

    private final ExpenseDAO expenseDAO = new ExpenseDAO();
    
    public String forward(AccountPayable payable) {
        this.payment = new PaymentViewPresenter(payable);
        this.accountPayableId = payable.getId();
        return "payment";
    }

    public String payAccount() {
        expenseDAO.save(payment.buildExpense());
        return "/accounts-payable/list.xhtml?faces-redirect=true";
    }

    public String cancel() {
        return "/accounts-payable/list.xhtml";
    }
}
