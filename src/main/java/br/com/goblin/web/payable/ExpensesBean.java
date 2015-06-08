package br.com.goblin.web.payable;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import br.com.goblin.domain.account.Expense;
import br.com.goblin.persistence.dao.accounts.ExpenseDAO;

@ManagedBean
public class ExpensesBean {
	
	private final ExpenseDAO expenseDAO = new ExpenseDAO();
	
	private List<Expense> expenses = null;
	
	public List<Expense> getList() {
		if (expenses == null) {
			return expenseDAO.findAll();
		}
		
		return new ArrayList<Expense>();
	}
	
	public String newExpense() {
		return "new-expense.xhtml";
	}
}