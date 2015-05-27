package br.com.goblin.persistence.dao.supplier;

import java.util.List;

import br.com.goblin.domain.supplier.Supplier;
import br.com.goblin.persistence.JpaUtil;

public class SupplierDAO {

	public List<Supplier> list() {
		return JpaUtil.getEntityManager().createQuery("select s from Supplier s order by s.regularDayOfPayment", Supplier.class).getResultList();
	}

	public void save(Supplier supplier) {
		JpaUtil.getEntityManager().persist(supplier);
	}

	public void remove(Long id) {
		Supplier supplier = JpaUtil.getEntityManager().find(Supplier.class, id);
		JpaUtil.getEntityManager().remove(supplier);
	}
	
}
