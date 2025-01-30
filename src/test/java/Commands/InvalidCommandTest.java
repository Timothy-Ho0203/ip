package Commands;

import Exceptions.InvalidFormatException;
import TommyTalks.Storage;
import TommyTalks.Ui;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
public class InvalidCommandTest {
    @Test
    public void execute_invalidInput_exceptionThrown() {
        Storage data = new Storage("./ip/data/TommyTalks.txt");
        Ui ui = new Ui();
        InvalidFormatException thrown = assertThrows(InvalidFormatException.class, () -> {
            new InvalidCommand("markkk").execute(data, ui);
        });
    }
}
