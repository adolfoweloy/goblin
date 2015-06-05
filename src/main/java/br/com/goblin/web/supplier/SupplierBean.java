package br.com.goblin.web.supplier;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.com.goblin.domain.account.TaxPayerType;
import br.com.goblin.domain.supplier.Supplier;
import br.com.goblin.persistence.dao.supplier.SupplierDAO;
import br.com.goblin.web.dialog.ConfirmationBean;
import br.com.goblin.web.dialog.ConfirmationBean.ConfirmationAction;
import br.com.goblin.web.faces.FacesUtils;

@ManagedBean
public class SupplierBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<Supplier> suppliers;
	
	private transient SupplierDAO dao;
	
	private SupplierViewPresenter supplier = new SupplierViewPresenter();
	
	@PostConstruct
	public void init() {
		dao = new SupplierDAO();
	}
	
	public String create() {
		return "create";
	}
	
	public List<Supplier> getList() {
		
		if (suppliers == null) {
			suppliers = dao.list();
		}
		
		return suppliers;
	}

	public String save() {
		dao.save(supplier.buildSupplier());
		return "list";
	}

	public String cancel() {
		return "list";
	}
	
	public String edit(Long id) {
		Supplier supplier = dao.findById(id);
		
		this.supplier = new SupplierViewPresenter(id);
		this.supplier.setName(supplier.getName());
		this.supplier.setRegularDayOfPayment(supplier.getRegularDayOfPayment());
		this.supplier.setTaxPayerNumber(supplier.getTaxPayerNumber());
		this.supplier.setTaxPayerType(supplier.getTaxPayerType());
		
		return "create";
	}
	
	public String delete(Long id) {
		ConfirmationBean confirmBean = FacesUtils.findBean(ConfirmationBean.BEAN_NAME);
		confirmBean.setMessage("Do you want to remove selected supplier?");
		confirmBean.setConfirm(new ConfirmationAction("supplierBean", "confirmDelete", new Object[]{id}));
		confirmBean.setCancel(new ConfirmationAction("supplierBean", "cancelDelete", new Object[]{}));
		
		return confirmBean.showMessage();
	}
	
	public String confirmDelete(Long id) {
		
		if (dao.hasAccountsPayable(id)) {
			FacesContext ctx = FacesContext.getCurrentInstance();
			ctx.addMessage("supplier", 
				new FacesMessage(
						FacesMessage.SEVERITY_ERROR, 
						"Current supplier has registered accounts payable", 
						"Current supplier has being used"));
			
		} else { 
			dao.remove(id);
		}
		
		return "/supplier/list.xhtml";
		
	}
	
	public String cancelDelete() {
		return "/supplier/list.xhtml?faces-redirect=true";
	}
	
	public SupplierViewPresenter getSupplier() {
		return supplier;
	}
	
	public List<SelectItem> getTaxPayerTypes() {
		List<SelectItem> items = new ArrayList<>();
		
		FacesContext context = FacesContext.getCurrentInstance();
		Application application = context.getApplication();
		ResourceBundle msg = application.getResourceBundle(context, "msg");
		
		for (TaxPayerType type : TaxPayerType.values()) {
			items.add(new SelectItem(type, msg.getString(type.getI18nMessage())));
		}
		
		return items;
	}
}
