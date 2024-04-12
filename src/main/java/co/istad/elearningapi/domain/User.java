package co.istad.elearningapi.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name ="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 120)
    private String address1;

    @Column(length = 120)
    private String address2;

//    private City city;
//
//    private Country country;

    private LocalDate dob;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(length = 100)
    private String familyName;

    @Column(length=30)
    private String gender;

    @Column(length = 100)
    private String givenName;

    @Column
    private Boolean isDeleted;

    @Column
    private Boolean isVerified;

    @Column(nullable= false, unique = true,length = 30)
    private String nationalIdCard;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false,unique =true , length = 30)
    private String phoneNumber;

    @Column(nullable = false)
    private String profile;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String uuid;

    @Column(length = 100)
    private String verifiedCode;

    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;

    @OneToMany(mappedBy = "user")
    private List<Student> students;

    @OneToMany(mappedBy = "user")
    private List<Instructor> instructors;

    @ManyToOne
    private Country country;

    @ManyToOne
    private City city;
}
