import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import models.*;
import logics.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        session.close();
//        HibernateUtil.shutdown();

        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(ExpenseCategory.class)
                .addAnnotatedClass(IncomeCategory.class)
                .addAnnotatedClass(TypeOfExpenses.class)
                .addAnnotatedClass(TypeOfIncome.class)
                .addAnnotatedClass(Expenses.class)
                .addAnnotatedClass(Income.class)
                .addAnnotatedClass(FamilyMember.class)
                .buildSessionFactory();

        boolean isWork = true;
        while(isWork){
            System.out.println("Введите 1 для работы с категориями расходов");
            System.out.println("Введите 2 для работы с типами расходов");
            System.out.println("Введите 3 для работы с категориями доходов");
            System.out.println("Введите 4 для работы с типами доходов");
            System.out.println("Введите 5 для работы с членом семьи");
            System.out.println("Введите 6 для работы с расходами");
            System.out.println("Введите 7 для работы с доходами");
            System.out.println("Введите 8 для выхода");

            Scanner scanner = new Scanner(System.in);
            int i = scanner.nextInt();

            switch (i){
                case 1:
                    ExpenseCategoryLogic expenseCategoryLogic = new ExpenseCategoryLogic();
                    expenseCategoryLogic.work(sessionFactory);
                    break;
                case 2:
                    TypeOfExpensesLogic typeOfExpensesLogic = new TypeOfExpensesLogic();
                    typeOfExpensesLogic.work(sessionFactory);
                    break;
                case 3:
                    IncomeCategoryLogic incomeCategoryLogic = new IncomeCategoryLogic();
                    incomeCategoryLogic.work(sessionFactory);
                    break;
                case 4:
                    TypeOfIncomeLogic typeOfIncomeLogic = new TypeOfIncomeLogic();
                    typeOfIncomeLogic.work(sessionFactory);
                    break;
                case 5:
                    FamilyMemberLogic familyMemberLogic = new FamilyMemberLogic();
                    familyMemberLogic.work(sessionFactory);
                    break;
                case 6:
                    ExpensesLogic expensesLogic = new ExpensesLogic();
                    expensesLogic.work(sessionFactory);
                    break;
                case 7:
                    IncomeLogic incomeLogic = new IncomeLogic();
                    incomeLogic.work(sessionFactory);
                    break;
                case 8:
                    isWork = false;
                    break;
            }
        }

        sessionFactory.close();
    }
}