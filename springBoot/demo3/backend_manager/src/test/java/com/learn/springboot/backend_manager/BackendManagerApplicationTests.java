package com.learn.springboot.backend_manager;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import com.learn.springboot.backend_manager.Service.UserService;
import com.learn.springboot.backend_manager.bean.UserForSQL;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.jdbc.core.JdbcTemplate;

import lombok.extern.slf4j.Slf4j;

// 测试原则: 边写功能边测试, 否则错上加错
@SpringBootTest
@Slf4j
class BackendManagerApplicationTests {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	UserService userService;
	
	@Autowired
	StringRedisTemplate redisTemplate;

	@BeforeEach
	void beforeEach() {
		System.out.println("beforeEach");
	}
	@AfterEach
	void afterEach() {
		System.out.println("afterEach");
	}
	@BeforeAll
	static void beforeAll() {
		System.out.println("beforeAll");
	}
	@AfterAll
	static void afterAll() {
		System.out.println("afterAll");
	}


	@RepeatedTest(10)
	@DisplayName("测试jdbcTemplate")
	void contextLoads() {
		Long count = jdbcTemplate.queryForObject("select count(*) from t_user", Long.class);
		log.info("count: {}", count);
	}

	@Test
	@DisplayName("测试userService")
	@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
	void testUserMapper() {
		UserForSQL users = userService.getById(1);
		log.info("users: {}", users);
	}

	@Test
	@DisplayName("测试redisTemplate")
	@Disabled
	void testRedis() {
		ValueOperations<String, String> op = redisTemplate.opsForValue();
		op.set("test", "test");
		log.info("test: {}", op.get("test"));
	}

	@Test
	void testAssert() {
		assertEquals(0, 1, "测试assert");
		assumeTrue(0==1, "测试assumeTrue");
	}

	@Test
	void testAsserAll() {
		assertAll("heading", 
					()-> assertEquals(1, 1, "测试assertAll"),
					()-> assertEquals(1, 1, "测试assertAll")
		);
	}

	@Test
	void testException() {
		assertThrows(Throwable.class, ()->{
			throw new Throwable("测试异常");
		}, "上述内容一定抛出了异常, 否则测试失败");
	}

	@Test
	void testTimeout() {
		assertTimeout(Duration.ofMillis(500), ()->{
			TimeUnit.MILLISECONDS.sleep(1000);
		}, "测试超时");
	}

	@Test
	void testFastFail() {
		fail("失败");
	}

	@ParameterizedTest
	@ValueSource(ints = {1, 2, 3})
	void testParam(int i) {
		System.out.println(i);
	}

	@ParameterizedTest
	@MethodSource("randomNum")
	void testParam2(int i) {
		System.out.println(i);
	}

	static int[] randomNum() {
		Random random = new Random();
		return new int[] {random.nextInt(), random.nextInt(), random.nextInt()};
	}

	static Stream<Integer> randomNum2() {
		Random random = new Random();
		return Stream.of(random.nextInt(), random.nextInt(), random.nextInt());
	}

}
