package ba.unsa.etf.ppis.constants;

public class ApiResponseMessages {
    public static final int MAX_NAME_LENGTH = 50;
    public static final int MAX_DESCRIPTION_LENGTH = 255;
    public static final int PASSWORD_MAX_LENGTH = 25;
    public static final int PASSWORD_MIN_LENGTH = 8;

    // successful
    public static final String ALL_USERS_FOUND = "Successfully found all users";
    public static final String ALL_EVENTS_FOUND = "Successfully found all events";
    public static final String EVENT_FOUND = "Successfully found event";
    public static final String EVENT_NOT_FOUND = "Event not found";
    public static final String USER_WAS_FOUND = "Successfully found the user with provided information";
    public static final String USER_WAS_VERIFIED = "The user was verified";
    public static final String USER_CREATED = "The user was successfully created!";
    public static final String USER_UPDATED = "The user was successfully updated!";
    public static final String EMAIL_SUCCESSFULLY_CHANGED = "The email was successfully changed!";
    public static final String PASSWORD_SUCCESSFULLY_CHANGED = "The password was successfully changed!";
    public static final String USER_IS_UP_TO_DATE = "The user is already up to date. No actions required!";
    public static final String CITY_CREATED = "The city was successfully created!";
    public static final String COUNTRY_CREATED = "The country was successfully created!";
    public static final String ALL_LOCATIONS_FOUND = "Successfully found all locations in the system";
    public static final String CONNECTION_SENT = "The connection request was successfully sent!";
    public static final String CONNECTION_ACCEPTED = "The connection request was successfully accepted!";
    public static final String CONNECTION_REJECTED = "The connection request was successfully rejected!";
    public static final String MAIL_SENT = "The mail was successfully sent";
    public static final String NOTIFICATION_CREATED = "The notification has been created";
    public static final String VERIFICATION_CODE_WAS_SENT = "The verification code was sent. Please check your email.";

    // not found
    public static final String USER_NOT_FOUND_WITH_ID = "The user with provided id was not found";
    public static final String USER_NOT_FOUND_WITH_EMAIL = "The user with provided email was not found";
    public static final String USER_NOT_FOUND_WITH_UUID = "The user with provided uuid was not found";
    public static final String WRONG_EMAIL_OR_PASSWORD = "The provided email and password combination is not valid";

    // missing data
    public static final String MISSING_EMAIL = "Missing email. Please provide.";
    public static final String MISSING_COUNTRY = "Missing country name in location. Please provide.";
    public static final String MISSING_CITY = "Missing city name in location. Please provide.";
    public static final String MISSING_LOCATION = "Missing location. Please provide.";
    public static final String MISSING_ACCOUNT_TYPE = "Missing account type. Please provide.";
    public static final String MISSING_PASSWORD = "Missing password. Please provide.";
    public static final String MISSING_UUID = "Missing uuid. Please provide.";


    // wrong data
    public static final String USER_ALREADY_EXISTS = "The user with this e-mail already exists";
    public static final String COUNTRY_DOES_NOT_EXISTS = "The provided county does not exist";
    public static final String CITY_DOES_NOT_EXISTS = "The provided city does not exist";
    public static final String CITY_DOES_NOT_MATCH_THE_COUNTRY = "The provided city does not match the provided country";
    public static final String CONNECTION_FOR_ACCEPTANCE_DOES_NOT_EXISTS = "The connection for acceptance was not found!";
    public static final String CONNECTION_FOR_REJECTING_DOES_NOT_EXISTS = "The connection for rejecting was not found!";
    public static final String CONNECTION_ALREADY_EXISTS = "The connection is already pending or accepted!";
    public static final String CANT_HAVE_CONNECTION_WITH_YOURSELF = "The connection can not start between same users";
    public static final String USER_IS_ALREADY_VERIFIED = "The user is already verified";
    public static final String WRONG_VERIFICATION_CODE = "The verification code is not correct!";


    // wrong format
    public static final String EMAIL_HAS_WRONG_FORMAT = "The provided email has a wrong format. Follow the next patter: email@mail.com";
    public static final String EMAIL_TOO_LONG = "The provided email is too long. Tha max allowed characters: " + MAX_NAME_LENGTH;
    public static final String CITY_NAME_TOO_LONG = "The provided city name is too long. Tha max allowed characters: " + MAX_NAME_LENGTH;
    public static final String COUNTRY_NAME_TOO_LONG = "The provided county name is too long. Tha max allowed characters: " + MAX_NAME_LENGTH;
    public static final String FIRST_NAME_TOO_LONG = "The provided name is too long. Tha max allowed characters: " + MAX_NAME_LENGTH;
    public static final String LAST_NAME_TOO_LONG = "The provided last name is too long. Tha max allowed characters: " + MAX_NAME_LENGTH;
    public static final String NAME_TOO_LONG = "The provided account name is too long. Tha max allowed characters: " + MAX_NAME_LENGTH;
    public static final String DESCRIPTION_TO_LONG = "The provided description is too long. Tha max allowed characters: " + MAX_DESCRIPTION_LENGTH;
    public static final String CAN_NOT_DECLARE_NAME_WITH_PRIVATE_ACCOUNT = "The data is not matching. Can not add a name to a private account. Please define first and last name";
    public static final String CAN_NOT_DECLARE_NAME_WITH_COMPANY_ACCOUNT = "The data is not matching. Can not add a first or last name to a company account. Please define account name";
    public static final String PASSWORD_LENGTH = "The password should be in between " + PASSWORD_MIN_LENGTH + " and " + PASSWORD_MAX_LENGTH;
    public static final String PASSWORD_TO_WEAK = "The password is too weak. Provide a password with at least one uppercase, one lowercase letter and one number.";
    public static final String UUIDS_DO_NOT_MATCH = "The provided UUID from path and body do not match!";
    public static final String PASSWORDS_DO_NOT_MATCH = "The old password that was provided do not match!";

    // no access
    public static final String NO_ACCESS = "You don't have access to this resource";
    private ApiResponseMessages() {
    }
}
