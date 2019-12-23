package view.impl;


import model.User;
import model.UserRole;
import service.UserService;
import service.impl.UserServiceImpl;
import shared.ResponseStatus;
import view.Menu;

import java.util.Scanner;

public class LoginMenu implements Menu {

    private UserService userService = new UserServiceImpl();
    private String[] items = {"1.Login", "2.Register"};
    private Scanner scanner;

    @Override
    public void show() {
        showItems(items);
        System.out.println("0. Exit");

        scanner = new Scanner(System.in);


        while(true)
        {
          int choice =  scanner.nextInt();

          switch (choice)
          {
              case 1 :
                  loginSubMenu(scanner); break;
              case 2 :
                  registerSubMenu(scanner); break;
              case 0 : exit(); break;
          }
        }
    }

    @Override
    public void exit() {
        System.exit(0);
    }

    private void loginSubMenu(Scanner scanner)
    {
        String login = null;
        String password = null;
        System.out.println("input login:");
        while (scanner.hasNext())
        {
            login = scanner.nextLine();
            break;
        }

        scanner.reset();
        System.out.println("input password:");
        while (scanner.hasNext())
        {
            password = scanner.nextLine();
            break;
        }

        ResponseStatus<User> status = userService.login(login, password);
        if(status.isSuccess()) {
            User u = status.getResult();
            if (UserRole.ADMIN.equals(u.getUserRole())) {
                System.out.println("Admin logged in!");
            } else if (UserRole.REGULAR.equals(u.getUserRole())) {
                System.out.println("User logged in!");
            }
        }
        else {
            System.out.println(status.getResponseMsg());
            show();
        }
    }

    private void registerSubMenu(Scanner scanner)
    {
        show(); //todo add impl
    }
}
