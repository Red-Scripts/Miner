package Miner;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;

public class MinerGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	public MinerGUI() {
		initComponents();
	}

	private void startActionPerformed(ActionEvent e) {
		String noSpaces = ids.getText().replace(" ", "");
		String noComma[] = noSpaces.split(",");
		Main.rockID = new int[noComma.length];
		for (int i = 0; i < noComma.length; i++) {
			Main.rockID[i] = Integer.parseInt(noComma[i]);
		}
		
		Main.startTime = System.currentTimeMillis();
		Main.waitingForGUI = false;
		this.dispose();
		
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Red Plate
		label1 = new JLabel();
		label2 = new JLabel();
		label3 = new JLabel();
		ids = new JTextField();
		start = new JButton();

		//======== this ========
		setTitle("Red's Miner");
		Container contentPane = getContentPane();

		//---- label1 ----
		label1.setText("Please Enter The Ids Of The Rocks");

		//---- label2 ----
		label2.setText("Please separate IDs with a comma");

		//---- label3 ----
		label3.setText("Example: 7171,7172,7173");

		//---- start ----
		start.setText("Start");
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startActionPerformed(e);
			}
		});

		GroupLayout contentPaneLayout = new GroupLayout(contentPane);
		contentPane.setLayout(contentPaneLayout);
		contentPaneLayout.setHorizontalGroup(
			contentPaneLayout.createParallelGroup()
				.addGroup(contentPaneLayout.createSequentialGroup()
					.addGap(27, 27, 27)
					.addGroup(contentPaneLayout.createParallelGroup()
						.addComponent(label2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(contentPaneLayout.createSequentialGroup()
							.addGroup(contentPaneLayout.createParallelGroup()
								.addGroup(contentPaneLayout.createSequentialGroup()
									.addGap(10, 10, 10)
									.addComponent(label3, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE))
								.addComponent(label1, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
								.addComponent(ids, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
							.addGap(0, 76, Short.MAX_VALUE)))
					.addContainerGap())
				.addGroup(contentPaneLayout.createSequentialGroup()
					.addGap(28, 28, 28)
					.addComponent(start, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
					.addGap(0, 0, Short.MAX_VALUE))
		);
		contentPaneLayout.setVerticalGroup(
			contentPaneLayout.createParallelGroup()
				.addGroup(contentPaneLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(label1)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addComponent(label2)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addComponent(label3)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addComponent(ids, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(31, 31, 31)
					.addComponent(start)
					.addContainerGap(14, Short.MAX_VALUE))
		);
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Red Plate
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JTextField ids;
	private JButton start;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
