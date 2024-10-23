package eapli.base.SharedBoard.domain;

public class UpdateBoardPostItThread implements Runnable{

    private final Board board;
    private final int row;
    private final int column;
    private final PostIt postIt;

    public UpdateBoardPostItThread(Board board, int row, int column, PostIt postIt){
        this.board=board;
        this.row=row;
        this.column=column;
        this.postIt=postIt;
    }

    public void run(){
        while (!Thread.interrupted()) {
            if(board.updateCell(row, column, postIt)){
                System.out.println("Post-it successfully updated in the board.");
            }else{
                System.out.println("You are not the author of the post-it in the desired position.");
            }
        }
    }
}