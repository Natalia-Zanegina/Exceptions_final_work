import java.util.Scanner;
public class View {
    private Scanner scanner;

    public View() {
        scanner = new Scanner(System.in);
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayError(String errorMessage) {
        System.err.println(errorMessage);
    }

    public String getInput() {
        return scanner.nextLine();
    }
}
