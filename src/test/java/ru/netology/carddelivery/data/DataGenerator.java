package ru.netology.carddelivery.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {

    public static String generateCity() {
        String[] cities = {"Москва", "Казань", "Екатеринбург", "Нижний Новгород", "Кемерово", "Санкт-Петербург", "Новосибирск"};
        Random random = new Random();
        String city = cities[random.nextInt(cities.length)];
        return city;
    }

    public static String generateDate(int daysToAdd) {
        String date = String.valueOf(LocalDate.now().plusDays(daysToAdd).format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        return date;
    }

    public static String generateName(String locale) {
        Faker faker = new Faker(new Locale(locale));
        String name = faker.name().firstName() + " " + faker.name().lastName();
        return name;
    }

    public static String generatePhone(String locale) {
        Faker faker = new Faker(new Locale(locale));
        String phone = faker.phoneNumber().phoneNumber();
        return phone;
    }

    public static class Registration {
        private Registration() {
        }

        public static UserInfo generateUser(String locale) {
            UserInfo user = new UserInfo(
                    generateCity(),
                    generateName(locale),
                    generatePhone(locale));
            return user;
        }
    }

    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }
}


