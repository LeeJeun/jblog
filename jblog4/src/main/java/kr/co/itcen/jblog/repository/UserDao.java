package kr.co.itcen.jblog.repository;

import java.util.HashMap;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.jblog.vo.UserVo;


@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public Boolean insert(UserVo vo) {
		int count = sqlSession.insert("user.insert", vo);
		System.out.println(vo);	
		return count == 1;	
	}
	
	public Boolean blogInsert(UserVo vo) {
		vo.setName(vo.getName()+"의 블로그");
		int count = sqlSession.insert("user.blogInsert", vo);
		return count == 1;
	}
	
	public Boolean categoryInsert(UserVo vo) {
		int count = sqlSession.insert("user.categoryInsert", vo);
		return count == 1;
	}
	
	public Boolean update(UserVo vo) {
		int count = sqlSession.update("user.update", vo);
		return count == 1;
	}
	
	public UserVo get(String id) {
		UserVo result = sqlSession.selectOne("user.getById", id);
		return result;		
	}
	
	public UserVo get(UserVo vo) {
		UserVo result = sqlSession.selectOne("user.getByIdAndPassword1", vo);
		return result;
	}
	
	public UserVo get(String id, String password) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("password", password);
		
		UserVo result = sqlSession.selectOne("user.getByEmailAndPassword2", map);
		return result;		
	}

}
