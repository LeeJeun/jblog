package kr.co.itcen.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.itcen.jblog.repository.PostDao;
import kr.co.itcen.jblog.vo.PostVo;

@Service
public class PostService {
	
	@Autowired
	private PostDao postDao;
	
	public void insert(PostVo vo) {
		postDao.insert(vo);
	}
	
	public List<PostVo> list(Long categoryNo) {
		return postDao.getList(categoryNo);
	}
	
	public Long selectedPost(Long categoryNo) {
		return postDao.getSelectedPost(categoryNo);
	}
	
	public PostVo view(Long postNo) {
		return postDao.getView(postNo);
	}

	public void delete(Long no) {
		postDao.delete(no);
	}
}
