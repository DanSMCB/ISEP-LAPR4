package eapli.base.Student_Teacher.Teacher.Domain;

import eapli.base.Student_Teacher.Date_Of_Birth;
import eapli.base.Student_Teacher.Tax_Payer_Number;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Teacher implements AggregateRoot<Long>, Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private Acronym acronym;

    @OneToOne
    private SystemUser systemUser;
    private Tax_Payer_Number tax_payer_number;
    private Date_Of_Birth Date_of_Birth;
    //private SystemUser systemUser;
    // O id pode ser o acronym

    public Teacher(){}

    public Teacher(SystemUser systemUser, Acronym acronym, Tax_Payer_Number tax_payer_number, Date_Of_Birth date){
        this.systemUser=systemUser;
        this.acronym=acronym;
        this.tax_payer_number=tax_payer_number;
        this.Date_of_Birth=date;

    }

    public Acronym getAcronym() {
        return this.acronym;
    }
    @Override
    public boolean sameAs(Object other)
    {
        return Objects.equals(this.getId(), ((Teacher) other).getId());
    }

    @Override
    public String toString(){return acronym.toString();}

    public Long getId() {
        return id;
    }

    public Tax_Payer_Number getTax_payer_number() {
        return tax_payer_number;
    }

    public SystemUser getSystemUser() {
        return systemUser;
    }

    public Date_Of_Birth getDate_of_Birth() {
        return Date_of_Birth;
    }

    @Override
    public Long identity() {
        return id;
    }


}
