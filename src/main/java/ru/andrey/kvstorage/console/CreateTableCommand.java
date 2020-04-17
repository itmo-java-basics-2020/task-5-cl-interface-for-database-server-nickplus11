package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

import java.util.Optional;

public class CreateTableCommand implements DatabaseCommand {
    private ExecutionEnvironment env;
    private String databaseName, tableName;

    public CreateTableCommand(ExecutionEnvironment env, String... args) {
        this.env = env;
        if (args.length < 3) {
            databaseName = null;
            tableName = null;
        } else {
            databaseName = args[1];
            tableName = args[2];
        }
    }

    @Override
    public DatabaseCommandResult execute() throws DatabaseException {
        if (databaseName == null || tableName == null) {
            return DatabaseCommandResult.error("Not enough information");
        }

        Optional<Database> database = env.getDatabase(databaseName);

        if (database.isPresent()) {
            database.get().createTableIfNotExists(tableName);
            return DatabaseCommandResult.success("Table was created");
        } else {
            return DatabaseCommandResult.error("Database isn't provided");
        }
    }
}
