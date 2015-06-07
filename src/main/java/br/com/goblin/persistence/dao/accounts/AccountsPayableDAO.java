package br.com.goblin.persistence.dao.accounts;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.goblin.domain.account.AccountPayable;
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
		return em.createQuery("select ap from AccountPayable ap order by ap.dueDate", AccountPayable.class).getResultList();
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
}
