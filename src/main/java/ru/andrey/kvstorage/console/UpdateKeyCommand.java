package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

import javax.xml.crypto.Data;
import java.util.Optional;

public class UpdateKeyCommand implements DatabaseCommand {
    private ExecutionEnvironment env;
    private String databaseName, tableName, key, value;

    public UpdateKeyCommand(ExecutionEnvironment env, String... args) {
        this.env = env;
        if (args.length < 5) {
            databaseName = null;
            tableName = null;
            key = null;
            value = null;
        } else {
            databaseName = args[1];
            tableName = args[2];
            key = args[3];
            value = args[4];
        }
    }

    @Override
    public DatabaseCommandResult execute() throws DatabaseException {
        if (
                databaseName == null
                        || tableName == null
                        || key == null
                        || value == null
        ) {
            return DatabaseCommandResult.error("Not enough information");
        }

        Optional<Database> database = env.getDatabase(databaseName);

        if (database.isPresent()) {
            database.get().write(tableName, key, value);
            return DatabaseCommandResult.success("UpdateCommand is success");
        } else {
            return DatabaseCommandResult.error("Key was updated");
        }
    }
}
