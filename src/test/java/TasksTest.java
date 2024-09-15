import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.Epic;
import ru.netology.Meeting;
import ru.netology.SimpleTask;

public class TasksTest {
    @Test
    public void testMatchesFalseWhenQueryNotInSubtasks() {
        Epic epic = new Epic(1, new String[]{"Протереть пыль", "Разобрать шкаф", "Помыть полы"});
        String query = "Приготовить еду";
        boolean expected = false;
        boolean actual = epic.matches(query);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testMatchesTrueWhenQueryInSubtasks() {
        Epic epic = new Epic(1, new String[]{"Протереть пыль", "Разобрать шкаф", "Помыть полы"});
        String query = "Разобрать шкаф";
        boolean expected = true;
        boolean actual = epic.matches(query);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testMatchesFalseWhenQueryNotInTitle() {
        SimpleTask task = new SimpleTask(1, "Уборка");
        String query = "Глажка";
        boolean expected = false;
        boolean actual = task.matches(query);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testMatchesTrueWhenQueryInTitle() {
        SimpleTask task = new SimpleTask(1, "Уборка");
        String query = "Уборка";
        boolean expected = true;
        boolean actual = task.matches(query);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testMatchesTrueWhenQueryInTopic() {
        Meeting meeting = new Meeting(1, "Учет расходов", "Внедрение новой базы", "16/09/2024 09:00");
        String query = "Учет";
        boolean expected = true; // строка запроса содержится в теме встречи
        boolean actual = meeting.matches(query);
        Assertions.assertEquals(expected, actual);
    }

    public void testMatchesTrueWhenQueryInProject() {
        Meeting meeting = new Meeting(1, "Учет расходов", "Внедрение новой базы", "16/09/2024 09:00");
        String query = "базы";
        boolean expected = true; // строка запроса содержится в теме встречи
        boolean actual = meeting.matches(query);
        Assertions.assertEquals(expected, actual);
    }

    public void testMatchesFalseWhenQueryNotInProjectOrTopic() {
        Meeting meeting = new Meeting(1, "Учет расходов", "Внедрение новой базы", "16/09/2024 09:00");
        String query = "оптимизация";
        boolean expected = false; // строка запроса содержится в теме встречи
        boolean actual = meeting.matches(query);
        Assertions.assertEquals(expected, actual);
    }
}

