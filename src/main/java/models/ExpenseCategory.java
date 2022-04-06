package models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.*;

@Entity
@Table(name = "expense_category", schema = "public", catalog = "lab4")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExpenseCategory {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "expense_category")
    private String expenseCategory;

    @OneToMany(mappedBy = "expenseCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Expenses> expenses;

    public ExpenseCategory(String expenseCategory) {
        this.expenseCategory = expenseCategory;
        expenses = new ArrayList<>();
    }

    @Override
    public String toString() {
        return String.format("\nid: %d || Категория расходов: %s", id, expenseCategory);
    }
}