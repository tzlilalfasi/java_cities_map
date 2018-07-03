import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.util.Vector;


public class CitiesMapPanel extends JPanel {

	private CitiesMap manager;
	private JButton cmdAddWay, cmdFindPath, cmdClearPath, cmdClearMap;
	private JLabel lblFrom, lblTo;
	private JTextField  txtFrom, txtTo;
	private Vector<City> pathVector;
	private final int SIZE = 3;

	public CitiesMapPanel(){
		manager=new CitiesMap();
		pathVector = new Vector<City>();
		cmdAddWay=new JButton("Add Way");
		cmdFindPath=new JButton("Find Path");
		cmdClearPath=new JButton("Clear Path");
		cmdClearMap=new JButton("Clear Map");
		lblFrom=new JLabel("From:");
		lblTo=new JLabel("To:");
		txtFrom = new JTextField(30);
		txtTo = new JTextField(30);

		setLayout(new BorderLayout(20,20));

		JPanel down= new JPanel(); 
		down.setLayout(new GridLayout(2,4,10,10));  //grid layout for buttons
		down.add(lblFrom);
		down.add(txtFrom);
		down.add(lblTo);
		down.add(txtTo);
		down.add(cmdAddWay);
		down.add(cmdFindPath);
		down.add(cmdClearMap);
		down.add(cmdClearPath);
		add(down, BorderLayout.SOUTH); //positioning the "down" panel in the south 

		MyListener a = new MyListener();
		addMouseListener(a);

		MyListener2 b=new MyListener2();
		cmdAddWay.addActionListener(b);
		cmdFindPath.addActionListener(b);
		cmdClearMap.addActionListener(b);
		cmdClearPath.addActionListener(b);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(Color.WHITE);
		if(manager.getCities()!=null){
			for (City s : manager.getCities()) { //draws the cities 
				g.setColor(Color.BLACK);
				g.fillOval(s.getCenterX(), s.getCenterY(), SIZE, SIZE);
				g.drawString(s.getName(), s.getCenterX(), s.getCenterY()-SIZE);
			}

			for (City s : manager.getCities()) {  //draws the lines that connects between two cities
				if(manager.getWays(s)!=null) 
					for (City s1 : manager.getWays(s)) {
						g.setColor(Color.black);
						g.drawLine(s.getCenterX(), s.getCenterY(), s1.getCenterX(), s1.getCenterY());
					}
			}

			if(pathVector!=null)	{  //draws a red line that shows the path between two cities
				g.setColor(Color.RED);
				for (int i = 0; i < pathVector.size()-1; i++){
					g.drawLine(pathVector.get(i).getCenterX(),pathVector.get(i).getCenterY() , pathVector.get(i+1).getCenterX(), pathVector.get(i+1).getCenterY());
				}
			}
		}
	}


	private class MyListener implements MouseListener{
		public void mouseClicked(MouseEvent e) { //adds a city when a mouse clicked
			String inputValue = JOptionPane.showInputDialog("Please insert a city");
			if(inputValue!=null){
				if(inputValue.isEmpty()){
					JOptionPane.showMessageDialog(null,"Please insert a city"); //in case the user didn't enter any value
					return;}
				City c1=new City(inputValue, e.getX(), e.getY());
				if(!manager.addCity(c1)){
					JOptionPane.showMessageDialog(null, "The city is already in!");
					return;}
				manager.addCity(c1);}
			repaint();
		}
		public void mouseEntered(MouseEvent arg0) {}
		public void mouseExited(MouseEvent arg0) {}
		public void mousePressed(MouseEvent arg0) {}
		public void mouseReleased(MouseEvent arg0) {}
	}

	private class MyListener2 implements ActionListener{ 
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == cmdAddWay){ 
				City city1=null;
				City city2=null;
				if(manager.getCities()!=null){
					String from1=txtFrom.getText();
					String to1=txtTo.getText();
					for (City s : manager.getCities()) { //checks if the values that entered are actual cities
						if(s.getName().equals(from1)){
							city1=s;
							for (City s1 : manager.getCities()){
								if(s1.getName().equals(to1)){
									city2=s1;
									manager.addWay(s, s1);
								}
							}
						}
					}
					if(city1==null || city2==null){ //checks if the user entered existing cities in the map
						JOptionPane.showMessageDialog(null,"Please insert existing cities");
						txtFrom.setText(""); //cleaning the text fields
						txtTo.setText("");
						return;}
				}
				else{ //in case the map is empty - there are no cities 
					JOptionPane.showMessageDialog(null,"There are no cities in the map to add a way for");
					txtFrom.setText("");
					txtTo.setText("");
					return;}
				repaint();
				txtFrom.setText("");
				txtTo.setText("");
			}

			///////////////////////////////
			if(e.getSource() == cmdFindPath){
				String from1 = txtFrom.getText();
				String to1 = txtTo.getText();
				if(manager.getCities() == null){
					JOptionPane.showMessageDialog(null,"There are no cities to find path for");
					return;}
				if(from1.isEmpty() || to1.isEmpty()) //in case the user entered empty values
					return;
				City c1 = null;
				City c2 = null;

				for (City s : manager.getCities()) {
					if(s.getName().equals(from1))
						c1 = new City(s.getName(), s.getCenterX(), s.getCenterY());
					if(s.getName().equals(to1))
						c2 = new City(s.getName(), s.getCenterX(), s.getCenterY());
				}
					pathVector = manager.findPath(c1, c2); 
				if(pathVector.isEmpty())
					JOptionPane.showMessageDialog(null, "There is no path");
			}

			///////////////////////////////
			if(e.getSource() == cmdClearPath)
				pathVector = null;
			if(e.getSource() == cmdClearMap){				
				pathVector = null;
				manager = new CitiesMap();}
			repaint();
		}
	}
}

