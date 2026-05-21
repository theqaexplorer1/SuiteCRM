package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.Builder.Default;

/**
 * Value Object для сущности Contact.
 * Хранит данные для создания/проверки контакта.
 */
@Builder
@Getter
@ToString
@AllArgsConstructor
public class Contact {
    private final String firstName;
    private final String lastName;
    @Default
    private final String salutation = "";
    @Default
    private final String title = "";
    @Default
    private final String department = "";
    private final String phoneWork;
    @Default
    private final String phoneMobile = "";
    @Default
    private final String phoneFax = "";
    private final String email;
    @Default
    private final String accountName = "";
    @Default
    private final String primaryStreet = "";
    @Default
    private final String primaryCity = "";
    @Default
    private final String primaryState = "";
    @Default
    private final String primaryPostalCode = "";
    @Default
    private final String primaryCountry = "";
    @Default
    private final String altStreet = "";
    @Default
    private final String altCity = "";
    @Default
    private final String altState = "";
    @Default
    private final String altPostalCode = "";
    @Default
    private final String altCountry = "";
    @Default
    private final String leadSource = "";
    @Default
    private final String description = "";
}