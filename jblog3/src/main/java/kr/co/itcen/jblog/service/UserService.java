package kr.co.itcen.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.itcen.jblog.repository.UserDao;
import kr.co.itcen.jblog.vo.UserVo;


@Service
public class UserService {
	@Autowired // 의존성주입
	private UserDao userDao;

	public void join(UserVo vo) {
		userDao.insert(vo);
		userDao.blogInsert(vo);
		userDao.categoryInsert(vo);
	}

	public UserVo getUser(UserVo vo) {
		return userDao.get(vo);
	}
	
	public UserVo getUser(String id) {
		return userDao.get(id);
	}
	
	public void update(UserVo vo) {
		userDao.update(vo);
	}

	public Boolean existUser(String id) {
		return userDao.get(id) != null;
	}
}
