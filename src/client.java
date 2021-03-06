import java.rmi.*;
import java.util.*;
import javax.swing.*;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.*;

public class client extends JFrame implements ActionListener  {
	double n1 = 0.0;
	double n2 = 0.0;
	double d1;
	JButton butoane[] = new JButton[27];
	JTextField tf;
	Container con;
	int i;
	String button;
	String str;
	String num;
	JPanel tp,bp,lp;
	Interf serv=null;
	Interf_Generator Ob = null;
	public client( ){
		num="";
		Scanner sc = new Scanner(System.in);
		System.out.println("Adresa server: ");
		String url = "rmi://"+sc.next()+":1099/Ob";
		try{
			Ob = (Interf_Generator) Naming.lookup(url);
		} catch(Exception e){
			System.out.println("Failed to connect!");
			setVisible(false);
			System.exit(0);
			
		}
		try{
			serv= Ob.server_propriu();

		} catch(Exception e){
			setVisible(false);
			System.out.println("Doesn't connect");
	
		};
		setTitle("Calculator");
		tp = new JPanel();
		bp = new JPanel();
		lp = new JPanel();
		tf = new JTextField(22);
		tf.setEditable(false);
		con = getContentPane();
		bp.setLayout( new GridLayout(5,5));
		lp.setLayout( new GridLayout(1,2));
	
		tp.add(tf);
		con.add(tp,"North");
		butoane[0] = new JButton("M+");
		butoane[1] = new JButton("M-");
		butoane[2] = new JButton("Ms");
		butoane[3] = new JButton("Mr");
		butoane[4] = new JButton("Mc");
		
		butoane[5] = new JButton("7");
		butoane[6] = new JButton("8");
		butoane[7] = new JButton("9");
		butoane[8] = new JButton("+");
		butoane[9] = new JButton("-");
		
		butoane[10] = new JButton("4");
		butoane[11] = new JButton("5");
		butoane[12] = new JButton("6");
		butoane[13] = new JButton("*");
		butoane[14] = new JButton("/");
		
		butoane[15] = new JButton("1");
		butoane[16] = new JButton("2");
		butoane[17] = new JButton("3");
		butoane[18] = new JButton("x!");
		butoane[19] = new JButton("1/x");
		
		butoane[20] = new JButton("C");
		butoane[21] = new JButton("0");
		butoane[22] = new JButton(".");
		butoane[23] = new JButton("^");
		butoane[24] = new JButton("√");
		
		butoane[25] = new JButton("Clear");
		butoane[26] = new JButton("=");
		
		for (i = 0; i<27; ++i){
			butoane[i].addActionListener(this);
			if (i<25) bp.add(butoane[i]);
			else lp.add(butoane[i]);
			
		}
		con.add(bp,"Center");
		con.add(lp,"South");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
	public static void main(String[] args){
		JFrame f = new client();
		f.setSize(300,300);
		f.setVisible(true);
		
		
		
		
		
	}


	@Override
	public void actionPerformed(ActionEvent ae){
		
		// TODO Auto-generated method stub
		boolean ok = false; //it's a baby number!!
		
		str = ae.getActionCommand();
		//System.out.println(str);
		if (ae.getSource() == butoane[22]|| ae.getSource()==butoane[21]){
			//e .
			ok = true;
			num = num.concat(str);
			tf.setText(num);
		}else if (ae.getSource()==butoane[26]){
			//is ==
			ok = true;
	
			if (num=="") System.out.println("You need 2 operators, you only have one");
			else{
				
				tf.setText("");
				n1 = Double.parseDouble(num);
				//System.out.println(n1);
				//System.out.println(button);
				num = "";
				try{
					int ok1 = 1; // e ok
					if (button == "+")serv.add(n1);
					else if (button == "-" ) serv.substract(n1);
					else if (button == "*" ) serv.multiply(n1);
					else if (button == "/" ) ok1 = serv.divide(n1);
					else if (button == "^" ) ok1 = serv.power(n1);
					str = String.valueOf(serv.get_result());
					//System.out.println(serv.get_result());
					if (ok1 == 1){
						tf.setText(str);
						num = str;
					} else tf.setText("");
				}catch(RemoteException e){
					System.out.println("Remote Exception occured.");
				}
			}
			
		}else if (ae.getSource()==butoane[25]){
			// clear
	
			ok = true;
			tf.setText("");
			num ="";
			n1 = 0.0;
			n2 = 0.0;
			button = "";
			try{
				serv.set_state(0);
			}catch( RemoteException e){
				System.out.println("Remote Exception occured.");
			}
		
		
		}else if (ae.getSource()==butoane[20]){
			//e C
	
			ok = true;
			if (num!="")
				num = num.substring(0, num.length()-1);
			else if (button!="") button= "";
		
		
		}else if (ae.getSource() == butoane[18] || ae.getSource()==butoane[19]  || ae.getSource()==butoane[24] ){
			// unary operation
	
			ok = true;
			if (num!=""){
				//get the operand
				tf.setText("");
				n1 = Double.parseDouble(num);
				num = "";
				try{
					int ok1 = 1;
					serv.set_state(n1);
					if (ae.getSource() == butoane[18]) ok1 = serv.factorial();
					else if (ae.getSource() == butoane[19] ) ok1 = serv.invert();
					else if (ae.getSource() == butoane[24] ) ok1 = serv.radical();
					str = String.valueOf(serv.get_result());
					if (ok1==1){
					tf.setText(str);
					num = str;
					}else tf.setText("");
				}catch(RemoteException e){
					System.out.println("Remote Exception occured.");
				}
			}
		
		}else if (ae.getSource() == butoane[8] || ae.getSource()==butoane[9] || ae.getSource()==butoane[13] || ae.getSource()==butoane[14] || ae.getSource()==butoane[23]){
			//2 operators operation
				ok = true;	
	
				tf.setText("");
				n1 = Double.parseDouble(num);
				num ="";
				try{
					serv.set_state(n1);
					button = str;
				}catch(RemoteException e){
					System.out.println("Remote Exception occured.");
				}
		
		}else if (ae.getSource()==butoane[0] ||ae.getSource()==butoane[1] || ae.getSource()==butoane[2] || ae.getSource()==butoane[3] || ae.getSource()==butoane[4] ){
			//it's memory operation
			ok = true;
	
			if(num!=""){
				tf.setText("");
				n1 = Double.parseDouble(num);
				num = "";
			}
			
			//System.out.println(n1);
			int i = 0;
			for (int j = 0; j<5; ++j)
				if (ae.getSource() == butoane[j]){
					i = j;
					break;
				}
			
				switch(i) {
				case 0 :
					try{
						
						serv.mem_plus(n1);
						System.out.println(n1);
						double aux = serv.get_mem();
						str = String.valueOf(aux);
												
						tf.setText(str);
					}catch(RemoteException e){
						System.out.println("Remote Exception occured.");
					}
					break;
					
				case 1 :
					try{
						serv.mem_minus(n1);
						double aux = serv.get_mem();
						str = String.valueOf(aux);
						tf.setText(str);
					}catch(RemoteException e){
						System.out.println("Remote Exception occured.");
					}
					break;
				case 2:
					try{
						serv.mem_stock();
						double aux = serv.get_mem();
						str = String.valueOf(aux);
						tf.setText(str);
					}catch(RemoteException e){
						System.out.println("Remote Exception occured.");
					}
					break;
				case 3:
					try{
						//e m read
						n2 = serv.mem_read();
						num = String.valueOf(n2);
						tf.setText(num);
					}catch(RemoteException e){
						System.out.println("Remote Exception occured.");
					}
					break;
				case 4:
					try{
						serv.mem_reset();
					}catch(RemoteException e){
						System.out.println("Remote Exception occured.");
					}
				}
			
		}// close memory operations
		if (ok == false){
			for(int i =1; i<=3; ++i)
				if (ae.getSource()== butoane[5*i] || ae.getSource()==butoane[5*i+2] || ae.getSource()==butoane[5*i+1]){
					//it's a number
					
					if (num=="") num=str;
					else {
						
						
						num = num.concat(str);
					}
				
					tf.setText(num);
				}
			
		}
	}

}
