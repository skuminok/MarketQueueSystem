import java.util.LinkedList;
import java.util.Queue;

// Интерфейс QueueBehaviour для управления очередью
interface QueueBehaviour {
    void enterQueue(Person person); // Человек становится в очередь
    void exitQueue(); // Человек выходит из очереди
    void processNext(); // Обрабатывается следующий в очереди
}

// Интерфейс MarketBehaviour для работы с очередью в магазине
interface MarketBehaviour {
    void addPerson(Person person); // Добавить человека в магазин
    void removePerson(); // Удалить человека из магазина
    void update(); // Обновление состояния магазина (прием и выдача заказов)
}

// Класс Person представляет человека
class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

// Класс Market, реализующий оба интерфейса
class Market implements QueueBehaviour, MarketBehaviour {
    private Queue<Person> queue = new LinkedList<>();

    // Человек становится в очередь
    @Override
    public void enterQueue(Person person) {
        queue.add(person);
        System.out.println(person.getName() + " встал(а) в очередь.");
    }

    // Человек выходит из очереди
    @Override
    public void exitQueue() {
        if (!queue.isEmpty()) {
            Person person = queue.poll();
            System.out.println(person.getName() + " вышел(а) из очереди.");
        } else {
            System.out.println("Очередь пуста, никто не может выйти.");
        }
    }

    // Обрабатывается следующий в очереди
    @Override
    public void processNext() {
        if (!queue.isEmpty()) {
            Person person = queue.peek();
            System.out.println(person.getName() + " сейчас обслуживается.");
        } else {
            System.out.println("Очередь пуста, никто не обслуживается.");
        }
    }

    // Добавление человека в магазин
    @Override
    public void addPerson(Person person) {
        enterQueue(person);
    }

    // Удаление человека из магазина
    @Override
    public void removePerson() {
        exitQueue();
    }

    // Обновление состояния магазина: прием и выдача заказов
    @Override
    public void update() {
        System.out.println("Обновление состояния магазина...");
        processNext(); // Обрабатывается следующий клиент
        exitQueue();   // Клиент покидает очередь
    }
}

// Главный класс для запуска приложения
public class MainMarketApp {
    public static void main(String[] args) {
        Market market = new Market();

        // Создаем людей
        Person person1 = new Person("Иван");
        Person person2 = new Person("Анна");
        Person person3 = new Person("Петр");
        Person person4 = new Person("Мария");
        Person person5 = new Person("Алексей");

        // Добавляем людей в магазин
        market.addPerson(person1);
        market.addPerson(person2);
        market.addPerson(person3);
        market.addPerson(person4);
        market.addPerson(person5);

        // Обновляем состояние магазина
        market.update();
        market.update();
        market.update();
        market.update();
        market.update();

        // Попробуем обновить состояние ещё раз, когда очередь пуста
        market.update();
    }
}

