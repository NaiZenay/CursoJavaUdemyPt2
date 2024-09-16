package org.yanez.userControl;

import org.yanez.userControl.models.User;
import org.yanez.userControl.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/*
Para la tarea se requiere crear un proyecto llamado ProyectoMantenedorUsuariosJDBC para administrar
a los usuarios de la Base de Datos, con las operaciones: actualizar, eliminar, crear, listar y salir.
 */

public class UserControl {
    private static final UserRepository userRepository = new UserRepository();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Map<Integer, Runnable> options = new HashMap<>();
        options.put(1, UserControl::resgisterUser);
        options.put(2, UserControl::readUsers);
        options.put(3, UserControl::deleteUser);
        options.put(4, UserControl::updateUser);
        options.put(5, () -> System.exit(0));

        while (true) {
            System.out.println("USER CONTROL" +
                    "\n1)Register users" +
                    "\n2)List users" +
                    "\n3)Delete user" +
                    "\n4)Update user" +
                    "\n5)exit");
            int option = getValidInteger();
            if (options.containsKey(option)) {
                options.get(option).run();
            } else {
                System.out.println("Not an option.Try again");
            }
        }
    }

    private static int getValidInteger() {
        int option;
        while (true) {
            try {
                option = scanner.nextInt();
                scanner.nextLine();
                return option;
            } catch (Exception e) {
                System.out.println("Invalid.try again");
                scanner.nextLine();
            }
        }
    }

    private static void deleteUser() {
        userRepository.delete(searchByID());
        System.out.println("===== USER DELETED CORRECTLY =====");
    }

    private static void resgisterUser() {
        User user = new User();
        setUserName(user);
        setUserEmail(user);
        setUserPassWord(user);
        userRepository.register(user);
        System.out.println("===== USER REGISTERED CORRECTLY =====");
        System.out.println(user);
    }

    private static void updateUser() {
        User userUpdate = searchByID();
        Map<Integer, Runnable> updateOptions = new HashMap<>();
        updateOptions.put(1, () -> UserControl.setUserName(userUpdate));
        updateOptions.put(2, () -> UserControl.setUserEmail(userUpdate));
        updateOptions.put(3, () -> UserControl.setUserPassWord(userUpdate));

        System.out.println("Which atributte would you like to update?" +
                "\n1)Name" +
                "\n2)Email" +
                "\n3)Password");
        int updateOption = getValidInteger();
        if (updateOptions.containsKey(updateOption)) {
            updateOptions.get(updateOption).run();
        } else {
            return;
        }
        userRepository.update(userUpdate);

    }

    private static User searchByID() {
        System.out.println("Write de Id of the user");
        int id = getValidInteger();
        return userRepository.byID((long) id);
    }

    private static void readUsers() {
        userRepository.listAll().forEach(System.out::println);
    }


    private static void setUserPassWord(User user) {
        while (true) {
            System.out.println("User password");
            String password = scanner.nextLine();
            if (!(password.isEmpty())) {
                user.setPassword(password);
                break;
            } else {
                System.out.println("Password is empty");
            }
        }
    }

    private static void setUserEmail(User user) {
        String REGEX_EMAIL = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        while (true) {
            System.out.println("User email");
            String email = scanner.nextLine();
            if (!(email.isEmpty())) {
                Pattern pattern = Pattern.compile(REGEX_EMAIL);
                Matcher matcher = pattern.matcher(email);
                if (matcher.matches()) {
                    user.setEmail(email);
                    break;
                } else {
                    System.out.println("Invalid Email");
                }
            }
        }
    }

    private static void setUserName(User user) {
        while (true) {
            System.out.println("User name");
            String name = scanner.nextLine();
            if (!(name.isEmpty())) {
                user.setName(name);
                break;
            } else {
                System.out.println("User name is empty");
            }
        }
    }
}