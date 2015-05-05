package br.com.goblin.persistence.dao.supplier;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.goblin.domain.supplier.Supplier;
import br.com.goblin.persistence.JpaUtil;

public class SupplierDAO {

	private EntityManager em;

	public SupplierDAO() {
		em = JpaUtil.getEntityManager();
	}
	
	public List<Supplier> list() {
		return em.createQuery("select s from Supplier s order by s.regularDayOfPayment", Supplier.class).getResultList();
	}

	public void save(Supplier supplier) {
		em.persist(supplier);
	}

	public void remove(Long id) {
		Supplier supplier = em.find(Supplier.class, id);
		em.remove(supplier);
	}
	
}
