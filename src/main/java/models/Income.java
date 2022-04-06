package models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "income", schema = "public", catalog = "lab5")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Income {
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
    @JoinColumn(name = "income_category_id")
    private IncomeCategory incomeCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_of_income_id")
    private TypeOfIncome typeOfIncome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "family_member_id")
    private FamilyMember familyMember;

    public Income(Date transactionDate, int amount, String comment, IncomeCategory incomeCategory, TypeOfIncome typeOfIncome, FamilyMember familyMember) {
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.comment = comment;
        this.incomeCategory = incomeCategory;
        this.typeOfIncome = typeOfIncome;
        this.familyMember = familyMember;
    }

    @Override
    public String toString() {
        return String.format("\nid: %d || Дата: %s  || Сумма: %s  || Комменатрий: %s  || IncomeCategoryID: %s  || TypeOfIncomeID: %s  || FamilyMemberID: %s",
                id, transactionDate, amount, comment, incomeCategory.getId(), typeOfIncome.getId(), familyMember.getId());
    }
}