package net.engineeringdigest.journalApp.entity;

import com.sun.corba.se.spi.ior.ObjectId;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Indexed;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)  // or another strategy for UUID
    private int id;
    @NonNull
    @Column(unique = true)
    private String username;
    @NonNull
    private String password;

    @OneToMany
    private List<JournalEntry> journalEntries;


}
