package br.com.goblin.web.payable;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import br.com.goblin.domain.account.AccountPayable;
import br.com.goblin.domain.supplier.Supplier;
import br.com.goblin.persistence.dao.accounts.AccountsPayableDAO;
import br.com.goblin.persistence.dao.supplier.SupplierDAO;
import br.com.goblin.web.dialog.ConfirmationBean;
import br.com.goblin.web.dialog.ConfirmationBean.ConfirmationAction;
import br.com.goblin.web.faces.FacesUtils;

@ManagedBean
public class AccountsPayableBean {

	private List<AccountPayable> accountsPayable;
	
	private AccountsPayableDAO accounts;
	
	private SupplierDAO suppliers;
	
	private AccountPayableViewPresenter accountPayable = new AccountPayableViewPresenter();

	@ManagedProperty(value="#{expensesBean}")
	private ExpensesBean expensesBean;
	
	@PostConstruct
	public void init() {
		this.accounts = new AccountsPayableDAO();
		this.suppliers = new SupplierDAO();
	}
	
	public String create() {
		return "create";
	}
	
	public String edit(Long id) {
		AccountPayable payable = accounts.findBy(id);
		
		accountPayable = new AccountPayableViewPresenter(id);
		accountPayable.setDueDate(payable.getDueDate());
		accountPayable.setSupplier(payable.getSupplier());
		accountPayable.setValue(payable.getValue());
		
		return "create";
	}
	
	public String delete(Long id) throws NoSuchMethodException, SecurityException {
		
		ConfirmationBean confirmBean = FacesUtils.findBean(ConfirmationBean.BEAN_NAME);
		confirmBean.setMessage("Do you want to remove selected account payable");
		confirmBean.setConfirm(new ConfirmationAction("accountsPayableBean", "confirmDelete", new Object[]{id}));
		confirmBean.setCancel(new ConfirmationAction("accountsPayableBean", "cancelDelete", new Object[]{}));
		
		return confirmBean.showMessage();
	}
	
	public String confirmDelete(Long id) {
		accounts.delete(id);
		return "/accounts-payable/list.xhtml?faces-redirect=true";
	}
	
	public String cancelDelete() {
		return "/accounts-payable/list.xhtml?faces-redirect=true";
	}
	
	public String save() {
		accounts.save(accountPayable.build());
		return "list.xhtml?faces-redirect=true";
	}
	
	public String pay(Long id) {
		AccountPayable payable = accounts.findBy(id);
		return expensesBean.payment(payable);
	}
	
	public List<Supplier> getSuppliers() {
		return suppliers.list();
	}
	
	public List<AccountPayable> getList() {
		
		if (accountsPayable == null) {
			accountsPayable = accounts.getList(); 
		}
		
		return accountsPayable;
	}

	public AccountPayableViewPresenter getAccountPayable() {
		return accountPayable;
	}

	public void setAccountPayable(AccountPayableViewPresenter accountPayable) {
		this.accountPayable = accountPayable;
	}

	public void setExpensesBean(ExpensesBean expensesBean) {
		this.expensesBean = expensesBean;
	}
}