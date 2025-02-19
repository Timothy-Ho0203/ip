package commands;

import exceptions.InvalidFormatException;
import org.junit.jupiter.api.Test;
import tommyTalks.Storage;
import tommyTalks.Ui;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class EventCommandTest {
    @Test
    public void execute_invalidKeyword_exceptionThrown() {
        Storage data = new Storage("./ip/data/TommyTalks.txt");
        Ui ui = new Ui();
        InvalidFormatException thrown = assertThrows(InvalidFormatException.class, () -> {
            new EventCommand("event test /starting 02 02 2020 10:00 /to 03 02 2020 12:00").execute(data, ui);
        });
    }

    @Test
    public void execute_invalidTime_exceptionThrown() {
        Storage data = new Storage("./ip/data/TommyTalks.txt");
        Ui ui = new Ui();
        InvalidFormatException thrown = assertThrows(InvalidFormatException.class, () -> {
            new EventCommand("event test /from 40 02 2020 10:00 /to 03 02 2020 12:00").execute(data, ui);
        });
    }
}
