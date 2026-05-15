package dto;

/**
 * Value Object для сущности Contact.
 */
public class Contact {
    private final String firstName;
    private final String lastName;
    private final String salutation;
    private final String title;
    private final String department;
    private final String phoneWork;
    private final String phoneMobile;
    private final String phoneFax;
    private final String email;
    private final String accountName;
    private final String primaryStreet;
    private final String primaryCity;
    private final String primaryState;
    private final String primaryPostalCode;
    private final String primaryCountry;
    private final String altStreet;
    private final String altCity;
    private final String altState;
    private final String altPostalCode;
    private final String altCountry;
    private final String leadSource;
    private final String description;

    // Конструктор с основными полями
    public Contact(String firstName, String lastName, String phoneWork, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salutation = "";
        this.title = "";
        this.department = "";
        this.phoneWork = phoneWork;
        this.phoneMobile = "";
        this.phoneFax = "";
        this.email = email;
        this.accountName = "";
        this.primaryStreet = "";
        this.primaryCity = "";
        this.primaryState = "";
        this.primaryPostalCode = "";
        this.primaryCountry = "";
        this.altStreet = "";
        this.altCity = "";
        this.altState = "";
        this.altPostalCode = "";
        this.altCountry = "";
        this.leadSource = "";
        this.description = "";
    }

    // Полный конструктор
    public Contact(String firstName, String lastName, String salutation, String title, String department,
                   String phoneWork, String phoneMobile, String phoneFax, String email, String accountName,
                   String primaryStreet, String primaryCity, String primaryState, String primaryPostalCode,
                   String primaryCountry, String altStreet, String altCity, String altState,
                   String altPostalCode, String altCountry, String leadSource, String description) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salutation = salutation;
        this.title = title;
        this.department = department;
        this.phoneWork = phoneWork;
        this.phoneMobile = phoneMobile;
        this.phoneFax = phoneFax;
        this.email = email;
        this.accountName = accountName;
        this.primaryStreet = primaryStreet;
        this.primaryCity = primaryCity;
        this.primaryState = primaryState;
        this.primaryPostalCode = primaryPostalCode;
        this.primaryCountry = primaryCountry;
        this.altStreet = altStreet;
        this.altCity = altCity;
        this.altState = altState;
        this.altPostalCode = altPostalCode;
        this.altCountry = altCountry;
        this.leadSource = leadSource;
        this.description = description;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSalutation() {
        return salutation;
    }

    public String getTitle() {
        return title;
    }

    public String getDepartment() {
        return department;
    }

    public String getPhoneWork() {
        return phoneWork;
    }

    public String getPhoneMobile() {
        return phoneMobile;
    }

    public String getPhoneFax() {
        return phoneFax;
    }

    public String getEmail() {
        return email;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getPrimaryStreet() {
        return primaryStreet;
    }

    public String getPrimaryCity() {
        return primaryCity;
    }

    public String getPrimaryState() {
        return primaryState;
    }

    public String getPrimaryPostalCode() {
        return primaryPostalCode;
    }

    public String getPrimaryCountry() {
        return primaryCountry;
    }

    public String getAltStreet() {
        return altStreet;
    }

    public String getAltCity() {
        return altCity;
    }

    public String getAltState() {
        return altState;
    }

    public String getAltPostalCode() {
        return altPostalCode;
    }

    public String getAltCountry() {
        return altCountry;
    }

    public String getLeadSource() {
        return leadSource;
    }

    public String getDescription() {
        return description;
    }
}