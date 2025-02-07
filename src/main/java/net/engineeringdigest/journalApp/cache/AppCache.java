package net.engineeringdigest.journalApp.cache;

import net.engineeringdigest.journalApp.Repository.ConfigJournalAppRepository;
import net.engineeringdigest.journalApp.entity.ConfigJournalApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    public enum keys{
        WEATHER_API;
    }

    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;

    public Map<String, String> appCache;

    @PostConstruct
    public void loadCache(){
        appCache = new HashMap<>();
        List<ConfigJournalApp> rows = configJournalAppRepository.findAll();
        for(ConfigJournalApp config : rows){
            appCache.put(config.getConfigKey(), config.getConfigValue());
        }
    }


}
