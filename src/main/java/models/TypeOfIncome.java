package models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "type_of_income", schema = "public", catalog = "lab5")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TypeOfIncome {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "type_of_income")
    private String typeOfIncome;

    @OneToMany(mappedBy = "typeOfIncome", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Income> incomes;

    public TypeOfIncome(String typeOfIncome) {
        this.typeOfIncome = typeOfIncome;
    }

    @Override
    public String toString() {
        return String.format("\nid: %d || Тип доходов: %s", id, typeOfIncome);
    }
}