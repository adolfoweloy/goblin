package br.com.goblin.web.payable;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import lombok.Getter;
import lombok.Setter;
import br.com.goblin.domain.account.AccountPayable;
import br.com.goblin.domain.general.Month;
import br.com.goblin.domain.supplier.Supplier;
import br.com.goblin.persistence.dao.accounts.AccountsPayableDAO;
import br.com.goblin.persistence.dao.supplier.SupplierDAO;
import br.com.goblin.web.dialog.ConfirmationBean;
import br.com.goblin.web.dialog.ConfirmationBean.ConfirmationAction;
import br.com.goblin.web.faces.FacesUtils;

@ManagedBean
public class AccountsPayableBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<AccountPayable> accountsPayable;

	private AccountsPayableDAO accounts;

	private SupplierDAO suppliers;

	@Getter
	@Setter
	private boolean editing;

	@Getter
	@Setter
	private AccountPayableViewPresenter accountPayable = new AccountPayableViewPresenter();

	@Getter
	@Setter
	@ManagedProperty(value="#{accountsPayableSearch}")
	private AccountsPayableSearch search = new AccountsPayableSearch();
	
	@ManagedProperty(value = "#{paymentBean}")
	private PaymentBean paymentBean;

	@Getter
	private BigDecimal totalPayed = BigDecimal.ZERO;

	@Getter
	private BigDecimal totalToPay = BigDecimal.ZERO;

	@PostConstruct
	public void init() {
		this.accounts = new AccountsPayableDAO();
		this.suppliers = new SupplierDAO();
		this.search.setMonth(Month.currentMonth());
	}

	public List<Month> getMonths() {
		return Arrays.asList(Month.values());
	}
	
	public String create() {
		setEditing(false);

		return "create";
	}

	public String edit(Long id) {
		AccountPayable payable = accounts.findBy(id);

		accountPayable = new AccountPayableViewPresenter(id);
		accountPayable.setDueDate(payable.getDueDate().getTime());
		accountPayable.setSupplier(payable.getSupplier());
		accountPayable.setValue(payable.getValue());

		setEditing(true);

		return "create";
	}

	public String delete(Long id) throws NoSuchMethodException,
			SecurityException {

		ConfirmationBean confirmBean = FacesUtils.findBean(ConfirmationBean.BEAN_NAME);
		confirmBean.setMessage("Do you want to remove selected account payable");
		confirmBean.setConfirm(new ConfirmationAction("accountsPayableBean", "confirmDelete", new Object[] { id }));
		confirmBean.setCancel(new ConfirmationAction("accountsPayableBean", "cancelDelete", new Object[] {}));

		return confirmBean.showMessage();
	}

	public String confirmDelete(Long id) {
		accounts.delete(id);
		return "/accounts-payable/dashboard.xhtml?faces-redirect=true";
	}

	public String cancelDelete() {
		return "/accounts-payable/dashboard.xhtml?faces-redirect=true";
	}

	public String save() {
		accounts.save(accountPayable.build());
		return "dashboard.xhtml?faces-redirect=true";
	}

	public String cancel() {
		return "dashboard.xhtml?faces-redirect=true";
	}

	public String pay(Long id) {
		return paymentBean.forward(accounts.findBy(id));
	}

	public List<Supplier> getSuppliers() {
		return suppliers.list();
	}

	public List<AccountPayable> getList() {

		if (accountsPayable == null) {
			accountsPayable = accounts.getListByMonth(search.getMonth());
		}

		totalPayed = BigDecimal.ZERO;
		totalToPay = BigDecimal.ZERO;
		for (AccountPayable p : accountsPayable) {
			if (p.getPayed()) {
				totalPayed = p.getValue().add(totalPayed);
			} else {
				totalToPay = p.getValue().add(totalToPay);
			}
		}
		
		return accountsPayable;
	}

	public void setPaymentBean(PaymentBean paymentBean) {
		this.paymentBean = paymentBean;
	}
}