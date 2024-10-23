package eapli.base.SharedBoard.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import javax.persistence.*;

@Entity
public class Board implements ValueObject {

    private NumberofColumns numberC;
    private NumberofRows numberR;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Transient
    private PostIt[][] boardData;

    public Board(NumberofRows numberR, NumberofColumns numberC) {
        this.numberR = numberR;
        this.numberC = numberC;
        initializeBoard(numberR, numberC);
    }

    protected Board() {
    }

    public void initializeBoard(NumberofRows numberR, NumberofColumns numberC){
        this.boardData = new PostIt[numberR.getValue()][numberC.getValue()];
        for (int i = 0; i < numberR.getValue(); i++) {
            for (int j = 0; j < numberC.getValue(); j++) {
                boardData[i][j]=null;
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < numberR.getValue(); i++) {
            for (int j = 0; j < numberC.getValue(); j++) {
                System.out.print((boardData[i][j] != null ? boardData[i][j].getContent() : "#") + " ");
            }
            System.out.println();
        }
    }

    public PostIt getCell(int row, int column) {
        return boardData[row][column];
    }

    public synchronized void setCell(int row, int column, PostIt value) {
        boardData[row][column] = value;
    }

    public synchronized boolean insertCell(int row, int column, PostIt value) {
        if(getCell(row, column)==null){
            setCell(row, column, value);
            return true;
        }return false;
    }

    public synchronized boolean updateCell(int row, int column, PostIt value) {
        if(getCell(row, column)!=null){
            if(getCell(row,column).getAuthor().equals(value.getAuthor())){
                setCell(row, column, value);
                return true;
            }
            return false;
        }else{
            setCell(row,column,value);
            return true;
        }
    }

    public synchronized int moveCell(int row, int column, int newRow, int newColumn, SystemUser systemUser){
        if(getCell(row, column)!=null){
            if(getCell(newRow, newColumn)==null){
                if(getCell(row,column).getAuthor().equals(systemUser)){
                    setCell(newRow, newColumn, getCell(row,column));
                    setCell(row, column, null);
                    return 0;
                } return 1;
            }return 2;
        } return 3;

    }

    public NumberofColumns getNumberC() {
        return numberC;
    }

    public NumberofRows getNumberR() {
        return numberR;
    }

    public PostIt[][] getBoardData() {
        return boardData;
    }
}
