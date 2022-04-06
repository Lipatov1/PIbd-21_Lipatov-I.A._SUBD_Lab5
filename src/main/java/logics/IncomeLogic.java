package logics;

import models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.List;
import java.util.Scanner;

public class IncomeLogic {
    public void work(SessionFactory sessionFactory) {
        System.out.println("Введите 1 для создания доходов");
        System.out.println("Введите 2 для чтения доходов");
        System.out.println("Введите 3 для изменения доходов");
        System.out.println("Введите 4 для удаления доходов");
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
        System.out.println("Введите дату транкзакции");
        String date = scanner.next();
        java.util.Date myDate = new java.util.Date(date);
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());

        System.out.println("Введите сумму транкзации");
        int amount = scanner.nextInt();

        System.out.println("Введите комменатрий");
        String comment = scanner.next();

        System.out.println("Введите номер категории доходов");
        int incomeCategory = scanner.nextInt();

        System.out.println("Введите номер типа доходов");
        int typeOfIncome = scanner.nextInt();

        System.out.println("Введите номер члена семьи");
        int familyMember = scanner.nextInt();

        Income income = new Income(sqlDate, amount, comment, session.get(IncomeCategory.class, incomeCategory),
                session.get(TypeOfIncome.class, typeOfIncome), session.get(FamilyMember.class, familyMember));
        session.save(income);
    }

    private void read(Session session) {
        List<Income> incomes = session.createQuery("SELECT a from Income a", Income.class).getResultList();
        System.out.println(incomes);
    }

    private void update(Session session) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ввдение id доходов");
        int id = scanner.nextInt();

        System.out.println("Введите дату транкзакции");
        String date = scanner.next();
        java.util.Date myDate = new java.util.Date(date);
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());

        System.out.println("Введите сумму транкзации");
        int amount = scanner.nextInt();

        System.out.println("Введите комменатрий");
        String comment = scanner.next();

        System.out.println("Введите номер категории доходов");
        int incomeCategory = scanner.nextInt();

        System.out.println("Введите номер типа доходов");
        int typeOfIncome = scanner.nextInt();

        System.out.println("Введите номер члена семьи");
        int familyMember = scanner.nextInt();

        Income income = session.get(Income.class, id);
        income.setTransactionDate(sqlDate);
        income.setAmount(amount);
        income.setComment(comment);
        income.setIncomeCategory(session.get(IncomeCategory.class, incomeCategory));
        income.setTypeOfIncome(session.get(TypeOfIncome.class, typeOfIncome));
        income.setFamilyMember(session.get(FamilyMember.class, familyMember));
        session.save(familyMember);
    }

    private void delete(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ввдение id доходов");
        int id = scanner.nextInt();

        Income income = session.get(Income.class, id);
        session.delete(income);
    }

    private void filterRead(Session session) {
        System.out.println("Введите 1 для фильтра по дате транкзакции");
        System.out.println("Введите 2 для фильтра по сумме транкзакции");
        System.out.println("Введите 3 для фильтра по комментарию");
        System.out.println("Введите 4 для фильтра по номеру категории доходов");
        System.out.println("Введите 5 для фильтра по номеру типа доходов");
        System.out.println("Введите 6 для фильтра по номеру члена семьи");

        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        List<Income> incomes = null;
        switch(i) {
            case 1:
                System.out.println("Введите дату транкзакции");
                String date = scanner.next();
                java.util.Date myDate = new java.util.Date(date);
                java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
                incomes = session.createQuery("SELECT a from Income a where transactionDate = \'" + sqlDate + "\'", Income.class).getResultList();
                break;
            case 2:
                System.out.println("Введите сумму транкзации");
                int amountt = scanner.nextInt();
                incomes = session.createQuery("SELECT a from Income a where amount = " + amountt, Income.class).getResultList();
                break;
            case 3:
                System.out.println("Введите комменатрий");
                String comments = scanner.next();
                incomes = session.createQuery("SELECT a from Income a where comment = \'" + comments + "\'", Income.class).getResultList();
                break;
            case 4:
                System.out.println("Введите номер категории доходов");
                int incomeCategories = scanner.nextInt();
                incomes = session.createQuery("SELECT a from Income a where incomeCategory = " + incomeCategories, Income.class).getResultList();
                break;
            case 5:
                System.out.println("Введите номер типа доходов");
                int typeOfIncomes = scanner.nextInt();
                incomes = session.createQuery("SELECT a from Income a where typeOfIncome = " + typeOfIncomes, Income.class).getResultList();
                break;
            case 6:
                System.out.println("Введите номер члена семьи");
                int familyMembers = scanner.nextInt();
                incomes = session.createQuery("SELECT a from Income a where familyMember = " + familyMembers, Income.class).getResultList();
                break;
        }
        System.out.println(incomes);
    }
}