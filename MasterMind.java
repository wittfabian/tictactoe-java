import de.ovgu.dke.teaching.ml.tictactoe.api.IBoard;
import de.ovgu.dke.teaching.ml.tictactoe.api.IPlayer;
import de.ovgu.dke.teaching.ml.tictactoe.api.IllegalMoveException;
import de.ovgu.dke.teaching.ml.tictactoe.game.Move;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Steven Brandt, Fabian Witt
 */
public class MasterMind implements IPlayer {
    private double[] m_hypothesis = {0.02025804588907398,-0.002959724112745574,-0.0036887227407396105,-0.16593980473726685,0.2713410646023497,0.03415778294944823,0.03,-0.017887808497458347,0.005698204593518297,1.0029965783568568,-8.687630041876928E-4};
    //private double[] m_hypothesis = {0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.03};
    private ArrayList<int[]> m_successors;

    /**
     *
     * @param board
     * @return values of the next move
     */
    @Override
    public int[] makeMove(IBoard board) {

        // create a clone of the board that can be modified
        IBoard copy = board.clone();

        //List all free positions
        m_successors = getSuccessors(copy);

        //init Max-Values 
        int[] max_y_pos;
        double max_y = -1;

        Iterator<int[]> iter = m_successors.iterator();

        //set first possible move as max
        int[] tempIter = iter.next();
        max_y_pos = tempIter;
        IBoard tempCopy = board.clone();
        
        try {
            tempCopy.makeMove(new Move(this, tempIter));
            max_y = calculateFunction(tempCopy); 

        } catch (IllegalMoveException ex) {
            Logger.getLogger(MasterMind.class.getName()).log(Level.SEVERE, null, ex);
        }

        //check all other moves whether they produce a higher/same y
        while(iter.hasNext()){
            int[] akt_iter = iter.next();

            tempCopy = board.clone();
            try {
                tempCopy.makeMove(new Move(this, akt_iter));

                //if the function value is higher -> save new position
                if(calculateFunction(tempCopy) >= max_y){
                    max_y_pos = akt_iter;
                    max_y = calculateFunction(tempCopy);
                }   

            } catch (IllegalMoveException e) {

            }   
        }
        return max_y_pos;
    }

    /**
     *
     * @return MasterMind - Playername
     */
    @Override
    public String getName() {
        return "MasterMind";
    }
        
    /**
     *
     * @param board
     */
    @Override
    public void onMatchEnds(IBoard board) {

    }
        
    /**
     * Set hypothesis of MasterMind
     * 
     * @param hyp - list of hypothesis
     */
    public void setHypothesis(double[] hyp){
        m_hypothesis = hyp;
    }
        
    /**
     * find all possible moves
     *
     * @param board
     * @return successors - all possible moves
     */
    private ArrayList<int[]> getSuccessors(IBoard board){

        ArrayList<int[]> successors = new ArrayList<>();

        for(int z = 0; z < board.getSize(); z++){
            for(int y = 0; y < board.getSize(); y++){
                for(int x = 0; x < board.getSize(); x++){

                    //if FieldValue equals null -> empty field
                    if(board.getFieldValue(new int[] {x, y, z}) == null){
                        successors.add(new int[] {x, y, z});
                    }
                }
            }
        }

        return successors;
    }

    /**
     * calculate linear function with current hypothesis an x-values
     * 
     * @param board
     * @return value of linear function
     */
    private double calculateFunction(IBoard board){

        //count x-values of current board
        Counter counter = new Counter();
        counter.setM_playername(this.getName());
        counter.setBoardandCount(board);

        int[] c_values = counter.getCount();
        
        return (m_hypothesis[0] + (m_hypothesis[1] * c_values[0]) + 
                                  (m_hypothesis[2] * c_values[1]) + 
                                  (m_hypothesis[3] * c_values[2]) + 
                                  (m_hypothesis[4] * c_values[3]) + 
                                  (m_hypothesis[5] * c_values[4]) + 
                                  (m_hypothesis[6] * c_values[5]) + 
                                  (m_hypothesis[7] * c_values[6]) + 
                                  (m_hypothesis[8] * c_values[7]) + 
                                  (m_hypothesis[9] * c_values[8]) + 
                                 (m_hypothesis[10] * c_values[9])); 
    }
}