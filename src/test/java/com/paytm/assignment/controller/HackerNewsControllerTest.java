package com.paytm.assignment.controller;

import com.paytm.assignment.model.TopStory;
import com.paytm.assignment.service.HackerNewsService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@RunWith(MockitoJUnitRunner.class)
public class HackerNewsControllerTest {

	@InjectMocks
	private HackerNewsController hackerNewsController;

	@Mock
	private HackerNewsService hackerNewsService;

	@Test
	public void getTopStories() {
		Mockito.when(hackerNewsService.getTopStories())
				.thenReturn(Arrays.asList(new TopStory()));
		Assert.assertNotNull(hackerNewsController.getTopStories());
	}
}
