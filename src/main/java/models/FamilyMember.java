package models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "family_member", schema = "public", catalog = "lab5")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FamilyMember {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "gender")
    private String gender;

    @Column(name = "birthday")
    private Date birthday;

    @OneToMany(mappedBy = "familyMember", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Expenses> expenses;

    @OneToMany(mappedBy = "familyMember", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Income> incomes;

    public FamilyMember(String name, String lastName, String patronymic, String gender, Date birthday) {
        this.name = name;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.gender = gender;
        this.birthday = birthday;
        expenses = new ArrayList<>();
        incomes = new ArrayList<>();
    }

    @Override
    public String toString() {
        return String.format("\nid: %d || iмя: %s || Фамилия: %s || Отчество: %s || Пол: %s || Дата рождения: %s",
                id, name, lastName, patronymic, gender, birthday);
    }
}