package logics;

import models.TypeOfIncome;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;

public class TypeOfIncomeLogic {
    public void work(SessionFactory sessionFactory) {
        System.out.println("Введите 1 для создания тип доходов");
        System.out.println("Введите 2 для чтения типов доходов");
        System.out.println("Введите 3 для изменения типа доходов");
        System.out.println("Введите 4 для удаления типа доходов");
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
        System.out.println("Введите тип доходов");
        String nameTypeOfIncome= scanner.next();

        TypeOfIncome typeOfIncome = new TypeOfIncome(nameTypeOfIncome);
        session.save(typeOfIncome);
    }

    private void read(Session session) {
        List<TypeOfIncome> typeOfIncome = session.createQuery("SELECT a from TypeOfIncome a", TypeOfIncome.class).getResultList();
        System.out.println(typeOfIncome);
    }

    private void update(Session session) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите id типа доходов");
        int id = scanner.nextInt();

        System.out.println("Введите тип доходов");
        String nameTypeOfIncome = scanner.next();

        TypeOfIncome typeOfIncome = session.get(TypeOfIncome.class, id);
        typeOfIncome.setTypeOfIncome(nameTypeOfIncome);
        session.save(typeOfIncome);
    }

    private void delete(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите id типа доходов");
        int id = scanner.nextInt();

        TypeOfIncome typeOfIncome = session.get(TypeOfIncome.class, id);
        session.delete(typeOfIncome);
    }

    private void filterRead(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите тип доходов");
        String nameTypeOfIncome = scanner.next();

        List<TypeOfIncome> typeOfIncomes = session.createQuery("SELECT a from TypeOfIncome a where typeOfIncome = \'" + nameTypeOfIncome + "\'", TypeOfIncome.class).getResultList();
        System.out.println(typeOfIncomes);
    }
}