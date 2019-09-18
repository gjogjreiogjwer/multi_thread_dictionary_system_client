package saul.representation;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import saul.service.SendRequest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@Log4j2
public class MainFrame {
	private SendRequest sendRequest;
	private @Getter TextArea resultText;
	public MainFrame(SendRequest sendRequest) {
		this.sendRequest = sendRequest;
		resultText = new TextArea("", 5, 30, TextArea.SCROLLBARS_VERTICAL_ONLY );
	}
	
	public void start() {
        JFrame f = new JFrame("dictionary query client");
        f.setSize(500,300);
        f.setLocation(200,100);
        f.setLayout(null);
   
        TextField textField = new TextField();
    	textField.setBounds(100, 60, f.getWidth()*7/10, 50);
		textField.setBackground(Color.white);
		f.add(textField);
		
		resultText.setBounds(100, 120, f.getWidth()*7/10, 50);
		resultText.setEditable(false);
		resultText.setText("notice: when you add word, using \",\" to separate \nword and meaning.");
		resultText.setForeground(Color.red);
		

		JLabel lab2 = new JLabel("Input: "); 
		lab2.setBounds(45, 60, 50, 50);
		lab2.setFont(new Font("Dialog",Font.BOLD, 12) );
		
		JLabel lab3 = new JLabel("Result: "); 
		lab3.setBounds(45, 120, 50, 50);
		lab3.setFont(new Font("Dialog",Font.BOLD, 12));
		
		JLabel lab4 = new JLabel("Dictionary Query System"); 
		lab4.setBounds(145, 10, 210, 40);
		lab4.setFont(new Font("Dialog",Font.BOLD, 15));
		lab4.setForeground(Color.black);
	
		f.add(resultText);
		f.add(lab2);
		f.add(lab3);
		f.add(lab4);

		
        MyButtonListener myActionListener = new MyButtonListener(textField, sendRequest, resultText);//创建一个按钮监听事件对象
        
        JButton jButton1 = new JButton("search");
        JButton jButton2 = new JButton("add");
        JButton jButton3 = new JButton("remove");
        JButton jButton4 = new JButton("clear");
        
        jButton1.setBounds(35, 200, 100, 50);
        jButton2.setBounds(145, 200, 100, 50);
        jButton3.setBounds(255, 200, 100, 50);
        jButton4.setBounds(365, 200, 100, 50);
        
        f.add(jButton1);
        f.add(jButton2);
        f.add(jButton3);
        f.add(jButton4);
        jButton1.addActionListener(myActionListener); //添加myActionListener监听事件
        jButton2.addActionListener(myActionListener);
		jButton3.addActionListener(myActionListener);
		jButton4.addActionListener(myActionListener);
		
        f.addWindowListener(new WindowAdapter() {
        	@Override
            public void windowClosing(WindowEvent e) {
        		log.info("unconnect with server, client closed.");
                System.exit(0);
            }
        });
        f.setVisible(true);
        log.info("the client window start successfully.");
    }
}
