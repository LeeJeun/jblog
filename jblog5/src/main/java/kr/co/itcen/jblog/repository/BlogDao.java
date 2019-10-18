package kr.co.itcen.jblog.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.jblog.vo.BlogVo;

@Repository
public class BlogDao {

	@Autowired
	private SqlSession sqlSession;
	
	public Boolean update(BlogVo vo) {
		int count = sqlSession.insert("blog.update", vo);
		return count == 1;
	}

	public BlogVo getList(String blogId) {
		BlogVo result = sqlSession.selectOne("blog.getList", blogId);
		return result;
	}

	public Map<String, Object> getAll(String id, Long categoryNo, Long postNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		parameters.put("blogId", id);
		parameters.put("categoryNo", categoryNo);
		parameters.put("postNo", postNo);
		
		map.put("categoryVo", sqlSession.selectList("category.getList", id));
		map.put("postVo", sqlSession.selectList("post.getList", parameters));
		map.put("viewPost", sqlSession.selectOne("post.getView", parameters));
		
		return map;
	}
}
