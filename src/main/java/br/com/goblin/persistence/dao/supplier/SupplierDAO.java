package br.com.goblin.persistence.dao.supplier;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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

	public Supplier findById(Long id) {
		return JpaUtil.getEntityManager().find(Supplier.class, id);
	}
	
	public boolean hasAccountsPayable(Long id) {
		EntityManager em = JpaUtil.getEntityManager();
		Query query = em.createQuery("select count(a) from AccountPayable a inner join a.supplier s where s.id = :id");
		query.setParameter("id", id);
		Long count = (Long) query.getSingleResult();
		return count > 0;
	}
	
}
