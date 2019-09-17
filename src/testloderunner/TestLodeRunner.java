package testloderunner;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;


//"C:\Program Files\Java\jdk1.8.0_144\bin\javac.exe"  TestLodeRunner.java Map.java BLOCK_TYPE.java
//"C:\Program Files\Java\jdk1.8.0_144\bin\javac.exe" -d . TestLodeRunner.java Map.java BLOCK_TYPE.java
//"C:\Program Files\Java\jdk1.8.0_144\bin\java.exe"  testloderunner.TestLodeRunner


public class TestLodeRunner extends JPanel implements KeyListener, ActionListener {
	
	public enum PlayerMoveFlag {
		PLAYER_STOP,
		PLAYER_LEFT,
		PLAYER_RIGHT,
		PLAYER_UP,
		PLAYER_DOWN
	};
	
	private PlayerMoveFlag playermoveflag = PlayerMoveFlag.PLAYER_STOP;
	
	private Timer time = new Timer(33, (ActionListener) this);
	
	private Map map = null;
	private int maplen = 0;   //side of a block length, its a square
	private int blocklen = 0; //side of a map in numbers of blocks, its a square
	
	final static Color player_color = Color.orange;
	
	final static Color air_color    = Color.cyan;
	final static Color ladder_color = Color.gray;
	final static Color brick_color  = Color.red;
	final static Color steel_color  = Color.black;
	
	final static int SCREEN_LEN = 560;
	
	final static int SCREEN_TOP_YY  = 30;
	final static int SCREEN_LEFT_XX = 30;
	
	int player_loc_xx = SCREEN_LEFT_XX;
	int player_loc_yy = SCREEN_TOP_YY;
	
	public void keyPressed(KeyEvent e) {  
		char cc = e.getKeyChar();
		
		//String infostr = String.format("Key Pressed = %c", cc);
        //System.out.println(infostr);
		
		if (cc == 'd')
			playermoveflag = PlayerMoveFlag.PLAYER_RIGHT;
		else if (cc == 'a')
			playermoveflag = PlayerMoveFlag.PLAYER_LEFT;
		else if (cc == 's')
			playermoveflag = PlayerMoveFlag.PLAYER_DOWN;
		else if (cc == 'w')
			playermoveflag = PlayerMoveFlag.PLAYER_UP;
    } 
	
    public void keyReleased(KeyEvent e) {  
        char cc = e.getKeyChar();
		
		//String infostr = String.format("Key Released = %c", cc);
        //System.out.println(infostr);
		
		if (cc == 'd')
			playermoveflag = PlayerMoveFlag.PLAYER_STOP;
		else if (cc == 'a')
			playermoveflag = PlayerMoveFlag.PLAYER_STOP;
		else if (cc == 's')
			playermoveflag = PlayerMoveFlag.PLAYER_STOP;
		else if (cc == 'w')
			playermoveflag = PlayerMoveFlag.PLAYER_STOP; 
    }
	
    public void keyTyped(KeyEvent e) {  
        //System.out.println("Key Typed");  
    } 
	
	public void actionPerformed(ActionEvent ee) {
		//this is called about 30 frames a second
		
		//player movement
		//(1) check in bounds of game screen



		if (playermoveflag == PlayerMoveFlag.PLAYER_UP)
		{
			//(1)
			if (player_loc_yy > SCREEN_TOP_YY)
				player_loc_yy--;
		}
		else if (playermoveflag == PlayerMoveFlag.PLAYER_DOWN)
			player_loc_yy++;
		else if (playermoveflag == PlayerMoveFlag.PLAYER_RIGHT)
			player_loc_xx++;
		else if (playermoveflag == PlayerMoveFlag.PLAYER_LEFT)
		{
			//(1)
			if (player_loc_xx > SCREEN_LEFT_XX)
				player_loc_xx--;
		}
		
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		super.paintComponent(g); //clear map
		
		//draw current state of map
		if (map == null)
		{
			map = new Map();
			boolean maploaded = map.LoadMap("./map1.map");
			if (maploaded == false)
				System.exit(0);
			
			blocklen = map.GetBlockLen();
			maplen = map.GetMapLen();
		}
		
		for (int yy=0; yy < maplen; yy++)
		{
			for (int xx=0; xx < maplen; xx++)
			{
				Rectangle2D myrect = map.GetRect(xx,yy);
				BLOCK_TYPE blocktype = map.GetBlockType(xx,yy);
				if (blocktype == BLOCK_TYPE.AIR)
					g2.setPaint(air_color);
				else if (blocktype == BLOCK_TYPE.LADDER)
					g2.setPaint(ladder_color);
				else if (blocktype == BLOCK_TYPE.BRICK)
					g2.setPaint(brick_color);
				else
					g2.setPaint(steel_color);
				g2.fill(myrect);
			}
		}
		
		Ellipse2D player = new Ellipse2D.Double(player_loc_xx, player_loc_yy, 50, 50);
		g2.setPaint(player_color);
		g2.fill(player);
		
		
	}
	
	public TestLodeRunner() {
		setPreferredSize(new Dimension(SCREEN_LEN,SCREEN_LEN));
		time.start();
	}
	
	private static void createAndShowCanvas() {
        JFrame frame = new JFrame("test test");
		TestLodeRunner myapp = new TestLodeRunner();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(myapp);
		
        frame.getContentPane().add(myapp);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
	
	public static void main(String[] args) {
		
		java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                createAndShowCanvas();
            }
        });

	}

}
