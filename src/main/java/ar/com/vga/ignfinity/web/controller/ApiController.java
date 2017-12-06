package ar.com.vga.ignfinity.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.vga.ignfinity.core.domain.ScheduledTask;
import ar.com.vga.ignfinity.core.exception.IgnfinityException;
import ar.com.vga.ignfinity.core.service.HashtagLikeService;
import ar.com.vga.ignfinity.core.service.LoginService;

@RestController
public class ApiController {

	@Autowired
	private LoginService loginService;

	@Autowired
	private HashtagLikeService hashtagLikeService;

	@RequestMapping("/{hashtag}")
	public List<ScheduledTask> index(@PathVariable String hashtag) throws IgnfinityException {

		return null;
	}

}
