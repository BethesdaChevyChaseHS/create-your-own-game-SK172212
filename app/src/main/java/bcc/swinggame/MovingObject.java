package bcc.swinggame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class MovingObject extends JPanel implements KeyListener {

    private int x = 10;
    private int y = 300;
    private final int ballSize = 10; 
    private final int[][] maze; 
    private final int cellSize = 35;
    
    
    public static void main(String[] args) {
       JFrame frame = new JFrame("Hard Ball-Maze 2.0");
       MovingObject panel = new MovingObject();
       frame.add(panel);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setSize(840,825);
       frame.setVisible(true);
       panel.requestFocusInWindow();
       
       
       
    }

    public MovingObject(){
        setFocusable(true);
        addKeyListener(this);
        

        maze = new int[][] {
            //  1 = wall
            //  0 = walkway
              {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,1,1},
              {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,1,1,1,1,1},
              {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,1,1},
              {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,1,1,1,0,0,1,1},
              {0,0,0,0,0,0,1,0,0,0,0,0,0,1,1,1,0,1,1,0,1,0,0,1},
              {0,1,1,1,1,0,1,0,1,1,1,1,0,1,1,1,0,0,0,3,0,1,0,0},
              {0,1,0,0,0,0,0,0,1,0,0,0,1,1,1,0,1,1,1,0,1,1,1,0},
              {1,1,0,1,1,1,1,1,1,0,1,0,1,0,0,1,0,0,0,0,1,1,1,0},
              {2,0,0,1,0,0,0,0,0,0,1,0,1,1,0,1,0,1,1,1,0,1,1,0},
              {1,1,0,1,0,1,1,1,1,1,1,0,0,0,0,1,0,1,1,1,0,0,1,0},
              {1,1,0,1,0,1,0,0,0,0,0,1,1,1,1,0,1,0,0,0,1,0,1,0},
              {1,1,0,0,0,1,0,0,1,0,0,1,0,0,1,1,0,1,1,0,1,0,1,0},
              {1,1,0,1,1,1,0,0,1,0,0,0,0,0,0,1,1,0,0,0,1,0,1,0},
              {1,1,0,1,1,1,1,0,1,1,1,1,1,1,0,1,0,1,0,1,0,0,1,0},
              {1,1,0,0,0,0,0,0,0,1,1,1,1,1,0,1,0,1,0,1,1,0,1,0},
              {1,1,0,1,1,1,1,1,1,1,1,1,1,1,0,0,0,1,0,0,0,0,1,0},
              {1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,0,1,0,0,1,1,1,1,0},
              {1,1,1,1,1,1,1,1,1,1,1,0,1,1,0,0,1,1,0,0,0,0,0,0},
              {1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,1,1,1,1,1},
              {1,1,0,1,1,1,1,1,1,1,1,1,1,1,0,0,1,0,1,1,0,1,0,1},
              {1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,1,1,1,1},
              {1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,0,1,0,1,0,1,0,1},
              {1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,1,0,1,0,0,0,1}
              
        };
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        for(int i = 0; i < maze.length; i++){
            for(int j =0;j<maze[0].length;j++){
                if(maze[i][j]==1){
                    g.setColor(Color.RED);
                } else if(maze[i][j]==0){
                    g.setColor(Color.WHITE);
                } else if (maze[i][j]==2) {
                    g.setColor(Color.GREEN);
                }
                else{
                    g.setColor(Color.BLUE);
                }
                
                g.fillRect(j*cellSize,i*cellSize,cellSize,cellSize);
                g.drawRect(j*cellSize,i*cellSize,cellSize,cellSize);
                
            }
    }
        g.setColor(Color.BLACK);
        g.fillOval(x,y,ballSize,ballSize);
    }
    public boolean validLocation(int newX, int newY){
        int newRow = newY  / cellSize;
        int newCol = newX  / cellSize;
       if(newX >= 0 && newX < 1000-ballSize && maze[newRow][newCol] != 1){
           return true;
       } else{
          return false;
       }
    }
    @Override
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        int newY =y;
        int newX =x;
        if(key== KeyEvent.VK_UP){
            newY-=10;
        } else if(key == KeyEvent.VK_DOWN){
            newY+=10;
        } else if(key == KeyEvent.VK_LEFT){
            newX-=10;
        } else if(key == KeyEvent.VK_RIGHT){
            newX+=10;
        }
         int newRow = newY  / cellSize;
         int newCol = newX  / cellSize;
        if(validLocation(newX, newY) && validLocation(newX + ballSize, newY) && validLocation(newX, newY + ballSize) && validLocation(newX + ballSize, newY + ballSize)){
            x = newX;
            y = newY;
        } else{
            x = 10;
            y = 300;
        }
        if(reachedFinish()){
            JFrame winFrame = new JFrame("YOU WON!");
            winFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            winFrame.setSize(400,400);
            winFrame.add(new JLabel("Congratulations!  You Win!!",SwingConstants.CENTER));
            winFrame.setVisible(true);
            winFrame.setLocationRelativeTo(null);
            SwingUtilities.getWindowAncestor(this).setVisible(false);
        }
        repaint();
    }

    public boolean reachedFinish(){
        int row = (y + ballSize) / cellSize;
        int col = (x + ballSize) / cellSize;
        return maze[row][col]==3;
    }

    @Override
    public void keyReleased(KeyEvent e){};


    @Override
    public void keyTyped(KeyEvent e){};

}




    


 


