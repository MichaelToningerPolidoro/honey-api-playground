package com.honeyautomation.apiplayground.constants;

public final class Validations {

    private Validations() {}

    public static final String EMAIL_VALIDATION_REGEX_PATTERN = "^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    public static final String DATE_VALIDATION_REGEX_PATTERN = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$";
    public static final String PASSWORD_VALIDATION_REGEX_PATTERN = "^[a-zA-Z0-9!\";#$%&'()*+,\\-/:<=>?@\\[\\]_{|}\\.]+$";

    public static final int HOBBY_VALUE_MAX_LENGTH = 64;
}
