package dto;

/**
 * Value Object для сущности Account.
 * Хранит данные для создания/проверки аккаунта.
 */
public class Account {
    private final String name;
    private final String phone;
    private final String fax;
    private final String website;
    private final String billingStreet;
    private final String billingCity;
    private final String billingState;
    private final String billingPostalCode;
    private final String billingCountry;
    private final String shippingStreet;
    private final String shippingCity;
    private final String shippingState;
    private final String shippingPostalCode;
    private final String shippingCountry;
    private final String type;
    private final String industry;
    private final String description;
    // Поля для чекбоксов email
    private final boolean emailOptedOut;
    private final boolean emailInvalid;

    // Конструктор с основными полями
    public Account(String name, String phone, String fax, String website,
                   String billingStreet, String type, String industry) {
        this.name = name;
        this.phone = phone;
        this.fax = fax;
        this.website = website;
        this.billingStreet = billingStreet;
        this.billingCity = "";
        this.billingState = "";
        this.billingPostalCode = "";
        this.billingCountry = "";
        this.shippingStreet = "";
        this.shippingCity = "";
        this.shippingState = "";
        this.shippingPostalCode = "";
        this.shippingCountry = "";
        this.type = type;
        this.industry = industry;
        this.description = "";
        this.emailOptedOut = false;
        this.emailInvalid = false;
    }

    // Полный конструктор с чекбоксами
    public Account(String name, String phone, String fax, String website,
                   String billingStreet, String billingCity, String billingState,
                   String billingPostalCode, String billingCountry,
                   String shippingStreet, String shippingCity, String shippingState,
                   String shippingPostalCode, String shippingCountry,
                   String type, String industry, String description,
                   boolean emailOptedOut, boolean emailInvalid) {
        this.name = name;
        this.phone = phone;
        this.fax = fax;
        this.website = website;
        this.billingStreet = billingStreet;
        this.billingCity = billingCity;
        this.billingState = billingState;
        this.billingPostalCode = billingPostalCode;
        this.billingCountry = billingCountry;
        this.shippingStreet = shippingStreet;
        this.shippingCity = shippingCity;
        this.shippingState = shippingState;
        this.shippingPostalCode = shippingPostalCode;
        this.shippingCountry = shippingCountry;
        this.type = type;
        this.industry = industry;
        this.description = description;
        this.emailOptedOut = emailOptedOut;
        this.emailInvalid = emailInvalid;
    }

    // Геттеры
    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getFax() { return fax; }
    public String getWebsite() { return website; }
    public String getBillingStreet() { return billingStreet; }
    public String getBillingCity() { return billingCity; }
    public String getBillingState() { return billingState; }
    public String getBillingPostalCode() { return billingPostalCode; }
    public String getBillingCountry() { return billingCountry; }
    public String getShippingStreet() { return shippingStreet; }
    public String getShippingCity() { return shippingCity; }
    public String getShippingState() { return shippingState; }
    public String getShippingPostalCode() { return shippingPostalCode; }
    public String getShippingCountry() { return shippingCountry; }
    public String getType() { return type; }
    public String getIndustry() { return industry; }
    public String getDescription() { return description; }
    //Геттеры для чекбоксов
    public boolean isEmailOptedOut() { return emailOptedOut; }
    public boolean isEmailInvalid() { return emailInvalid; }
}