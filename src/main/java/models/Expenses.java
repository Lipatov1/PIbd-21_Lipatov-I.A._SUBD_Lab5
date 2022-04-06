package models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "expenses", schema = "public", catalog = "lab5")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Expenses {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "transaction_date")
    private Date transactionDate;

    @Column(name = "amount")
    private int amount;

    @Column(name = "comment")
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expense_category_id")
    private ExpenseCategory expenseCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_of_expenses_id")
    private TypeOfExpenses typeOfExpenses;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "family_member_id")
    private FamilyMember familyMember;

    public Expenses(Date transactionDate, int amount, String comment, ExpenseCategory expenseCategory, TypeOfExpenses typeOfExpenses, FamilyMember familyMember) {
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.comment = comment;
        this.expenseCategory = expenseCategory;
        this.typeOfExpenses = typeOfExpenses;
        this.familyMember = familyMember;
    }

    @Override
    public String toString() {
        return String.format("\nid: %d || Дата: %s  || Сумма: %s  || Комменатрий: %s  || ExpenseCategoryID: %s  || TypeOfExpensesID: %s  || FamilyMemberID: %s",
                id, transactionDate, amount, comment, expenseCategory.getId(), typeOfExpenses.getId(), familyMember.getId());
    }
}