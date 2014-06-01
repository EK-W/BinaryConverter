import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class MathyStuff extends JPanel implements KeyListener, ActionListener{

Keys util = new Keys();
Timer animate = new Timer(10,this);
String displayNum = "Hi";
	
public static void main(String[] args){
	JFrame fr = new JFrame();
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	fr.setSize((int)dim.getWidth(),(int) dim.getHeight());
	fr.setExtendedState(JFrame.MAXIMIZED_BOTH);
	fr.setUndecorated(true);
	GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
	device.setFullScreenWindow(fr);
	MathyStuff db = new MathyStuff();
	db.setSize((int)dim.getWidth(),(int) dim.getHeight());
	fr.add(db);
	fr.setFocusable(true);
	fr.setVisible(true);
	fr.setResizable(false);	
}

public void paint(Graphics g2){
Graphics2D g = (Graphics2D) g2;
Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
g.clearRect(0,0,(int)dim.getWidth(),(int)dim.getHeight());
g.setColor(new Color(30,0,255,255));
g.fillRect(100,75,125,100);
g.setColor(new Color(255,255,255,255));
g.setFont(new Font("TimesRoman", Font.PLAIN, 30)); 
g.drawString("Number",100,100);
g.drawString("to",100,130);
g.drawString("binary",100,160);

g.setFont(new Font("TimesRoman", Font.PLAIN, 12)); 
g.setColor(new Color(0,0,0,255));
g.drawString(displayNum,400,100);
g.drawString(String.valueOf(Keys.mouseLoc.getX()),10,15);
g.drawString(String.valueOf(Keys.mouseLoc.getY()),10,30);
}

public void addNotify(){
super.addNotify();
addMouseListener(util);

addMouseMotionListener(util);
animate.start();
addKeyListener(this);
}

@Override
public void keyPressed(KeyEvent e) {
	int id = e.getID();
	if (id == KeyEvent.KEY_TYPED) {
	char c = e.getKeyChar();
	displayNum=join(displayNum,c);
	}else if(e.getKeyCode()==KeyEvent.VK_DELETE){
	displayNum=displayNum.substring(0,displayNum.length()-1);
	}
}

@Override
public void keyReleased(KeyEvent arg0) {}

@Override
public void keyTyped(KeyEvent e) {
int id = e.getID();
if (id == KeyEvent.KEY_TYPED) {
char c = e.getKeyChar();
displayNum=join(displayNum,c);
}else if(e.getKeyCode()==KeyEvent.VK_DELETE){
displayNum=displayNum.substring(0,displayNum.length()-1);
}
}

@Override
public void actionPerformed(ActionEvent arg0) {
repaint();
}
String join(Object a,Object b){
	return (String.valueOf(a)+""+String.valueOf(b));
}
}
