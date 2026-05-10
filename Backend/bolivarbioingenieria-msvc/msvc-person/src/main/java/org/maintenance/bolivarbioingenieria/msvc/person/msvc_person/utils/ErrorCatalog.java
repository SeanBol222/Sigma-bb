package org.maintenance.bolivarbioingenieria.msvc.person.msvc_person.utils;

import lombok.Getter;

@Getter
public enum ErrorCatalog {

    PERSON_NOT_FOUND("ERR_PERSON_001", "Person not found"),
    INVALID_PERSON_DATA("ERR_PERSON_002", "Invalid person data"),
    DATABASE_ERROR("ERR_DATABASE_003", "Database error"),
    UNKNOWN_ERROR("ERR_UNKNOW_999", "Unknown error");

    private final String code;
    private final String message;

    ErrorCatalog(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
