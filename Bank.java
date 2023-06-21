package banking;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Bank {

    private static double amount = 0;
    Map<String, Customer> customerMap;

    Bank() {
        customerMap = new HashMap<String, Customer>();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Customer customer;
        Bank bank = new Bank();
        int choice;
        outer:
        while (true) {

            System.out.println("\n-------------------");
            System.out.println("BANK    OF     JAVA");
            System.out.println("-------------------\n");
            System.out.println("1. Продолжить регистрацию.");
            System.out.println("2. Логин.");
            System.out.println("3. Выход.");
            System.out.print("\nВведите свой выбор : ");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Введите имя : ");
                    String firstName = sc.nextLine();
                    System.out.print("Введите фамилию : ");
                    String lastName = sc.nextLine();
                    System.out.print("Введите адрес : ");
                    String address = sc.nextLine();
                    System.out.print("Введите контактный телефон : ");
                    String phone = sc.nextLine();
                    System.out.println("Имя пользователя : ");
                    String username = sc.next();
                    while (bank.customerMap.containsKey(username)) {
                        System.out.println("Имя пользователя уже существует. Установите снова : ");
                        username = sc.next();
                    }
                    System.out.println("Введите пароль:");
                    String password = sc.next();
                    sc.nextLine();

                    customer = new Customer(firstName, lastName, address, phone, username, password, new Date());
                    bank.customerMap.put(username, customer);
                    break;

                case 2:
                    System.out.println("Введите логин : ");
                    username = sc.next();
                    sc.nextLine();
                    System.out.println("Введите пароль : ");
                    password = sc.next();
                    sc.nextLine();
                    if (bank.customerMap.containsKey(username)) {
                        customer = bank.customerMap.get(username);
                        if (customer.getPassword().equals(password)) {
                            while (true) {
                                System.out.println("\n-------------------");
                                System.out.println("ДОБРО ПОЖАЛОВАТЬ");
                                System.out.println("-------------------\n");
                                System.out.println("1. Депозит.");
                                System.out.println("2. Перемещение.");
                                System.out.println("3.Последние 5 транзакций.");
                                System.out.println("4. Информация о пользователе.");
                                System.out.println("5. Выйти из системы.");
                                System.out.print("\nВведите свой выбор : ");
                                choice = sc.nextInt();
                                sc.nextLine();
                                switch (choice) {
                                    case 1:
                                        System.out.print("Введите сумму : ");
                                        while (!sc.hasNextDouble()) {
                                            System.out.println("Недопустимая сумма. Введите снова :");
                                            sc.nextLine();
                                        }
                                        amount = sc.nextDouble();
                                        sc.nextLine();
                                        customer.deposit(amount, new Date());
                                        break;

                                    case 2:
                                        System.out.print("Введите имя пользователя получателя : ");
                                        username = sc.next();
                                        sc.nextLine();
                                        System.out.println("Введите сумму : ");
                                        while (!sc.hasNextDouble()) {
                                            System.out.println("Недопустимая сумма. Введите снова :");
                                            sc.nextLine();
                                        }
                                        amount = sc.nextDouble();
                                        sc.nextLine();
                                        if (amount > 300) {
                                            System.out.println("Превышен лимит перевода. Свяжитесь с менеджером Банка.");
                                            break;
                                        }
                                        if (bank.customerMap.containsKey(username)) {
                                            Customer payee = bank.customerMap.get(username); //Todo: check
                                            payee.deposit(amount, new Date());
                                            customer.withdraw(amount, new Date());
                                        } else {
                                            System.out.println("Имя пользователя не существует.");
                                        }
                                        break;

                                    case 3:
                                        for (String transactions : customer.getTransactions()) {
                                            System.out.println(transactions);
                                        }
                                        break;

                                    case 4:
                                        System.out.println("Titularul de cont cu numele: " + customer.getFirstName());
                                        System.out.println("Titularul de cont cu prenumele : " + customer.getLastName());
                                        System.out.println("Titularul de cont cu numele de utilizator : " + customer.getUsername());
                                        System.out.println("Titularul de cont cu addresa : " + customer.getAddress());
                                        System.out.println("Titularul de cont cu numarul de telefon : " + customer.getPhone());
                                        break;
                                    case 5:
                                        continue outer;
                                    default:
                                        System.out.println("Неправильный выбор !");
                                }
                            }
                        } else {
                            System.out.println("Неправильное имя пользователя/пароль.");
                        }
                    } else {
                        System.out.println("Неправильный логин/пароль.");
                    }
                    break;

                case 3:
                    System.out.println("\nСпасибо за выбор Bank Of Java.");
                    System.exit(1);
                    break;
                default:
                    System.out.println("Неправильный выбор !");
            }
        }
    }
}