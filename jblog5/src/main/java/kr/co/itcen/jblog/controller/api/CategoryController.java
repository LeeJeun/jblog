package kr.co.itcen.jblog.controller.api;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.itcen.jblog.dto.JSONResult;
import kr.co.itcen.jblog.service.CategoryService;
import kr.co.itcen.jblog.vo.CategoryVo;
import kr.co.itcen.jblog.vo.UserVo;

@Controller("categoryApiController")
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@ResponseBody
	@RequestMapping("/add")
	public JSONResult add(@RequestBody CategoryVo vo, HttpSession session) {
		UserVo userVo = (UserVo)session.getAttribute("authUser");
		vo.setBlogId(userVo.getId());
		Boolean exist = categoryService.add(vo);
		return JSONResult.success(exist);
	}
	
	@ResponseBody
	@RequestMapping("/getList")
	public List<CategoryVo> getList(HttpSession session) {
		UserVo userVo = (UserVo)session.getAttribute("authUser");
		List<CategoryVo> result = categoryService.list(userVo.getId());
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public JSONResult delete(@RequestBody CategoryVo vo) {
		Boolean exist = categoryService.delete(vo.getNo());
		return JSONResult.success(exist);
	}
}
