package br.com.goblin.domain.account;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

@Entity
@Table(name = "expense")
@NamedQueries({
    @NamedQuery(name="allExpenses", query="select e from Expense e"),
    @NamedQuery(name="accountByExpense", query="select a from Expense e join e.accountPayable a where e.id = :id")})
public class Expense {

    @Getter @Setter
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    @OneToOne // maybe it could be ManyToOne regarding parts of payments
    @JoinColumn(name = "account_payable_id", nullable = false)
    private AccountPayable accountPayable;

    @Getter @Setter
    @Column(name = "payment_value", nullable = false)
    private BigDecimal paymentValue;

    @Getter @Setter
    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_payment", nullable = false)
    private Calendar dateOfPayment;

    @Getter @Setter
    @Column(name = "description", nullable = true, length = 100)
    private String description;

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public boolean equals(final Object obj) {

        if (obj instanceof Expense) {
            Expense other = (Expense) obj;
            return Objects.equal(other.id, id);
        }

        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", this.id)
                .add("accountPayable", this.accountPayable)
                .add("paymentValue", this.paymentValue)
                .add("dateOfPayment", this.dateOfPayment)
                .toString();
    }

    public boolean hasAccountPayable() {
        return accountPayable != null;
    }

}