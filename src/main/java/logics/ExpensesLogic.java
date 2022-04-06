package logics;

import models.ExpenseCategory;
import models.Expenses;
import models.FamilyMember;
import models.TypeOfExpenses;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.List;
import java.util.Scanner;

public class ExpensesLogic {
    public void work(SessionFactory sessionFactory) {
        System.out.println("Введите 1 для создания расходов");
        System.out.println("Введите 2 для чтения расходов");
        System.out.println("Введите 3 для изменения расходов");
        System.out.println("Введите 4 для удаления расходов");
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

        System.out.println("Введите номер категории расходов");
        int expenseCategory = scanner.nextInt();

        System.out.println("Введите номер типа расходов");
        int typeOfExpenses = scanner.nextInt();

        System.out.println("Введите номер члена семьи");
        int familyMember = scanner.nextInt();

        Expenses expenses = new Expenses(sqlDate, amount, comment, session.get(ExpenseCategory.class, expenseCategory),
                session.get(TypeOfExpenses.class, typeOfExpenses),session.get(FamilyMember.class, familyMember));
        session.save(expenses);
    }

    private void read(Session session) {
        List<Expenses> expenses = session.createQuery("SELECT a from Expenses a", Expenses.class).getResultList();
        System.out.println(expenses);
    }

    private void update(Session session) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ввдение id расходов");
        int id = scanner.nextInt();

        System.out.println("Введите дату транкзакции");
        String date = scanner.next();
        java.util.Date myDate = new java.util.Date(date);
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());

        System.out.println("Введите сумму транкзации");
        int amount = scanner.nextInt();

        System.out.println("Введите комменатрий");
        String comment = scanner.next();

        System.out.println("Введите номер категории расходов");
        int expenseCategory = scanner.nextInt();

        System.out.println("Введите номер типа расходов");
        int typeOfExpenses = scanner.nextInt();

        System.out.println("Введите номер члена семьи");
        int familyMember = scanner.nextInt();

        Expenses expenses = session.get(Expenses.class, id);
        expenses.setTransactionDate(sqlDate);
        expenses.setAmount(amount);
        expenses.setComment(comment);
        expenses.setExpenseCategory(session.get(ExpenseCategory.class, expenseCategory));
        expenses.setTypeOfExpenses(session.get(TypeOfExpenses.class, typeOfExpenses));
        expenses.setFamilyMember(session.get(FamilyMember.class, familyMember));
        session.save(familyMember);
    }

    private void delete(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ввдение id расходов");
        int id = scanner.nextInt();

        Expenses expenses = session.get(Expenses.class, id);
        session.delete(expenses);
    }

    private void filterRead(Session session) {
        System.out.println("Введите 1 для фильтра по дате транкзакции");
        System.out.println("Введите 2 для фильтра по сумме транкзакции");
        System.out.println("Введите 3 для фильтра по комментарию");
        System.out.println("Введите 4 для фильтра по номеру категории расходов");
        System.out.println("Введите 5 для фильтра по номеру типа расходов");
        System.out.println("Введите 6 для фильтра по номеру члена семьи");

        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        List<Expenses> expenses = null;
        switch(i) {
            case 1:
                System.out.println("Введите дату транкзакции");
                String date = scanner.next();
                java.util.Date myDate = new java.util.Date(date);
                java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
                expenses = session.createQuery("SELECT a from Expenses a where transactionDate = \'" + sqlDate + "\'", Expenses.class).getResultList();
                break;
            case 2:
                System.out.println("Введите сумму транкзации");
                int amountt = scanner.nextInt();
                expenses = session.createQuery("SELECT a from Expenses a where amount = " + amountt, Expenses.class).getResultList();
                break;
            case 3:
                System.out.println("Введите комменатрий");
                String comments = scanner.next();
                expenses = session.createQuery("SELECT a from Expenses a where comment = \'" + comments + "\'", Expenses.class).getResultList();
                break;
            case 4:
                System.out.println("Введите номер категории расходов");
                int expenseCategorys = scanner.nextInt();
                expenses = session.createQuery("SELECT a from Expenses a where expenseCategory = " + expenseCategorys, Expenses.class).getResultList();
                break;
            case 5:
                System.out.println("Введите номер типа расходов");
                int typeOfExpense = scanner.nextInt();
                expenses = session.createQuery("SELECT a from Expenses a where typeOfExpenses = " + typeOfExpense, Expenses.class).getResultList();
                break;
            case 6:
                System.out.println("Введите номер члена семьи");
                int familyMembers = scanner.nextInt();
                expenses = session.createQuery("SELECT a from Expenses a where familyMember = " + familyMembers, Expenses.class).getResultList();
                break;
        }
        System.out.println(expenses);
    }
}