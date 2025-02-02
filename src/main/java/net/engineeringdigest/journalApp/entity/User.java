package net.engineeringdigest.journalApp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Indexed;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // or another strategy for UUID
    private int id;
    @NonNull
    @Column(unique = true)
    private String username;
    @NonNull
    private String password;

    private String roles;
    @OneToMany
    private List<JournalEntry> journalEntries;




}
