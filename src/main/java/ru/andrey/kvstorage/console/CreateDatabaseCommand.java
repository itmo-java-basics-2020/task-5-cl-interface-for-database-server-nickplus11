package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

public class CreateDatabaseCommand implements DatabaseCommand {
    private ExecutionEnvironment env;
    private String databaseName;

    public CreateDatabaseCommand(ExecutionEnvironment env, String... args) {
        this.env = env;
        if (args.length < 2) {
            databaseName = null;
        } else {
            databaseName = args[1];
        }
    }

    @Override
    public DatabaseCommandResult execute() throws DatabaseException {
        if (databaseName == null) {
            return DatabaseCommandResult.error("Not enough information");
        }

        if (env.getDatabase(databaseName).isPresent()) {
            // here will be a future implementation of this method
            return DatabaseCommandResult.success("Database was created");
        } else {
            return DatabaseCommandResult.error("CreateDBCommand isn't success");
        }
    }
}
