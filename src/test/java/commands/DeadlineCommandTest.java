package commands;

import exceptions.InvalidFormatException;
import org.junit.jupiter.api.Test;
import tommyTalks.Storage;
import tommyTalks.Ui;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeadlineCommandTest {
    @Test
    public void execute_invalidInput_exceptionThrown() {
        Storage data = new Storage("./ip/data/TommyTalks.txt");
        Ui ui = new Ui();
        InvalidFormatException thrown = assertThrows(InvalidFormatException.class, () -> {
            new DeadlineCommand("deadline test /until 02 02 2020").execute(data, ui);
        });
    }
}
