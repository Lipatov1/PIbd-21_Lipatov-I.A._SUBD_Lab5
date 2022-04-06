package logics;

import models.FamilyMember;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.List;
import java.util.Scanner;

public class FamilyMemberLogic {
    public void work(SessionFactory sessionFactory) {
        System.out.println("Введите 1 для создания члена семьи");
        System.out.println("Введите 2 для чтения членов семьи");
        System.out.println("Введите 3 для изменения члена семьи");
        System.out.println("Введите 4 для удаления члена семьи");
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
        System.out.println("Введите имя");
        String name = scanner.next();

        System.out.println("Введите фамилию");
        String lastName = scanner.next();

        System.out.println("Введите отчество");
        String patronymic = scanner.next();

        System.out.println("Введите пол");
        String gender = scanner.next();

        System.out.println("Введите дату рождения");
        String date = scanner.next();
        java.util.Date myDate = new java.util.Date(date);
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());

        FamilyMember familyMember = new FamilyMember(name, lastName, patronymic, gender, sqlDate);
        session.save(familyMember);
    }

    private void read(Session session) {
        List<FamilyMember> familyMembers = session.createQuery("SELECT a from FamilyMember a", FamilyMember.class).getResultList();
        System.out.println(familyMembers);
    }

    private void update(Session session) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите id члена семьи");
        int id = scanner.nextInt();

        System.out.println("Введите имя");
        String name = scanner.next();

        System.out.println("Введите фамилию");
        String lastName = scanner.next();

        System.out.println("Введите отчество");
        String patronymic = scanner.next();

        System.out.println("Введите пол");
        String gender = scanner.next();

        System.out.println("Введите дату рождения");
        String date = scanner.next();
        java.util.Date myDate = new java.util.Date(date);
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());

        FamilyMember familyMember = session.get(FamilyMember.class, id);
        familyMember.setName(name);
        familyMember.setLastName(lastName);
        familyMember.setPatronymic(patronymic);
        familyMember.setGender(gender);
        familyMember.setBirthday(sqlDate);
        session.save(familyMember);
    }

    private void delete(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите id члена семьи");
        int id = scanner.nextInt();

        FamilyMember familyMember = session.get(FamilyMember.class, id);
        session.delete(familyMember);
    }

    private void filterRead(Session session) {
        System.out.println("Введите 1 для фильтра по имени");
        System.out.println("Введите 2 для фильтра по фамилии");
        System.out.println("Введите 3 для фильтра по отчеству");
        System.out.println("Введите 4 для фильтра по полу");
        System.out.println("Введите 5 для фильтра по дню рождения");

        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        List<FamilyMember> familyMembers = null;
        switch(i) {
            case 1:
                System.out.println("Введите имя");
                String _name = scanner.next();
                familyMembers = session.createQuery("SELECT a from FamilyMember a where name = \'" + _name + "\'", FamilyMember.class).getResultList();
                break;
            case 2:
                System.out.println("Введите фамилию");
                String _lastName = scanner.next();
                familyMembers = session.createQuery("SELECT a from FamilyMember a where lastName = \'" + _lastName + "\'", FamilyMember.class).getResultList();
                break;
            case 3:
                System.out.println("Введите отчество");
                String _patronymic = scanner.next();
                familyMembers = session.createQuery("SELECT a from FamilyMember a where patronymic = \'" + _patronymic + "\'", FamilyMember.class).getResultList();
                break;
            case 4:
                System.out.println("Введите пол");
                String _gender = scanner.next();
                familyMembers = session.createQuery("SELECT a from FamilyMember a where gender = \'" + _gender + "\'", FamilyMember.class).getResultList();
                break;
            case 5:
                System.out.println("Введите дату рождения");
                String date = scanner.next();
                java.util.Date myDate = new java.util.Date(date);
                java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());

                familyMembers = session.createQuery("SELECT a from FamilyMember a where birthday = \'" + sqlDate + "\'", FamilyMember.class).getResultList();
                break;
        }
        System.out.println(familyMembers);
    }
}