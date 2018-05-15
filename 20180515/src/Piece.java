import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class Piece extends JFrame
{
    //放數字九官格
    private String [][] box = new String[][]{{"1","2","3"},
            {"4","5","6"},
            {"7","8"," "}};
    private keyLis kl = new keyLis();//鍵盤事件
    private ButtonHandler bh = new ButtonHandler();//功能表單事件
    private final int LEFT = 37,UP = 38, RIGHT = 39, DOWN = 40;
    private JLabel [] jl = new JLabel [9];
    private JMenuBar jmb;
    private JMenu game = new JMenu("遊戲"),about = new JMenu("關於");;
    private JMenuItem [] gm = new JMenuItem[3];//遊戲
    private JMenuItem [] abo = new JMenuItem[2];//關於
    private boolean isStart = false; //遊戲開始

    //初始化遊戲變數值
    private String[][] init()
    {
        final int ROW = 3, COL = 3;
        String [][] boxTemp = new String[][]{{"1","2","3"},
                {"4","5","6"},
                {"7","8"," "}};

        //用亂數打亂數字的排列
        for(int count = 0; count < 200; count++)
        {
            String temp = " ";
            int [] zero = null;
            int ranNum = (int)(Math.random()*4)+LEFT;

            zero = getZero(boxTemp);  //取得0的位置
            switch(ranNum)
            {
                case LEFT: //左移
                    if(zero[1]+1<boxTemp[zero[0]].length)
                    {
                        temp = boxTemp[zero[0]][zero[1]+1];
                        boxTemp[zero[0]][zero[1]+1] = " ";
                    }
                    break;
                case UP: //上移
                    if(zero[0]+1<boxTemp.length)
                    {
                        temp = boxTemp[zero[0]+1][zero[1]];
                        boxTemp[zero[0]+1][zero[1]] = " ";
                    }
                    break;
                case RIGHT: //右移
                    if(zero[1]-1 >= 0)
                    {
                        temp = boxTemp[zero[0]][zero[1]-1];
                        boxTemp[zero[0]][zero[1]-1] = " ";
                    }
                    break;
                case DOWN: //下移
                    if(zero[0]-1 >= 0)
                    {
                        temp = boxTemp[zero[0]-1][zero[1]];
                        boxTemp[zero[0]-1][zero[1]] = " ";
                    }
                    break;
                default:
            }
            boxTemp[zero[0]][zero[1]] = temp;
        }

        return boxTemp;
    }

    //取得0的位置
    private int [] getZero(String [][] boxArr)
    {
        int location[] = new int[2];

        for(int r = 0; r < boxArr.length; r++)
        {
            for(int c = 0; c < boxArr[r].length; c++)
            {
                //取得數字0的位置
                if(boxArr[r][c].equals(" "))
                {
                    location[0] = r;  //" "的x位置
                    location[1] = c;  //" "的y位置
                }
            }
        }

        return location;
    }

    public Piece()
    {
        super("數字拼圖");
        Container c = getContentPane();
        c = this.getContentPane();
        jmb = new JMenuBar();
        this.setJMenuBar(jmb); //加入工具列

        //遊戲的選擇項目
        jmb.add(game);
        gm[0] = new JMenuItem("新局");
        gm[1] = new JMenuItem("放棄");
        gm[2] = new JMenuItem("結束遊戲");
        game.add(gm[0]);
        game.add(gm[1]);
        game.addSeparator();
        game.add(gm[2]);
        gm[1].setEnabled(false);

        //關於的選擇項目
        jmb.add(about);
        abo[0] = new JMenuItem("遊戲說明");
        abo[1] = new JMenuItem("作者");
        about.add(abo[0]);
        about.add(abo[1]);

        //數字的排為3*3
        c.setLayout(new GridLayout(3,3));

        for(int i = 0; i < jl.length; i++)
        {
            jl[i] = new JLabel("  "+box[i/box.length][i%box.length]);
            jl[i].setFont(new Font("Serif",Font.BOLD,40));
            c.add(jl[i]);
        }

        //設定視窗
        setSize(200,200);
        setLocation(300,250);
        setResizable(false);//視窗放大按鈕無效
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //註冊功能表單傾聽者
        for(int m=0;m<gm.length;m++)
            gm[m].addActionListener(bh);
        abo[0].addActionListener(bh);
        abo[1].addActionListener(bh);

        //註冊鍵盤傾聽者
        this.addKeyListener(kl);
    }

    //鍵盤事件處理
    private class keyLis implements KeyListener
    {
        public void keyPressed(KeyEvent e)
        {
            if(isStart == true)
            {
                String temp = " ";
                int [] zero = null;

                zero = getZero(box);  //取得" "的位置
                switch(e.getKeyCode())
                {
                    case LEFT: //按左鍵
                        if(zero[1]+1<box[zero[0]].length)
                        {
                            temp = box[zero[0]][zero[1]+1];
                            box[zero[0]][zero[1]+1] = " ";
                        }
                        break;
                    case UP: //按上鍵
                        if(zero[0]+1<box.length)
                        {
                            temp = box[zero[0]+1][zero[1]];
                            box[zero[0]+1][zero[1]] = " ";
                        }
                        break;
                    case RIGHT: //按右鍵
                        if(zero[1]-1 >= 0)
                        {
                            temp = box[zero[0]][zero[1]-1];
                            box[zero[0]][zero[1]-1] = " ";
                        }
                        break;
                    case DOWN: //按下鍵
                        if(zero[0]-1 >= 0)
                        {
                            temp = box[zero[0]-1][zero[1]];
                            box[zero[0]-1][zero[1]] = " ";
                        }
                        break;
                    default:
                }
                box[zero[0]][zero[1]] = temp;

                //更新數字顯示的位置
                for(int i=0; i< jl.length; i++)
                    jl[i].setText("  "+box[i/box.length][i%box.length]);

                //判斷是否過關
                if(box[0][0].equals("1") && box[0][1].equals("2") &&
                        box[0][2].equals("3") && box[1][0].equals("4") &&
                        box[1][1].equals("5") && box[1][2].equals("6") &&
                        box[2][0].equals("7") && box[2][1].equals("8") &&
                        box[2][2].equals(" ")
                        )
                {
                    JOptionPane.showMessageDialog(null, "過關了!!", "訊息",
                            JOptionPane.INFORMATION_MESSAGE);

                    //判斷是否再玩一次
                    if(JOptionPane.showConfirmDialog(null, "再玩一次？", "訊息",
                            JOptionPane.YES_NO_OPTION) == 0
                            )
                    {
                        box = init();
                        for(int i=0; i< jl.length; i++)//更新數字顯示的位置
                            jl[i].setText("  "+box[i/box.length][i%box.length]);
                    }
                    else System.exit(0);  //結束遊戲
                }
            }
        }

        public void keyReleased(KeyEvent e){}
        public void keyTyped(KeyEvent e){}
    }

    //功能表單事件處理
    private class ButtonHandler implements  ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            if(ae.getSource() == gm[0]) //新局
            {
                box = init();
                for(int i=0; i< jl.length; i++)//更新數字顯示的位置
                    jl[i].setText("  "+box[i/box.length][i%box.length]);
                isStart = true;
                gm[1].setEnabled(true);
            }
            else if(ae.getSource() == gm[1]) //放棄
            {
                isStart = false;
                for(int i=0; i< jl.length; i++)//初始化數字顯示的位置
                {
                    box[i/box.length][i%box.length] = Integer.toString(i+1);
                    if(box[i/box.length][i%box.length].equals("9")) box[i/box.length][i%box.length] = " ";
                    jl[i].setText("  "+box[i/box.length][i%box.length]);
                }

            }
            else if(ae.getSource() == gm[2]) //結束遊戲
            {
                System.exit(0);
            }
            else if(ae.getSource() == abo[0]) //遊戲說明
            {
                JOptionPane.showMessageDialog(null, "過關排列方式      操作方式\n"+
                                "    1  2  3                  ↑ 上移\n" +
                                "    4  5  6        ←  左移    →  右移\n" +
                                "    7  8                      ↓ 下移" , "遊戲規則",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            else if(ae.getSource() == abo[1]) //作者
            {
                JOptionPane.showMessageDialog(null,"        程式設計：吉他手\n"+
                                "http://blog.xuite.net/ray00000test/blog" ,
                        "作者",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        }
    }

    public static void main(String [] args)
    {
        new Piece();
    }
}
