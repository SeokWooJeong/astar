package Astar_done;

import java.awt.BorderLayout; 
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.Iterator;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class View extends JFrame implements ActionListener {

	private Map map;
	private BoardColor boardColor;
	private MouseAdapter mouseAdapter;
	private boolean enableStart;
	private boolean enableEnd;
	private JPanel panelStart;
	private JPanel panelEnd;
	private Astar astar;
	private PointList close = null;
	private PointList route = null;

	private JPanel mainView;
	private JButton btnStart;
	private JButton btnReset;
	private JPanel board;
	private int mapSize=32;
	private JPanel[][] point = new JPanel[32][32];
	private JPanel panel;
	private Component verticalStrut;
	private Component verticalStrut_1;

	public View() {
		map = new Map();
		boardColor = new BoardColor();
//		mouseAdapter = set();
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
		board.setLayout(new GridLayout(32, 32, 0, 0));

		for (int y = 0; y < mapSize; ++y) {
			for (int x = 0; x < mapSize; ++x) {

				point[y][x] = new JPanel();
				point[y][x].setBorder(new LineBorder(new Color(0, 0, 0)));

				if (map.getMap(x, y) == 0) {
					point[y][x].setBackground(boardColor.getRoad());
					point[y][x].addMouseListener(mouseAdapter);

				} else {
					point[y][x].setBackground(boardColor.getWall());
				}

				board.add(point[y][x]);
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

//	public MouseAdapter set() {
//		MouseAdapter tmp = new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//
//				System.out.println(e.getComponent().hashCode());
//
//				if (e.getButton() == MouseEvent.BUTTON1 && !enableStart) {
//					panelStart = (JPanel) e.getComponent();
//					panelStart.setBackground(boardColor.getStart());
//					enableStart = true;
//
////					Dialog d = new Dialog(View.this);
////					d.setVisible(true);
//				}
//				if (e.getButton() == MouseEvent.BUTTON3 && !enableEnd) {
//					panelEnd = (JPanel) e.getComponent();
//					panelEnd.setBackground(boardColor.getEnd());
//					enableEnd = true;
//				}
//
//			}
//		};
//		return tmp;
//	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnStart) {

			astar = new Astar(map);
			Thread t = new Thread(() -> {
				while (route == null) {
					route = astar.getFindPath();
					close = astar.getCloseList();
				}
				seeClose();
				seeRoute();
			});
			t.start();

		} else if (e.getSource() == btnReset) {
			
			enableStart = false;
			enableEnd = false;
			map.reset();
			board = new JPanel();
			mainView.add(board, BorderLayout.CENTER);
			board.setLayout(new GridLayout(32, 32, 0, 0));

			for (int y = 0; y < 32; ++y) {
				for (int x = 0; x < 32; ++x) {
					point[y][x] = new JPanel();
					point[y][x].setBorder(new LineBorder(new Color(0, 0, 0)));
					if (map.getMap(x, y) == 0) {
						point[y][x].setBackground(boardColor.getRoad());
						point[y][x].addMouseListener(mouseAdapter);

					} else if (map.getMap(x, y) == 1) {
						point[y][x].setBackground(boardColor.getWall());
					} else if (map.getMap(x, y) == 2) {
						point[y][x].setBackground(boardColor.getStart());
					} else if (map.getMap(x, y) == 3){
						point[y][x].setBackground(boardColor.getEnd());
					}
					board.add(point[y][x]);
				}
			}
			route = null;
			this.setVisible(true);
//			panelStart.setBackground(boardColor.getRoad());
//			panelEnd.setBackground(boardColor.getRoad());

		}
	}

	public void seeClose() {
		Iterator x = close.iterator();
		while(x.hasNext()) {
			Point p = (Point) x.next();
			point[p.getCurDot().getY()][p.getCurDot().getX()].setBackground(boardColor.getSearch());
			this.setVisible(true);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void seeRoute() {
		Iterator x = route.iterator();
		while(x.hasNext()) {
			Point p = (Point) x.next();
			point[p.getCurDot().getY()][p.getCurDot().getX()].setBackground(boardColor.getPath());
			this.setVisible(true);
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
