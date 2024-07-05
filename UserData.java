public class UserData {

    private String surname;
    private String name;
    private String patronymic;
    private String birthDate;
    private String phoneNumber;
    private String genderExplanation;

    public UserData(String surname, String firstName, String patronymic, String birthDate, String phoneNumber, char gender) {
        this.surname = surname;
        this.name = firstName;
        this.patronymic = patronymic;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.genderExplanation = getGenderExplanation(gender);
    }

    public String getSurname() {
        return surname;
    }

    public String getFirstName() {
        return name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getGenderExplanation() {
        return genderExplanation;
    }

    public String formatData() {
        return surname + " " + name + " " + patronymic + " " + birthDate + " " + phoneNumber + " " + genderExplanation;
    }

    private String getGenderExplanation(char gender) {
        if (gender == 'f') {
            return "женский";
        } else if (gender == 'm') {
            return "мужской";
        } else {
            return "не указано";
        }
    }
}
