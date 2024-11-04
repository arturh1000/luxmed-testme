package pl.luxmed.testme.infrastructure.entities;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Transactional
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, length = 128)
    String name;

    @ManyToOne
    private Department department;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_id", referencedColumnName = "id")

    private Project project;
}
