import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;
import javax.swing.Timer;

import java.awt.Panel;
import javax.swing.border.TitledBorder;
import javax.swing.Box;

public class Window {

	public JFrame frame;
	private JLabel timeRemaining;
	private JLabel redScore;
	private JLabel blueScore;
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 640, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		PowerupQueue powerUpQueue = new PowerupQueue();
		
		redScore = new JLabel("0");
		redScore.setBounds(37, 22, 61, 16);
		redScore.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		redScore.setForeground(Color.RED);
		frame.getContentPane().add(redScore);

		blueScore = new JLabel("0");
		blueScore.setBounds(127, 22, 61, 16);
		blueScore.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		blueScore.setForeground(Color.BLUE);
		frame.getContentPane().add(blueScore);

		JLabel lblRed = new JLabel("Red");
		lblRed.setBounds(37, 6, 61, 16);
		frame.getContentPane().add(lblRed);

		JLabel lblBlue = new JLabel("Blue");
		lblBlue.setBounds(127, 6, 61, 16);
		frame.getContentPane().add(lblBlue);

		{
			JButton redSwitchTop = new JButton("0");
			redSwitchTop.setBounds(175, 106, 73, 73);

			redSwitchTop.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					GameState.getRedSwitch().addBlock(new Block(), Side.TOP);

					redSwitchTop.setText(GameState.getRedSwitch().getPartialScore(Side.TOP));
				}
			});

			JButton redSwitchBottom = new JButton("0");
			redSwitchBottom.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					GameState.getRedSwitch().addBlock(new Block(), Side.BOTTOM);

					redSwitchBottom.setText(GameState.getRedSwitch().getPartialScore(Side.BOTTOM));
				}
			});
			redSwitchBottom.setBounds(179, 216, 73, 73);

			if (Side.TOP == GameState.getRedSwitch().litSide()) {
				redSwitchTop.setOpaque(true);
				redSwitchBottom.setOpaque(true);
				redSwitchTop.setBackground(Color.RED);
				redSwitchBottom.setBackground(Color.BLUE);
			} else {
				redSwitchBottom.setOpaque(true);
				redSwitchBottom.setBackground(Color.RED);
				redSwitchTop.setOpaque(true);
				redSwitchTop.setBackground(Color.BLUE);
			}

			frame.getContentPane().add(redSwitchTop);
			frame.getContentPane().add(redSwitchBottom);

			JButton scaleTop = new JButton("0");
			scaleTop.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					GameState.getScale().addBlock(new Block(), Side.TOP);

					scaleTop.setText(Integer.toString(GameState.getScale().getTopScore()));
				}
			});
			scaleTop.setOpaque(true);
			scaleTop.setBackground(GameState.getScale().redIsTop() ? Color.RED : Color.BLUE);
			scaleTop.setForeground(Color.BLACK);
			scaleTop.setBounds(260, 130, 117, 70);
			frame.getContentPane().add(scaleTop);

			JButton scaleBottom = new JButton("0");
			scaleBottom.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					GameState.getScale().addBlock(new Block(), Side.BOTTOM);
					scaleBottom.setText(Integer.toString(GameState.getScale().getBottomScore()));
				}
			});
			scaleBottom.setOpaque(true);
			scaleBottom.setBackground(GameState.getScale().redIsTop() ? Color.BLUE : Color.RED);

			scaleBottom.setForeground(Color.BLACK);
			scaleBottom.setBounds(260, 202, 117, 70);
			frame.getContentPane().add(scaleBottom);

			JPanel boostPanel = new JPanel();
			boostPanel.setBorder(new TitledBorder(null, "BOOST", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			boostPanel.setBounds(0, 133, 157, 53);
			frame.getContentPane().add(boostPanel);
			boostPanel.setLayout(null);

			JButton vaultRedBoost1 = new JButton("X");
			vaultRedBoost1.setBounds(6, 18, 33, 29);
			boostPanel.add(vaultRedBoost1);

			JButton vaultRedBoost2 = new JButton("X");
			vaultRedBoost2.setBounds(40, 18, 33, 29);
			boostPanel.add(vaultRedBoost2);
			vaultRedBoost2.setEnabled(false);

			JButton vaultRedBoost3 = new JButton("X");
			vaultRedBoost3.setBounds(72, 18, 33, 29);
			boostPanel.add(vaultRedBoost3);
			vaultRedBoost3.setEnabled(false);

			JToggleButton redBoostAvailable = new JToggleButton("B");
			redBoostAvailable.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					redBoostAvailable.setEnabled(false);

					vaultRedBoost1.setEnabled(false);
					vaultRedBoost2.setEnabled(false);
					vaultRedBoost3.setEnabled(false);
					
					powerUpQueue.addPowerUp(Team.RED, VaultType.BOOST, GameState.getRedVault().getBoostCount());

//					if (GameState.getRedVault().getBoostCount() == 1) {
//						GameState.getRedSwitch().setBoosting(GameState.tenSeconds());
//					}
//
//					if (GameState.getRedVault().getBoostCount() == 2) {
//						GameState.getScale().setBoosting(GameState.tenSeconds());
//					}
//
//					if (GameState.getRedVault().getBoostCount() == 3) {
//						GameState.getScale().setBoosting(GameState.tenSeconds());
//						GameState.getRedSwitch().setBoosting(GameState.tenSeconds());
//					}

				}
			});
			redBoostAvailable.setBounds(111, 18, 40, 29);
			boostPanel.add(redBoostAvailable);
			redBoostAvailable.setEnabled(false);
			vaultRedBoost3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					vaultRedBoost3.setEnabled(false);
					redBoostAvailable.setEnabled(true);

					vaultRedBoost3.setOpaque(true);
					vaultRedBoost3.setBackground(Color.YELLOW);
					GameState.getRedVault().addBlock(VaultType.BOOST);
				}
			});
			vaultRedBoost2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					vaultRedBoost2.setEnabled(false);
					redBoostAvailable.setEnabled(true);

					vaultRedBoost3.setEnabled(true);
					vaultRedBoost2.setOpaque(true);
					vaultRedBoost2.setBackground(Color.YELLOW);
					GameState.getRedVault().addBlock(VaultType.BOOST);
				}
			});
			vaultRedBoost1.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					GameState.getRedVault().addBlock(VaultType.BOOST);
					redBoostAvailable.setEnabled(true);

					vaultRedBoost1.setEnabled(false);
					vaultRedBoost2.setEnabled(true);
					vaultRedBoost1.setOpaque(true);
					vaultRedBoost1.setBackground(Color.YELLOW);
				}
			});

			JPanel forcePanel = new JPanel();
			forcePanel.setBorder(new TitledBorder(null, "FORCE", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			forcePanel.setBounds(0, 235, 157, 54);
			frame.getContentPane().add(forcePanel);
			forcePanel.setLayout(null);

			JButton vaultRedForce1 = new JButton("X");
			vaultRedForce1.setBounds(6, 19, 33, 29);
			forcePanel.add(vaultRedForce1);

			JButton vaultRedForce3 = new JButton("X");
			vaultRedForce3.setBounds(72, 19, 33, 29);
			forcePanel.add(vaultRedForce3);
			vaultRedForce3.setEnabled(false);

			JButton vaultRedForce2 = new JButton("X");
			vaultRedForce2.setBounds(40, 19, 33, 29);
			forcePanel.add(vaultRedForce2);
			vaultRedForce2.setEnabled(false);

			JToggleButton redForceAvailable = new JToggleButton("F");
			redForceAvailable.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					redForceAvailable.setEnabled(false);

					vaultRedForce1.setEnabled(false);
					vaultRedForce2.setEnabled(false);
					vaultRedForce3.setEnabled(false);
					
					powerUpQueue.addPowerUp(Team.RED, VaultType.FORCE, GameState.getRedVault().getForceCount());


//					if (GameState.getRedVault().getForceCount() == 1) {
//						GameState.getRedSwitch().setForcing(GameState.tenSeconds());
//					}
//
//					if (GameState.getRedVault().getForceCount() == 2) {
//						GameState.getScale().setForcing(GameState.tenSeconds(), GameState.getScale().getRedSide());
//					}
//
//					if (GameState.getRedVault().getForceCount() == 3) {
//						GameState.getScale().setForcing(GameState.tenSeconds(), GameState.getScale().getRedSide());
//						GameState.getRedSwitch().setForcing(GameState.tenSeconds());
//					}

				}
			});

			redForceAvailable.setBounds(111, 18, 40, 29);
			forcePanel.add(redForceAvailable);
			redForceAvailable.setEnabled(false);
			vaultRedForce2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					vaultRedForce2.setEnabled(false);
					vaultRedForce3.setEnabled(true);
					vaultRedForce2.setOpaque(true);
					vaultRedForce2.setBackground(Color.YELLOW);

					GameState.getRedVault().addBlock(VaultType.FORCE);

				}
			});
			vaultRedForce3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					vaultRedForce3.setEnabled(false);
					redForceAvailable.setEnabled(true);
					vaultRedForce3.setOpaque(true);
					vaultRedForce3.setBackground(Color.YELLOW);
					GameState.getRedVault().addBlock(VaultType.FORCE);
				}
			});
			vaultRedForce1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					redForceAvailable.setEnabled(true);
					vaultRedForce1.setEnabled(false);
					vaultRedForce2.setEnabled(true);
					vaultRedForce1.setOpaque(true);
					vaultRedForce1.setBackground(Color.YELLOW);
					GameState.getRedVault().addBlock(VaultType.FORCE);
				}
			});

			JPanel levitatePanel = new JPanel();
			levitatePanel
					.setBorder(new TitledBorder(null, "LEVITATE", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			levitatePanel.setBounds(0, 185, 158, 53);
			frame.getContentPane().add(levitatePanel);
			levitatePanel.setLayout(null);

			JButton vaultRedLevitate1 = new JButton("X");
			vaultRedLevitate1.setBounds(6, 18, 33, 29);
			levitatePanel.add(vaultRedLevitate1);

			JButton vaultRedLevitate2 = new JButton("X");
			vaultRedLevitate2.setBounds(40, 18, 33, 29);
			levitatePanel.add(vaultRedLevitate2);
			vaultRedLevitate2.setEnabled(false);

			JButton vaultRedLevitate3 = new JButton("X");
			vaultRedLevitate3.setBounds(72, 18, 33, 29);
			levitatePanel.add(vaultRedLevitate3);
			vaultRedLevitate3.setEnabled(false);

			JToggleButton redLevitateAvailable = new JToggleButton("L");
			redLevitateAvailable.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					redLevitateAvailable.setEnabled(false);
					vaultRedLevitate1.setEnabled(false);
					vaultRedLevitate2.setEnabled(false);
					vaultRedLevitate3.setEnabled(false);

					if (GameState.getRedVault().getLevitateCount() == 3) {
						GameState.getRedHangar().setLevitating();
					}

				}
			});

			redLevitateAvailable.setBounds(112, 18, 40, 29);
			levitatePanel.add(redLevitateAvailable);
			redLevitateAvailable.setEnabled(false);
			vaultRedLevitate3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					redLevitateAvailable.setEnabled(true);
					vaultRedLevitate3.setEnabled(false);
					vaultRedLevitate3.setOpaque(true);
					vaultRedLevitate3.setBackground(Color.YELLOW);

					GameState.getRedVault().addBlock(VaultType.LEVITATE);
				}
			});
			vaultRedLevitate2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					vaultRedLevitate2.setEnabled(false);
					vaultRedLevitate3.setEnabled(true);
					vaultRedLevitate2.setOpaque(true);
					vaultRedLevitate2.setBackground(Color.YELLOW);
					GameState.getRedVault().addBlock(VaultType.LEVITATE);
				}
			});
			vaultRedLevitate1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					vaultRedLevitate1.setEnabled(false);
					vaultRedLevitate2.setEnabled(true);
					vaultRedLevitate1.setOpaque(true);
					vaultRedLevitate1.setBackground(Color.YELLOW);
					GameState.getRedVault().addBlock(VaultType.LEVITATE);
				}
			});

			timeRemaining = new JLabel("0");
			timeRemaining.setForeground(Color.ORANGE);
			timeRemaining.setFont(new Font("Lucida Grande", Font.BOLD, 13));
			timeRemaining.setBounds(545, 22, 61, 16);
			
			
			frame.getContentPane().add(timeRemaining);

			JLabel lblCountdown = new JLabel("Countdown");
			lblCountdown.setBounds(545, 6, 89, 16);
			frame.getContentPane().add(lblCountdown);

		}

		JButton blueSwitchTop = new JButton("0");
		blueSwitchTop.setBounds(392, 106, 73, 73);

		blueSwitchTop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				GameState.getBlueSwitch().addBlock(new Block(), Side.TOP);

				blueSwitchTop.setText(GameState.getBlueSwitch().getPartialScore(Side.TOP));
			}
		});

		JButton blueSwitchBottom = new JButton("0");
		blueSwitchBottom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameState.getBlueSwitch().addBlock(new Block(), Side.BOTTOM);

				blueSwitchBottom.setText(GameState.getBlueSwitch().getPartialScore(Side.BOTTOM));
			}
		});
		blueSwitchBottom.setBounds(389, 216, 73, 73);

		if (Side.TOP == GameState.getBlueSwitch().litSide()) {
			blueSwitchTop.setOpaque(true);
			blueSwitchBottom.setOpaque(true);
			blueSwitchTop.setBackground(Color.BLUE);
			blueSwitchBottom.setBackground(Color.RED);
		} else {
			blueSwitchBottom.setOpaque(true);
			blueSwitchBottom.setBackground(Color.BLUE);
			blueSwitchTop.setOpaque(true);
			blueSwitchTop.setBackground(Color.RED);
		}

		frame.getContentPane().add(blueSwitchTop);
		frame.getContentPane().add(blueSwitchBottom);

		JPanel blueBoostPanel = new JPanel();
		blueBoostPanel.setBorder(new TitledBorder(null, "BOOST", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		blueBoostPanel.setBounds(477, 133, 157, 53);
		frame.getContentPane().add(blueBoostPanel);
		blueBoostPanel.setLayout(null);

		JButton vaultBlueBoost1 = new JButton("X");
		vaultBlueBoost1.setBounds(6, 18, 33, 29);
		blueBoostPanel.add(vaultBlueBoost1);

		JButton vaultBlueBoost2 = new JButton("X");
		vaultBlueBoost2.setBounds(40, 18, 33, 29);
		blueBoostPanel.add(vaultBlueBoost2);
		vaultBlueBoost2.setEnabled(false);

		JButton vaultBlueBoost3 = new JButton("X");
		vaultBlueBoost3.setBounds(72, 18, 33, 29);
		blueBoostPanel.add(vaultBlueBoost3);
		vaultBlueBoost3.setEnabled(false);

		JToggleButton blueBoostAvailable = new JToggleButton("B");
		blueBoostAvailable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				blueBoostAvailable.setEnabled(false);

				vaultBlueBoost1.setEnabled(false);
				vaultBlueBoost2.setEnabled(false);
				vaultBlueBoost3.setEnabled(false);
				
				powerUpQueue.addPowerUp(Team.BLUE, VaultType.BOOST, GameState.getBlueVault().getBoostCount());

//				if (GameState.getBlueVault().getBoostCount() == 1) {
//					GameState.getBlueSwitch().setBoosting(GameState.tenSeconds());
//				}
//
//				if (GameState.getBlueVault().getBoostCount() == 2) {
//					GameState.getScale().setBoosting(GameState.tenSeconds());
//				}
//
//				if (GameState.getBlueVault().getBoostCount() == 3) {
//					GameState.getScale().setBoosting(GameState.tenSeconds());
//					GameState.getBlueSwitch().setBoosting(GameState.tenSeconds());
//				}

			}
		});
		blueBoostAvailable.setBounds(111, 18, 40, 29);
		blueBoostPanel.add(blueBoostAvailable);
		blueBoostAvailable.setEnabled(false);
		vaultBlueBoost3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vaultBlueBoost3.setEnabled(false);
				blueBoostAvailable.setEnabled(true);

				vaultBlueBoost3.setOpaque(true);
				vaultBlueBoost3.setBackground(Color.YELLOW);
				GameState.getBlueVault().addBlock(VaultType.BOOST);
			}
		});
		vaultBlueBoost2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vaultBlueBoost2.setEnabled(false);
				blueBoostAvailable.setEnabled(true);

				vaultBlueBoost3.setEnabled(true);
				vaultBlueBoost2.setOpaque(true);
				vaultBlueBoost2.setBackground(Color.YELLOW);
				GameState.getBlueVault().addBlock(VaultType.BOOST);
			}
		});
		vaultBlueBoost1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				GameState.getBlueVault().addBlock(VaultType.BOOST);
				blueBoostAvailable.setEnabled(true);

				vaultBlueBoost1.setEnabled(false);
				vaultBlueBoost2.setEnabled(true);
				vaultBlueBoost1.setOpaque(true);
				vaultBlueBoost1.setBackground(Color.YELLOW);
			}
		});

		JPanel forcePanel = new JPanel();
		forcePanel.setBorder(new TitledBorder(null, "FORCE", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		forcePanel.setBounds(477, 243, 157, 54);
		frame.getContentPane().add(forcePanel);
		forcePanel.setLayout(null);

		JButton vaultBlueForce1 = new JButton("X");
		vaultBlueForce1.setBounds(6, 19, 33, 29);
		forcePanel.add(vaultBlueForce1);

		JButton vaultBlueForce3 = new JButton("X");
		vaultBlueForce3.setBounds(72, 19, 33, 29);
		forcePanel.add(vaultBlueForce3);
		vaultBlueForce3.setEnabled(false);

		JButton vaultBlueForce2 = new JButton("X");
		vaultBlueForce2.setBounds(40, 19, 33, 29);
		forcePanel.add(vaultBlueForce2);
		vaultBlueForce2.setEnabled(false);

		JToggleButton blueForceAvailable = new JToggleButton("F");
		blueForceAvailable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				blueForceAvailable.setEnabled(false);

				vaultBlueForce1.setEnabled(false);
				vaultBlueForce2.setEnabled(false);
				vaultBlueForce3.setEnabled(false);

				powerUpQueue.addPowerUp(Team.BLUE, VaultType.FORCE, GameState.getBlueVault().getForceCount());
				
//				if (GameState.getBlueVault().getForceCount() == 1) {
//					GameState.getBlueSwitch().setForcing(GameState.tenSeconds());
//				}
//
//				if (GameState.getBlueVault().getForceCount() == 2) {
//					GameState.getScale().setForcing(GameState.tenSeconds(), GameState.getScale().getBlueSide());
//				}
//
//				if (GameState.getBlueVault().getForceCount() == 3) {
//					GameState.getScale().setForcing(GameState.tenSeconds(), GameState.getScale().getBlueSide());
//					GameState.getBlueSwitch().setForcing(GameState.tenSeconds());
//				}

			}
		});

		blueForceAvailable.setBounds(111, 18, 40, 29);
		forcePanel.add(blueForceAvailable);
		blueForceAvailable.setEnabled(false);
		vaultBlueForce2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				vaultBlueForce2.setEnabled(false);
				vaultBlueForce3.setEnabled(true);
				vaultBlueForce2.setOpaque(true);
				vaultBlueForce2.setBackground(Color.YELLOW);

				GameState.getBlueVault().addBlock(VaultType.FORCE);

			}
		});
		vaultBlueForce3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vaultBlueForce3.setEnabled(false);
				blueForceAvailable.setEnabled(true);
				vaultBlueForce3.setOpaque(true);
				vaultBlueForce3.setBackground(Color.YELLOW);
				GameState.getBlueVault().addBlock(VaultType.FORCE);
			}
		});
		vaultBlueForce1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				blueForceAvailable.setEnabled(true);
				vaultBlueForce1.setEnabled(false);
				vaultBlueForce2.setEnabled(true);
				vaultBlueForce1.setOpaque(true);
				vaultBlueForce1.setBackground(Color.YELLOW);
				GameState.getBlueVault().addBlock(VaultType.FORCE);
			}
		});

		JPanel levitatePanel = new JPanel();
		levitatePanel.setBorder(new TitledBorder(null, "LEVITATE", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		levitatePanel.setBounds(476, 185, 158, 53);
		frame.getContentPane().add(levitatePanel);
		levitatePanel.setLayout(null);

		JButton vaultBlueLevitate1 = new JButton("X");
		vaultBlueLevitate1.setBounds(6, 18, 33, 29);
		levitatePanel.add(vaultBlueLevitate1);

		JButton vaultBlueLevitate2 = new JButton("X");
		vaultBlueLevitate2.setBounds(40, 18, 33, 29);
		levitatePanel.add(vaultBlueLevitate2);
		vaultBlueLevitate2.setEnabled(false);

		JButton vaultBlueLevitate3 = new JButton("X");
		vaultBlueLevitate3.setBounds(72, 18, 33, 29);
		levitatePanel.add(vaultBlueLevitate3);
		vaultBlueLevitate3.setEnabled(false);

		JToggleButton blueLevitateAvailable = new JToggleButton("L");
		blueLevitateAvailable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				blueLevitateAvailable.setEnabled(false);
				vaultBlueLevitate1.setEnabled(false);
				vaultBlueLevitate2.setEnabled(false);
				vaultBlueLevitate3.setEnabled(false);

				if (GameState.getBlueVault().getLevitateCount() == 3) {
					GameState.getBlueHangar().setLevitating();
				}

			}
		});

		blueLevitateAvailable.setBounds(112, 18, 40, 29);
		levitatePanel.add(blueLevitateAvailable);
		blueLevitateAvailable.setEnabled(false);
		vaultBlueLevitate3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				blueLevitateAvailable.setEnabled(true);
				vaultBlueLevitate3.setEnabled(false);
				vaultBlueLevitate3.setOpaque(true);
				vaultBlueLevitate3.setBackground(Color.YELLOW);

				GameState.getBlueVault().addBlock(VaultType.LEVITATE);
			}
		});
		vaultBlueLevitate2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vaultBlueLevitate2.setEnabled(false);
				vaultBlueLevitate3.setEnabled(true);
				vaultBlueLevitate2.setOpaque(true);
				vaultBlueLevitate2.setBackground(Color.YELLOW);
				GameState.getBlueVault().addBlock(VaultType.LEVITATE);
			}
		});
		vaultBlueLevitate1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				vaultBlueLevitate1.setEnabled(false);
				vaultBlueLevitate2.setEnabled(true);
				vaultBlueLevitate1.setOpaque(true);
				vaultBlueLevitate1.setBackground(Color.YELLOW);
				GameState.getBlueVault().addBlock(VaultType.LEVITATE);
			}
		});

		JPanel panel = new JPanel();
		panel.setBackground(Color.RED);
		panel.setBounds(6, 53, 277, 16);
		frame.getContentPane().add(panel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.BLUE);
		panel_1.setBounds(347, 53, 287, 16);
		frame.getContentPane().add(panel_1);
		
		JButton btnNewButton = new JButton("Start");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton.setEnabled(false);
				Timer SimpleTimer = new Timer(1000, new ActionListener(){
				    @Override
				    public void actionPerformed(ActionEvent e) {
				    	GameState.getInstance().tick();
				        timeRemaining.setText(Integer.toString(GameState.getInstance().currentTick()));
				        redScore.setText(Integer.toString(GameState.getInstance().getRedScore()));
				        blueScore.setText(Integer.toString(GameState.getInstance().getBlueScore()));
				    }
				});
				SimpleTimer.start();
				
			}
		});
		btnNewButton.setBounds(260, 326, 117, 29);
		frame.getContentPane().add(btnNewButton);

	}

	public void setSecondsLeft(int runForSeconds) {
		timeRemaining.setText(Integer.toString(runForSeconds));
	}

	public void setRedScore(int score) {
		redScore.setText(Integer.toString(score));
	}

	public void setBlueScore(int score) {
		blueScore.setText(Integer.toString(score));
	}
}
