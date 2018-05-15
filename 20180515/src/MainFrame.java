import javax.swing.*;
import java.awt.*;

public class MainFrame  extends JFrame{
    private JButton B1[][] = new JButton[5][4] ;
    private JLabel L1 = new JLabel("");
    private Container cp ;
    private JPanel P1 = new JPanel(new GridLayout());
    private JPanel P2 = new JPanel(new GridLayout(4,5,3,3));
    public MainFrame(){
        init();
    }
    private void init(){
        this.setBounds(100,100,425,500);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        cp = this.getContentPane();
        cp.add(P1,BorderLayout.NORTH);
        cp.add(P2,BorderLayout.CENTER);
        P1.add(L1);

        B1[0][0]=new JButton("7");
        B1[0][1]=new JButton("8");
        B1[0][2]=new JButton("9");
        B1[0][3]=new JButton("/");
        B1[1][0]=new JButton("AC");
        B1[1][1]=new JButton("4");
        B1[1][2]=new JButton("5");
        B1[1][3]=new JButton("6");
        B1[2][0]=new JButton("*");
        B1[2][1]=new JButton("SQRT");
        B1[2][2]=new JButton("1");
        B1[2][3]=new JButton("2");
        B1[3][0]=new JButton("3");
        B1[3][1]=new JButton("-");
        B1[3][2]=new JButton("PI");
        B1[3][3]=new JButton("0");
        B1[4][0]=new JButton(".");
        B1[4][1]=new JButton("=");
        B1[4][2]=new JButton("+");
        B1[4][3]=new JButton("ENTER");
        for (int i = 0 ; i < 5 ; i++){
            for (int j = 0 ; j<4 ;j++){
                P2.add(B1[i][j]);
            }
        }
    }
}
