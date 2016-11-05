package com.coursecreation.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coursecreation.domain.Course;
import com.coursecreation.domain.Section;
import com.coursecreation.repository.CourseRepository;

@Controller
public class CourseController {

	private CourseRepository courseRepo; 
	
	
	
	public CourseRepository getCourseRepo() {
		return courseRepo;
	}

	@Autowired
	public void setCourseRepo(CourseRepository courseRepo) {
		this.courseRepo = courseRepo;
	}

	@RequestMapping("/")
	public String root(){
		
		return "redirect:/courses";
	}
	
	@RequestMapping(value="courses", method=RequestMethod.GET)
	public String  coursesGet(ModelMap model){
		List<Course> courses = courseRepo.findAll();
		model.put("courses", courses);
		Course course = new Course();
		model.put("course", course);
		return "courses";
	}
	
	@RequestMapping(value="courses", method=RequestMethod.POST)
	public String coursePost(@ModelAttribute Course course, Model model){
		courseRepo.save(course);
		return "redirect:/";
	}
	
	
	
	@RequestMapping(value="editCourse/{courseId}", method=RequestMethod.GET)
	public String editCourse(@PathVariable Long courseId, ModelMap model){
		Course course = courseRepo.findOne(courseId);
		model.put("course", course);
		if(course == null)
			return "redirect:/";
		model.put("sections", course.getSections());
		return "editCourse";
	}
	
	@RequestMapping(value="editCourse/createSection", method=RequestMethod.POST)
	public @ResponseBody Course createSectionPost(@RequestParam Long courseId, @RequestParam String sectionName, ModelMap model){
		Course course = courseRepo.findOne(courseId);
		Section section = new Section();
		section.setName(sectionName);
		section.setCourse(course);
		course.getSections().add(section);
		Course courseSaved = courseRepo.save(course);
		
		return courseSaved;
	}
	
	@RequestMapping(value="createCourse", method=RequestMethod.GET)
	public String createCourseGet(ModelMap model){
		Course course = new Course();
		model.put("course", course);
		return "createCourse";
	}
//	
//	@RequestMapping(value="createCourse", method=RequestMethod.POST)
//	public String createCoursePost(@ModelAttribute Course course, ModelMap model){
//		courseRepo.save(course);
//		return "redirect:/";
//	}
//	
//	@RequestMapping(value="editCourse/addLesson/{courseId}", method=RequestMethod.GET)
//	public String addCourseGet(@PathVariable Long courseId, ModelMap model){
//		Lesson lesson = new Lesson();
//		model.put("lesson", lesson);
//		Course course = courseRepo.findOne(courseId);
//		model.put("course", course);
//		return "addLesson";
//	}
//	
//	
//	@RequestMapping(value="editCourse/addLesson/{courseId}", method=RequestMethod.POST)
//	public String addCoursePost(@ModelAttribute Lesson lesson, @PathVariable Long courseId, ModelMap model){
//		Course course = courseRepo.findOne(courseId);
//		lesson.setCourse(course);
//		course.getLessons().add(lesson);
//		courseRepo.save(course);
//		return "redirect:/";
//	}
	
	
}
