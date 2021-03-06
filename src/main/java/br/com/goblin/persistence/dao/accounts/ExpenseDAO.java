/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.goblin.persistence.dao.accounts;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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
	public void save(final Expense expense) {

		AccountPayable payable = expense.getAccountPayable();
		AccountsPayableDAO accountPayableDao = new AccountsPayableDAO();
		accountPayableDao.save(payable);

		//        AccountPayable payable = em.find(AccountPayable.class,
		//            expense.getAccountPayable().getId());
		payable.setPayed(true);

		expense.setAccountPayable(payable);

		if (expense.getId() == null) {
			em.persist(expense);
		} else {
			em.merge(expense);
		}

	}

	@SuppressWarnings("unchecked")
	public List<Expense> findByAccountPayable(final Long id) {
		String sql = "select e from Expense e join e.accountPayable a where a.id = :id order by e.dateOfPayment desc";
		Query query = em.createQuery(sql, Expense.class);
		query.setParameter("id", id);

		return query.getResultList();
	}

	public List<Expense> findAll() {
		TypedQuery<Expense> query = em.createQuery("select e from Expense e order by e.dateOfPayment desc", Expense.class);
		return query.getResultList();
	}

	public Expense findById(final Long id) {
		return em.find(Expense.class, id);
	}

	public void delete(final Long id) {
		Expense expense = findById(id);

		if (expense == null) {
			throw new RuntimeException("Expense not found");
		}

		em.remove(expense);
	}
}
