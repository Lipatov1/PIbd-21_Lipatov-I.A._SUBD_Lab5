package logics;

import models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;

public class ExpenseCategoryLogic {
    public void work(SessionFactory sessionFactory) {
        System.out.println("Введите 1 для создания категории расходов");
        System.out.println("Введите 2 для чтения категорий расходов");
        System.out.println("Введите 3 для изменения категории расходов");
        System.out.println("Введите 4 для удаления категории расходов");
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
        System.out.println("Введите название категории расходов");
        String nameExpenseCategory = scanner.next();

        ExpenseCategory expenseCategory = new ExpenseCategory(nameExpenseCategory);
        session.save(expenseCategory);
    }

    private void read(Session session) {
        List<ExpenseCategory> expenseCategorys = session.createQuery("SELECT a from ExpenseCategory a", ExpenseCategory.class).getResultList();
        System.out.println(expenseCategorys);
    }

    private void update(Session session) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите id категории расходов");
        int id = scanner.nextInt();

        System.out.println("Введите название категории расходов");
        String nameExpenseCategory = scanner.next();

        ExpenseCategory expenseCategory = session.get(ExpenseCategory.class, id);
        expenseCategory.setExpenseCategory(nameExpenseCategory);
        session.save(expenseCategory);
    }

    private void delete(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите id категории расходов");
        int id = scanner.nextInt();

        ExpenseCategory expenseCategory = session.get(ExpenseCategory.class, id);
        session.delete(expenseCategory);
    }

    private void filterRead(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите название категории расходов");
        String nameExpenseCategory = scanner.next();

        List<ExpenseCategory> expenseCategories = session.createQuery("SELECT a from ExpenseCategory a where expenseCategory = \'" + nameExpenseCategory + "\'", ExpenseCategory.class).getResultList();
        System.out.println(expenseCategories);
    }
}