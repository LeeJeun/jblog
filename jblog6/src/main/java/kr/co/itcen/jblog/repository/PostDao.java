package kr.co.itcen.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.jblog.vo.PostVo;

@Repository
public class PostDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public Boolean insert(PostVo vo) {
		int count = sqlSession.insert("post.insert", vo);
		return count == 1;
	}
	
	public List<PostVo> getList(Long categoryNo) {
		List<PostVo> result = sqlSession.selectList("post.getList", categoryNo);
		return result;
	}
	
	public Long getSelectedPost(Long categoryNo) {
		return sqlSession.selectOne("category.selectedPost", categoryNo);
	}
	
	public void delete(Long no) {
		sqlSession.delete("post.delete", no);
	}

	public PostVo getView(Long postNo) {
		PostVo result = sqlSession.selectOne("post.getView", postNo);
		return result;
	}
}
