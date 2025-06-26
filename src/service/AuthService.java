package service;

import model.User;
import utils.FileService;

import java.io.*;
import java.util.*;

public class AuthService {
    private static final String USER_FILE = "data/users.txt";

    // ✅ Register a new user and save to file
    public static User register(String name, String email, String password) {
        String userId = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        User user = new User(userId, name, email, password);
        FileService.saveUser(user);
        System.out.println("✅ Registration successful. Your User ID: " + userId);
        return user;
    }

    // ✅ Login by checking email and password
    public static User login(String email, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String id = parts[0];
                    String name = parts[1];
                    String savedEmail = parts[2];
                    String savedPassword = parts[3];

                    if (savedEmail.equals(email) && savedPassword.equals(password)) {
                        System.out.println("✅ Login successful. Welcome, " + name + "!");
                        return new User(id, name, email, password);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("❌ Error reading user file: " + e.getMessage());
        }

        System.out.println("❌ Invalid email or password.");
        return null;
    }
}
