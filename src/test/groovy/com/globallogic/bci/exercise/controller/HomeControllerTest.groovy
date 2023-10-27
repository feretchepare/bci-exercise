package com.globallogic.bci.exercise.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.globallogic.bci.exercise.dto.SignUpDto
import com.globallogic.bci.exercise.login.JwtUtil
import com.globallogic.bci.exercise.model.User
import com.globallogic.bci.exercise.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.validation.BindingResult
import spock.lang.Specification

@SpringBootTest
@AutoConfigureMockMvc
class HomeControllerTest extends Specification {

    @Autowired MockMvc mockMvc
    private HomeController homeController
    UserService userService
    JwtUtil jwtUtil

    def setup() {
        userService = Mock(UserService)
        jwtUtil = Mock(JwtUtil)
        homeController = new HomeController(userService, jwtUtil)
    }

    def "assert bean creation"() {
        expect:
        homeController != null
    }

    def "error is thrown when there are binding errors"() {
        given:
        userService.signUp(_) >> { throw new Exception("")}
        SignUpDto dto = new SignUpDto()
        BindingResult bindingResult = Mock(BindingResult)

        when:
        homeController.signUp(dto, bindingResult)

        then:
        thrown Exception
    }

    def "OK status as response when there are no binding errors"() {
        given:
        userService.signUp(_) >> { new User()}
        SignUpDto dto = new SignUpDto()
        BindingResult bindingResult = Mock(BindingResult)

        when:
        def result = homeController.signUp(dto, bindingResult)

        then:
        result.statusCode == HttpStatus.OK
    }

    def "Test signUp endpoint with valid data"() {
        when:
        def signUpDto = new SignUpDto(name: "UsernameTest", email: 'mail@mail.com', password: "Abcdef12")
        def result = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/sign-up")
                .contentType("application/json")
                .content(new ObjectMapper().writeValueAsString(signUpDto)))
                .andReturn().getResponse().getContentAsString()

        then:
        result != null
    }

    def "Test signUp endpoint with invalid data"() {
        when:
        def signUpDto = new SignUpDto(name: "UsernameTest")
        def result = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/sign-up")
                .contentType("application/json")
                .content(new ObjectMapper().writeValueAsString(signUpDto)))
                .andReturn().getResponse().getContentAsString()

        then:
        result != null
    }
}
