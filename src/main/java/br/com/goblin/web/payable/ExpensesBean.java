package br.com.goblin.web.payable;

import java.util.List;

import javax.faces.bean.ManagedBean;

import lombok.Getter;
import br.com.goblin.domain.account.Expense;
import br.com.goblin.persistence.dao.accounts.ExpenseDAO;
import br.com.goblin.web.dialog.ConfirmationBean;
import br.com.goblin.web.dialog.ConfirmationBean.ConfirmationAction;
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

	public String edit(final Long id) {
		Expense expense = expenseDAO.findById(id);

		this.expense.setExpenseId(id);
		this.expense.setAccountPayableId(expense.getAccountPayable().getId());
		this.expense.setDateOfPayment(expense.getDateOfPayment().getTime());
		this.expense.setDescription(expense.getDescription());
		this.expense.setPaymentValue(expense.getPaymentValue());
		this.expense.setSupplier(expense.getAccountPayable().getSupplier());

		return "new-expense";
	}

	public String delete(final Long id) {

		Expense expense = expenseDAO.findById(id);
		if (expense.hasAccountPayable()) {
			FacesUtils.addMessage(
					"You cannot remove an expense related to an account payable",
					"first remove associated account payable");
			return null;
		}

		ConfirmationBean confirmBean = FacesUtils.findBean(ConfirmationBean.BEAN_NAME);
		confirmBean.setMessage("Do you want to remove selected expense");
		confirmBean.setConfirm(new ConfirmationAction("expensesBean", "confirmDelete", new Object[] { id }));
		confirmBean.setCancel(new ConfirmationAction("expensesBean", "cancelDelete", new Object[] {}));

		return confirmBean.showMessage();
	}

	public String confirmDelete(final Long id) {
		expenseDAO.delete(id);
		return "/accounts-payable/expenses.xhtml?faces-redirect=true";
	}

	public String cancelDelete() {
		return "/accounts-payable/expenses.xhtml?faces-redirect=true";
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