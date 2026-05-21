package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * Value Object для сущности Account.
 * Хранит данные для создания/проверки аккаунта.
 */
@Builder
@Getter
@ToString
@AllArgsConstructor
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

    // Конструктор с основными полями для короткой версии
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
}