# 💳 Bank-Java-OOP

A simple console-based banking application built in Java using core Object-Oriented Programming (OOP) principles. It allows users to register, log in, manage their accounts, and perform basic banking operations.

---

## 🔑 Features

- ✅ User registration & login
- ✅ Create Saving or Current account
- ✅ Deposit, Withdraw, Transfer money
- ✅ View account balance
- ✅ Data saved using file handling (no database)

---

## 💻 Technologies Used

- Java (JDK 17+)
- File I/O for persistence
- OOP principles (Abstraction, Inheritance, Polymorphism, Encapsulation)
- VS Code or any Java IDE

---

## 🧠 OOP Concepts Used

| Concept       | Where it's used                                |
|---------------|------------------------------------------------|
| Abstraction   | `Account` is an abstract class                 |
| Inheritance   | `SavingAccount`, `CurrentAccount` extend `Account` |
| Polymorphism  | `deposit()` and `withdraw()` overridden        |
| Encapsulation | Fields made private with public getters/setters|

---

## 📂 Project Structure

```

SmartBank-Java/
├── src/
│   ├── main/              # Main.java
│   ├── model/             # Account classes, User
│   ├── service/           # Auth, Bank, Transaction services
│   └── utils/             # FileService, helpers
├── data/                  # users.txt, accounts.txt, transactions.txt
└── README.md

````

---

## 🛠️ How to Run

### Step 1: Compile
```bash
javac -d out src/main/Main.java src/model/*.java src/service/*.java src/utils/*.java
````

### Step 2: Run

```bash
java -cp out main.Main
```

> Make sure your JDK is installed and configured properly.

---

## 🚫 .gitignore

Make sure to exclude generated files:

```
out/
*.class
.vscode/
.idea/
```

---

## 📌 Notes

* All data is stored in `.txt` files under the `data/` folder.
* This is a beginner-friendly Java project that can be extended with GUI or database integration.

---

## 🙋 Author

**Priya P.**
Full Stack Developer & Mentor
[[GitHub](https://github.com/Priyap1038)](#) | [[LinkedIn](https://www.linkedin.com/in/priya-p-50a998250/)](#)

---

## 📖 License

This project is open for learning and demonstration purposes.

```
