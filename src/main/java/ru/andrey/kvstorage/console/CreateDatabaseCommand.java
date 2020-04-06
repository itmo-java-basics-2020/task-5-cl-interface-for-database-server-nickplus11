package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;

public class CreateDatabaseCommand implements DatabaseCommand {
    ExecutionEnvironment env;
    String[] args;

    public CreateDatabaseCommand(ExecutionEnvironment env, String... args) {
        this.env = env;
        this.args = args;
    }

    @Override
    public DatabaseCommandResult execute() throws DatabaseException {
        if(args.length < 2){
            return DatabaseCommandResult.error("Not enough arguments");
        }

        if (env.getDatabase(args[1]).isPresent()) {
            // here will be a future realization of this method
            return DatabaseCommandResult.error("CreateDBCommand isn't success");
        } else {
            return DatabaseCommandResult.success("Database was created");
        }
    }
}
