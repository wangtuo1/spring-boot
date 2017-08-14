package learn.springboot.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
public class HelloResource {
	
//	@ApiOperation(value="/")
	@GetMapping("/")
	public String base() {
		return "hello world!";
	}
	
//	@ApiOperation(value="/home")
//	@ApiImplicitParam(name="name", value = "username", required = true, dataType = "String")
	@GetMapping("/home")
	public ModelAndView index(String name) {
		ModelAndView mv = new ModelAndView("hello");
        return mv;
	}
	

//	@ApiOperation(value="aaa")
	@GetMapping("/aaa")
	public String aaa(){
		return "aaa";
	}
	
	@GetMapping("/index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("index");
        return mv;
	}
	
//	@ApiOperation(value="error")
	@GetMapping("/error")
	public String error(){
		throw new RuntimeException("error");
	}
}
