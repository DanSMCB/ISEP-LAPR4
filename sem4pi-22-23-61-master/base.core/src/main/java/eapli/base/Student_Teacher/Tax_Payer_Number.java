package eapli.base.Student_Teacher;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
public class Tax_Payer_Number
{
    private String number;

    public Tax_Payer_Number(String number) {
        this.number=number;
    }

    public static Tax_Payer_Number valueOf(String number) {
        // Validate the taxpayer number format and throw an IllegalArgumentException if invalid
        if (!isValidTaxPayerNumber(number)) {
            throw new IllegalArgumentException("Invalid tax payer number format");
        }
        return new Tax_Payer_Number(number);
    }

    private static boolean isValidTaxPayerNumber(String number) {
        // Validate the taxpayer number format
        // For example, the taxpayer number may need to have a certain length and format, or satisfy some other criteria
        // Return true if the taxpayer number is valid, and false otherwise
        return true; // Replace with actual validation logic
    }

    @Override
    public String toString() {
        return number;
    }
}