package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

import java.util.Optional;

public class ReadKeyCommand implements DatabaseCommand {
    private ExecutionEnvironment env;
    private String databaseName, tableName, key;

    public ReadKeyCommand(ExecutionEnvironment env, String... args) {
        this.env = env;
        if (args.length < 4) {
            databaseName = null;
            tableName = null;
            key = null;
        } else {
            databaseName = args[1];
            tableName = args[2];
            key = args[3];
        }
    }

    @Override
    public DatabaseCommandResult execute() throws DatabaseException {
        if (
                databaseName == null
                        || tableName == null
                        || key == null
        ) {
            return DatabaseCommandResult.error("Not enough information");
        }

        Optional<Database> database = env.getDatabase(databaseName);

        if (database.isPresent()) {
            database.get().read(tableName, key);
            return DatabaseCommandResult.success("Key was read");
        } else {
            return DatabaseCommandResult.error("Database isn't provided");
        }
    }
}
