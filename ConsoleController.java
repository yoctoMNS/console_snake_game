import java.io.IOException;


public class ConsoleController {
    private ProcessBuilder process;


    public ConsoleController(String... args) {
        process = new ProcessBuilder(args);
    }


    public void execute() {
        try {
            process.inheritIO().start().waitFor();
        } catch (InterruptedException e) {
        } catch (IOException e) {
        }
    }
}
