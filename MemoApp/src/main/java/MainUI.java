import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class MainUI extends JFrame {

	private static final long serialVersionUID = 1L;
	
	JTable table;
	
	// 데이터를 저장할 콜렉션 
	List<Map<String, Object>> list;
	
	// 테이블 뷰의 헤더로 사용할 배열 
	String headers [] = {"번호", "제목", "작성일"};

	//생성자 (Constructor)
	public MainUI() {
		// 윈도우의 기본 옵션 설정 
		setTitle("메모 애플리케이션");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// JPanel - Layout 
		// iOS와 windows는 기본적으로 Layout이 없다: null Layout 
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new BorderLayout());
		
		// 클래스 안에서 final을 붙이는 것은 값을 변경하지 못하도록하기 위해서이고, 
		// 메소드 안에서 붙이는 것은 대부분 anonymous class 안에서 지역변수를 사용하기 위해서이다. 
		// swift의 let과 java의 final은 값을 변경하지 못하도록 해주는 예약어이다. 
		// 값을 변경하지 못한다는 것은 자신이 저장하고 있는 주소를 변경 하지 못한다는 것이지, 내부 요소의 값을 변경하지 못한다는 의미가 아니다.
		final JLabel lblTitle = new JLabel("제목");
		northPanel.add("North", lblTitle);
		
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new BorderLayout());
		final JTextArea lblContents = new JTextArea(10,30);
		contentPanel.add("North", lblContents);
		
		final JLabel lblImage = new JLabel();
		contentPanel.add("Center", lblImage);
		
		northPanel.add("Center", contentPanel);
		add("North", northPanel);
		
		// 스윙에서는 스크롤 가능성이 있는 뷰들은 JScrollPane 위에 출력해야 함. 
		// 헤더 부분이 잘리거나 영역을 넘어서는 부분은 출력할 수 없기 때문. 
		table = new JTable();
		
		table.setBackground(new Color(255,255,205));
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		
		
		
		setSize(500, 500);
		setVisible(true);		
		// 스마트폰 애플리케이션은 윈도우가 하나 제공되기 때문에 바로 윈도우 위에 배치만 하면 출력되지만, 
		// 일반 애플리케이션은 윈도우가 제공이 안되기 때문에 직접 출력을 설정해야 함.
	}
}
