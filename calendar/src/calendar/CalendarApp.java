package calendar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;

public class CalendarApp {

	month[] monthArr = new month[12];
	int year = 2019;
	int startingDay = 2; // hari pertama(0-> Minggu, 2->Selasa)
	int currentMonth = 0; // Januari
	
	{
		int februaryDate;
		if(year%4 == 0) {
			februaryDate = 29;
		} else {
			februaryDate = 28;
		}

		monthArr[0] = new month("Januari", 31, startingDay);
		startingDay = (startingDay + 3) % 7;
		monthArr[1] = new month("Februari", februaryDate, startingDay);
		startingDay = (startingDay + februaryDate % 7) % 7;		
		monthArr[2] = new month("Maret", 31, startingDay);
		startingDay = (startingDay + 3) % 7;
		monthArr[3] = new month("April", 30, startingDay);
		startingDay = (startingDay + 2) % 7;
		monthArr[4] = new month("Mei", 31, startingDay);
		startingDay = (startingDay + 3) % 7;
		monthArr[5] = new month("Juni", 30, startingDay);
		startingDay = (startingDay + 2) % 7;
		monthArr[6] = new month("Juli", 31, startingDay);
		startingDay = (startingDay + 3) % 7;
		monthArr[7] = new month("Agustus", 31, startingDay);
		startingDay = (startingDay + 3) % 7;
		monthArr[8] = new month("September", 30, startingDay);
		startingDay = (startingDay + 2) % 7;
		monthArr[9] = new month("Oktober", 31, startingDay);
		startingDay = (startingDay + 3) % 7;
		monthArr[10] = new month("November", 30, startingDay);
		startingDay = (startingDay + 2) % 7;
		monthArr[11] = new month("Desember", 31, startingDay);
	}
	
	private void setDate(month[] monthArr, int currentMonth, JPanel datePanel) {
		
		datePanel.removeAll();
		
		String[] column = {"M", "S", "S", "R", "K", "J", "S"};
		
		for (int i=0 ; i<7 ; i++) {
			JLabel columnHead = new JLabel(column[i]);
			columnHead.setHorizontalAlignment(SwingConstants.CENTER);
			columnHead.setFont(new Font("Serif", Font.BOLD, 20));
			datePanel.add(columnHead);
		}
		
		for(int i=0 ; i<monthArr[currentMonth].getStartingDay() ; i++) {
			JButton emptyDate = new JButton();
			emptyDate.setOpaque(false);
			emptyDate.setContentAreaFilled(false);
			emptyDate.setBorderPainted(false);
			datePanel.add(emptyDate);
		}
		
		for(int i=1 ; i<=monthArr[currentMonth].getDayNumber() ; i++) {
			final Integer innerI = new Integer(i);
			JButton dateButton = new JButton(String.valueOf(i));
			dateButton.setFont(new Font("Serif", Font.PLAIN, 20));
			dateButton.addMouseListener(new MouseListener()
				{
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					if(e.getButton() == MouseEvent.BUTTON1) {
						JFrame frame = new JFrame("Tugas " + innerI + " " + monthArr[currentMonth].getName());
						frame.setLayout(new BorderLayout());
						frame.setSize(new Dimension(500,200));
						frame.setLocationRelativeTo(dateButton);
						
						JTextArea taskTextArea = new JTextArea(monthArr[currentMonth].getDay(innerI).getTask());
						taskTextArea.setFont(new Font("Serif", Font.PLAIN, 20));
						
						JButton saveBtn = new JButton("Simpan");
						saveBtn.setFont(new Font("Serif", Font.PLAIN, 20));
						
						JButton clearBtn = new JButton("Hapus");
						clearBtn.setFont(new Font("Serif", Font.PLAIN, 20));
						
						JPanel taskControlPanel = new JPanel();
						taskControlPanel.setLayout(new BoxLayout(taskControlPanel, BoxLayout.X_AXIS));
						taskControlPanel.add(saveBtn);
						taskControlPanel.add(Box.createHorizontalGlue());
						taskControlPanel.add(clearBtn);
						
						frame.add(taskTextArea, BorderLayout.CENTER);
						frame.add(taskControlPanel, BorderLayout.SOUTH);
						frame.setVisible(true);
						
						saveBtn.addActionListener((event) -> {
							monthArr[currentMonth].getDay(innerI).setTask(taskTextArea.getText());
							if (taskTextArea.getText() != null)
								dateButton.setBackground(new Color(150, 150, 150));
							else {
								dateButton.setBackground(null);
								dateButton.repaint();
							}
							frame.setVisible(false);
						});
						
						clearBtn.addActionListener((event) -> {
							taskTextArea.selectAll();
							taskTextArea.replaceSelection(null);
						});
					}
				}
			});
			
			if( monthArr[currentMonth].getDay(i).getTask() != null)
				dateButton.setBackground(new Color(150, 150, 150));
			
			datePanel.add(dateButton);
		}
		
