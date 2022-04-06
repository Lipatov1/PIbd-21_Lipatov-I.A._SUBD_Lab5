package models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "type_of_expenses", schema = "public", catalog = "lab5")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TypeOfExpenses {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "type_of_expenses")
    private String typeOfExpenses;

    @OneToMany(mappedBy = "typeOfExpenses", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Expenses> expenses;

    public TypeOfExpenses(String typeOfExpenses) {
        this.typeOfExpenses = typeOfExpenses;
        expenses = new ArrayList<>();
    }

    @Override
    public String toString() {
        return String.format("\nid: %d || Тип расходов: %s", id, typeOfExpenses);
    }
}