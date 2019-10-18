package kr.co.itcen.jblog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.co.itcen.jblog.security.Auth;
import kr.co.itcen.jblog.service.BlogService;
import kr.co.itcen.jblog.service.CategoryService;
import kr.co.itcen.jblog.service.PostService;
import kr.co.itcen.jblog.vo.BlogVo;
import kr.co.itcen.jblog.vo.CategoryVo;
import kr.co.itcen.jblog.vo.PostVo;


@Controller
@RequestMapping( "/{id:(?!assets)(?!images).*}" )
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PostService postService;
	
	@RequestMapping( {"", "/{pathNo1}", "/{pathNo1}/{pathNo2}" } )
	public String index( 
		@PathVariable("id") String id,
		@PathVariable Optional<Long> pathNo1,
		@PathVariable Optional<Long> pathNo2,
		ModelMap modelMap ) {

		Long categoryNo = 0L;
		Long postNo = 0L;
		
		if( pathNo2.isPresent() ) {
			postNo = pathNo2.get();
			categoryNo = pathNo1.get();
		} else if( pathNo1.isPresent() ){
			categoryNo = pathNo1.get();
		}
		
		
		modelMap.putAll( blogService.getAll( id, categoryNo, postNo ) );
		return "blog/blog-main";
	}
	
	//----------------------------------기본설정-----------------------------------
	@Auth
	@RequestMapping( "/admin/basic" )
	public String basic( @PathVariable("id") String id, Model model) {
		BlogVo blogVo = blogService.list(id);
		model.addAttribute("blogVo", blogVo);
		return "blog/blog-admin-basic";
	}
	
	@RequestMapping(value="/admin/basic", method=RequestMethod.POST)
	public String basic( @PathVariable("id") String id, 
			@RequestParam(value="title", required = true, defaultValue = "") String title,
			@RequestParam(value="logo-file", required = false) MultipartFile multipartFile,
			BlogVo vo) {
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@:"+title);
		String logo = blogService.restore(multipartFile);
		vo.setBlogId(id);
		vo.setTitle(title);
		vo.setLogo(logo);
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@:"+vo);
		blogService.update(vo);
		return "redirect:/"+id;
	}
	
	//----------------------------------카테고리-----------------------------------
	@Auth
	@RequestMapping("/admin/category")
	public String category( @PathVariable("id") String id, Model model) {
		BlogVo blogVo = blogService.list(id);
		model.addAttribute("blogVo", blogVo);
		
		return "blog/blog-admin-category";
	}
	
	//----------------------------------글작성-----------------------------------
	@Auth
	@RequestMapping("/admin/write")
	public String write( @PathVariable("id") String id, Model model ) {
		BlogVo blogVo = blogService.list(id);
		model.addAttribute("blogVo", blogVo);
		
		List<CategoryVo> vo = categoryService.list(id);
		model.addAttribute("list", vo);
		return "blog/blog-admin-write";
	}
	
	@RequestMapping(value = "/admin/write", method=RequestMethod.POST)
	public String write( @PathVariable("id") String id, PostVo vo, @RequestParam("category")Long no) {
		vo.setCategoryNo(no);
		postService.insert(vo);
		return "redirect:/"+id;
	}
}