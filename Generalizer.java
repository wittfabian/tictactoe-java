import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Steven Brandt, Fabian Witt
 */
public class Generalizer {
    
    private double[] m_weights;
    private double m_learningRate; 
    private ArrayList<double[]> m_train;
    
    /**
     * Set weights for the linear function
     *
     * @param w - list of weights
     */
    public void setWeights(double[] w){
        m_weights = w;
    }
    
    /**
     * Set the training examples
     * 
     * @param train - lists of training exmaples
     */
    public void setTrainingExamples(ArrayList<double[]> train){
        m_train = train;
    }
    
    /**
     * Set a new learning rate
     *
     * @param learningRate
     */
    public void setLearningRate(double learningRate){
        m_learningRate = learningRate;
    }
    
    /**
     * calculate the new weights an return it
     *
     * @return m_weights - list of new weights
     */
    public double[] getUpdatedWeights(){
        
        calculateNewWeights();
        
        return m_weights;
    }
    
    /**
     * calculates the new weights with the current training examples
     * 
     */
    private void calculateNewWeights(){
        
        Iterator<double[]> iter = m_train.iterator();
        
        //iterate through the training examples
        while(iter.hasNext()){
            double[] aktIter = iter.next();
            
            //y from the training examples
            double y = aktIter[8];
            
            //calculate y with the linear function
            double y_n = m_weights[0] + (m_weights[1] * aktIter[0]) + 
                                        (m_weights[2] * aktIter[1]) +
                                        (m_weights[3] * aktIter[2]) +
                                        (m_weights[4] * aktIter[3]) +
                                        (m_weights[5] * aktIter[4]) +
                                        (m_weights[6] * aktIter[5]) +
                                        (m_weights[7] * aktIter[6]) +
                                        (m_weights[8] * aktIter[7]) +
                                        (m_weights[9] * aktIter[8]) +
                                       (m_weights[10] * aktIter[9]) ;
            
            //calcualte error with different y
            double error = y - y_n;

            //calculate weights with learning rate, error and x-value
            for(int i = 0; i < m_weights.length; i++){
                
                if(i > 0){
                    m_weights[i] = m_weights[i] + ( m_learningRate * error * aktIter[i - 1] );
                } else {
                    m_weights[i] = m_weights[i] + ( m_learningRate * error * 1 );
                }
            }
        }
    }
}
