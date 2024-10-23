package eapli.base.SharedBoard.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.representations.dto.DTOable;
import eapli.framework.representations.dto.GeneralDTO;
import eapli.framework.validations.Preconditions;
import eapli.framework.visitor.Visitable;
import eapli.framework.visitor.Visitor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "SharedBoard")
public class SharedBoard implements AggregateRoot<Shared_Board_Title>, DTOable<GeneralDTO>, Visitable<GeneralDTO>, Serializable {

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private SystemUser owner;

    @ElementCollection  // Isto indica que sharedUsers é uma coleção de instâncias de um tipo básico (neste caso, AccessType).
    @CollectionTable(name="UserAccess", joinColumns=@JoinColumn(name="sharedboard_id")) // Isto indica que a tabela para a coleção sharedUsers é UserAccess e ela está ligada à tabela SharedBoard através da coluna sharedboard_id.
    @MapKeyJoinColumn(name="systemuser_id") // Isto indica que a chave do mapa em sharedUsers está mapeada para a coluna systemuser_id.
    @Column(name="access_type") // // Isto indica que o valor de cada instância na coleção sharedUsers será armazenado na coluna access_type
    @Enumerated(EnumType.STRING) // Isto indica que o valor em sharedUsers será persistido como uma sting
    private Map<SystemUser, AccessType> sharedUsers;

    @EmbeddedId
    private Shared_Board_Title title;

    @JoinColumn(name = "board_id")
    private Board board;

    private boolean isArchived = false;

    public enum AccessType {
        READ, WRITE
    }

    public SharedBoard(SystemUser owner, Shared_Board_Title title, NumberofRows numberofRows, NumberofColumns numberofColumns) {
        Preconditions.ensure(owner != null, "Owner cannot be null");
        Preconditions.ensure(title != null, "Title cannot be null");
        this.board = new Board(numberofRows, numberofColumns);
        this.title = title;
        this.owner = owner;
        this.sharedUsers = new HashMap<>();
    }

    public SharedBoard() {
    }

    public void shareBoard(SystemUser currentUser, SystemUser newUser, AccessType accessType) {
        Preconditions.ensure(currentUser != null, "Current user cannot be null");
        Preconditions.ensure(newUser != null, "User to share the board with cannot be null");

        if (!this.owner.equals(currentUser)) {
            throw new IllegalStateException("Only the owner can share the board");
        }
        if (!this.sharedUsers.containsKey(newUser)) {
            this.sharedUsers.put(newUser, accessType);
        }
    }

    public SystemUser getOwner() {
        return owner;
    }

    public void archiveBoard() {
        this.isArchived = true;

        // Se o board ta arquivado as permissoes de todos os users vao para READ e só o Owner é que tem write
        for (Map.Entry<SystemUser, AccessType> entry : sharedUsers.entrySet()) {
            if (!entry.getKey().equals(this.owner)) {
                entry.setValue(AccessType.READ);
            }
        }
    }


    public boolean isArchived() {
        return isArchived;
    }

    public Shared_Board_Title getTitle() {
        return title;
    }

    @Override
    public Shared_Board_Title identity() {
        return title;
    }

    public Board getBoard() {
        return board;
    }

    public AccessType getAccessType(SystemUser user) {
        return this.sharedUsers.get(user);
    }

    public Map<SystemUser, AccessType> getSharedUsers(){
        return sharedUsers;
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof SharedBoard)) {
            return false;
        }
        SharedBoard otherBoard = (SharedBoard) other;
        return this.identity().equals(otherBoard.identity());
    }

    @Override
    public GeneralDTO toDTO() {
        final GeneralDTO ret = new GeneralDTO("shared board");
        ret.put("title", title.toString());
        ret.put("owner", owner.username().toString());

        return ret;
    }

    @Override
    public void accept(Visitor<GeneralDTO> visitor) {
        visitor.visit(toDTO());
    }
}
