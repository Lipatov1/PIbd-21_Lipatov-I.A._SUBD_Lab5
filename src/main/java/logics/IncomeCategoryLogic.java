package logics;

import models.IncomeCategory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.List;
import java.util.Scanner;

public class IncomeCategoryLogic {
    public void work(SessionFactory sessionFactory) {
        System.out.println("Введите 1 для создания категории доходов");
        System.out.println("Введите 2 для чтения категорий доходов");
        System.out.println("Введите 3 для изменения категории доходов");
        System.out.println("Введите 4 для удаления категории доходов");
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
        System.out.println("Введите название категории доходов");
        String nameIncomeCategory = scanner.next();

        IncomeCategory incomeCategory = new IncomeCategory(nameIncomeCategory);
        session.save(incomeCategory);
    }

    private void read(Session session) {
        List<IncomeCategory> incomeCategory = session.createQuery("SELECT a from IncomeCategory a", IncomeCategory.class).getResultList();
        System.out.println(incomeCategory);
    }

    private void update(Session session) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите id категории доходов");
        int id = scanner.nextInt();

        System.out.println("Введите название категории доходов");
        String nameIncomeCategory = scanner.next();

        IncomeCategory incomeCategory = session.get(IncomeCategory.class, id);
        incomeCategory.setIncomeCategory(nameIncomeCategory);
        session.save(incomeCategory);
    }

    private void delete(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите id категории доходов");
        int id = scanner.nextInt();

        IncomeCategory incomeCategory = session.get(IncomeCategory.class, id);
        session.delete(incomeCategory);
    }

    private void filterRead(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите название категории доходов");
        String nameIncomeCategory = scanner.next();

        List<IncomeCategory> incomeCategories = session.createQuery("SELECT a from IncomeCategory a where incomeCategory = \'" + nameIncomeCategory + "\'", IncomeCategory.class).getResultList();
        System.out.println(incomeCategories);
    }
}