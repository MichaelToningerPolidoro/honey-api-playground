package com.honey.apiplayground.constants;

public final class ValidationsMessages {

    private ValidationsMessages() {}

    public static final String REGEX_PATTERN_EMAIL_VALIDATION = "The email needs to be valid";
    public static final String REGEX_PATTERN_NAME_VALIDATION = "The name can only contain letters and space";
    public static final String REGEX_PATTERN_NICKNAME_VALIDATION = "The nickname must start with a letter and cannot contain spaces, only numbers";
    public static final String REGEX_PATTERN_PASSWORD_VALIDATION = "The password can only contain letters, numbers and these characters !\";#$%&'()*+,-/:<=>?@[]_{|}.";

    public static final String MAX_LENGTH_NICK_NAME = "The character limit for nickName is " + Validations.MAX_LENGTH_NICK_NAME;
    public static final String MAX_LENGTH_NAME = "The character limit for name is " + Validations.MAX_LENGTH_NAME;
    public static final String MAX_LENGTH_EMAIL = "The character limit for email is " + Validations.MAX_LENGTH_EMAIL;
    public static final String MAX_LENGTH_PASSWORD = "The character limit for password is " + Validations.MAX_LENGTH_PASSWORD;
    public static final String MAX_LENGTH_PROGRAMMING_TIME = "The character limit for programmingTime is " + Validations.MAX_LENGTH_PROGRAMMING_TIME;
    public static final String MAX_LENGTH_HOBBIES_LIST = "The max size os hobbies list is " + Validations.MAX_LENGTH_HOBBIES_LIST;
    public static final String MAX_LENGTH_BORN_DATA_DATE = "The character limit for date is " + Validations.MAX_LENGTH_BORN_DATA_DATE;
    public static final String MAX_LENGTH_COUNTRY = "The character limit for country is " + Validations.MAX_LENGTH_COUNTRY;

    public static final String NOT_BLANK_NICK_NAME = "The nickName cannot be null or blank";
    public static final String NOT_BLANK_NAME = "The name cannot be null or blank";
    public static final String NOT_BLANK_EMAIL = "The email cannot be null or blank";
    public static final String NOT_BLANK_PASSWORD = "The password cannot be null or blank";
    public static final String NOT_BLANK_PROGRAMMING_TIME = "The programmingTime cannot be null or blank";
    public static final String NOT_BLANK_DATE = "The date cannot be null or blank";
    public static final String NOT_BLANK_COUNTRY = "The country cannot be null or blank";

    public static final String NOT_NULL_BORN_DATA = "The bornData cannot be null";
    public static final String NOT_NULL_HOBBIES_LIST = "The hobbies cannot be null or empty";


}
