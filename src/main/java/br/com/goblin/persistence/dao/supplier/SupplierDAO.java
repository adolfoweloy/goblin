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
		Query query = em.createQuery("select new TotalOfAccountsPayable(count(a)) from AccountPayable a inner join a.supplier s where s.id = :id", 
			TotalOfAccountsPayable.class);
		query.setParameter("id", id);
		TotalOfAccountsPayable totalOfAccountsPayable = (TotalOfAccountsPayable) query.getSingleResult();
		return totalOfAccountsPayable.getTotal() > 0;
	}
	
	private static class TotalOfAccountsPayable {
		private int total;
		
		@SuppressWarnings("unused")
		public TotalOfAccountsPayable(int total) { this.total = total; }
		
		public int getTotal() {
			return total;
		}
	}
}
