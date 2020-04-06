package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

import javax.xml.crypto.Data;
import java.util.Optional;

public class UpdateKeyCommand implements DatabaseCommand {
    ExecutionEnvironment env;
    String[] args;

    public UpdateKeyCommand(ExecutionEnvironment env, String... args) {
        this.env = env;
        this.args = args;
    }

    @Override
    public DatabaseCommandResult execute() throws DatabaseException {
        if (args.length < 5) {
            return DatabaseCommandResult.error("Not enough arguments");
        }

        Optional<Database> database = env.getDatabase(args[1]);
        String tableName = args[2], key = args[3], value = args[4];

        if (database.isPresent()) {
            database.get().write(tableName, key, value);
            return DatabaseCommandResult.success("UpdateCommand is success");
        } else {
            return DatabaseCommandResult.error("Key was updated");
        }
    }
}
