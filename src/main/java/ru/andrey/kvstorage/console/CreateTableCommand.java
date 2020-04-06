package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;
import java.util.Optional;

public class CreateTableCommand implements DatabaseCommand {
    ExecutionEnvironment env;
    String[] args;

    public CreateTableCommand(ExecutionEnvironment env, String... args) {
        this.env = env;
        this.args = args;
    }

    @Override
    public DatabaseCommandResult execute() throws DatabaseException {
        if (args.length < 3) {
            return DatabaseCommandResult.error("Not enough arguments");
        }

        Optional<Database> database = env.getDatabase(args[1]);
        String tableName = args[2];

        if (database.isPresent()) {
            database.get().createTableIfNotExists(tableName);
            return DatabaseCommandResult.success("Table was created");
        } else {
            return DatabaseCommandResult.error("Database isn't provided");
        }
    }
}
