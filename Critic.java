import de.ovgu.dke.teaching.ml.tictactoe.api.IMove;
import de.ovgu.dke.teaching.ml.tictactoe.api.IllegalMoveException;
import de.ovgu.dke.teaching.ml.tictactoe.game.Board3D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The class Critic computes from the history of moves the training examples. 
 * @author Steven Brandt, Fabian Witt
 */
public class Critic {
    
    private List<IMove> m_moves;
    private int m_size;
    private double[] m_weights;
    private String m_playername;
    private String m_winnername;

    
/**
 * Method to set the actual learned weights.
 * @param weights array of double values
 */    
    public void setWeights(double[] weights) {
        m_weights = weights;
    }
    
/**
 * Method to set the list of moves from the finished game.
 * @param moves list of IMove elements
 */
    public void setMoves(List<IMove> moves){
        m_moves = moves;
    }
    
/**
 * Method to set the size of the board.
 * @param size int value of the size
 */
    public void setSize(int size){
        m_size = size;
    }

/**
 * Method to set the name of the player for which the examples are.
 * @param m_playername string value of the name
 */
    public void setM_playername(String m_playername) {
        this.m_playername = m_playername;
    }

/**
 * Method to set the name of the match winner.
 * @param m_winnername string value of the winnername
 */
    public void setM_winnername(String m_winnername) {
        this.m_winnername = m_winnername;
    }
    
    
/**
 * Method to compute the training examples.
 * @return the list of double array's. Each element is a training example. The
 * last value in every array is the training value.
 */
    public ArrayList<double[]> getTrainingExamples(){
        
        Iterator<IMove> iter = m_moves.iterator();
        Board3D m_board = new Board3D(m_size);
        Counter m_counter = new Counter();
        m_counter.setM_playername(m_playername);
        ArrayList<double[]> m_examples = new ArrayList<>();
        IMove m_currentmove;
        int[] m_count_b;
        int[] m_count_successor;
        double m_ytrain;
        
        while(iter.hasNext()){
            
            m_currentmove = iter.next();
            try {
                m_board.makeMove(m_currentmove);
            } catch (IllegalMoveException ex) {
                Logger.getLogger(Critic.class.getName()).log(Level.SEVERE, null, ex);
            }
            m_counter.setBoardandCount(m_board);
            m_count_b = m_counter.getCount();
            
            if(m_currentmove.getPlayer().getName().equals(m_playername) && !iter.hasNext()){               
                if(m_winnername.equals(m_playername)){
                    m_ytrain = 100;
                }
                else {
                    m_ytrain = 0;
                }
                m_examples.add(new double[] {   m_count_b[0],
                                                m_count_b[1],
                                                m_count_b[2],
                                                m_count_b[3],
                                                m_count_b[4],
                                                m_count_b[5],
                                                m_count_b[6],
                                                m_count_b[7],
                                                m_count_b[8],
                                                m_count_b[9],
                                                m_ytrain } );
            }
            
            else if(m_currentmove.getPlayer().getName().equals(m_playername) && iter.hasNext()){
                m_currentmove = iter.next();  
                try {
                    m_board.makeMove(m_currentmove);
                } catch (IllegalMoveException ex) {
                    Logger.getLogger(Critic.class.getName()).log(Level.SEVERE, null, ex);
                }
                m_counter.setBoardandCount(m_board);
                m_count_successor = m_counter.getCount();
                
                if(!iter.hasNext()){
                    if(m_winnername.equals("")){
                        m_ytrain = 0;
                    }
                    else {
                       m_ytrain = -100;
                    }
                }
                else {
                    m_ytrain =   m_weights[0] + 
                                (m_weights[1] * m_count_successor[0]) + 
                                (m_weights[2] * m_count_successor[1]) +
                                (m_weights[3] * m_count_successor[2]) +
                                (m_weights[4] * m_count_successor[3]) +
                                (m_weights[5] * m_count_successor[4]) +
                                (m_weights[6] * m_count_successor[5]) +
                                (m_weights[7] * m_count_successor[6]) +
                                (m_weights[8] * m_count_successor[7]) +
                                (m_weights[9] * m_count_successor[8]) +
                                (m_weights[10] * m_count_successor[9]) ;
                }
                m_examples.add(new double[] {   m_count_b[0],
                                                m_count_b[1],
                                                m_count_b[2],
                                                m_count_b[3],
                                                m_count_b[4],
                                                m_count_b[5],
                                                m_count_b[6],
                                                m_count_b[7],
                                                m_count_b[8],
                                                m_count_b[9],
                                                m_ytrain } );
            }
                    
        }
        return m_examples;
    }
}
