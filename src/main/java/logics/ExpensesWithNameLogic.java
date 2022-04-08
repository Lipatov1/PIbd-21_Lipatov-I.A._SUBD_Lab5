package logics;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import models.Expenses;
import java.util.List;

public class ExpensesWithNameLogic {
    public void work(SessionFactory sessionFactory) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        List<Expenses> expenses = session.createQuery("SELECT ex FROM Expenses ex", Expenses.class).getResultList();
        System.out.println(" Iмя\t\t\tДата\t\tСумма\t\t Комменатрий");
        for (int i = 0; i < expenses.size(); i++) {
            System.out.format("%s \t\t %s \t %s\t\t%s %n", expenses.get(i).getFamilyMember().getName(), expenses.get(i).getTransactionDate(), expenses.get(i).getAmount(), expenses.get(i).getComment());
        }
        session.getTransaction().commit();
    }
}

// List<Expenses> listt = session.createQuery(
// "SELECT ex.familyMember.name, ex.transactionDate, ex.amount, ex.comment FROM FamilyMember f right JOIN f.expenses ex").getResultList();
