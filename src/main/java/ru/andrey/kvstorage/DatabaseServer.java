package ru.andrey.kvstorage;

import ru.andrey.kvstorage.console.*;
import ru.andrey.kvstorage.exception.DatabaseException;

enum DatabaseCommands {
    CREATE_DATABASE {
        @Override
        DatabaseCommand getCommand(ExecutionEnvironment env, String... args) {
            return new CreateDatabaseCommand(env, args);
        }
    },
    CREATE_TABLE {
        @Override
        DatabaseCommand getCommand(ExecutionEnvironment env, String... args) {
            return new CreateTableCommand(env, args);
        }
    },
    UPDATE_KEY {
        @Override
        DatabaseCommand getCommand(ExecutionEnvironment env, String... args) {
            return new UpdateKeyCommand(env, args);
        }
    },
    READ_KEY {
        @Override
        DatabaseCommand getCommand(ExecutionEnvironment env, String... args) {
            return new ReadKeyCommand(env, args);
        }
    };

    abstract DatabaseCommand getCommand(ExecutionEnvironment env, String... args);
}

public class DatabaseServer {

    private final ExecutionEnvironment env;

    public DatabaseServer(ExecutionEnvironment env) {
        this.env = env;
    }

    public static void main(String[] args) {
    }

    DatabaseCommandResult executeNextCommand(String commandText) {
        if (commandText == null) {
            return DatabaseCommandResult.error("Unknown command name");
        }

        String[] lexemes = commandText.split(" ");
        boolean commandExists = false;

        for (var command : DatabaseCommands.values()) {
            if (command.name().equals(lexemes[0])) {
                commandExists = true;
                break;
            }
        }

        if (!commandExists) {
            return DatabaseCommandResult.error("Unknown command name");
        }

        try {
            DatabaseCommand nextCommand = DatabaseCommands.valueOf(lexemes[0]).getCommand(env, lexemes);
            return nextCommand.execute();
        } catch (DatabaseException dbe) {
            return DatabaseCommandResult.error(dbe.getMessage());
        }
    }
}
