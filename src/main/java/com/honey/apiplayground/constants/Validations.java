package com.honey.apiplayground.constants;

public final class Validations {

    private Validations() {}

    public static final String REGEX_PATTERN_EMAIL_VALIDATION = "^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    public static final String REGEX_PATTERN_DATE_VALIDATION = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$";
    public static final String REGEX_PATTERN_NAME_VALIDATION = "^[ A-Za-z]+$";
    public static final String REGEX_PATTERN_NICKNAME_VALIDATION = "^[^\\s^\\d]{1}[a-zA-z0-9]+$";
    public static final String REGEX_PATTERN_PASSWORD_VALIDATION = "^[a-zA-Z0-9!\";#$%&'()*+,\\-/:<=>?@\\[\\]_{|}\\.]+$";

    public static final int MAX_LENGTH_HOBBY_VALUE = 64;
    public static final int MAX_LENGTH_NICK_NAME = 32;
    public static final int MAX_LENGTH_NAME = 64;
    public static final int MAX_LENGTH_EMAIL = 64;
    public static final int MAX_LENGTH_PASSWORD = 64;
    public static final int MAX_LENGTH_PROGRAMMING_TIME = 256;
    public static final int MAX_LENGTH_HOBBIES_LIST = 3;
    public static final int MAX_LENGTH_BORN_DATA_DATE = 10;
    public static final int MAX_LENGTH_COUNTRY = 48;
    public static final int MAX_LENGTH_COUNTRY_ISO = 2;

}
