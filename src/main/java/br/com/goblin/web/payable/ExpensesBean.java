package br.com.goblin.web.payable;

import java.util.List;

import javax.faces.bean.ManagedBean;

import lombok.Getter;
import br.com.goblin.domain.account.Expense;
import br.com.goblin.persistence.dao.accounts.ExpenseDAO;
import br.com.goblin.web.faces.FacesUtils;

@ManagedBean
public class ExpensesBean {

	private final ExpenseDAO expenseDAO = new ExpenseDAO();

	@Getter
	private final ExpenseViewPresenter expense = new ExpenseViewPresenter();

	private List<Expense> expenses = null;

	public List<Expense> getList() {
		if (expenses == null) {
			expenses = expenseDAO.findAll();
			return expenses;
		}

		return expenses;
	}

	public String newExpense() {
		return "/accounts-payable/new-expense.xhtml";
	}
	
	public String save() {

		expenseDAO.save(expense.build());
		FacesUtils.addMessage("Expense saved", "Expense successfuly saved");

		return "/accounts-payable/dashboard.xhtml?faces-redirect=true";

	}

	public String cancelEdition() {
		return "/accounts-payable/dashboard.xhtml";
	}
}