package br.com.goblin.web.supplier;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import br.com.goblin.domain.account.TaxPayerType;
import br.com.goblin.domain.supplier.Supplier;
import br.com.goblin.persistence.dao.supplier.SupplierDAO;

@ManagedBean
@ViewScoped
public class SupplierBean {

	private List<Supplier> suppliers;
	
	private SupplierDAO dao;
	
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
	
	public String delete(Long id) {
		dao.remove(id);
		return "list.xhtml?faces-redirect=true";
	}
	
	public SupplierViewPresenter getSupplier() {
		return supplier;
	}
	
	public List<SelectItem> getTaxPayerTypes() {
		List<SelectItem> items = new ArrayList<>();
		
		for (TaxPayerType type : TaxPayerType.values()) {
			items.add(new SelectItem(type, type.getDescription()));
		}
		
		return items;
	}
}
