package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    private static final UserService userService = new UserServiceImpl();
    public static void main(String[] args) {
        System.out.println("Создание таблицы для User(ов) – не должно приводить к исключению, если такая таблица уже существует");
        userService.createUsersTable();

        System.out.println("Добавление пользователей");
        userService.saveUser("John", "Parker", (byte)24);
        userService.saveUser("John2", "Parker2", (byte)25);
        userService.saveUser("John3", "Parker3", (byte)26);
        userService.saveUser("John4", "Parker4", (byte)27);

        System.out.println("Удаление пользователя по Id");
        userService.removeUserById(2);

        System.out.println("Получение всех User из базы и вывод в консоль");
        userService.getAllUsers();

        System.out.println("Очистка содержания таблицы");
        userService.cleanUsersTable();

        System.out.println("Удаление таблицы User(ов) – не должно приводить к исключению, если таблицы не существует");
        userService.dropUsersTable();

    }
}
