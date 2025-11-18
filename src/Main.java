import java.util.Scanner;

// Gender enumeration
enum Gender {
  MALE,
  FEMALE
}

// Person class
class Person {

  String firstName;
  String lastName;
  Gender gender;
  int age;
  float weight;
  float height;
  String ethnicGroup;
  String religion;

  // Displays person in a nice format
  @Override
  public String toString() {
    return String.format(
      "%s %s\nGender: %s\nAge: %d years\nWeight: %.2f lbs\nHeight: %.2f in\nEthnic Group: %s\nReligion: %s",
      firstName,
      lastName,
      gender,
      age,
      weight,
      height,
      ethnicGroup,
      religion
    );
  }

  public void greeting() {
    if (age < 10) {
      System.out.format(
        "Hello! I'm %s, and I'm %d years old!\n",
        firstName,
        age
      );
    } else {
      System.out.format("Hello! I'm %s.\n", firstName);
    }
  }

  public void sayPrayer() {
    System.out.println(religion);
    if (religion.toUpperCase().equals("CHRISTIAN")) {
      System.out.println(
        "Dear God, thank you for dying on the cross to save me."
      );
    } else {
      System.out.println("I don't pray to your god.");
    }
  }

  public void takeNap() {
    System.out.println("ZZZ...");
  }

  public void eat() {
    if (age < 10) {
      System.out.println("Yum! I love marshmallows!");
    } else if (age < 20) {
      System.out.println("<Crunch, crunch> I love this cereal!");
    } else {
      System.out.println("This is delicious!");
    }
  }

  public String getName() {
    return firstName + " " + lastName;
  }

  public void setReligion(String newReligion) {
    religion = newReligion;
  }

  public String getPossessivePronoun() {
    if (gender == Gender.MALE) {
      return "his";
    } else {
      return "her";
    }
  }
}

class Main {

  // Helper functions to get user input with fault tolerance
  public static float getFloatingPoint(Scanner scanner) {
    while (true) {
      try {
        float providedValue = scanner.nextFloat();

        if (providedValue > 0) {
          return providedValue;
        } else {
          System.err.println("You must enter a positive number.");
        }
      } catch (NumberFormatException e) {
        System.err.println("You must enter a number.");
      } finally {
        scanner.nextLine();
      }
    }
  }

  public static int getPositiveInteger(Scanner scanner) {
    while (true) {
      try {
        int providedValue = scanner.nextInt();

        if (providedValue > 0) {
          return providedValue;
        } else {
          System.err.println("You must enter a positive integer.");
        }
      } catch (NumberFormatException e) {
        System.err.println("You must enter an integer.");
      } finally {
        scanner.nextLine();
      }
    }
  }

  public static Gender getGender(Scanner scanner) {
    while (true) {
      String providedValue = scanner.nextLine();

      if (providedValue.matches("[Mm](ale)?")) {
        return Gender.MALE;
      } else if (providedValue.matches("[Ff](emale)?")) {
        return Gender.FEMALE;
      } else {
        System.err.print(
          "You must enter either male or female (Genesis 1:27). "
        );
      }
    }
  }

  // Wrapper function for consistency with above
  public static String getString(Scanner scanner) {
    return scanner.nextLine();
  }

  public static void main(String[] args) {
    Person person = new Person();

    // Input user data
    try (Scanner scanner = new Scanner(System.in)) {
      System.out.print("Enter the person's first name: ");
      person.firstName = getString(scanner);

      System.out.format("Enter %s's last name: ", person.firstName);
      person.lastName = getString(scanner);

      System.out.format("Enter %s's gender: ", person.firstName);
      person.gender = getGender(scanner);

      System.out.format("Enter %s's age: ", person.firstName);
      person.age = getPositiveInteger(scanner);

      System.out.format("Enter %s's weight in pounds: ", person.firstName);
      person.weight = getFloatingPoint(scanner);

      System.out.format("Enter %s's height: ", person.firstName);
      person.height = getFloatingPoint(scanner);

      System.out.format("Enter %s's ethic group: ", person.firstName);
      person.ethnicGroup = getString(scanner);

      System.out.format("Enter %s's religion: ", person.firstName);
      person.religion = getString(scanner);

      // Output person
      System.out.println(person.toString());

      String userInput;

      printOptions();

      while (true) {
        System.out.print("> ");
        userInput = scanner
          .nextLine()
          .strip()
          .toUpperCase()
          .replaceAll("[?,.-]", "");

        if (userInput.matches(".*GREETING.*")) {
          person.greeting();
        } else if (userInput.matches(".*PRAYER.*")) {
          person.sayPrayer();
        } else if (userInput.matches(".*NAP.*")) {
          person.takeNap();
        } else if (userInput.matches(".*EAT.*")) {
          person.eat();
        } else if (userInput.matches(".*NAME.*")) {
          System.out.println(person.getName());
        } else if (userInput.matches(".*RELIGION.*")) {
          System.out.format(
            "OK, enter %s new religion: ",
            person.getPossessivePronoun()
          );
          person.setReligion(scanner.nextLine());
        } else if (userInput.matches("DONE|EXIT")) {
          break;
        } else {
          System.err.println("You must enter one of the available options.");
          printOptions();
        }
      }
    }
  }

  public static void printOptions() {
    String[] options = new String[] {
      "Say greeting",
      "Say prayer",
      "Take a nap",
      "Eat something",
      "Get name",
      "Change religion"
    };
    System.out.println(
      "What would you like to do? Your options are as follows:"
    );
    for (String option : options) {
      System.out.println(option);
    }
  }
}
