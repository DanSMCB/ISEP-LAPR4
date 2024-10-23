package eapli.base.SharedBoard.domain;

public class CreateBoardPostItThread implements Runnable{

    private final Board board;
    private final int row;
    private final int column;
    private final PostIt postIt;

    public CreateBoardPostItThread(Board board, int row, int column, PostIt postIt){
        this.board=board;
        this.row=row;
        this.column=column;
        this.postIt=postIt;
    }

    public void run(){
        while (!Thread.interrupted()) {
            if(board.insertCell(row, column, postIt)){
                System.out.println("Post-it successfully added to the board.");
            }else{
                System.out.println("The desired position is already occupied.");
            }
        }
    }
}
