import java.io.FileWriter;
import java.io.IOException;
public class Controller {

    private View view;
    private UserData userData;

    public Controller(View view) {
        this.view = view;
    }

    public void start() {
        view.displayMessage("Введите данные в формате: Фамилия Имя Отчество Дата_рождения(dd.mm.yyyy) Номер_телефона(XXXXXXXXXXX) Пол(f/m):");
        String input = view.getInput();

        try {
            parseInputData(input);
            saveUserDataToFile();
            view.displayMessage("Данные успешно сохранены");
        } catch (IllegalArgumentException | ExceptionHandler | IOException e) {
            view.displayError(e.getMessage());
        }
    }

    private void parseInputData(String input) throws ExceptionHandler {
        String[] parts = input.split(" ");

        if (parts.length != 6) {
            throw new IllegalArgumentException("Неверное количество данных");
        }

        String surname = parts[0];
        String firstName = parts[1];
        String patronymic = parts[2];
        String birthDate = parts[3];
        String phoneNumber = parts[4];
        char gender = parts[5].charAt(0);

        if (!isValidPhoneNumber(phoneNumber)) {
            throw new ExceptionHandler("Неверный формат номера телефона");
        }

        if (!isValidBirthDate(birthDate)) {
            throw new ExceptionHandler("Неверный формат даты рождения");
        }

        if (!isValidGender(parts[5])) {
            throw new ExceptionHandler("Неверный формат пола");
        }

        userData = new UserData(surname, firstName, patronymic, birthDate, phoneNumber, gender);
    }
    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\\d{11}");
    }
    private boolean isValidBirthDate(String birthDate) {
        if (birthDate.matches("\\d{2}\\.\\d{2}\\.\\d{4}")) {
            String[] parts = birthDate.split("\\.");
            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);

            if (day >= 1 && day <= 31 && month >= 1 && month <= 12 && year >= 1900 && year <= 9999) {
                return true;
            }
        }

        return false;
    }

    private boolean isValidGender(String gender) {
        return gender.equalsIgnoreCase("f") || gender.equalsIgnoreCase("m");
    }

    private void saveUserDataToFile() throws IOException {
        String fileName = userData.getSurname() + ".txt";
        FileWriter writer = null;

        try {
            writer = new FileWriter(fileName, true);
            writer.write(userData.formatData() + "\n");
        } catch (IOException e) {
            throw new IOException("Ошибка при записи в файл");
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    throw new IOException("Ошибка при закрытии файла");
                }
            }
        }
    }
}
