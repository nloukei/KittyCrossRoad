package it123;

import java.awt.CardLayout;
import java.awt.Color;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import javax.swing.border.EmptyBorder;



public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	
    CardLayout cardLayout;
    private JToggleButton blackcat;
    private JToggleButton whitecat;
    private JToggleButton orange;
    ButtonGroup catGroup = new ButtonGroup();
	private String selectedCharacter;
    
	
	/**
	 * Launch the application.
	 */
    public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Main frame = new Main();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
	/**
	 * Create the frame.
	
	 */
	public Main() {
		setResizable(false);	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1100, 658);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setLocationRelativeTo(null);
		setContentPane(contentPane);
		setTitle("Kitty Cross Road");
		
		blackcat = new JToggleButton();
        whitecat = new JToggleButton();
        orange = new JToggleButton();
        catGroup.add(blackcat);
        catGroup.add(whitecat);
        catGroup.add(orange);
		 
        RunMusic.MainSound("Sounds/musicgame.wav");
        
        cardLayout = new CardLayout();
        contentPane.setLayout(cardLayout);

        JPanel panel = new JPanel();
        contentPane.add(panel, "name_1633938216219500");
        panel.setLayout(null);

        JButton playbutton = new JButton("");

        playbutton.addMouseListener(new MouseAdapter() {

            public void mouseEntered(MouseEvent e) {
                playbutton.setIcon(new ImageIcon(Main.class.getResource("/playbuttomimg/playbutton2.png")));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                playbutton.setIcon(new ImageIcon(Main.class.getResource("/playbuttomimg/playbutton.png")));
            }
        });

        playbutton.setBackground(new Color(240, 240, 240));
        playbutton.setIcon(new ImageIcon(Main.class.getResource("/playbuttomimg/playbutton.png")));
        playbutton.setBounds(450, 487, 233, 106);
        playbutton.setOpaque(false);
        playbutton.setBorderPainted(false);
        playbutton.setFocusPainted(false);
        playbutton.setOpaque(false);
        playbutton.setContentAreaFilled(false);
        playbutton.setBorderPainted(false);
        panel.add(playbutton);

        JLabel mainsign = new JLabel("");
        mainsign.setIcon(new ImageIcon(Main.class.getResource("/Kittysign/signkitty.png")));
        mainsign.setBounds(167, 36, 780, 456);
        panel.add(mainsign);

        JLabel mainbackground = new JLabel("");
        mainbackground.setIcon(new ImageIcon(Main.class.getResource("/background/background.png")));
        mainbackground.setBounds(-4, -5, 1100, 626);
        panel.add(mainbackground);
        
        playbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	charapanel();
                cardLayout.show(contentPane, "chara");                       
                RunMusic.stopMainSound();
                RunMusic.SoundNoLoop("Sounds/soundplay.wav");     
             }
         });
        setContentPane(contentPane);	

    }
	public void charapanel() {
		JPanel menu = new JPanel();
        menu.setBackground(new Color(255, 255, 255));
        contentPane.add(menu, "chara");
        menu.setLayout(null);
   
        
        JLabel arrow = new JLabel("");
        arrow.setIcon(new ImageIcon(Main.class.getResource("/charalogo/arrow.png")));
        arrow.setBounds(241, 168, 108, 124);
        arrow.setVisible(false);
        menu.add(arrow);
        
        JLabel arrow2 = new JLabel("");
        arrow2.setIcon(new ImageIcon(Main.class.getResource("/charalogo/arrow.png")));
        arrow2.setBounds(499, 168, 108, 122);
        arrow2.setVisible(false);
        menu.add(arrow2);
        
        JLabel arrow3 = new JLabel("");
        arrow3.setIcon(new ImageIcon(Main.class.getResource("/charalogo/arrow.png")));
        arrow3.setBounds(750, 168, 108, 130);
        arrow3.setVisible(false);
        menu.add(arrow3);
        
        JButton select = new JButton();
        select.setBounds(436, 442, 228, 89);
        select.setIcon(new ImageIcon(Main.class.getResource("/charalogo/select.png")));
        select.setContentAreaFilled(false);
        select.setFocusPainted(false);
        select.setBorder(null);
        select.setVisible(false);
        menu.add(select);
        
        select.addMouseListener(new MouseAdapter() {

            public void mouseEntered(MouseEvent e) {
            	select.setIcon(new ImageIcon(Main.class.getResource("/charalogo/selectL.png")));
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	select.setIcon(new ImageIcon(Main.class.getResource("/charalogo/select.png")));
            }
        });
	 
        blackcat = new JToggleButton();
        blackcat.setBounds(230, 280, 127, 130);
        blackcat.setIcon(new ImageIcon(Main.class.getResource("/catblack/blackcatgif.gif")));
        blackcat.setContentAreaFilled(false);
        blackcat.setBorderPainted(false);
        blackcat.setFocusPainted(false);   
        menu.add(blackcat);

        whitecat = new JToggleButton();
        whitecat.setBounds(487, 280, 127, 130);
        whitecat.setIcon(new ImageIcon(Main.class.getResource("/catwhite/whitecatgif.gif")));
        whitecat.setContentAreaFilled(false);
        whitecat.setBorderPainted(false);
        whitecat.setFocusPainted(false);
        menu.add(whitecat);

        orange = new JToggleButton();
        orange.setBounds(740, 280, 127, 130);
        orange.setIcon(new ImageIcon(Main.class.getResource("/catorange/orangegif.gif")));
        orange.setContentAreaFilled(false);
        orange.setBorderPainted(false);
        orange.setFocusPainted(false);
        menu.add(orange);
        
        
        ActionListener toggleActionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Deselect other toggle buttons
                if (e.getSource() == blackcat) {
                	RunMusic.SoundNoLoop("Sounds/meowblack.wav");
                    whitecat.setSelected(false);
                    orange.setSelected(false);
                } else if (e.getSource() == whitecat) {
                	RunMusic.SoundNoLoop("Sounds/catwhitesound.wav");
                    blackcat.setSelected(false);
                    orange.setSelected(false);
                } else if (e.getSource() == orange) {
                	RunMusic.SoundNoLoop("Sounds/catorangesound.wav");
                    blackcat.setSelected(false);
                    whitecat.setSelected(false);
                }

              
                arrow.setVisible(blackcat.isSelected());
                arrow2.setVisible(whitecat.isSelected());
                arrow3.setVisible(orange.isSelected());
                select.setVisible(blackcat.isSelected() || whitecat.isSelected() || orange.isSelected());
            }
        };  
        blackcat.addActionListener(toggleActionListener);
        whitecat.addActionListener(toggleActionListener);
        orange.addActionListener(toggleActionListener);
        
        select.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Check which character is selected and store the information
                if (blackcat.isSelected()) {
                	selectedCharacter = "Black Cat";
                } else if (whitecat.isSelected()) {
                	selectedCharacter = "White Cat";
                } else if (orange.isSelected()) {
                	selectedCharacter = "Orange Cat";
                }

                // Now 'selectedCharacter' contains the name of the selected character
                // You can use this information as needed, for example, pass it to the 'gameplay()' method
                RunMusic.stopMainSound();
                gameplay();
            }
        });
        
        
        JLabel choose = new JLabel("");
        choose.setIcon(new ImageIcon(Main.class.getResource("/charalogo/kittylabel.png")));
        choose.setBounds(370, 56, 562, 167);
        menu.add(choose);
        
        
        JLabel charalogo = new JLabel();
        charalogo.setIcon(new ImageIcon(Main.class.getResource("/charalogo/charalog.png")));
        charalogo.setBounds(90, 10, 935, 585);
        menu.add(charalogo);
        
        
        
        JLabel background = new JLabel();
        background.setIcon(new ImageIcon(Main.class.getResource("/background/background.png")));
        background.setBounds(-4, -5, 1100, 626);
        menu.add(background);
        
        
	}
	
	
	public void gameplay() {
		
		GameplayDisplay play = new GameplayDisplay(false, selectedCharacter);
	    contentPane.add(play, "gameplay");
	    cardLayout.show(contentPane, "gameplay");
		
      
	}
	
}
	