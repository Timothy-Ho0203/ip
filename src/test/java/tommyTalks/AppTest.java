package tommyTalks;

import exceptions.InvalidFormatException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AppTest {
    @Test
    public void parseInput_invalidInput_invalidCommand() {
        Storage data = new Storage("./ip/data/TommyTalks.txt");
        Ui ui = new Ui();
        App test = new App();
        String testStr = "deadline test /by 16 jan 2025";
        assertThrows(InvalidFormatException.class, () -> test.parseInput(testStr).execute(data, ui));
    }
}
