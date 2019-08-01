import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.Dialog;

import javax.swing.Box;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class View extends JFrame implements ActionListener,Runnable{

	private Map map;
	private BoardColor boardColor;	
	private MouseAdapter mouseAdapter;
	private boolean enableStart;
	private boolean enableEnd;
	private JPanel panelStart;
	private JPanel panelEnd;
	private Astar astar;
	PointList route,close;
	
	private JPanel mainView;
	private JButton btnStart;
	private JButton btnReset;
	private JPanel board;
	private JPanel[][] point = new JPanel[8][8];
	private JPanel panel;
	private Component verticalStrut;
	private Component verticalStrut_1;
	
	public View() {
		map = new Map();
		boardColor = new BoardColor();	
		mouseAdapter = set();
		enableStart = false;
		enableEnd = false;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 870);
		mainView = new JPanel();
		mainView.setBorder(new EmptyBorder(10, 50, 10, 50));
		setContentPane(mainView);
		mainView.setLayout(new BorderLayout(0, 0));
		
		board = new JPanel();
		mainView.add(board, BorderLayout.CENTER);
		board.setLayout(new GridLayout(8, 8, 0, 0));
		

		for(int i=0; i < 8; ++i) {
			for(int j=0; j < 8; ++j) {
				
				point[i][j] = new JPanel();
				point[i][j].setBorder(new LineBorder(new Color(0,0,0)));
				
				if(map.getMap(i, j) == 0) {
					point[i][j].setBackground(boardColor.getRoad());
					point[i][j].addMouseListener(mouseAdapter);
					
				} else {
					point[i][j].setBackground(boardColor.getWall());
				}
				
				board.add(point[i][j]);								
			}
		}
		
		
		JPanel panel_1 = new JPanel();
	
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		mainView.add(panel_1, BorderLayout.SOUTH);
		
		verticalStrut = Box.createVerticalStrut(50);
		panel_1.add(verticalStrut);
		
		btnStart = new JButton("Start");
		btnStart.addActionListener(this);
		panel_1.add(btnStart);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		panel_1.add(horizontalStrut);
		
		btnReset = new JButton("Reset");
		btnReset.addActionListener(this);
		panel_1.add(btnReset);
		
		verticalStrut_1 = Box.createVerticalStrut(50);
		panel_1.add(verticalStrut_1);
	}
	
	public MouseAdapter set() {
		MouseAdapter tmp = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				System.out.println(e.getComponent().hashCode());
				
				if (e.getButton() == MouseEvent.BUTTON1 && !enableStart) {					
					panelStart = (JPanel) e.getComponent();
					panelStart.setBackground(boardColor.getStart());
					enableStart = true;
					
//					Dialog d = new Dialog(View.this);
//					d.setVisible(true);
				} 
				if (e.getButton() == MouseEvent.BUTTON3 && !enableEnd) {					
					panelEnd = (JPanel) e.getComponent();
					panelEnd.setBackground(boardColor.getEnd());
					enableEnd = true;
				}			
				
			}
		};
		return tmp;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnStart) {
			
			astar = new Astar(map);
			
			run();
			
		} else if(e.getSource() == btnReset){
			enableStart = false;
			enableEnd = false;
			map.reset();
			board = new JPanel();
			mainView.add(board, BorderLayout.CENTER);
			board.setLayout(new GridLayout(8, 8, 0, 0));
			
			for(int i=0; i < 8; ++i) {
				for(int j=0; j < 8; ++j) {
					
					point[i][j] = new JPanel();
					point[i][j].setBorder(new LineBorder(new Color(0,0,0)));
					
					if(map.getMap(i, j) == 0) {
						point[i][j].setBackground(boardColor.getRoad());
						point[i][j].addMouseListener(mouseAdapter);
						
					} else if(map.getMap(i, j) == 1) {
						point[i][j].setBackground(boardColor.getWall());
					} else if(map.getMap(i, j) == 2) {
						point[i][j].setBackground(boardColor.getStart());
					} else {
						point[i][j].setBackground(boardColor.getEnd());
					}					
					board.add(point[i][j]);
					this.setVisible(true);
				}
			}
//			panelStart.setBackground(boardColor.getRoad());
//			panelEnd.setBackground(boardColor.getRoad());
			
		}
	}

	@Override
	public void run() {
		route = null;
		while (route == null) {
			route = astar.getFindPath();
			close = astar.getCloseList();

		}
		seeClose();
		seeRoute();
	}

	public void seeClose() {

	}

	public void seeRoute() {

	}
}
