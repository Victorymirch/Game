import java.util.Random;
import java.util.Scanner;

public class game {
    static Random random = new Random();
    static Scanner scanner = new Scanner(System.in);

    static int strength, dexterity, intelligence;
    static int health = 100;
    static boolean hasWeapon = false;

    public static void main(String[] args) {
        System.out.println("Добро пожаловать в игру \"За гранью реальности\"!");
        createCharacter();
        startAdventure();
    }

    public static void createCharacter() {
        System.out.println("Создание персонажа...");
        strength = rollDice(6) + 5;
        dexterity = rollDice(6) + 5;
        intelligence = rollDice(6) + 5;

        System.out.println("Ваши характеристики:");
        System.out.println("Сила: " + strength);
        System.out.println("Ловкость: " + dexterity);
        System.out.println("Интеллект: " + intelligence);
        System.out.println("Здоровье: " + health);
    }

    public static void startAdventure() {
        System.out.println("Вы просыпаетесь в заброшенном бункере в зоне отчуждения...");
        System.out.println("1. Осмотреться вокруг");
        System.out.println("2. Выйти наружу");
        int choice = scanner.nextInt();

        if (choice == 1) {
            exploreBunker();
        } else {
            leaveBunker();
        }
    }

    public static void exploreBunker() {
        System.out.println("Вы находите старый рюкзак с экипировкой и карту зоны.");
        System.out.println("Карта указывает на аномалию в 3 км к северу.");

        System.out.println("1. Отправиться к аномалии");
        System.out.println("2. Искать оружие в бункере");
        int choice = scanner.nextInt();

        if (choice == 1) {
            anomalyEncounter();
        } else {
            findWeapon();
        }
    }

    public static void leaveBunker() {
        System.out.println("Вы выходите наружу и сразу чувствуете странное напряжение в воздухе...");
        battle("мутант", 30, 10);
    }

    public static void anomalyEncounter() {
        System.out.println("Вы подходите к аномалии, она светится странным синим светом...");
        System.out.println("Бросьте кубик, чтобы узнать, что произойдет!");
        int roll = rollDice(20);
        System.out.println("Вы выбросили: " + roll);

        if (roll + intelligence >= 15) {
            System.out.println("Вы успешно анализируете аномалию и находите артефакт!");
        } else {
            System.out.println("Аномалия взрывается, и вы получаете урон!");
            health -= 30;
        }
    }

    public static void findWeapon() {
        System.out.println("Вы находите ржавый автомат Калашникова.");
        System.out.println("Теперь у вас есть оружие!");
        hasWeapon = true;
    }

    public static void battle(String enemy, int enemyHealth, int enemyDamage) {
        System.out.println("Вы встречаете " + enemy + "!");
        while (health > 0 && enemyHealth > 0) {
            System.out.println("Вы атакуете " + enemy + "!");
            int attackRoll = rollDice(20) + strength;
            if (attackRoll >= 15) {
                int damage = hasWeapon ? rollDice(12) + 5 : rollDice(6) + 2;
                System.out.println("Вы наносите " + damage + " урона!");
                enemyHealth -= damage;
            } else {
                System.out.println("Вы промахнулись!");
            }

            if (enemyHealth > 0) {
                System.out.println(enemy + " атакует вас!");
                int enemyRoll = rollDice(20);
                if (enemyRoll >= 10) {
                    System.out.println("Вас ранили! Вы потеряли " + enemyDamage + " здоровья.");
                    health -= enemyDamage;
                } else {
                    System.out.println("Вы увернулись!");
                }
            }
        }
        if (health <= 0) {
            System.out.println("Вы погибли...");
        } else {
            System.out.println("Вы победили " + enemy + "!");
        }
    }

    public static int rollDice(int sides) {
        return
                random.nextInt(sides) + 1;
    }
}