	package it123;
	
	import javax.swing.*;
	
	import java.awt.*;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.awt.event.KeyAdapter;
	import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
	import java.util.Random;
	
	public class GameplayDisplay extends JPanel implements ActionListener{
		/**
		 * 
		 */
		private static final long serialVersionUID = 6319934147135521650L;
		
		//Stored data from the other class
		private String selectedCharacter;

		//Creates a strip generator object.
		private LandGenerator stripGen = new LandGenerator();
		//Holds Number of strips on screen.
		private int numOfStrips = 9;
		//2D array for holding sprite strips.
		private Sprite[][] allStrips = new Sprite[numOfStrips][12];
		//Holds the index values of special strip images.
		private ArrayList<Integer> special = new ArrayList<>();
		//Holds number of special images in special strip.
		private int land = 1, water = 0;
		//Array that holds the cars.
		private ArrayList<Sprite> cars = new ArrayList<>();
		//Array that holds the trains.
		private ArrayList<Sprite> trains = new ArrayList<>();
		
		private JButton back;
		private JLabel ctrl_screen;
		private JButton startButton;
		private JButton setting;
		private JButton continueButton;
		private JButton restart;
		private JButton restart2;
		private JButton controlbutton;
		private JButton exit;
		private JLabel gameover;
		private JLabel scoreboard;
		private JLabel guide;
		private JLabel story;
		private JButton invisiblebutton;
		private JButton invisiblebutton2;
		private JButton invisiblebutton3;
		private JLabel line2;
		private JLabel line3;
		
		private Vehicles vManager = new Vehicles();
		public Sprite cat;
		//Variable to hold score and travel.
		private int score = 0, movement = 0;
		//Variables for directional control.
		private int up = 0, down = 0, left = 0, right = 0;
		private boolean press = false;
		//Variables for hero invincibility power.
		private boolean invincibility = false;
		private int invDuration = 0, invTimeLeft = 0;
	
		//Create timer.
		public Timer gameLoop;
	
	    //Create random generator.
	    private Random rand = new Random();
	    
	   
	    
	    // Set the selected character
	    public void setCharacter(String selectedCharacter) {
		    this.selectedCharacter = selectedCharacter;
		}
		    
	    
		/**
		 * Default constructor.
		 */
	    GameplayDisplay(boolean pause, String selectedCharacter) {
	    	
	    	RunMusic.GameSound();
	    	
	    	
	    	// Set the hero sprite based on the selected character
	    	setCharacter(selectedCharacter);

	        if ("Black Cat".equals(selectedCharacter)) {
	            cat = new Sprite("/catblack/cat back.png");
	        } else if ("White Cat".equals(selectedCharacter)) {
	        	cat = new Sprite("/catwhite/catwhiteback.png");
	        } else if ("Orange Cat".equals(selectedCharacter)) {
	        	cat = new Sprite("/catorange/catorangeback.png");
	        }
	        
	        story = new JLabel("");
			story.setIcon(new ImageIcon(editt.class.getResource("/Kittysign/storycat1.png")));
			story.setBounds(-520, -300, 1594, 918);
			add(story);
			story.setVisible(true);
	    	
			line2 = new JLabel("");
			line2.setIcon(new ImageIcon(editt.class.getResource("/Kittysign/line2.png")));
			line2.setBounds(-520, -300, 1594, 918);
			add(line2);
			line2.setVisible(false);
		
			line3 = new JLabel("");
			line3.setIcon(new ImageIcon(editt.class.getResource("/Kittysign/line3.png")));
			line3.setBounds(-520, -300, 1594, 918);
			add(line3);
			line3.setVisible(false);
			
	    	setting = new JButton("");
			setting.setIcon(new ImageIcon(GameplayDisplay.class.getResource("/buttons/setting.png")));
			setting.setBounds(970, 0, 88, 100);
			setting.setContentAreaFilled(false);
	        setting.setBorderPainted(false);
	        setting.setFocusPainted(false); 
	        setting.setVisible(false);
			add(setting);
			setting.addActionListener(this);
			
			back = new JButton("");
			back.setIcon(new ImageIcon(editt.class.getResource("/buttons/back (1).png")));
			back.setContentAreaFilled(false);
			back.setBorderPainted(false);
			back.setFocusPainted(false);
			back.setVisible(false);
			back.setBounds(10, 11, 151, 36);
			add(back);
			back.addActionListener(this);
			
			ctrl_screen = new JLabel("");
			ctrl_screen.setIcon(new ImageIcon(editt.class.getResource("/background/ctrl-board.png")));
			ctrl_screen.setBounds(55, -28, 1178, 721);
			add(ctrl_screen);
			ctrl_screen.setVisible(false);
			
			startButton = new JButton("");
			startButton.setIcon(new ImageIcon(GameplayDisplay.class.getResource("/buttons/start.png")));
			setLayout(null);
			startButton.setContentAreaFilled(false);
			startButton.setBorderPainted(false);
			startButton.setFocusPainted(false); 
			startButton.setBounds(400, 175, 300, 200);
			add(startButton);
			startButton.setVisible(false);
			startButton.addActionListener(this);
			
			invisiblebutton = new JButton("");
			invisiblebutton.setBounds(0, 0, 992, 606);
			add(invisiblebutton);
			invisiblebutton.setBorderPainted(false);
			invisiblebutton.setFocusPainted(false);
			invisiblebutton.setContentAreaFilled(false);
			invisiblebutton.setVisible(true);
			invisiblebutton.addActionListener(this);
			
			invisiblebutton2 = new JButton("");
			invisiblebutton2.setBounds(0, 0, 992, 606);
			add(invisiblebutton2);
			invisiblebutton2.setBorderPainted(false);
			invisiblebutton2.setFocusPainted(false);
			invisiblebutton2.setContentAreaFilled(false);
			invisiblebutton2.setVisible(false);
			invisiblebutton2.addActionListener(this);
			
			invisiblebutton3 = new JButton("");
			invisiblebutton3.setBounds(0, 0, 992, 606);
			add(invisiblebutton3);
			invisiblebutton3.setBorderPainted(false);
			invisiblebutton3.setFocusPainted(false);
			invisiblebutton3.setContentAreaFilled(false);
			invisiblebutton3.setVisible(false);
			invisiblebutton3.addActionListener(this);
			
			
			continueButton = new JButton("");
			continueButton.setIcon(new ImageIcon(GameplayDisplay.class.getResource("/buttons/continueS.png")));
			continueButton.setBounds(430, 147, 238, 100);
			add(continueButton);
			continueButton.setContentAreaFilled(false);
			continueButton.setBorderPainted(false);
			continueButton.setFocusPainted(false);
			continueButton.setVisible(false);
			continueButton.addActionListener(this);
			continueButton.addMouseListener(new MouseAdapter() {

	            public void mouseEntered(MouseEvent e) {
	            	continueButton.setIcon(new ImageIcon(Main.class.getResource("/buttons/continueL.png")));
	            }

	            @Override
	            public void mouseExited(MouseEvent e) {
	            	continueButton.setIcon(new ImageIcon(Main.class.getResource("/buttons/continueS.png")));
	            }
	        });

			restart = new JButton("");
			restart.setIcon(new ImageIcon(GameplayDisplay.class.getResource("/buttons/restartS.png")));
			restart.setBounds(430, 230, 238, 100);
			add(restart);
			restart.setContentAreaFilled(false);
			restart.setBorderPainted(false);
			restart.setFocusPainted(false);
			restart.setVisible(false);
			
			restart.addActionListener(this);
			
			restart.addMouseListener(new MouseAdapter() {

	            public void mouseEntered(MouseEvent e) {
	            	restart.setIcon(new ImageIcon(Main.class.getResource("/buttons/restartL.png")));
	            }

	            @Override
	            public void mouseExited(MouseEvent e) {
	            	restart.setIcon(new ImageIcon(Main.class.getResource("/buttons/restartS.png")));
	            }
	        });
			
			restart2 = new JButton("");
			restart2.setIcon(new ImageIcon(GameplayDisplay.class.getResource("/buttons/restartS.png")));
			restart2.setBounds(435, 270, 238, 100);
			add(restart2);
			restart2.setContentAreaFilled(false);
			restart2.setBorderPainted(false);
			restart2.setFocusPainted(false);
			restart2.setVisible(false);
			
			restart2.addActionListener(this);
			
			restart2.addMouseListener(new MouseAdapter() {

	            public void mouseEntered(MouseEvent e) {
	            	restart2.setIcon(new ImageIcon(Main.class.getResource("/buttons/restartL.png")));
	            }

	            @Override
	            public void mouseExited(MouseEvent e) {
	            	restart2.setIcon(new ImageIcon(Main.class.getResource("/buttons/restartS.png")));
	            }
	        });
			
			controlbutton = new JButton("");
			controlbutton.setIcon(new ImageIcon(GameplayDisplay.class.getResource("/buttons/controlsS.png")));
			controlbutton.setBounds(430, 330, 238, 100);
			add(controlbutton);
			controlbutton.setContentAreaFilled(false);
			controlbutton.setBorderPainted(false);
			controlbutton.setFocusPainted(false);
			controlbutton.setVisible(false);
			controlbutton.addActionListener(this);
			controlbutton.addMouseListener(new MouseAdapter() {

	            public void mouseEntered(MouseEvent e) {
	            	controlbutton.setIcon(new ImageIcon(Main.class.getResource("/buttons/controlsL.png")));
	            }

	            @Override
	            public void mouseExited(MouseEvent e) {
	            	controlbutton.setIcon(new ImageIcon(Main.class.getResource("/buttons/controlsS.png")));
	            }
	        });
			
			
			exit = new JButton("");
			exit.setIcon(new ImageIcon(GameplayDisplay.class.getResource("/buttons/exitS.png")));
			exit.setBounds(430, 420, 238, 100);
			add(exit);
			exit.setContentAreaFilled(false);
			exit.setBorderPainted(false);
			exit.setFocusPainted(false);
			exit.setVisible(false);
			exit.addActionListener(this);
			exit.addMouseListener(new MouseAdapter() {

	            public void mouseEntered(MouseEvent e) {
	            	exit.setIcon(new ImageIcon(Main.class.getResource("/buttons/exitL.png")));
	            }

	            @Override
	            public void mouseExited(MouseEvent e) {
	            	exit.setIcon(new ImageIcon(Main.class.getResource("/buttons/exitS.png")));
	            }
	        });
			
			

			gameover = new JLabel("");
			gameover.setIcon(new ImageIcon(GameplayDisplay.class.getResource("/buttons/gameover.png")));
			setLayout(null);
			gameover.setBounds(245, 10, 666, 375);
			add(gameover);
			gameover.setVisible(false);

			guide = new JLabel("");
			guide.setIcon(new ImageIcon(GameplayDisplay.class.getResource("/buttons/guide.png")));
			setLayout(null);
			guide.setBounds(122, 40, 800, 500);
			add(guide);
			guide.setVisible(false);

			scoreboard = new JLabel("");
	        scoreboard.setIcon(new ImageIcon(GameplayDisplay.class.getResource("/scoreboard/board.png")));
	        scoreboard.setBounds(6, 10, 250, 200);
	        add(scoreboard);
	        scoreboard.setVisible(false);

	        //Set the focus to JPanel.
			setFocusable(true);
			
			//Create key listener for character.
			addKeyListener(new KeyPressing());
			
			//Make the movement smooth.
			setDoubleBuffered(true);
	
			//Method to set the sprite locations.
			setInitialLocations();
			
			//Create the game timer and start it.
			gameLoop = new Timer(25, this);
	
			///Pauses the game on first run.
			
			
			if (!pause) {
				if(!invisiblebutton.isVisible()) {
					startButton.setVisible(true);
				}
				gameLoop.stop();
			
				
		}
			
		}
	    

	
		/**
		 * Method to set the initial location of all the sprites.
		 */
		private void setInitialLocations() {
	
			 // Sets the cat's location.
			cat.setXLoc(560);
		    cat.setYLoc(400);

		    // Initializes game with land strips.
		    for (int i = 0; i < numOfStrips; i++) {

				//Creates a new land sprite strip.
				Sprite[] strip = stripGen.getLandStrip();
				

				//Adds sprite strip to strips array.
				allStrips[i] = strip;
			}

			//Sets a grass image under and in front of the cats location.
			//(Prevents the cat from starting on a tree or shrub)
			allStrips[5][3].setImage("/Misc/Grass.png");
			allStrips[4][3].setImage("/Misc/Grass.png");	
			
			/*
			 * Sets the location for the sprites in the strip array.
			 */
			//Spaces sprites 100 pixels apart horizontally.
			int x = 0;
			//Spaces sprites 100 pixels apart vertically.
			int y = -100;

			for (int i = 0; i < numOfStrips; i++) {

				for (int z = 0; z < 12; z++) {

					allStrips[i][z].setXLoc(x);

					allStrips[i][z].setYLoc(y);

					x += 100;
				}
				x = 0;
				y += 100;
			}
			

			//Sets special array to first initialized land sprite array.
			//Prevents water/logs offset if it is generated right after the grass field.
			for (int i = 0; i < 12; i++) {
				if (allStrips[0][i].getFileName().equals("/Misc/Grass.png")) {
					special.add(i);
					land++;
				}
			}
		}
	
	
		/**
		 * Timer runs these statement on a loop.
		 */
		
		
		
		
		public void actionPerformed(ActionEvent e) {
			
			
	
			//Makes a new game if start button is clicked.
			if (e.getSource() == startButton) {
				
				
			    newGame();
				setting.setVisible(true);
				startButton.setVisible(false);
				story.setVisible(false);
				
	
			}
			else if (e.getSource() == setting) {
				
				RunMusic.stopGameSound();
				gameLoop.stop();
				continueButton.setVisible(true);
				restart.setVisible(true);
				controlbutton.setVisible(true);
				exit.setVisible(true);
	
			}
			
			else if (e.getSource() == continueButton) {
				
				RunMusic.resumeGameSound();
				continueButton.setVisible(false);
				restart.setVisible(false);
				controlbutton.setVisible(false);
				exit.setVisible(false);
				gameLoop.start();
	
			}
			else if (e.getSource() == restart) {
				
				RunMusic.GameSound();
				setting.setVisible(true);
				continueButton.setVisible(false);
				gameover.setVisible(false);
				restart.setVisible(false);
				controlbutton.setVisible(false);
				exit.setVisible(false);
				
				newGame();
	
			}
			else if (e.getSource() == restart2) {
				
				RunMusic.GameSound();
				setting.setVisible(true);
				continueButton.setVisible(false);
				gameover.setVisible(false);
				restart2.setVisible(false);
				controlbutton.setVisible(false);
				exit.setVisible(false);
				
				newGame();
	
			}
			
			else if (e.getSource() == controlbutton) {
				ctrl_screen.setVisible(true);
				back.setVisible(true);
			}
			else if (e.getSource() == back) {
				ctrl_screen.setVisible(false);
				back.setVisible(false);
			}
			else if(e.getSource() == invisiblebutton) {
				line2.setVisible(true);
				invisiblebutton2.setVisible(true);
				story.setVisible(false);
				invisiblebutton.setVisible(false);
			}
			else if(e.getSource() == invisiblebutton2) {
				line3.setVisible(true);
				invisiblebutton3.setVisible(true);
				line2.setVisible(false);
				invisiblebutton2.setVisible(false);
			}
			else if(e.getSource() == invisiblebutton3) {
				startButton.setVisible(true);
				line3.setVisible(false);
				invisiblebutton3.setVisible(false);
			}
			else if (e.getSource() == exit) {
				
				
			    int response = JOptionPane.showOptionDialog(
			            this,
			            "Are you sure you want to go back to the main menu?",
			            "Confirmation",
			            JOptionPane.YES_NO_OPTION,
			            JOptionPane.QUESTION_MESSAGE,
			            null,
			            new Object[]{"Yes", "No"},
			            "No"
			    );

			    if (response == JOptionPane.YES_OPTION) {
			    	
			        JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
			        currentFrame.dispose();
			        Main.main(null);
			    }
			}
			
			
			
			//Runs the timer.
			else {
	
				//Method that prevents hero from moving onto 
				//trees and checks for death and invincibility.
				heroBounds();
	
				//Sprite method that moves the hero.
				cat.move();
				
				//Method to smoothly move the character one block.
				movecat();
	
	
				//Method to move cars.
				manageCars();
	
				//Method to move trains.
				manageTrains();
	
	
				//Moves all the sprites in the sprite strips.
				for (int i = 0; i < numOfStrips; i++) {
					for (int x = 0; x < 12; x++) {
						allStrips[i][x].move();
					}
				}
	
				//Method that resets the strips.
				manageStrips();
	
	
				//Method to set the scrolling speed.
				scrollScreen();
	
				//Assigns farthest travel to score.
				if (movement > score) {
					score = movement;
	
				}
				//Redraws sprites on screen.
				repaint();
	
				
			}
		}
	
	
	
		/**
		 * Method that starts a new game.
		 */
		private void newGame() {
			trains.clear();
			cars.clear();
			 
			// Reset or clear game-related data and variables here
		    score = 0;
		    movement = 0;
		    up = down = left = right = 0;

		    setInitialLocations();

		    // Reset invincibility
		    invincibility = false;
		    invDuration = 0;
		    invTimeLeft = 0;


		    // Restart the game loop timer
		    gameLoop.start();

		 }

	    /**
	     * Method to end game.
	     * Stops loop and displays message.
	     */
		 private void killMsg(String killer) {
    	
		        repaint();
		        gameLoop.stop();

		        //Displays correct message based on death.
		        switch (killer) {
		        
		            case "water":
		            	RunMusic.stopGameSound();
		            	RunMusic.SoundNoLoop("Sounds/catdrown.wav");
		            	JOptionPane.showMessageDialog(null, "You drowned!" + "\nScore: " + score);
		            	gameover.setVisible(true);
		            	restart2.setVisible(true);
		            	setting.setVisible(false);
		            	
		                break;
		                
		            case "tooFarDown":
		            	
		            	RunMusic.stopGameSound();
	            		RunMusic.SoundNoLoop("Sounds/gameover.wav");
		                JOptionPane.showMessageDialog(null, "You were trapped!" + "\nScore: " + score);
		                gameover.setVisible(true);
		                restart2.setVisible(true);
		                setting.setVisible(false);
		                
		                break;
		                
		            case "tooFarUp":
		            	RunMusic.stopGameSound();
	            		RunMusic.SoundNoLoop("Sounds/gameover.wav");
		                JOptionPane.showMessageDialog(null, "You left the game!" + "\nScore: " + score);
		                gameover.setVisible(true);
		                restart2.setVisible(true);
		                setting.setVisible(false);
		                
		                break;
		                
		            case "car":
		            	
		            	RunMusic.stopGameSound();
		            	RunMusic.SoundNoLoop("Sounds/cathitcar.wav");
		            	JOptionPane.showMessageDialog(null, "You got hit by a car!" + "\nScore: " + score);
		            	gameover.setVisible(true);
		            	restart2.setVisible(true);
		            	setting.setVisible(false);
		            	
		                break;
		                
		            case "train":
		            	RunMusic.stopGameSound();
		            	RunMusic.SoundNoLoop("Sounds/trainhit.wav");
		            	JOptionPane.showMessageDialog(null, "You got hit by a train!" + "\nScore: " + score);
		            	gameover.setVisible(true);
		            	restart2.setVisible(true);
		            	setting.setVisible(false);
		            	
		                break;
		        }   
		    }

		/**
		 * for death with water, train, or cars.
		 */
		private void heroBounds() {
	
			for (int i = 0; i < numOfStrips; i++) {
		        for (Sprite s : allStrips[i]) {
		                if (s.getFileName().equals("/Misc/Tree_One.png") || s.getFileName().equals("/Misc/Tree_Two.png")) {
		                    if (collision(cat, s)) {
		                        // Adjust hero's movement based on collision
		                    	adjustMovement(s);
		                    }
		                } else if (s.getFileName().equals("/Misc/Water.png")) {
		                    if (collision(cat, s)) {
		                    	
		                    		killMsg("water");
		                    		
		                    }
		                }
					//Ends game if user goes too far down.
					if (cat.getYLoc() > 620) {
	
						//Reset hero location.
						setInitialLocations();
	
						//Method to end game.
						killMsg("tooFarDown");
					}
	
					//Ends game if user goes too far up.
					if (cat.getYLoc() < -110) {
	
						//Reset hero location.
						setInitialLocations();
	
						//Method to end game.
						killMsg("tooFarUp");
					}
				}
			}
	
		}
		
		private void adjustMovement(Sprite obstacle) {
		    final int obstacleSize = 100;
		    final int proximityThreshold = 5;
		    final int nearThreshold = 125;
		    final int farThreshold = 20;

		    int obstacleBottom = obstacle.getYLoc() + obstacleSize;
		    int obstacleRight = obstacle.getXLoc() + obstacleSize;

		    int heroBottom = cat.getYLoc() + 100;
		    int heroRight = cat.getXLoc() + 100;

		    if (obstacleBottom - cat.getYLoc() < proximityThreshold && heroRight - obstacle.getXLoc() < nearThreshold && heroRight - obstacle.getXLoc() > farThreshold) {
		        up = 0;
		    } else if (heroBottom > obstacle.getYLoc() && heroRight - obstacle.getXLoc() < nearThreshold && heroRight - obstacle.getXLoc() > farThreshold) {
		        down = 0;
		    } else if (cat.getXLoc() - obstacleRight > -proximityThreshold && obstacleBottom - cat.getYLoc() < nearThreshold && obstacleBottom - cat.getYLoc() > farThreshold) {
		        left = 0;
		    } else if (obstacle.getXLoc() - heroRight > -proximityThreshold && heroBottom - obstacle.getYLoc() < nearThreshold && heroBottom - obstacle.getYLoc() > farThreshold) {
		        right = 0;
		    }
		}
		
	
		/**
		 * Moves the character one strip forward or
		 * one strip backwards WITHOUT OFF-SETTING THE
		 * LOCATION DUE TO SCROLLING.
		 *
		 * Moves hero sprite smoothly by movement and not location.
		 * up,down,left,right : number of iterations.
		 * press : prevents over moving issue.
		 */
		private void movecat() {
	
			//Holds the hero's location.
			int location;
	
			//If left/right is pressed.
			if (left > 0 && press) {
				cat.setXDir(-12.5);
				left--;
				
				if("Black Cat".equals(selectedCharacter)) {
					cat.setImage("/catblack/catleft.png");
				}
				else if("White Cat".equals(selectedCharacter)) {
					cat.setImage("/catwhite/catwhiteleft.png");
				}
				else if ("Orange Cat".equals(selectedCharacter)) {
					
					cat.setImage("/catorange/catorangeleft.png");
				}
				
			} else if (right > 0 && press) {
				cat.setXDir(12.5);
				right--;
				
				
				if("Black Cat".equals(selectedCharacter)) {
					cat.setImage("/catblack/catright.png");
				}
				else if ("White Cat".equals(selectedCharacter)) {
					
					cat.setImage("/catwhite/catwhiteright.png");
					
				}
				else if ("Orange Cat".equals(selectedCharacter)) {
					
					cat.setImage("/catorange/catorangeright.png");
				}
			} else if (left == 0 && right == 0 && up == 0 && down == 0) {
				cat.setXDir(0);
				press = false;
			}
	
	
			//If up is pressed.
			if (up > 0 && press) {
	
				//Set hero speed.
				cat.setYDir(-10);
				cat.move();
				
				if(("Black Cat".equals(selectedCharacter))) {
					cat.setImage("/catblack/cat back.png");
				}
				else if(("White Cat".equals(selectedCharacter))) {
					cat.setImage("/catwhite/catwhiteback.png");
					}
				else if(("Orange Cat".equals(selectedCharacter))) {
					cat.setImage("/catorange/catorangeback.png");
					}
				
				//Get hero Y location.
				location = cat.getYLoc();
	
				//Sets the hero's location up one strip.
				for (int i = 0; i < numOfStrips; i++) {
	
					Sprite x = allStrips[i][0];
	
					//Aligns hero to strip after movement.
					if (location - x.getYLoc() > 95 && location - x.getYLoc() < 105) {
	
						cat.setYDir(0);
						up = 0;
						press = false;
	
						cat.setYLoc(x.getYLoc() + 100);
	
						//Increases travel keeper.
						movement++;
	
						i = numOfStrips;
					}
				}
			}
	
			//If down in pressed.
			else if (down > 0 && press) {
	
				//Set hero speed.
				cat.setYDir(10);
				cat.move();
				
				if(("Black Cat".equals(selectedCharacter))) {
					cat.setImage("/catblack/catfront.png");
				}
				else if(("White Cat".equals(selectedCharacter))) {
					cat.setImage("/catwhite/catwhitefront.png");
					
				}
				else if(("Orange Cat".equals(selectedCharacter))) {
					
					cat.setImage("/catorange/catorangefront.gif");
				}
				//Get hero location
				location = cat.getYLoc();
	
				//Sets the heros location down one strip.
				for (int i = 0; i < numOfStrips; i++) {
	
					Sprite x = allStrips[i][0];
	
					//Align hero to strip after movement.
					if (location - x.getYLoc() < -95 && location - x.getYLoc() > -105) {
	
						cat.setYDir(0);
						down = 0;
						press = false;
	
						cat.setYLoc(x.getYLoc() - 99);
	
	
						//Decreases travel keeper.
						movement--;
	
						i = numOfStrips;
					}
				}
			}
		}
		/**
		 * Method that:
		 * Moves cars.
		 * Removes cars passing Y bounds.
		 * Checks for car collisions.
		 * Note: foreach not working correctly.
		 */
		private void manageCars() {
	
			//Cycles through car sprites.
			for (int i = 0; i < cars.size(); i++) {
	
				Sprite car = cars.get(i);
	
	            //Removes cars passing Y bounds.
	            if (car.getYLoc() > 800) {
	                cars.remove(i);
	            }else {
	
	                //Moves car sprites.
	                car.move();
	
	
	                //Reset cars passing X bounds.
	                if (car.getXLoc() < -(rand.nextInt(700) + 400)){
	
	                    //Right to left.
	                    car.setXDir(-(rand.nextInt(7) + 7));
	
	                    car.setXLoc(900);
	
	                    car.setImage(vManager.randomCar("left"));
	                } else if (car.getXLoc() > (rand.nextInt(700) + 1100)) {
	
	                    //Left to right.
	                    car.setXDir((rand.nextInt(7) + 7));
	
	                    car.setXLoc(-200);
	
	                    car.setImage(vManager.randomCar("right"));
	                }
	            }
	
	
				//Checks for car collisions.
				if (collision(car, cat) && !invincibility) {
	
					//Method to end game.
					killMsg("car");
				}
			}
	
	
		}
	
		/**
		 * Method that:
		 * Moves trains.
		 * Removes trains passing Y bounds.
		 * Checks for train collisions.
		 */
		private void manageTrains() {
	
			//Cycles through car sprites.
			for (int i = 0; i < trains.size(); i++) {
	
	
				Sprite train = trains.get(i);
	
	
	            //Removes trains passing Y bounds.
	            if (train.getYLoc() > 800) {
	                trains.remove(i);
	            }else {
	
	                //Moves train sprites.
	                train.move();
	
	
	                //Reset X bounds.
	                if (train.getXLoc() < -(rand.nextInt(2500) + 2600)) {
	                    train.setXDir(-(rand.nextInt(10) + 10));
	
	                    train.setXLoc(900);
	
	                    train.setImage(vManager.randomTrain());
	                } else if (train.getXLoc() > rand.nextInt(2500) + 1800) {
	                    train.setXDir((rand.nextInt(10) + 10));
	
	                    train.setXLoc(-1500);
	
	                    train.setImage(vManager.randomTrain());
	                }
	
	            }
	
	
				//Checks for train collisions.
				if (collision(train, cat) && !invincibility) {
					
					
	
					//Method to end game.
					killMsg("train");
				}
			}
	
		}
	
		/**
		 * Method that correctly resets the strips.
		 */
		private void manageStrips() {
	
			//Blank strip test variables.
			int allWater;
			int allGrass;
	
	
			//Cycles through each strip.
			for (int v = 0; v < numOfStrips; v++) {
	
				//Checks if strip is out of bounds.
				if (allStrips[v][0].getYLoc() > 800) {
	
					//Generates a new strip.
					allStrips[v] = stripGen.getStrip();
	
	
					//Prevents an all water or grass strip.
					do {
						//Reset variables.
						allWater = 0;
						allGrass = 0;
	
						//Check sprites in strip.
						for (Sprite s : allStrips[v]) {
							if (s.getFileName().equals("/Misc/Water.png"))
								allWater++;
							if (s.getFileName().equals("/Misc/Grass.png"))
								allGrass++;
						}
	
						if (allWater == 12)
							allStrips[v] = stripGen.getWaterStrip();
						if (allGrass == 12)
							allStrips[v] = stripGen.getLandStrip();
	
	
					} while (allWater == 12 || allGrass == 12);
	
	
					//If there was previously a water strip, and this strip is a water strip, match this strips logs to the previous strip.
					if (water > 0) {
						if (allStrips[v][0].getFileName().equals("/Misc/Water.png") || allStrips[v][0].getFileName().equals("/Misc/Logs.png")) {
	
							water = 0;
	
							for (int i : special) {
								allStrips[v][i].setImage("/Misc/Logs.png");
							}
						}
					}
	
					//If there was previously a water strip, and this strip is a land strip, match the grass to the previous strips logs.
					if (water > 0) {
						if (allStrips[v][0].getFileName().equals("/Misc/Grass.png") || allStrips[v][0].getFileName().equals("/Misc/Shrub.png") ||
								allStrips[v][0].getFileName().equals("/Misc/Tree_One.png") || allStrips[v][0].getFileName().equals("/Misc/Tree_Two.png")) {
	
							allStrips[v] = stripGen.getSpecialLandStrip();
	
							water = 0;
	
							for (int i : special) {
								allStrips[v][i].setImage("/Misc/Grass.png");
								
							}
						}
					}
	
					//If there was previously a land strip, and this strip is a water strip, match the logs to the grass.
					if (land > 0) {
						if (allStrips[v][0].getFileName().equals("/Misc/Water.png") || allStrips[v][0].getFileName().equals("/Misc/Logs.png")) {
	
							land = 0;
	
							int val = 0;
	
							while (val == 0) {
	
								allStrips[v] = stripGen.getWaterStrip();
	
								for (int i = 0; i < 12; i++) {
									if (allStrips[v][i].getFileName().equals("/Misc/Logs.png")) {
	                                    for(int s : special){
	                                        if (i == s) {
	                                            val++;
	                                        }
	
	                                    }
									}
								}
							}
	
						}
					}
	
	
					//if there is a water strip, write down the index of the Logs.
					if (allStrips[v][0].getFileName().equals("/Misc/Water.png") || allStrips[v][0].getFileName().equals("/Misc/Logs.png")) {
	
						special.clear();
	
						water = 0;
	
						for (int i = 0; i < 12; i++) {
							if (allStrips[v][i].getFileName().equals("/Misc/Logs.png")) {
								special.add(i);
								water++;
							}
						}
					} else
						water = 0;
	
					//if there is a land strip, write down the index of the grass.
					if (allStrips[v][0].getFileName().equals("/Misc/Grass.png") || allStrips[v][0].getFileName().equals("/Misc/Shrub.png") ||
							allStrips[v][0].getFileName().equals("/Misc/Tree_One.png") || allStrips[v][0].getFileName().equals("/Misc/Tree_Two.png")) {
	
						special.clear();
	
						land = 0;
	
						for (int i = 0; i < 12; i++) {
							if (allStrips[v][i].getFileName().equals("/Misc/Grass.png")) {
								special.add(i);
								land++;
							}
						}
					}
	
	
					//Variable to reset horizontal strip location.
					int X = 0;
	
					//Reset the location of the strip.
					for (int i = 0; i < 12; i++) {
	
						allStrips[v][i].setYLoc(-99);
						allStrips[v][i].setXLoc(X);
	
						X += 100;
					}
	
					//Set car.
					if (allStrips[v][0].getFileName().equals("/Misc/Road.png")){
					    cars.add(vManager.setCar(allStrips[v][0].getYLoc() + 10));
					}
	
					//Set train.
	                if (allStrips[v][0].getFileName().equals("/Misc/Tracks.png")) {
	                    trains.add(vManager.setTrain(allStrips[v][0].getYLoc() + 10));
	                }
				}
			}
		}
	
	
	
		/**
		 * Scrolls the strips and the hero.
		 */
		private void scrollScreen() {
	
			//Cycles through strip array.
			for (int v = 0; v < numOfStrips; v++) {
				for (int x = 0; x < 12; x++) {
					allStrips[v][x].setYDir(2);
				}
			}
			//Sets scrolling if hero is not moving.
			if (!press) {
				cat.setYDir(2);
				
				
			}
		}
	
		/**
		 * Checks for sprite collisions.
		 */
		private boolean collision(Sprite one, Sprite two) {
	
			//Creates rectangles around sprites and checks for interesection.
			Rectangle first = new Rectangle(one.getXLoc(), one.getYLoc(), one.getWidth(), one.getHeight());
			Rectangle second = new Rectangle(two.getXLoc(), two.getYLoc(), two.getWidth(), two.getHeight());
	
			return first.intersects(second);
		}
	
		/**
		 * Draws graphics onto screen.
		 */
		public void paintComponent(Graphics g) {
	
			//Erases the previous screen.
			super.paintComponent(g);
			
			 
	
			//Draws strips.
			for (int i = 0; i < numOfStrips; i++) {
				for (int x = 0; x < 12; x++) {
					allStrips[i][x].paint(g, this);
				}
			}
	
			//Draw hero sprite.
			cat.paint(g, this);
	
			//Draw car sprites.
			for (Sprite s : cars)
				s.paint(g, this);
	
			//Draw train sprites.
			for (Sprite s : trains)
				s.paint(g, this);
	
			//Set the font size and color.
			Font currentFont = g.getFont();
			Font newFont = currentFont.deriveFont(currentFont.getSize() * 3f);
			g.setFont(newFont);
			g.setColor(Color.green);
	
			//Draws the high score on the screen.
	
			//Set the font size and color.
			scoreboard.paint(g);
			
			Font cF = g.getFont();
			Font nF = cF.deriveFont(cF.getSize() * 3f);
			g.setFont(nF);
			g.setColor(Color.white);
	
			//Draws the score on the screen.
			g.drawString("" + score, 152, 110);
	
	
			//Set the font size and color.
			Font CF = g.getFont();
			Font NF = CF.deriveFont(CF.getSize() * 1f);
			g.setFont(NF);
			g.setColor(Color.red);
			
			
			
	
			//Draws invincibility status.
			if (invincibility)
				g.drawString("" + invTimeLeft, 350, 350);
	
	
			//Stop stuttering (linux issue).
			Toolkit.getDefaultToolkit().sync();
			
			
		}
		
		
	
		/**
		 * Reads keyboard input for moving
		 * when key is pressed down.
		 */
		
		private class KeyPressing extends KeyAdapter {
	
			public void keyPressed(KeyEvent e) {
	
				switch (e.getKeyCode()) {
	
					//Moves hero within left and right bounds.
					case KeyEvent.VK_RIGHT:
						if (!press && cat.getXLoc() < 1059) {
							right = 8;
							press = true;
						}
						break;
					case KeyEvent.VK_LEFT:
						if (!press && cat.getXLoc() > 0) {
							left = 8;
							press = true;
						}
						break;
					case KeyEvent.VK_UP:
						if (!press) {
							up = 10;
							press = true;
							RunMusic.SoundNoLoop("Sounds/movesound.wav");
						}
						break;
					case KeyEvent.VK_DOWN:
						if (!press) {
							down = 10;
							press = true;
						}
						break;
					case KeyEvent.VK_ESCAPE:
						if (gameLoop.isRunning()) {
						gameLoop.stop();
						
						RunMusic.stopGameSound();
						continueButton.setVisible(true);
						restart.setVisible(true);
						controlbutton.setVisible(true);
						exit.setVisible(true);
						
						
						}
						else { 
							
							gameLoop.start();
							RunMusic.resumeGameSound();
							restart.setVisible(false);
							continueButton.setVisible(false);
							controlbutton.setVisible(false);
							exit.setVisible(false);
						}
						break;
						
				}
					
						
			}
				
	
			/**
			 * Reads keyboard for input for stopping
			 * when key is not pressed down.
			 */
			public void keyReleased(KeyEvent e) {
	
				switch (e.getKeyCode()) {
	
					case KeyEvent.VK_RIGHT:
						cat.setXDir(0);
						right = 0;
						break;
					case KeyEvent.VK_LEFT:
						cat.setXDir(0);
						left = 0;
						break;
					case KeyEvent.VK_UP:
						cat.setYDir(2);
						up = 0;
						break;
					case KeyEvent.VK_DOWN:
						cat.setYDir(2);
						down = 0;
						break;
				}
			}
		}
	}
	
	
