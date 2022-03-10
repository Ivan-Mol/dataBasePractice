import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Worker {
    private static int id;
    private String name;
    private String specialisation;
    private int age;
    private String gender;

    public Worker(String name, String specialisation, int age, String gender) {
        id = Integer.parseInt(Integer.toString(hashCode()).substring(0, 3) + name.length() + specialisation.length());
        this.name = name;
        this.specialisation = specialisation;
        this.age = age;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecialisation() {
        return specialisation;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return Stream.of(id, name, specialisation, age, gender).map(s -> "'" + s + "'").collect(Collectors.joining(", "));
//        return "'" + id + "', " + "'" + name + "', " + "'" + specialisation + "', " + "'" + age + "', " + "'" + gender + "'";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Worker)) return false;
        Worker worker = (Worker) o;
        return getAge() == worker.getAge() && getName().equals(worker.getName()) && getSpecialisation().equals(worker.getSpecialisation()) && getGender().equals(worker.getGender());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getSpecialisation(), getAge(), getGender());
    }
}
