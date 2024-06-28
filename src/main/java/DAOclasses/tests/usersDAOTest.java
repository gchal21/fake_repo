//package DAOclasses.tests;
//
//
//import DAOclasses.UsersDAO;
//import entities.User;
//
//import junit.framework.TestCase;
//import org.apache.commons.dbcp2.BasicDataSource;
//
//import java.util.ArrayList;
//
//public class usersDAOTest extends TestCase {
//
//
//    public void testAddingUsers(){
//        BasicDataSource basicDataSource = new BasicDataSource();
//        basicDataSource.setUrl("jdbc:mysql://localhost:3306/oop_final_project_db");
//        basicDataSource.setUsername("root");
//        basicDataSource.setPassword("rootroot");
//        UsersDAO userdao= UsersDAO.getInstance(basicDataSource);
//
//        User user1 = new User(-12, "svaniki", "skaska2_3", "svani321@gmail.com",null, 2);
//        userdao.addUser(user1);
//
//        User user2 = new User(-1, "Mariko", "skaska2_3", "mariko123@gmail.com",null, 1);
//        userdao.addUser(user2);
//
//        User user3 = new User(-1, "Levan", "leo111", "levanMessi@gmail.com",null, 1);
//        userdao.addUser(user3);
//
//        User user4 =new User(-23, "Phoebe", "fibi).239", "fibiAAA@gmail.com",null, 2);
//        userdao.addUser(user4);
//    }
//
//    public void testGettingUser(){
//        BasicDataSource basicDataSource = new BasicDataSource();
//        basicDataSource.setUrl("jdbc:mysql://localhost:3306/oop_final_project_db");
//        basicDataSource.setUsername("root");
//        basicDataSource.setPassword("rootroot");
//        UsersDAO userdao=new UsersDAO(basicDataSource);
//
//
//        System.out.println(userdao.getUser("svaniki"));
//        System.out.println(userdao.getUser("svani321@gmail.com"));
//        System.out.println();
//
//        System.out.println(userdao.getUser("Mariko"));
//        System.out.println();
//
//        System.out.println(userdao.getUser("levanMessi@gmail.com"));
//        System.out.println(userdao.getUser("Levan"));
//
//
//    }
//
//    public void testGettingAllUsers(){
//        BasicDataSource basicDataSource = new BasicDataSource();
//        basicDataSource.setUrl("jdbc:mysql://localhost:3306/oop_final_project_db");
//        basicDataSource.setUsername("root");
//        basicDataSource.setPassword("rootroot");
//        UsersDAO userdao=new UsersDAO(basicDataSource);
//
//        ArrayList<User> allUsers=userdao.getAllUsers();
//        for(User user:allUsers){
//            System.out.println(user);
//        }
//    }
//
//}