		for(int i=0 ; i<(42-monthArr[currentMonth].getStartingDay()-monthArr[currentMonth].getDayNumber()) ; i++) {
			JButton emptyDate = new JButton();
			emptyDate.setOpaque(false);
			emptyDate.setContentAreaFilled(false);
			emptyDate.setBorderPainted(false);
			datePanel.add(emptyDate);
		}
	}
	
	public CalendarApp() {
		//info
		JTextArea infoTextArea = new JTextArea();
		infoTextArea.setLineWrap(true);
		infoTextArea.setWrapStyleWord(true);
		infoTextArea.setText("Kalender " + year);
		infoTextArea.setFont(new Font("Serif", Font.BOLD, 25));
		infoTextArea.setBackground(new Color(241, 241, 241));
		infoTextArea.setEditable(false);
		infoTextArea.setMargin(new Insets(5, 5, 5, 5));
		
		JPanel infoPanel = new JPanel(new BorderLayout());
		infoPanel.add(infoTextArea, BorderLayout.CENTER);
		
		//control panel
		JButton prevBtn = new JButton("<<");
		prevBtn.setPreferredSize(new Dimension(100, 40));
		JButton nextBtn = new JButton(">>");
		nextBtn.setPreferredSize(new Dimension(100, 40));
		
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.X_AXIS));		
		controlPanel.add(prevBtn);
		controlPanel.add(Box.createHorizontalGlue());
		controlPanel.add(nextBtn);
		
		//calendar
		JLabel monthLabel = new JLabel(monthArr[currentMonth].getName());
		monthLabel.setHorizontalAlignment(SwingConstants.CENTER);
		monthLabel.setFont(new Font("Serif", Font.PLAIN, 30));
		
		JPanel datePanel = new JPanel();
		datePanel.setLayout(new GridLayout(7, 7));
		
		setDate(monthArr, currentMonth, datePanel);
		
		JPanel calendarPanel = new JPanel(new BorderLayout());
		calendarPanel.add(monthLabel, BorderLayout.NORTH);
		calendarPanel.add(datePanel, BorderLayout.CENTER);

		//main
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(infoPanel, BorderLayout.NORTH);
		mainPanel.add(calendarPanel, BorderLayout.CENTER);
		mainPanel.add(controlPanel, BorderLayout.SOUTH);
		
		JFrame frame = new JFrame();
		frame.setTitle("Kalender");
		frame.setLayout(new BorderLayout());
		frame.add(mainPanel, BorderLayout.CENTER);
		frame.setSize(new Dimension(800, 650));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	
		//actionListener
		prevBtn.addActionListener((e) -> {
			if (currentMonth == 0) {
				currentMonth = 11;
			} else {
				currentMonth--;
			}
			
			monthLabel.setText(monthArr[currentMonth].getName());
			setDate(monthArr, currentMonth, datePanel);
		});
		
		nextBtn.addActionListener((e) -> {
			if (currentMonth == 11) {
				currentMonth = 0;
			} else {
				currentMonth++;
			}
	
			monthLabel.setText(monthArr[currentMonth].getName());
			setDate(monthArr, currentMonth, datePanel);
		});
		
	}
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		SwingUtilities.invokeLater(() -> {new CalendarApp();});
	}
}
