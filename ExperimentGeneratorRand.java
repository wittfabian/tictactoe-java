import de.ovgu.dke.teaching.ml.tictactoe.game.Board3D;
import de.ovgu.dke.teaching.ml.tictactoe.game.Match;
import de.ovgu.dke.teaching.ml.tictactoe.player.RandomPlayer;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * The class with the main method to play a certain number of games (to learn
 * the parameters of the linear function).
 * @author Steven Brandt, Fabian Witt
 */
public class ExperimentGeneratorRand {
    
    /**
     * Main method to run the programm.
     * <p>
     * At first the weights are initialized, the players and the board are
     * created. Then after a game was played, the training examples are
     * computed, the weights are learned and at least the weights were
     * loaded into the player for the next match.
     * <p>
     * This is repeated some times.
     * @param args standard parameter of the main method
     */
    public static void main(String[] args) {
        
        //Initial values
        final double m_learning_rate = 0.001;
        final int m_size = 5;
        double[] m_weights = {0.03,0.03,0.03,0.03,1.03,0.03,0.03,0.03,0.03,0.03,0.03};
//        double[] m_weights = {0.10344096491024096,-2.5590548366762067,-3.4994232014984203,-0.7832407970162127,-0.4239882903457811,-2.8204372593685476,-1.3330987321836474,-0.552555171608373,2.256406601702278};

        
        
        MasterMind m_player_new = new MasterMind();
        m_player_new.setHypothesis(m_weights);
        
        RandomPlayer m_player_rand = new RandomPlayer();
        Critic m_critic = new Critic();
        
        m_critic.setSize(m_size);
        Generalizer m_generalizer = new Generalizer();
        ArrayList<double[]> m_train;
        
        int wins_player_new = 0;
        int wins_player_rand = 0;
        int draws = 0;
        
        
        for(int i = 1; i <= 50; i++){
       
        Board3D m_board = new Board3D(m_size);
        Match match = new Match(m_board, m_player_new, m_player_rand);
        match.play();
        String m_winner;
        try{
            m_winner = match.getWinner().getName();
        }
        catch(NullPointerException e){
            m_winner = "";
        }
        System.out.println("Winner:");
        System.out.println(m_winner);
        
        //count wins and draws
        if (m_winner.equals(m_player_new.getName())){
            wins_player_new += 1;
        }
        else if (m_winner.equals(m_player_rand.getName())) {
            wins_player_rand += 1;
        }
        else {
            draws += 1;
        }
        
        //compute training examples
        m_critic.setMoves(m_board.getMoveHistory());
        m_critic.setWeights(m_weights);
        m_critic.setM_playername(m_player_new.getName());
        m_critic.setM_winnername(m_winner);
        m_train = m_critic.getTrainingExamples();
        
        //printing of training examples
        Iterator<double[]> iter = m_train.iterator();
        System.out.println("Training Examples:");
        while(iter.hasNext()){
            double[] akt = iter.next();
            System.out.println(akt[0]+","+akt[1]+","+akt[2]+","+akt[3]+","+akt[4]+","+akt[5]+","+akt[6]+","+akt[7]+","+akt[8]+","+akt[9]+","+akt[10]);
        }
        
        //compute weights with lms
        m_generalizer.setTrainingExamples(m_train);     //List{x1,x2,x3,x4,x5,x6,x7,x8,x9,x10,y}
        m_generalizer.setWeights(m_weights);                  //{w0,w1,w2,w3,w4,w5,w6,w7,w8,w9,w10}
        m_generalizer.setLearningRate(m_learning_rate);
        m_weights = m_generalizer.getUpdatedWeights();   
        
        System.out.println("Weights:");
        System.out.println(m_weights[0]+","+m_weights[1]+","+m_weights[2]+","+m_weights[3]+","+m_weights[4]+","+m_weights[5]+","+m_weights[6]+","+m_weights[7]+","+m_weights[8]+","+m_weights[9]+","+m_weights[10]);
        
        //load weights into player
        m_player_new.setHypothesis(m_weights);
        

        }
        System.out.println("Wins player new:");
        System.out.println(wins_player_new);
        System.out.println("Wins player rand:");
        System.out.println(wins_player_rand);
        System.out.println("Draws:");
        System.out.println(draws);
    }
    
}
