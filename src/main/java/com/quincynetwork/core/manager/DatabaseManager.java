package com.quincynetwork.core.manager;

import com.quincynetwork.core.QuincyCore;
import com.quincynetwork.core.database.DBAutoSell;
import com.quincynetwork.core.database.DBQuestions;
import com.quincynetwork.core.database.Database;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    private QuincyCore main;
    private List<Database> databases = new ArrayList<>();
    private DBAutoSell autoSell;
    private DBQuestions questions;

    public DatabaseManager(QuincyCore main){
        this.main = main;
        databases.add(autoSell = new DBAutoSell(main));
        databases.add(questions = new DBQuestions(main));
    }

    public void loadDatabases(){
        for(Database config : databases){
            config.loadConfiguration();
        }
    }

    public void saveDatabases(){
        for(Database config : databases){
            config.saveConfiguration();
        }
    }

    public DBAutoSell getAutoSell() {
        return autoSell;
    }

    public DBQuestions getQuestions() {
        return questions;
    }
}
