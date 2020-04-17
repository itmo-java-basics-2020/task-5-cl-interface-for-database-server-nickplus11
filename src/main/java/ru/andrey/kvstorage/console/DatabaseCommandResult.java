package ru.andrey.kvstorage.console;

import java.util.Optional;

public interface DatabaseCommandResult {

    Optional<String> getResult();

    DatabaseCommandStatus getStatus();

    boolean isSuccess();

    String getErrorMessage();

    enum DatabaseCommandStatus {
        SUCCESS, FAILED
    }

    static DatabaseCommandResult success(String result) {
        return new DatabaseCommandResultClass(true, result);
    }

    static DatabaseCommandResult error(String message) {
        return new DatabaseCommandResultClass(false, message);
    }

    public class DatabaseCommandResultClass implements DatabaseCommandResult {

        private boolean resultIsSuccess;
        private String commandResultInfo;

        private DatabaseCommandResultClass(boolean isSuccess, String commandResultInfo) {
            resultIsSuccess = isSuccess;
            this.commandResultInfo = commandResultInfo;
        }

        @Override
        public Optional<String> getResult() {
            return resultIsSuccess ? Optional.of(commandResultInfo) : Optional.empty();
        }

        @Override
        public DatabaseCommandStatus getStatus() {
            return resultIsSuccess ? DatabaseCommandStatus.SUCCESS : DatabaseCommandStatus.FAILED;
        }

        @Override
        public boolean isSuccess() {
            return resultIsSuccess;
        }

        @Override
        public String getErrorMessage() {
            return resultIsSuccess ? null : commandResultInfo;
        }
    }
}