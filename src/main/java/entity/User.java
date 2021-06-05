package entity;
import javax.persistence.*;

@Entity
@Table(name="users")
public class User {

    public User() {}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private byte age;
    private String firstName;
    private String lastName;

    public User(long id, String userName, String firstName, String lastName) {
        this.id = id;
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
