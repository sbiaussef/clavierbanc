import java.awt.*; 
import java.awt.event.*;
import java.util.Random;

import javax.swing.*; 

public class ClavierSecurise extends JFrame implements ActionListener 
{ 

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	int lower = 0;
	int higher = 9;
	int[] tab=new int[9];
	String[] sign={"1","2","3","4","5","6","7","8","9","0"};
	JButton[] button=new JButton[sign.length];
public ClavierSecurise() 
{ 
this.setBounds(500, 200, 400, 400);
this.setTitle("clavier securise");
this.setDefaultCloseOperation(EXIT_ON_CLOSE);


JPanel back = new JPanel(); 
JPanel clavier = new JPanel(); 
JPanel operation = new JPanel(); 
JPanel clavscien= new JPanel(); 
 
back.setLayout(new BorderLayout()); 
operation.setLayout(new GridLayout(4,1)); 
clavscien.setLayout(new GridLayout(2,1)); 
clavier.setLayout(new GridLayout(4,3)); 


clavier.setPreferredSize(new Dimension(200, 200));
clavscien.setPreferredSize(new Dimension(100, 150));

JButton ok = new JButton("ok" ); 
ok.addActionListener(this); 
JButton reset = new JButton("reset" ); 
reset.addActionListener(this); 


for (int i = 0; i < sign.length; i++) 
{
	button[i] = new JButton(sign[i]);
	clavier.add(button[i]);
	button[i].addActionListener(this);
}

clavscien.add(ok); 
clavscien.add(reset); 



back.add(operation,BorderLayout.WEST); 
back.add(clavier,BorderLayout.CENTER); 
back.add(clavscien,BorderLayout.EAST); 


this.setContentPane(back); 
this.setVisible(true); 
this.pack(); 
startShuffleTimer();
}
boolean click=false;
int counter=0;
@Override
public void actionPerformed(ActionEvent e) {
	if(e.getActionCommand().equals("reset"))
	{
		tab=RandomizeArray(lower, higher);
		for (int i = 0; i < tab.length; i++) 
		{
			button[i].setText(""+tab[i]);
		}
	}
} 

public int[] RandomizeArray(int a, int b){
	Random rgen = new Random();  // Random number generator		
	int size = b+1;
	int[] array = new int[size];

	for(int i=0; i< size; i++){
		array[i] = a+i;
	}

	for (int i=0; i<array.length; i++) {
	    int randomPosition = rgen.nextInt(array.length);
	    int temp = array[i];
	    array[i] = array[randomPosition];
	    array[randomPosition] = temp;
	}
	return array;


}
private void startShuffleTimer() {
	new Thread(() -> {
		try {
			while (true) {
				Thread.sleep(30000);
				tab=RandomizeArray(lower, higher);
				for (int i = 0; i < tab.length; i++) {
					button[i].setText(""+tab[i]);

				
				
				}
			}
		} catch (InterruptedException e) {
		}
	}).start();


}}
