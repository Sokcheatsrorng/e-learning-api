package co.istad.elearningapi.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name="enrollments")
public class  Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String code;

    private LocalTime enrolledAt;

    private boolean isDeleted;

    private boolean isCertified;

    private int progress;

    @ManyToOne
    private Student student;

    @ManyToOne
    private  Course course;

}
