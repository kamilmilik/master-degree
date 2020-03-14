package com.example.masterdegree.integration;

import com.example.masterdegree.controllers.ChannelsController;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(ChannelsController.class)
public class ChannelsControllerTest {
}
// https://www.baeldung.com/spring-boot-testing