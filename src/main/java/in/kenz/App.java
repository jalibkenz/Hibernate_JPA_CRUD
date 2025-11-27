package in.kenz;

import in.kenz.service.L1L2DemoService;
import in.kenz.service.impl.EmployeeServiceImpl;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{


    public static void main( String[] args )
    {
        Scanner sc = new Scanner(System.in);
        EmployeeServiceImpl employeeServiceImpl=new EmployeeServiceImpl();

        int choice;

        do{
            System.out.println("\n\n🟥⬜️🟦MAIN MENU");
            System.out.println("1" +"----" +"CREATE");
            System.out.println("2" +"----" +"READ - FIND BY ID");
            System.out.println("3" +"----" +"READ - FIND BY NAME");
            System.out.println("4" +"----" +"READ - FIND ALL");
            System.out.println("5" +"----" +"UPDATE");
            System.out.println("6" +"----" +"DELETE");
            System.out.println("7" +"----" +"L1 L2 Demo Try Toggling @Cacheable on Employee and Run ");
            System.out.println("0" +"----" +"\uD83D\uDEABEXIT");

            System.out.println("Enter your choice:");
            choice=sc.nextInt();

            switch (choice){
                case 1 -> employeeServiceImpl.addEmployee(sc);
                case 2 -> employeeServiceImpl.findEmployeeById(sc);
                case 3 -> employeeServiceImpl.findEmployeeByName(sc);
                case 4 -> employeeServiceImpl.findAllEmployees();
                case 5 -> employeeServiceImpl.updateEmployee(sc);
                case 6 -> employeeServiceImpl.deleteEmployeeById(sc);
                case 7 -> L1L2DemoService.demonstrationPurpose(sc);
                case 0 -> System.out.println("Exit......");
            }

        }while(choice!=0);



    }
}
