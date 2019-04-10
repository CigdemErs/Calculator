import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.TextField;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Calculator {

	public String operation="";
	
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calculator window = new Calculator();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Calculator() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 550, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				int x = JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit?",null, JOptionPane.YES_NO_OPTION);
				if(x == JOptionPane.YES_OPTION) {
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	            }
				else{
	                frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	            }
			}
		});
		
		frame.getContentPane().setLayout(new GridLayout(3, 1));
		
		JPanel top=new JPanel();
		top.setLayout(new GridLayout(2,1));
		frame.add(top);
		
		TextField display=new TextField();
		display.setBackground(Color.darkGray);
		top.add(display);
		
		display.addKeyListener(new KeyListener(){
            @Override
               public void keyPressed(KeyEvent e) {
                   
            		   int keyInt=e.getKeyCode();
            		   char ch='0';
                	   if(keyInt==KeyEvent.VK_0 || keyInt==KeyEvent.VK_NUMPAD0) {
                		   ch='0';
                	   }
                	   else if(keyInt==KeyEvent.VK_1|| keyInt==KeyEvent.VK_NUMPAD1) {
                		   ch='1';
                	   }
                	   else if(keyInt==KeyEvent.VK_2|| keyInt==KeyEvent.VK_NUMPAD2) {
                		   ch='2';
                	   }
                	   else if(keyInt==KeyEvent.VK_3|| keyInt==KeyEvent.VK_NUMPAD3) {
                		   ch='3';
                	   }
                	   else if(keyInt==KeyEvent.VK_4|| keyInt==KeyEvent.VK_NUMPAD4) {
                		   ch='4';
                	   }
                	   else if(keyInt==KeyEvent.VK_5|| keyInt==KeyEvent.VK_NUMPAD5) {
                		   ch='5';
                	   }
                	   else if(keyInt==KeyEvent.VK_6|| keyInt==KeyEvent.VK_NUMPAD6) {
                		   ch='6';
                	   }
                	   else if(keyInt==KeyEvent.VK_7|| keyInt==KeyEvent.VK_NUMPAD7) {
                		   ch='7';
                	   }
                	   else if(keyInt==KeyEvent.VK_8|| keyInt==KeyEvent.VK_NUMPAD8) {
                		   ch='8';
                	   }
                	   else if(keyInt==KeyEvent.VK_9|| keyInt==KeyEvent.VK_NUMPAD9) {
                		   ch='9';
                	   }
                	   else if(keyInt==KeyEvent.VK_SLASH) {
                		   ch='/';
                	   }
                	   else if(keyInt==KeyEvent.VK_X) {
                		   ch='X';
                	   }
                	   else if(keyInt==KeyEvent.VK_EQUALS) {
                		   ch='=';
                	   }
                	   else if(keyInt==KeyEvent.VK_PLUS) {
                		   ch='+';
                	   }
                	   else if(keyInt==KeyEvent.VK_MINUS) {
                		   ch='-';
                	   }
                	   else if(keyInt==KeyEvent.VK_PERIOD) {
                		   ch='.';
                	   }
                	   else if(keyInt==KeyEvent.VK_LEFT_PARENTHESIS) {
                		   ch='(';
                	   }
                	   else if(keyInt==KeyEvent.VK_RIGHT_PARENTHESIS) {
                		   ch=')';
                	   }
                	   if(keyInt==KeyEvent.VK_EQUALS) {
                		   if(parenthesisControl(operation)) {
               				double result=operation(operation);
               				display.setText(result+"");
               				operation="";
               				}
               				else {
               					JOptionPane error=new JOptionPane();
               					error.showMessageDialog(null, "****Error: Wrong use of paranthesis.");
               					JButton ok=new JButton("ok");
               					ok.addActionListener(l-> {
               						System.exit(0);
               					});
               					error.add(ok);
               					operation="";
               				}
                	   }
                	   else {
                		   operation=operation+ch;
                	   }
                        
            }

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
        });
		
		JPanel firstoperationler=new JPanel();
		firstoperationler.setLayout(new GridLayout(1,4));
		top.add(firstoperationler);
		
		JButton ac=new JButton("AC");
		ac.setBackground(Color.lightGray);
		ac.addActionListener(e-> {
			operation="";
			display.setText(operation);
			}
		);
		firstoperationler.add(ac); 
		
		JButton acParantez=new JButton("(");
		acParantez.setBackground(Color.lightGray);
		acParantez.addActionListener(e-> {
			operation=operation+"(";
			display.setText(operation);
			}
		);
		firstoperationler.add(acParantez);
		
		JButton close=new JButton(")");
		close.setBackground(Color.lightGray);
		close.addActionListener(e-> {
			if(operation.length()==0) {
				JOptionPane error=new JOptionPane();
				error.showMessageDialog(null, "****Error: Wrong place for this operation!");
				JButton ok=new JButton("ok");
				ok.addActionListener(l-> {
					System.exit(0);
				});
				error.add(ok);
				
			}
			else {
				operation=operation+")";
				display.setText(operation);
			}
		}
		);
		firstoperationler.add(close);
		
		JButton division=new JButton("/"); //divisionme isaretini klavyemde bulamadim.
		division.setBackground(Color.orange);
		division.addActionListener(e-> {
			if(operation.length()==0||!Character.isDigit(operation.charAt(operation.length()-1))) {
				JOptionPane error=new JOptionPane();
				error.showMessageDialog(null, "****Error: Wrong place for this operation!");
				JButton ok=new JButton("ok");
				ok.addActionListener(l-> {
					System.exit(0);
				});
				error.add(ok);
				
			}
			else {
				operation=operation+"/";
				display.setText(operation);
			}
			}
		);
		firstoperationler.add(division);
		
		JPanel middle=new JPanel();
		middle.setLayout(new GridLayout(2,1));
		frame.add(middle);
		
		JPanel topmiddle=new JPanel();
		topmiddle.setLayout(new GridLayout(1,4));
		middle.add(topmiddle);
		
		JButton seven=new JButton("7");
		seven.setBackground(Color.lightGray);
		seven.addActionListener(e-> {
			operation=operation+"7";
			display.setText(operation);
			}
		);
		topmiddle.add(seven);
		
		JButton eight=new JButton("8");
		eight.setBackground(Color.lightGray);
		eight.addActionListener(e-> {
			operation=operation+"8";
			display.setText(operation);
			}
		);
		topmiddle.add(eight);
		
		JButton nine=new JButton("9");
		nine.setBackground(Color.lightGray);
		nine.addActionListener(e-> {
			operation=operation+"9";
			display.setText(operation);
			}
		);
		topmiddle.add(nine);
		
		JButton multiplication=new JButton("X");
		multiplication.setBackground(Color.orange);
		multiplication.addActionListener(e-> {
			if(operation.length()==0||!Character.isDigit(operation.charAt(operation.length()-1))) {
				JOptionPane error=new JOptionPane();
				error.showMessageDialog(null, "****Error: Wrong place for this operation!");
				JButton ok=new JButton("ok");
				ok.addActionListener(l-> {
					System.exit(0);
				});
				error.add(ok);
				
			}
			else {
				operation=operation+"X";
				display.setText(operation);
			}
			}
		);
		topmiddle.add(multiplication);
		
		JPanel bottommiddle=new JPanel();
		bottommiddle.setLayout(new GridLayout(1,4));
		middle.add(bottommiddle);
		
		JButton four=new JButton("4");
		four.setBackground(Color.lightGray);
		four.addActionListener(e-> {
			operation=operation+"4";
			display.setText(operation);
			}
		);
		bottommiddle.add(four);
		
		JButton five=new JButton("5");
		five.setBackground(Color.lightGray);
		five.addActionListener(e-> {
			operation=operation+"5";
			display.setText(operation);
			}
		);
		bottommiddle.add(five);
		
		JButton bottomi=new JButton("6");
		bottomi.setBackground(Color.lightGray);
		bottomi.addActionListener(e-> {
			operation=operation+"6";
			display.setText(operation);
			}
		);
		bottommiddle.add(bottomi);
		
		JButton substraction=new JButton("-");
		substraction.setBackground(Color.orange);
		substraction.addActionListener(e-> {
			operation=operation+"-";
			display.setText(operation);
			}
		);
		bottommiddle.add(substraction);
		
		JPanel bottom=new JPanel();
		bottom.setLayout(new GridLayout(2,1));
		frame.add(bottom);
		
		JPanel topbottom=new JPanel();
		topbottom.setLayout(new GridLayout(1,4));
		bottom.add(topbottom);
		
		JButton one=new JButton("1");
		one.setBackground(Color.lightGray);
		one.addActionListener(e-> {
			operation=operation+"1";
			display.setText(operation);
			}
		);
		topbottom.add(one);
		
		JButton second=new JButton("2");
		second.setBackground(Color.lightGray);
		second.addActionListener(e-> {
			operation=operation+"2";
			display.setText(operation);
			}
		);
		topbottom.add(second);
		
		JButton three=new JButton("3");
		three.setBackground(Color.lightGray);
		three.addActionListener(e-> {
			operation=operation+"3";
			display.setText(operation);
			}
		);
		topbottom.add(three);
		
		JButton arti=new JButton("+");
		arti.setBackground(Color.orange);
		arti.addActionListener(e-> {
			if(operation.length()==0||!Character.isDigit(operation.charAt(operation.length()-1))) {
				JOptionPane error=new JOptionPane();
				error.showMessageDialog(null, "****Error: Wrong place for this operation!");
				JButton ok=new JButton("ok");
				ok.addActionListener(l-> {
					System.exit(0);
				});
				error.add(ok);
				
			}
			else {
				operation=operation+"+";
				display.setText(operation);
			}
			}
		);
		topbottom.add(arti);
		
		JPanel bottombottom=new JPanel();
		bottombottom.setLayout(new GridLayout(1,2));
		bottom.add(bottombottom);
		
		JButton sifir=new JButton("0");
		sifir.setBackground(Color.lightGray);
		sifir.addActionListener(e-> {
			if(operation.length()>0) {
				if(operation.charAt(operation.length()-1)=='/') {
					JOptionPane error=new JOptionPane();
					error.showMessageDialog(null, "****Error: Divide by zero!");
					JButton ok=new JButton("ok");
					ok.addActionListener(l-> {
						System.exit(0);
					});
					error.add(ok);
					
				}
				else
					operation=operation+"0";
			}
			else
				operation=operation+"0";
			display.setText(operation);
			}
		);
		bottombottom.add(sifir);
	
		JPanel bottomoperation=new JPanel();
		bottomoperation.setLayout(new GridLayout(1,2));
		bottombottom.add(bottomoperation);
		
		JButton nokta=new JButton(".");
		nokta.setBackground(Color.lightGray);
		nokta.addActionListener(e-> {
			if(operation.length()==0||!Character.isDigit(operation.charAt(operation.length()-1))) {
				JOptionPane error=new JOptionPane();
				error.showMessageDialog(null, "****Error: Wrong place for this operation!");
				JButton ok=new JButton("ok");
				ok.addActionListener(l-> {
					System.exit(0);
				});
				error.add(ok);
				
			}
			else {
				operation=operation+".";
				display.setText(operation);
			}
			}
		);
		bottomoperation.add(nokta);
		
		JButton equal=new JButton("=");
		equal.setBackground(Color.orange);
		equal.addActionListener(e->{
			if(parenthesisControl(operation)) {
				double result=operation(operation);
				display.setText(result+"");
				operation="";
			}
			else {
				JOptionPane error=new JOptionPane();
				error.showMessageDialog(null, "****Error: Wrong place for this operation!");
				JButton ok=new JButton("ok");
				ok.addActionListener(l-> {
					System.exit(0);
				});
				error.add(ok);
				operation="";
			}
		});
		bottomoperation.add(equal);
		
	}
	
	public double operation(String operation) {
		double result=0;
		ArrayList<String> newoperation=new ArrayList<>();
		String number="";
		
		for(int i=0;i<operation.length();i++) {
			if(Character.isDigit(operation.charAt(i))) {
				number=number+operation.charAt(i);
				if(i==operation.length()-1)
					newoperation.add(number);
			}
			else if(operation.charAt(i)=='-'){
				if(number.length()!=0)
					newoperation.add(number);
				number="-";
			}
			else if(operation.charAt(i)=='.') {
				number=number+operation.charAt(i);
			}
			else {
				if(number.length()!=0)
					newoperation.add(number);
				number="";
				newoperation.add(operation.charAt(i)+"");
			}
		}
		
		for(int i=newoperation.size()-1;i>=0;i--) {
			if(newoperation.get(i).equals("(")) {
				for(int j=i;j<newoperation.size();j++) {
					if(newoperation.get(j).equals(")")) {
							for(int k=i+1;k<j;k++) {
								if(newoperation.get(k).equals("X")) {
									double first=Double.parseDouble(newoperation.get(k-1));
									double second=Double.parseDouble(newoperation.get(k+1));
									result=first*second;
									newoperation.remove(k+1);
									newoperation.remove(k);
									newoperation.set(k-1, result+"");
									k=i+1;
									j=j-2;
								}
								
							}
							for(int k=i+1;k<j;k++) {
								if(newoperation.get(k).equals("/")) {
									double first=Double.parseDouble(newoperation.get(k-1));
									double second=Double.parseDouble(newoperation.get(k+1));
									result=first/second;
									newoperation.remove(k-1);
									newoperation.remove(k);
									newoperation.set(k-1, result+"");
									k=i+1;
									j=j-2;
								}
								
							}
							for(int k=i+1;k<j;k++) {
								if(newoperation.get(k).equals("+")) {
									double first=Double.parseDouble(newoperation.get(k-1));
									double second=Double.parseDouble(newoperation.get(k+1));
									result=first+second;
									newoperation.remove(k-1);
									newoperation.remove(k);
									newoperation.set(k-1, result+"");
									k=i+1;
									j=j-2;
								}
								
							}
							
							for(int k=i+1;k<j;k++) {
								if(newoperation.get(k).contains("-")) {
									double first=Double.parseDouble(newoperation.get(k-1));
									double second=Double.parseDouble(newoperation.get(k));
									result=first+second;
									newoperation.remove(k-1);
									newoperation.set(k-1, result+"");
									k=i+1;
									j=j-1;
								}
						
							
						}
						newoperation.remove(j);
						newoperation.remove(i);
						j=newoperation.size();
					}
					
				}
			}
		}
		
		for(int i=0;i<newoperation.size();i++) {
			if(newoperation.get(i).equals("X")) {
				double first=Double.parseDouble(newoperation.get(i-1));
				double second=Double.parseDouble(newoperation.get(i+1));
				result=first*second;
				newoperation.remove(i-1);
				newoperation.remove(i);
				newoperation.set(i-1, result+"");
				i=0;
			}
		}
		
		for(int i=0;i<newoperation.size();i++) {
			if(newoperation.get(i).equals("/")) {
				double first=Double.parseDouble(newoperation.get(i-1));
				double second=Double.parseDouble(newoperation.get(i+1));
				result=first/second;
				newoperation.remove(i-1);
				newoperation.remove(i);
				newoperation.set(i-1, result+"");
				i=0;
			}
		}
		
		for(int i=0;i<newoperation.size();i++) {
			if(newoperation.get(i).equals("+")) {
				double first=Double.parseDouble(newoperation.get(i-1));
				double second=Double.parseDouble(newoperation.get(i+1));
				result=first+second;
				newoperation.remove(i-1);
				newoperation.remove(i);
				newoperation.set(i-1, result+"");
				i=0;
			}
		}
		
		if(newoperation.size()>1) {
			for(int i=newoperation.size()-1;i>=0;i--) {
				if(newoperation.get(i).contains("-")) {
					double first=Double.parseDouble(newoperation.get(i-1));
					double second=Double.parseDouble(newoperation.get(i));
					result=first+second;
					newoperation.remove(i-1);
					newoperation.set(i-1, result+"");
					i=0;
				}
			}
		}
		
		result=Double.parseDouble(newoperation.get(0));
		
		return result;
	}
	
	public boolean parenthesisControl(String newoperation) {
		boolean correct=false;
		int count=0;
		String tmp=newoperation;
		
		for(int i=tmp.length()-1;i>=0;i--) {
			if(tmp.charAt(i)=='(') {
				count++;
				for(int j=i;j<tmp.length();j++) {
					if(tmp.charAt(j)==')') { 
						count--;
						tmp=tmp.substring(0,j)+tmp.substring(j+1);
						j=tmp.length();
					}
				}
			}
		}
		
		if(count==0)
			correct=true;
		
		return correct;
	}
}

	
