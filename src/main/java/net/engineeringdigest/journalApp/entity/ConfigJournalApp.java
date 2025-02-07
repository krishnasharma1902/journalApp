package net.engineeringdigest.journalApp.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "config_journal_app")
@Data
public class ConfigJournalApp {

    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @Column(name = "config_key")
    private String configKey;

    @Column(name = "config_value")
    private String configValue;
}
