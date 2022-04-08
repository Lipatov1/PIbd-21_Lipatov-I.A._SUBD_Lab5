import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import models.*;
import logics.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
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
        while (isWork) {
            System.out.println("Выберите сущность для взаимодейтсвия: ");
            System.out.println("1 — категории расходов");
            System.out.println("2 — типы расходов");
            System.out.println("3 — категории доходов");
            System.out.println("4 — типы доходов");
            System.out.println("5 — члены семьи");
            System.out.println("6 — расходы");
            System.out.println("7 — доходы\n");
            System.out.println("Введите 8 для выхода");
            System.out.println("Введите 9 для вывода расходов с именами");

            Scanner scanner = new Scanner(System.in);
            int i = scanner.nextInt();

            switch (i) {
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
                case 9:
                    ExpensesWithNameLogic expensesWithNameLogic = new ExpensesWithNameLogic();
                    expensesWithNameLogic.work(sessionFactory);
                    break;
            }
        }
        sessionFactory.close();
    }
}