package kr.bit.model;


import java.sql.*;	//자바 sql API
import java.util.ArrayList;
public class MemberDAO {

	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	
	//데이터베이스 연결객체 생성
	public void getConnect() {
		//떼이터베이스 접속 URL SQL사마다 다름
		String URL ="jdbc:mysql://127.0.0.1:3306/test?characterEncoding=UTF-8&serverTimezone=UTC";
		String user="root";
		String password="jjh93231530!";
		
		//MySQL Driver loading  webcontent -> web-inf -> lib   JAR파일  MySQL 드라이버
		try {
			Class.forName("com.mysql.jdbc.Driver"); //동적 로딩(실행시점에서 객체를 생성하는 방법)  jar파일 찾음
			conn = DriverManager.getConnection(URL, user, password);  //접속 시도
		} catch (Exception e) {

			e.printStackTrace();
		}   
	}
	
	//회원 저장 동작
	public int memberInsert(MemberVO vo) {								 //파라메터 1 2 3 4 5 6
		String SQL="insert into member(id, pass, name, age, email, phone) values(?,?,?,?,?,?)";
		getConnect();
		//SQL문장을 전송하는 객체(ps) 생성
		int cnt = -1;
		try {
			ps= conn.prepareStatement(SQL); //미완성 SQL을 미리 컴파일을 시킨다.(속도가빠름)
			ps.setString(1, vo.getId()); //파라미터 1번 값
			ps.setString(2, vo.getPass()); 
			ps.setString(3, vo.getName()); 
			ps.setInt(4, vo.getAge()); 
			ps.setString(5, vo.getEmail()); 
			ps.setString(6, vo.getPhone()); 
			//성공한 값은 1 실패값 0
			cnt = ps.executeUpdate(); // 전송(실행)
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return cnt;
	}
	
	//회원(vo) 전체 리스트(ArrayList) 가져오기
	public ArrayList<MemberVO> memberList() {
		String SQL ="select * from member";
		getConnect();
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		try {
			ps=conn.prepareStatement(SQL);
			rs = ps.executeQuery(); // rs(조회한 쿼리 데이터 담음) -> 커서 
			while(rs.next()) { //데이터 조회 있으면 true 없으면 false
				int num = rs.getInt("num");
				String id = rs.getNString("id");
				String pass = rs.getNString("pass");
				String name = rs.getNString("name");
				int age = rs.getInt("age");
				String email = rs.getNString("email");
				String phone = rs.getNString("phone");
				// 데이터를 묶음
				MemberVO vo = new MemberVO(num, id, pass, name, age, email, phone);
				list.add(vo);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return list;
	}

	public int memberDelete(int num) {
		String SQL = "delete from member where num=?";
		getConnect();
		int cnt = -1;
		try {
			ps = conn.prepareStatement(SQL);
			ps.setInt(1, num);
			
			cnt = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return cnt;
	}
	
	public MemberVO memberContent(int num) {
		String SQL = "select * from member where num=?";
		getConnect();
		MemberVO vo = null;
		try {
			ps = conn.prepareStatement(SQL);
			ps.setInt(1, num);
			rs = ps.executeQuery();
			if (rs.next()) {
				//회원 한명의 정보를 가져옴
				num = rs.getInt("num");
				String id = rs.getNString("id");
				String pass = rs.getNString("pass");
				String name = rs.getNString("name");
				int age = rs.getInt("age");
				String email = rs.getNString("email");
				String phone = rs.getNString("phone");
				
				vo = new MemberVO(num, id, pass, name, age, email, phone);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return vo;
	}
	
	public int memberUpdate(MemberVO vo) {
		String SQL ="update member set age=?, email=?, phone=? where num=?";
		getConnect();
		int cnt = -1;
		try {
			ps=conn.prepareStatement(SQL);
			ps.setInt(1, vo.getAge());
			ps.setString(2, vo.getEmail());
			ps.setString(3, vo.getPhone());
			ps.setInt(4, vo.getNum());
			cnt = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return cnt;
	}
	
	// 데이터베이스 연결 끊기
	public void dbClose() {
		try {
			if(rs!=null) rs.close();
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
