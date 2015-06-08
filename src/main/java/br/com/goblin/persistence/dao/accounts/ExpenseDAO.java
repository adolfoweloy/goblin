/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.goblin.persistence.dao.accounts;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.goblin.domain.account.AccountPayable;
import br.com.goblin.domain.account.Expense;
import br.com.goblin.persistence.JpaUtil;

/**
 *
 * @author aeloy
 */
public class ExpenseDAO {
    
    private final EntityManager em;
    
    public ExpenseDAO() {
        em = JpaUtil.getEntityManager();
    }
    
    /**
     * Saves a new or update an existent expense.
     * @param expense 
     */
    public void save(Expense expense) {
        
        AccountPayable payable = em.find(AccountPayable.class, 
            expense.getAccountPayable().getId());
        payable.setPayed(true);
        
        expense.setAccountPayable(payable);
        
        if (expense.getId() == null) {
            em.persist(expense);
        } else {
            em.merge(expense);
        }
        
    }

    @SuppressWarnings("unchecked")
	public List<Expense> findByAccountPayable(Long id) {
		String sql = "select e from Expense e join e.accountPayable a where a.id = :id";
		Query query = em.createQuery(sql, Expense.class);
		query.setParameter("id", id);
		
		return query.getResultList();
	}
}
