import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;

import mainClasses.*;

public class GUI extends JFrame {
ArrayList<Edible> ed;
	
	private JPanel commonPanel;
	private JLabel labelMenu;
	private JScrollPane scroll;
	private JRadioButton radio1;
	private JRadioButton radio2;
	private JRadioButton radio3;
	private JButton buttonFillFromFile;
	private JButton buttonFillAuto;

	Container container;
	
	public GUI () {
		super("LR7");
		this.setBounds(150, 150, 800, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		container = this.getContentPane();
		container.setLayout(null);
		
		commonPanel = new JPanel();
		scroll = new JScrollPane(commonPanel);
		radio1 = new JRadioButton("Стиль 1");
		radio2 = new JRadioButton("Стиль 2");
		radio3 = new JRadioButton("Стиль 3");
		labelMenu = new JLabel();
		buttonFillFromFile = new JButton("Загрузка базы из файла");
		buttonFillAuto = new JButton("Автоматическое заполнение");
		
		
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		commonPanel.setLayout(new BoxLayout(commonPanel, BoxLayout.Y_AXIS));
		
//		container.setBackground(bgColor);
		
		ButtonGroup group = new ButtonGroup();
		group.add(radio1);
		group.add(radio2);
		group.add(radio3);
		
		radio1.setSelected(true);;
		
		radio1.setBounds(5,5, 100, 40);
		radio2.setBounds(5,55, 100, 40);
		radio3.setBounds(5,105, 100, 40);
		
//		radio2.setBackground(bgColor);
//		radio1.setBackground(bgColor);
//		radio3.setBackground(bgColor);
		
		radio1.addActionListener(new RadioButtonEventListener());
		radio2.addActionListener(new RadioButtonEventListener());
		radio3.addActionListener(new RadioButtonEventListener());
		
		
		labelMenu.setBounds(5, 220, 80,40);
		labelMenu.setHorizontalAlignment(JLabel.CENTER);
		labelMenu.setHorizontalTextPosition(JLabel.CENTER);
		labelMenu.setText("Меню");
//		labelMenu.setForeground(fontColor);
//		labelMenu.setFont(myFont);
		
		buttonFillFromFile.setBounds(5, 155, 180, 30);
		buttonFillAuto.setBounds(5, 185, 180, 30);
		

		
		buttonFillFromFile.addActionListener(new ButtonFillFromFileEventListener());
		buttonFillAuto.addActionListener(new ButtonFillAutoEventListener());
		
		scroll.setBounds(5, 250, 780, 400);
		
		container.add(radio1);
		container.add(radio2);
		container.add(radio3);
		container.add(labelMenu);
		container.add(buttonFillFromFile);
		container.add(buttonFillAuto);
		container.add(scroll);
		
//		updateStyles();

	}
	
	class ButtonFillFromFileEventListener implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			try {
				int i = 0;
				BufferedReader fileReader = new BufferedReader(new FileReader("food.txt"));
                ed = new ArrayList<>();
                boolean eof = false;
				try {
				while (!eof) {
					ed.add(StreamWorker.readEdible(fileReader));
				}
				} catch (NullPointerException ex) {
					eof = true;
				}
                fileReader.close();
                
                commonPanel.removeAll();
    			for (Edible edible:ed) {
    				JPanel newPanel = new JPanel();
    				JLabel labelEdible = new JLabel("Номер в базе: "+ (i++) +" " + edible);
    				newPanel.add(labelEdible);
    				newPanel.addMouseListener(new PanelClickEventListener(edible, i-1));
    				//newPanel.setBackground(myColor);
    				newPanel.repaint();
    				commonPanel.add(newPanel);
    			}
    			scroll.revalidate();
        	}
            catch (IOException err){
            	String message = err.getMessage();
    			JOptionPane.showMessageDialog(null, message, "Ошибка", JOptionPane.PLAIN_MESSAGE);
            } catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	class ButtonFillAutoEventListener implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			int i = 0;
			ed = new ArrayList<Edible>();
			
			for(int j = 0; j < 3; j++) {
				ed.add(StreamWorker.createInstance());
			}
			StreamWorker.setFactory(new FastFoodFactory());
			for(int j = 0; j < 2; j++) {
				ed.add(StreamWorker.createInstance());
			}
			StreamWorker.setFactory(new HomeFoodFactory());
			commonPanel.removeAll();
			for (Edible edible:ed) {
				JPanel newPanel = new JPanel();
				JLabel labelEdible = new JLabel("Номер в базе: "+ (i++) +" " + edible);
				newPanel.add(labelEdible);
				newPanel.addMouseListener(new PanelClickEventListener(edible, i-1));
				newPanel.repaint();
				commonPanel.add(newPanel);
			}
			scroll.revalidate();
		}
	}
	
	class RadioButtonEventListener implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			String laf;
			if (radio1.isSelected()) {
				try {
		            laf=UIManager.getCrossPlatformLookAndFeelClassName();
		            UIManager.setLookAndFeel(laf);
		            SwingUtilities.updateComponentTreeUI(container);
		        } catch (Exception ex) {
		        	JOptionPane.showMessageDialog(null,ex.getMessage());
		        }
			} else if (radio2.isSelected()) {
				try {
					laf="com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
		            UIManager.setLookAndFeel(laf);
		            SwingUtilities.updateComponentTreeUI(container);
		        } catch (Exception ex) {}
				
			} else if (radio3.isSelected()) {
				try {
		            laf="com.sun.java.swing.plaf.motif.MotifLookAndFeel";
		            UIManager.setLookAndFeel(laf);
		            SwingUtilities.updateComponentTreeUI(container);
		        } catch (Exception ex) {}
			}
			
		}
	}
	
	class PanelClickEventListener implements MouseListener {
		Edible edible;
		int n;
		public PanelClickEventListener (Edible ed, int n) {
			this.edible = ed;
			this.n = n;
		}
		public void mouseClicked(MouseEvent e) {
			String message = n+" элемент базы: ";
			message += edible;
			JOptionPane.showMessageDialog(null, message, "Информация об элементе №"+n + " базы", JOptionPane.PLAIN_MESSAGE);
       }
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
}
