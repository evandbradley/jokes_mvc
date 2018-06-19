	package com.el.jokes_mvc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.el.jokes_mvc.models.Joke;
import com.el.jokes_mvc.repositories.JokeRepository;

@Controller
public class JokesController {
	
	@Autowired
	private JokeRepository jokeRepository;

	public JokesController() {
		
	}
	
	@GetMapping("/")
	public ModelAndView read() {
		ModelAndView mv = new ModelAndView();
		List<Joke> jokes = jokeRepository.findAll();
		mv.addObject("jokes", jokes);
		mv.setViewName("read");
		return mv;
	}
	
	@GetMapping("/create")
	public ModelAndView newJoke() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("create");
		return mv;
	}
	
	@PostMapping("/create")
	public ModelAndView create(String joke, String punchline, int rating) {
		ModelAndView mv = new ModelAndView();
		jokeRepository.save(new Joke(joke, punchline, rating));
		mv.setViewName("read");
		return mv;
	}
	
	@GetMapping("/edit")
	public ModelAndView edit() {
		ModelAndView mv = new ModelAndView();
		List<Joke> jokes = jokeRepository.findAll();
		mv.addObject("jokes", jokes);
		mv.setViewName("edit");
		return mv;
	}
	
	@PostMapping("/edit")
	public ModelAndView update(int id, String joke, String punchline, int rating) {
		ModelAndView mv = new ModelAndView();
		Joke jokeObj = jokeRepository.findOne(id);
		jokeObj.setJoke(joke);
		jokeObj.setPunchline(punchline);
		jokeObj.setRating(rating);
		//jokeRepository.delete(id);
		jokeRepository.save(jokeObj);
		
		mv.setViewName("read");
		return mv;
	}
	
	@PostMapping("/delete")
	public ModelAndView delete(int id) {
		ModelAndView mv = new ModelAndView();
		jokeRepository.delete(id);
		mv.setViewName("read");
		return mv;
	}

}
