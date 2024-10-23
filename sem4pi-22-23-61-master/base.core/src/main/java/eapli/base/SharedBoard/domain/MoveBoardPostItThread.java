package eapli.base.SharedBoard.domain;

import eapli.framework.infrastructure.authz.domain.model.SystemUser;

public class MoveBoardPostItThread implements Runnable{

    private final Board board;
    private final int row;
    private final int column;
    private final int newRow;
    private final int newColumn;
    private final SystemUser systemUser;

    public MoveBoardPostItThread(Board board, int row, int column, int newRow, int newColumn, SystemUser systemUser){
        this.board=board;
        this.row=row;
        this.column=column;
        this.newRow=newRow;
        this.newColumn=newColumn;
        this.systemUser=systemUser;
    }

    public void run(){
        while (!Thread.interrupted()) {
            if(board.moveCell(row, column, newRow, newColumn, systemUser)==0){
                System.out.println("Post-it successfully updated in the board.");
            }else if(board.moveCell(row, column, newRow, newColumn, systemUser)==1){
                System.out.println("You are not the author of the original post-it.");
            }else if(board.moveCell(row, column, newRow, newColumn, systemUser)==2){
                System.out.println("The desired position is already occupied.");
            }else if(board.moveCell(row, column, newRow, newColumn, systemUser)==3) {
                System.out.println("The original position is not occupied with a post-it.");
            }
        }
    }
}
