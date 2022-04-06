package models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "income_category", schema = "public", catalog = "lab5")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IncomeCategory {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "income_category")
    private String incomeCategory;

    @OneToMany(mappedBy = "incomeCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Income> incomes;

    public IncomeCategory(String incomeCategory) {
        this.incomeCategory = incomeCategory;
        incomes = new ArrayList<>();
    }

    @Override
    public String toString() {
        return String.format("\nid: %d || Категория доходов: %s", id, incomeCategory);
    }
}