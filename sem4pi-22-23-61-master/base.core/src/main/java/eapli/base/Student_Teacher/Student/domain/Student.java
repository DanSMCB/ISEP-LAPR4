package eapli.base.Student_Teacher.Student.domain;


import eapli.base.Student_Teacher.Date_Of_Birth;
import eapli.base.Student_Teacher.Tax_Payer_Number;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import javax.persistence.*;


@Entity
public class Student implements AggregateRoot<MechanographicNumber> {

    @Id
    private MechanographicNumber mechanographicNumber;

    @OneToOne
    private SystemUser systemUser;

    @Embedded
    @AttributeOverride(name = "number", column = @Column(name = "taxPayerNumber"))
    private Tax_Payer_Number taxPayerNumber;

    @Embedded
    private Date_Of_Birth dateOfBirth;


    public Student(){}

    public Student(SystemUser systemUser, MechanographicNumber mechanographicNumber, Tax_Payer_Number tax_payer_number, Date_Of_Birth dateOfBirth){
        this.systemUser=systemUser;
        this.mechanographicNumber = mechanographicNumber;
        this.taxPayerNumber=tax_payer_number;
        this.dateOfBirth=dateOfBirth;
    }

    @Override
    public String toString() {
        return "Student{" +
                "mechanographicNumber=" + mechanographicNumber +
                ", taxPayerNumber=" + taxPayerNumber +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }

    public MechanographicNumber getMechanographicNumber() {
        return this.mechanographicNumber;
    }

    public Tax_Payer_Number getTaxPayerNumber() {
        return this.taxPayerNumber;
    }


    @Override
    public boolean sameAs(Object other) {
        Student student = (Student) other;
        return this.equals(student) && getTaxPayerNumber().equals(student.getTaxPayerNumber()) && student.getDateOfBirth().equals(student.getDateOfBirth());
    }

    private Date_Of_Birth getDateOfBirth() {
        return this.dateOfBirth;
    }

    @Override
    public boolean equals(final Object o) {
        return DomainEntities.areEqual(this, o);
    }

    @Override
    public MechanographicNumber identity() {
        return mechanographicNumber;
    }
}
