package br.com.goblin.persistence.dao.accounts;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.goblin.domain.account.AccountPayable;
import br.com.goblin.domain.general.Month;
import br.com.goblin.persistence.JpaUtil;

public class AccountsPayableDAO {

	private final EntityManager em;
	
	public AccountsPayableDAO() {
		em = JpaUtil.getEntityManager();
	}
	
	public void delete(Long id) {
		AccountPayable toRemove = em.find(AccountPayable.class, id);
		em.remove(toRemove);
	}
	
	public List<AccountPayable> getList() {
		return em.createQuery("select ap from AccountPayable ap where payed != true order by ap.dueDate", AccountPayable.class).getResultList();
	}

	public void save(AccountPayable payable) {
            if (payable.getId() == null) {
                em.persist(payable);
            } else {
                em.merge(payable);
            }
	}

	public AccountPayable findBy(Long id) {
		return em.find(AccountPayable.class, id);
	}

	public List<AccountPayable> getListByMonth(Month month) {
		
		Calendar lower = Calendar.getInstance();
		Calendar upper = Calendar.getInstance();
		
		lower.setTime(new Date());
		lower.set(Calendar.DAY_OF_MONTH, 1);
		lower.set(Calendar.MONTH, month.getYearMonth() - 1);
		
		upper.setTime(lower.getTime());
		upper.add(Calendar.MONTH, 1);
		upper.add(Calendar.DAY_OF_MONTH, -1);
		
		TypedQuery<AccountPayable> query = em.createQuery(
				"select ap from AccountPayable ap "
				+ "where ap.dueDate between :lower and :upper "
				+ "order by ap.dueDate desc",
				AccountPayable.class);
		
		query.setParameter("lower", lower);
		query.setParameter("upper", upper);
		
		return query.getResultList();
	}
}
