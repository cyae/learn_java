package com.learn.demo3;

import java.util.concurrent.CountDownLatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import com.learn.demo3.model.Book;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class Demo3Application {

	private static CountDownLatch countDownLatch = new CountDownLatch(9);
	
	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(Demo3Application.class, args);
		TicketRunnable ticketRunnable = new TicketRunnable();
		for (int i = 0; i <= 9; i++) {
			new Thread(ticketRunnable, "线程-" + i).start();
			countDownLatch.countDown();
		}
		Thread.currentThread().join();
	}

	public static class TicketRunnable implements Runnable {

		@Override
		public void run() {
			try {
				countDownLatch.await();
			} catch (Exception e) {
				e.printStackTrace();
			}
			RestTemplate restTemplate = new RestTemplate();
			Book book = restTemplate.getForObject("http://localhost:8001/book/1", Book.class);
			log.info(book.toString());
		}
	}
}
