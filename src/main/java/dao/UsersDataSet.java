package dao;
import accounts.User;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UsersDataSet {

    @Id
    @Column(name = "id", unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "email",nullable = false,unique = true)
    private String email;



    public UsersDataSet() {

    }

    public UsersDataSet(long id, String name, String password, String email) {
        this.setId(id);
        this.setName(name);
        this.setPassword(password);
        this.setEmail(email);
    }

    public UsersDataSet(User user){
        //this.id=
        this.setName(user.getName());
        this.setPassword(user.getPassword());
        this.setEmail(user.getEmail());
    }

    public long getId() {
        return id;
    }

    public  void setId(long id){
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password=password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email){
        this.email=email;
    }

    @Override
    public String toString(){
        return "Id= "+id+"name= "+name+"email= "+email;
    }
}
