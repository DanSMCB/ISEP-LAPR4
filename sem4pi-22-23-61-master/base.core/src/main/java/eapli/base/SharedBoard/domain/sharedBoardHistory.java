package eapli.base.SharedBoard.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import lombok.*;

import javax.persistence.Embeddable;
@Embeddable
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class sharedBoardHistory {

    private String history;

    public static sharedBoardHistory from(String history) throws Exception {
        Preconditions.ensure(history != null && !history.isEmpty(), "Invalid history");
        return new sharedBoardHistory(history);
    }

    public static sharedBoardHistory valueOf(String history) throws Exception {
        return from(history);
    }

    @Override
    public String toString() {
        return String.format("History: %s", history);
    }
}

