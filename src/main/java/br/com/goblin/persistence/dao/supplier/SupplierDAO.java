package br.com.goblin.persistence.dao.supplier;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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
            if (supplier.getId() == null) {
                em.persist(supplier);
            } else {
                em.merge(supplier);
            }
	}

	public void remove(Long id) {
		Supplier supplier = em.find(Supplier.class, id);
		em.remove(supplier);
	}

	public Supplier findById(Long id) {
		return em.find(Supplier.class, id);
	}
	
	public boolean hasAccountsPayable(Long id) {
		Query query = em.createQuery("select count(a) from AccountPayable a inner join a.supplier s where s.id = :id");
		query.setParameter("id", id);
		Long count = (Long) query.getSingleResult();
		return count > 0;
	}
	
}
