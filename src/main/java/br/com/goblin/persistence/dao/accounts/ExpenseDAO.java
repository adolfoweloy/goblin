/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.goblin.persistence.dao.accounts;

import br.com.goblin.domain.account.AccountPayable;
import br.com.goblin.domain.account.Expense;
import br.com.goblin.persistence.JpaUtil;
import javax.persistence.EntityManager;

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
        
        expense.setAccountPayable(payable);
        
        if (expense.getId() == null) {
            em.persist(expense);
        } else {
            em.merge(expense);
        }
        
    }
}
