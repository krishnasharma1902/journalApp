package net.engineeringdigest.journalApp.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "journal_entries")
@Getter
@Setter
public class JournalEntry {
    @Id
    private String id;
    private String title;
    private String content;
}
