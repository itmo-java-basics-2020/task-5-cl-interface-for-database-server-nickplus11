package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

import java.util.Optional;

public class ReadKeyCommand implements DatabaseCommand {
    ExecutionEnvironment env;
    String[] args;

    public ReadKeyCommand(ExecutionEnvironment env, String... args) {
        this.env = env;
        this.args = args;
    }

    @Override
    public DatabaseCommandResult execute() throws DatabaseException {
        if (args.length < 4) {
            return DatabaseCommandResult.error("Not enough arguments");
        }

        Optional<Database> database = env.getDatabase(args[1]);
        String tableName = args[2], key = args[3];

        if (database.isPresent()) {
            database.get().read(tableName, key);
            return DatabaseCommandResult.success("Key was read");
        } else {
            return DatabaseCommandResult.error("Database isn't provided");
        }
    }
}
