import de.ovgu.dke.teaching.ml.tictactoe.api.IBoard;

/**
 *
 * @author Steven Brandt, Fabian Witt
 */
public class Counter {
    
    private IBoard m_board;
    private int[] m_count;
    private String m_playername;
    
    /**
     *
     * @param board - board of the current game
     */
    public void setBoardandCount(IBoard board){
        m_board = board;
        countRows();
    }

    /**
     *
     * @param m_playername - playername
     */
    public void setM_playername(String m_playername) {
        this.m_playername = m_playername;
    }
    
    /**
     *
     * @return m_count - list of counted values
     */
    public int[] getCount(){
        return m_count;
    }
    
    /**
     * count different types of rows
     */
    private void countRows() {

        int[] c_row_p = {0, 0, 0, 0, 0};
        int[] c_row_o = {0, 0, 0, 0, 0};
        int count_p, count_o;
        //X-Reihen durchlaufen
        for(int y = 0; y < m_board.getSize(); y++){
            for(int z = 0; z < m_board.getSize(); z++){
                
                count_p = 0; count_o = 0;
                for(int x = 0; x < m_board.getSize(); x++){

                    if(m_board.getFieldValue(new int[] {x, y, z}) != null && !m_board.getFieldValue(new int[] {x, y, z}).getName().equals(m_playername)){
                        count_o++;
                    } else if(m_board.getFieldValue(new int[] {x, y, z}) != null && m_board.getFieldValue(new int[] {x, y, z}).getName().equals(m_playername)){
                        count_p++;
                    }
                }

                switch(count_p){
                    case 1: if(count_o == 0) c_row_p[0]++; break;
                    case 2: if(count_o == 0) c_row_p[1]++; break;
                    case 3: if(count_o == 0) c_row_p[2]++; break;
                    case 4: if(count_o == 0) c_row_p[3]++; break;
                    case 5: if(count_o == 0) c_row_p[4]++; break;
                }

                switch(count_o){
                    case 1: if(count_p == 0) c_row_o[0]++; break;
                    case 2: if(count_p == 0) c_row_o[1]++; break;
                    case 3: if(count_p == 0) c_row_o[2]++; break;
                    case 4: if(count_p == 0) c_row_o[3]++; break;
                    case 5: if(count_p == 0) c_row_o[4]++; break;
                }
            }
        }
        //Z-Reihen durchlaufen
            for(int y = 0; y < m_board.getSize(); y++){
                for(int x = 0; x < m_board.getSize(); x++){

                    count_p = 0; count_o = 0;
                    for(int z = 0; z < m_board.getSize(); z++){

                        if(m_board.getFieldValue(new int[] {x, y, z}) != null && !m_board.getFieldValue(new int[] {x, y, z}).getName().equals(m_playername)){
                            count_o++;
                        } else if(m_board.getFieldValue(new int[] {x, y, z}) != null && m_board.getFieldValue(new int[] {x, y, z}).getName().equals(m_playername)){
                            count_p++;
                        }
                    } 

                    switch(count_p){
                        case 1: if(count_o == 0) c_row_p[0]++; break;
                        case 2: if(count_o == 0) c_row_p[1]++; break;
                        case 3: if(count_o == 0) c_row_p[2]++; break;
                        case 4: if(count_o == 0) c_row_p[3]++; break;
                        case 5: if(count_o == 0) c_row_p[4]++; break;
                    }

                    switch(count_o){
                        case 1: if(count_p == 0) c_row_o[0]++; break;
                        case 2: if(count_p == 0) c_row_o[1]++; break;
                        case 3: if(count_p == 0) c_row_o[2]++; break;
                        case 4: if(count_p == 0) c_row_o[3]++; break;
                        case 5: if(count_p == 0) c_row_o[4]++; break;
                    }
                }
            }
            //Y-Reihen durchlaufen
            for(int z = 0; z < m_board.getSize(); z++){
                for(int x = 0; x < m_board.getSize(); x++){

                    count_p = 0; count_o = 0;
                    for(int y = 0; y < m_board.getSize(); y++){

                        if(m_board.getFieldValue(new int[] {x, y, z}) != null && !m_board.getFieldValue(new int[] {x, y, z}).getName().equals(m_playername)){
                            count_o++;
                        } else if(m_board.getFieldValue(new int[] {x, y, z}) != null && m_board.getFieldValue(new int[] {x, y, z}).getName().equals(m_playername)){
                            count_p++;
                        }
                    }  

                    switch(count_p){
                        case 1: if(count_o == 0) c_row_p[0]++; break;
                        case 2: if(count_o == 0) c_row_p[1]++; break;
                        case 3: if(count_o == 0) c_row_p[2]++; break;
                        case 4: if(count_o == 0) c_row_p[3]++; break;
                        case 5: if(count_o == 0) c_row_p[4]++; break;
                    }

                    switch(count_o){
                        case 1: if(count_p == 0) c_row_o[0]++; break;
                        case 2: if(count_p == 0) c_row_o[1]++; break;
                        case 3: if(count_p == 0) c_row_o[2]++; break;
                        case 4: if(count_p == 0) c_row_o[3]++; break;
                        case 5: if(count_p == 0) c_row_o[4]++; break;
                    }      
                }
            }
            
            //Diagonalen durchlaufen Ebene Boden - links unten -> rechts oben
            for(int y = 0; y < m_board.getSize(); y++){

                count_p = 0; count_o = 0;
                for(int z = 0, x = 0; z < m_board.getSize() && x < m_board.getSize(); z++, x++){

                    if(m_board.getFieldValue(new int[] {x, y, z}) != null && !m_board.getFieldValue(new int[] {x, y, z}).getName().equals(m_playername)){
                        count_o++;
                    } else if(m_board.getFieldValue(new int[] {x, y, z}) != null && m_board.getFieldValue(new int[] {x, y, z}).getName().equals(m_playername)){
                        count_p++;
                    }
                }

                switch(count_p){
                    case 1: if(count_o == 0) c_row_p[0]++; break;
                    case 2: if(count_o == 0) c_row_p[1]++; break;
                    case 3: if(count_o == 0) c_row_p[2]++; break;
                    case 4: if(count_o == 0) c_row_p[3]++; break;
                    case 5: if(count_o == 0) c_row_p[4]++; break;
                }

                switch(count_o){
                    case 1: if(count_p == 0) c_row_o[0]++; break;
                    case 2: if(count_p == 0) c_row_o[1]++; break;
                    case 3: if(count_p == 0) c_row_o[2]++; break;
                    case 4: if(count_p == 0) c_row_o[3]++; break;
                    case 5: if(count_p == 0) c_row_o[4]++; break;
                }                
            }
            
            //Diagonalen durchlaufen Ebene Boden - rechts unten -> links oben
            for(int y = 0; y < m_board.getSize(); y++){

                count_p = 0; count_o = 0;
                for(int z = 0, x = m_board.getSize() - 1; z < m_board.getSize() && x >= 0; z++, x--){

                    if(m_board.getFieldValue(new int[] {x, y, z}) != null && !m_board.getFieldValue(new int[] {x, y, z}).getName().equals(m_playername)){
                        count_o++;
                    } else if(m_board.getFieldValue(new int[] {x, y, z}) != null && m_board.getFieldValue(new int[] {x, y, z}).getName().equals(m_playername)){
                        count_p++;
                    }
                }

                switch(count_p){
                    case 1: if(count_o == 0) c_row_p[0]++; break;
                    case 2: if(count_o == 0) c_row_p[1]++; break;
                    case 3: if(count_o == 0) c_row_p[2]++; break;
                    case 4: if(count_o == 0) c_row_p[3]++; break;
                    case 5: if(count_o == 0) c_row_p[4]++; break;
                }

                switch(count_o){
                    case 1: if(count_p == 0) c_row_o[0]++; break;
                    case 2: if(count_p == 0) c_row_o[1]++; break;
                    case 3: if(count_p == 0) c_row_o[2]++; break;
                    case 4: if(count_p == 0) c_row_o[3]++; break;
                    case 5: if(count_p == 0) c_row_o[4]++; break;
                } 
            }
            
            //Diagonalen durchlaufen Ebene Front - links unten -> rechts oben 
            for(int z = 0; z < m_board.getSize(); z++){

                count_p = 0; count_o = 0;
                for(int y = 0, x = 0; y < m_board.getSize() && x < m_board.getSize(); y++, x++){

                    if(m_board.getFieldValue(new int[] {x, y, z}) != null && !m_board.getFieldValue(new int[] {x, y, z}).getName().equals(m_playername)){
                        count_o++;
                    } else if(m_board.getFieldValue(new int[] {x, y, z}) != null && m_board.getFieldValue(new int[] {x, y, z}).getName().equals(m_playername)){
                        count_p++;
                    }
                }

                switch(count_p){
                    case 1: if(count_o == 0) c_row_p[0]++; break;
                    case 2: if(count_o == 0) c_row_p[1]++; break;
                    case 3: if(count_o == 0) c_row_p[2]++; break;
                    case 4: if(count_o == 0) c_row_p[3]++; break;
                    case 5: if(count_o == 0) c_row_p[4]++; break;
                }

                switch(count_o){
                    case 1: if(count_p == 0) c_row_o[0]++; break;
                    case 2: if(count_p == 0) c_row_o[1]++; break;
                    case 3: if(count_p == 0) c_row_o[2]++; break;
                    case 4: if(count_p == 0) c_row_o[3]++; break;
                    case 5: if(count_p == 0) c_row_o[4]++; break;
                } 
            }
            
            //Diagonalen durchlaufen Ebene Front - rechts unten -> links oben
            for(int z = 0; z < m_board.getSize(); z++){

                count_p = 0; count_o = 0;
                for(int y = 0, x = m_board.getSize() - 1; y < m_board.getSize() && x >= 0; y++, x--){

                    if(m_board.getFieldValue(new int[] {x, y, z}) != null && !m_board.getFieldValue(new int[] {x, y, z}).getName().equals(m_playername)){
                        count_o++;
                    } else if(m_board.getFieldValue(new int[] {x, y, z}) != null && m_board.getFieldValue(new int[] {x, y, z}).getName().equals(m_playername)){
                        count_p++;
                    }
                }

                switch(count_p){
                    case 1: if(count_o == 0) c_row_p[0]++; break;
                    case 2: if(count_o == 0) c_row_p[1]++; break;
                    case 3: if(count_o == 0) c_row_p[2]++; break;
                    case 4: if(count_o == 0) c_row_p[3]++; break;
                    case 5: if(count_o == 0) c_row_p[4]++; break;
                }

                switch(count_o){
                    case 1: if(count_p == 0) c_row_o[0]++; break;
                    case 2: if(count_p == 0) c_row_o[1]++; break;
                    case 3: if(count_p == 0) c_row_o[2]++; break;
                    case 4: if(count_p == 0) c_row_o[3]++; break;
                    case 5: if(count_p == 0) c_row_o[4]++; break;
                } 
            }
            
            //Diagonalen durchlaufen Ebene Seite - rechts unten -> links oben
            for(int x = 0; x < m_board.getSize(); x++){

                count_p = 0; count_o = 0;
                for(int y = 0, z = 0; y < m_board.getSize() && z < m_board.getSize(); y++, z++){

                    if(m_board.getFieldValue(new int[] {x, y, z}) != null && !m_board.getFieldValue(new int[] {x, y, z}).getName().equals(m_playername)){
                        count_o++;
                    } else if(m_board.getFieldValue(new int[] {x, y, z}) != null && m_board.getFieldValue(new int[] {x, y, z}).getName().equals(m_playername)){
                        count_p++;
                    }
                }

                switch(count_p){
                    case 1: if(count_o == 0) c_row_p[0]++; break;
                    case 2: if(count_o == 0) c_row_p[1]++; break;
                    case 3: if(count_o == 0) c_row_p[2]++; break;
                    case 4: if(count_o == 0) c_row_p[3]++; break;
                    case 5: if(count_o == 0) c_row_p[4]++; break;
                }

                switch(count_o){
                    case 1: if(count_p == 0) c_row_o[0]++; break;
                    case 2: if(count_p == 0) c_row_o[1]++; break;
                    case 3: if(count_p == 0) c_row_o[2]++; break;
                    case 4: if(count_p == 0) c_row_o[3]++; break;
                    case 5: if(count_p == 0) c_row_o[4]++; break;
                } 
            }
            
            //Diagonalen durchlaufen Ebene Seite - links unten -> rechts oben
            for(int x = 0; x < m_board.getSize(); x++){

                count_p = 0; count_o = 0;
                for(int y = 0, z = m_board.getSize() - 1; y < m_board.getSize() && z >= 0; y++, z--){

                    if(m_board.getFieldValue(new int[] {x, y, z}) != null && !m_board.getFieldValue(new int[] {x, y, z}).getName().equals(m_playername)){
                        count_o++;
                    } else if(m_board.getFieldValue(new int[] {x, y, z}) != null && m_board.getFieldValue(new int[] {x, y, z}).getName().equals(m_playername)){
                        count_p++;
                    }
                }

                switch(count_p){
                    case 1: if(count_o == 0) c_row_p[0]++; break;
                    case 2: if(count_o == 0) c_row_p[1]++; break;
                    case 3: if(count_o == 0) c_row_p[2]++; break;
                    case 4: if(count_o == 0) c_row_p[3]++; break;
                    case 5: if(count_o == 0) c_row_p[4]++; break;
                }

                switch(count_o){
                    case 1: if(count_p == 0) c_row_o[0]++; break;
                    case 2: if(count_p == 0) c_row_o[1]++; break;
                    case 3: if(count_p == 0) c_row_o[2]++; break;
                    case 4: if(count_p == 0) c_row_o[3]++; break;
                    case 5: if(count_p == 0) c_row_o[4]++; break;
                } 
            }
            //Diagonale durchlaufen Raum - 1
            count_p = 0; count_o = 0;
            for(int x = 0, y = 0, z = 0; x < m_board.getSize() && y < m_board.getSize() && z < m_board.getSize(); x++, y++, z++){

                if(m_board.getFieldValue(new int[] {x, y, z}) != null && !m_board.getFieldValue(new int[] {x, y, z}).getName().equals(m_playername)){
                    count_o++;
                } else if(m_board.getFieldValue(new int[] {x, y, z}) != null && m_board.getFieldValue(new int[] {x, y, z}).getName().equals(m_playername)){
                    count_p++;
                }
            }
            switch(count_p){
                case 1: if(count_o == 0) c_row_p[0]++; break;
                case 2: if(count_o == 0) c_row_p[1]++; break;
                case 3: if(count_o == 0) c_row_p[2]++; break;
                case 4: if(count_o == 0) c_row_p[3]++; break;
                case 5: if(count_o == 0) c_row_p[4]++; break;
            }
            switch(count_o){
                case 1: if(count_p == 0) c_row_o[0]++; break;
                case 2: if(count_p == 0) c_row_o[1]++; break;
                case 3: if(count_p == 0) c_row_o[2]++; break;
                case 4: if(count_p == 0) c_row_o[3]++; break;
                case 5: if(count_p == 0) c_row_o[4]++; break;
            }
            
            //Diagonale durchlaufen Raum - 2
            count_p = 0; count_o = 0;
            for(int x = m_board.getSize() - 1, y = 0, z = 0; x >= 0  && y < m_board.getSize() && z < m_board.getSize(); x--, y++, z++){

                if(m_board.getFieldValue(new int[] {x, y, z}) != null && !m_board.getFieldValue(new int[] {x, y, z}).getName().equals(m_playername)){
                    count_o++;
                } else if(m_board.getFieldValue(new int[] {x, y, z}) != null && m_board.getFieldValue(new int[] {x, y, z}).getName().equals(m_playername)){
                    count_p++;
                }
            }
            switch(count_p){
                case 1: if(count_o == 0) c_row_p[0]++; break;
                case 2: if(count_o == 0) c_row_p[1]++; break;
                case 3: if(count_o == 0) c_row_p[2]++; break;
                case 4: if(count_o == 0) c_row_p[3]++; break;
                case 5: if(count_o == 0) c_row_p[4]++; break;
            }
            switch(count_o){
                case 1: if(count_p == 0) c_row_o[0]++; break;
                case 2: if(count_p == 0) c_row_o[1]++; break;
                case 3: if(count_p == 0) c_row_o[2]++; break;
                case 4: if(count_p == 0) c_row_o[3]++; break;
                case 5: if(count_p == 0) c_row_o[4]++; break;
            }
            //Diagonale durchlaufen Raum - 3
            count_p = 0; count_o = 0;
            for(int x = 0, y = 0, z = m_board.getSize() - 1; x < m_board.getSize()  && y < m_board.getSize() && z >= 0; x++, y++, z--){

                if(m_board.getFieldValue(new int[] {x, y, z}) != null && !m_board.getFieldValue(new int[] {x, y, z}).getName().equals(m_playername)){
                    count_o++;
                } else if(m_board.getFieldValue(new int[] {x, y, z}) != null && m_board.getFieldValue(new int[] {x, y, z}).getName().equals(m_playername)){
                    count_p++;
                }
            }
            switch(count_p){
                case 1: if(count_o == 0) c_row_p[0]++; break;
                case 2: if(count_o == 0) c_row_p[1]++; break;
                case 3: if(count_o == 0) c_row_p[2]++; break;
                case 4: if(count_o == 0) c_row_p[3]++; break;
                case 5: if(count_o == 0) c_row_p[4]++; break;
            }
            switch(count_o){
                case 1: if(count_p == 0) c_row_o[0]++; break;
                case 2: if(count_p == 0) c_row_o[1]++; break;
                case 3: if(count_p == 0) c_row_o[2]++; break;
                case 4: if(count_p == 0) c_row_o[3]++; break;
                case 5: if(count_p == 0) c_row_o[4]++; break;
            }
            
            //Diagonale durchlaufen Raum - 4
            count_p = 0; count_o = 0;
            for(int x = m_board.getSize() - 1, y = 0, z = m_board.getSize() - 1; x >= 0  && y < m_board.getSize() && z >= 0; x--, y++, z--){

                if(m_board.getFieldValue(new int[] {x, y, z}) != null && !m_board.getFieldValue(new int[] {x, y, z}).getName().equals(m_playername)){
                    count_o++;
                } else if(m_board.getFieldValue(new int[] {x, y, z}) != null && m_board.getFieldValue(new int[] {x, y, z}).getName().equals(m_playername)){
                    count_p++;
                }
            }
            switch(count_p){
                case 1: if(count_o == 0) c_row_p[0]++; break;
                case 2: if(count_o == 0) c_row_p[1]++; break;
                case 3: if(count_o == 0) c_row_p[2]++; break;
                case 4: if(count_o == 0) c_row_p[3]++; break;
                case 5: if(count_o == 0) c_row_p[4]++; break;
            }
            switch(count_o){
                case 1: if(count_p == 0) c_row_o[0]++; break;
                case 2: if(count_p == 0) c_row_o[1]++; break;
                case 3: if(count_p == 0) c_row_o[2]++; break;
                case 4: if(count_p == 0) c_row_o[3]++; break;
                case 5: if(count_p == 0) c_row_o[4]++; break;
            }
            
            m_count = new int[] {c_row_p[0], c_row_p[1], c_row_p[2], c_row_p[3], c_row_p[4], c_row_o[4], c_row_o[3], c_row_o[2], c_row_o[1], c_row_o[0]};
        }
    
}
