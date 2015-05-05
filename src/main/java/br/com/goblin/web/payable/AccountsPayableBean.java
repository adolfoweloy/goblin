package br.com.goblin.web.payable;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import br.com.goblin.domain.account.AccountPayable;
import br.com.goblin.domain.supplier.Supplier;
import br.com.goblin.persistence.dao.accounts.AccountsPayableDAO;
import br.com.goblin.persistence.dao.supplier.SupplierDAO;

@ManagedBean
public class AccountsPayableBean {

	private List<AccountPayable> accountsPayable;
	
	private AccountsPayableDAO accounts;
	
	private SupplierDAO suppliers;
	
	private AccountPayableViewPresenter accountPayable = new AccountPayableViewPresenter();
	
	@PostConstruct
	public void init() {
		this.accounts = new AccountsPayableDAO();
		this.suppliers = new SupplierDAO();
	}
	
	public String create() {
		return "create";
	}
	
	public String save() {
		accounts.save(accountPayable.build());
		return "list";
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

}