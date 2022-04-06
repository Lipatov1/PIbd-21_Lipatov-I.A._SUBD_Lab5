package logics;

import models.TypeOfExpenses;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.List;
import java.util.Scanner;

public class TypeOfExpensesLogic {
    public void work(SessionFactory sessionFactory) {
        System.out.println("Введите 1 для создания тип расходов");
        System.out.println("Введите 2 для чтения типов расходов");
        System.out.println("Введите 3 для изменения типа расходов");
        System.out.println("Введите 4 для удаления типа расходов");
        System.out.println("Введите 5 для фильтра");

        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        Session session = null;
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        switch (i) {
            case 1:
                create(session);
                break;
            case 2:
                read(session);
                break;
            case 3:
                update(session);
                break;
            case 4:
                delete(session);
                break;
            case 5:
                filterRead(session);
                break;
        }
        session.getTransaction().commit();
    }

    private void create(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите тип расходов");
        String nameTypeOfExpenses = scanner.next();

        TypeOfExpenses typeOfExpenses = new TypeOfExpenses(nameTypeOfExpenses);
        session.save(typeOfExpenses);
    }

    private void read(Session session) {
        List<TypeOfExpenses> typeOfExpenses = session.createQuery("SELECT a from TypeOfExpenses a", TypeOfExpenses.class).getResultList();
        System.out.println(typeOfExpenses);
    }

    private void update(Session session) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите id типа расходов");
        int id = scanner.nextInt();

        System.out.println("Введите тип расходов");
        String nameTypeOfExpenses = scanner.next();

        TypeOfExpenses typeOfExpenses = session.get(TypeOfExpenses.class, id);
        typeOfExpenses.setTypeOfExpenses(nameTypeOfExpenses);
        session.save(typeOfExpenses);
    }

    private void delete(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите id типа расходов");
        int id = scanner.nextInt();

        TypeOfExpenses typeOfExpenses = session.get(TypeOfExpenses.class, id);
        session.delete(typeOfExpenses);
    }

    private void filterRead(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите тип расходов");
        String nameTypeOfExpenses = scanner.next();

        List<TypeOfExpenses> typeOfExpense = session.createQuery("SELECT a from TypeOfExpenses a where typeOfExpenses = \'" + nameTypeOfExpenses + "\'", TypeOfExpenses.class).getResultList();
        System.out.println(typeOfExpense);
    }
}