import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.*;

public class TodosTest {
    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void TestReturnArrayOfTasksWhenSearchingWithOneMatch() {
        Todos todos = new Todos();
        todos.add(new SimpleTask(1, "Купить молоко"));
        todos.add(new Epic(2, new String[]{"Позвонить родителям"}));
        Task[] expected = {new SimpleTask(1, "Купить молоко")};
        Task[] actual = todos.search("молоко");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void TestReturnArrayOfAllTasksWhenSearchingWithEmptyQuery() {
        Todos todos = new Todos();
        todos.add(new SimpleTask(1, "Купить молоко"));
        todos.add(new Epic(2, new String[]{"Позвонить родителям"}));
        Task[] expected = {new SimpleTask(1, "Купить молоко"), new Epic(2, new String[]{"Позвонить родителям"})};
        Task[] actual = todos.search("");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test // находяться несколько задач
    public void TestFindSeveralTask() {
        Todos todos = new Todos();
        todos.add(new SimpleTask(1, "Купить картошку"));
        todos.add(new Epic(2, new String[]{"Приготовить разные блюда из картошки"}));
        Task[] expected = {new SimpleTask(1, "Купить картошку"), new Epic(2, new String[]{"Приготовить разные блюда из картошки"})};
        Task[] actual = todos.search("картошк");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test //находится ровно одна задача
    public void TestFindOneTask() {
        Todos todos = new Todos();
        todos.add(new SimpleTask(1, "Купить картошку"));
        todos.add(new Epic(2, new String[]{"Позвонить родителям"}));
        Task[] expected = {new Epic(2, new String[]{"Позвонить родителям"})};
        Task[] actual = todos.search("Позвонить");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test //находится 0 задач, т.е. ни одна задача не подходит
    public void TestWhenNoResults() {
        Todos todos = new Todos();
        todos.add(new SimpleTask(1, "Купить картошку"));
        todos.add(new Epic(2, new String[]{"Позвонить родителям"}));
        String[] expected = {};
        Task[] actual = todos.search("Забронировать отель");
        Assertions.assertArrayEquals(expected, actual);
    }
}

