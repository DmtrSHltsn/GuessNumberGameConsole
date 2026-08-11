import java.util.Random;
import java.util.Scanner;

public class GuessNumberGameConsole {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();

    public static void main(String[] args) {
        boolean playAgain = true;

        // Приветствие и запрос имени
        String playerName = getValidatedName();
        printHeader("ДОБРО ПОЖАЛОВАТЬ В ИГРУ 'УГАДАЙ ЧИСЛО', " + playerName.toUpperCase() + "!");

        while (playAgain) {
            // Выбор уровня сложности (по умолчанию средний)
            int maxRange = chooseDifficulty();

            // Генерация числа
            int hiddenNumber = random.nextInt(maxRange) + 1;
            int attempts = 0;
            boolean guessed = false;

            System.out.println("\nЯ загадал число от 1 до " + maxRange + ". Попробуй угадать!");

            while (!guessed) {
                int guess = getValidGuess(maxRange);
                attempts++;

                if (guess == hiddenNumber) {
                    guessed = true;
                    printSuccess(attempts);
                } else if (guess < hiddenNumber) {
                    System.out.println(">> Подсказка: загаданное число БОЛЬШЕ.");
                } else {
                    System.out.println(">> Подсказка: загаданное число МЕНЬШЕ.");
                }
            }

            // После победы спрашиваем, хочет ли сыграть ещё раз
            playAgain = askPlayAgain("Хочешь сыграть ещё раз? (да/нет): ");
        }

        System.out.println("\nСпасибо за игру, " + playerName + "! До встречи!");
    }

    // --- Валидация имени ---
    private static String getValidatedName() {
        String name = "";
        while (true) {
            System.out.print("Введи своё имя: ");
            name = scanner.nextLine().trim();

            if (name.isEmpty()) {
                System.out.println(">> Имя не может быть пустым. Попробуй ещё раз.");
                continue;
            }

            // Проверка, что не только цифры
            if (name.matches("\\d+")) {
                System.out.println(">> Имя не может состоять только из цифр. Попробуй ещё раз.");
                continue;
            }

            // Первая буква заглавная, остальное как есть
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            break;
        }
        return name;
    }

    // --- Выбор уровня сложности ---
    private static int chooseDifficulty() {
        System.out.println("\nВыбери уровень сложности:");
        System.out.println("1 — Лёгкий (число от 1 до 5)");
        System.out.println("2 — Средний (число от 1 до 10) — по умолчанию");
        System.out.println("3 — Сложный (число от 1 до 20)");

        String input = scanner.nextLine().trim();
        if (input.equals("1")) return 5;
        if (input.equals("3")) return 20;
        // По умолчанию — средний (10)
        return 10;
    }

    // --- Ввод и валидация числа ---
    private static int getValidGuess(int maxRange) {
        while (true) {
            System.out.print("Твой вариант: ");
            String line = scanner.nextLine().trim();

            try {
                int value = Integer.parseInt(line);
                if (value < 1 || value > maxRange) {
                    System.out.println(">> Число должно быть в диапазоне от 1 до " + maxRange + ".");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println(">> Вводи только целые числа!");
            }
        }
    }

    // --- Вопрос «сыграть ещё раз» ---
    private static boolean askPlayAgain(String prompt) {
        while (true) {
            System.out.print(prompt);
            String answer = scanner.nextLine().trim().toLowerCase();
            if (answer.equals("да") || answer.equals("д") || answer.equals("yes") || answer.equals("y")) {
                return true;
            } else if (answer.equals("нет") || answer.equals("н") || answer.equals("no") || answer.equals("n")) {
                return false;
            } else {
                System.out.println(">> Пожалуйста, ответь «да» или «нет».");
            }
        }
    }

    // --- Псевдографика (заголовки) ---
    private static void printHeader(String title) {
        int width = 60;
        char border = '=';
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < width; i++) {
            sb.append(border);
        }
        sb.append("\n");
        sb.append("   ").append(title).append("\n");
        for (int i = 0; i < width; i++) {
            sb.append(border);
        }
        sb.append("\n");

        System.out.println(sb.toString());
    }

    private static void printSuccess(int attempts) {
        int width = 60;
        char star = '*';
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < width; i++) {
            sb.append(star);
        }
        sb.append("\n");
        sb.append("   🎉 ПОЗДРАВЛЯЮ! Ты угадал! 🎉\n");
        sb.append("   Ты справился за " + attempts + " попыток!\n");
        for (int i = 0; i < width; i++) {
            sb.append(star);
        }
        sb.append("\n");

        System.out.println(sb.toString());
    }
}
