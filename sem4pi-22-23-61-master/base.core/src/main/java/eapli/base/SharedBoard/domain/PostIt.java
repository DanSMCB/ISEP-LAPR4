package eapli.base.SharedBoard.domain;

import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import javax.persistence.Entity;

@Entity
public class PostIt {

    private String content;

    private SystemUser author;

    protected PostIt(){}

    public PostIt(String content, SystemUser systemUser){
        this.content=content;
        this.author=systemUser;
    }

    public String getContent(){return content;}

    public SystemUser getAuthor(){return author;}
}
