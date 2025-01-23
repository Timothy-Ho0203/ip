
public class TommyTalks {
    public static void main(String[] args) {
        String logo = """
                ___________                           ___________       __   __           \s
                \\__    ___/___   _____   _____ ___.__.\\__    ___/____  |  | |  | __  ______
                  |    | /  _ \\ /     \\ /     <   |  |  |    |  \\__  \\ |  | |  |/ / /  ___/
                  |    |(  <_> )  Y Y  \\  Y Y  \\___  |  |    |   / __ \\|  |_|    <  \\___ \\\s
                  |____| \\____/|__|_|__/__|_|__/ ____|  |____|  (______/____/__|_|\\/____  >
                                               \\/                                       \\/\s
                """;
        System.out.println("Hello from\n" + logo);
        App chatBot = new App();
        chatBot.run();
    }
}
