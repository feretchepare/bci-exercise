package com.globallogic.bci.exercise.controller

import com.globallogic.bci.exercise.dto.SignUpDto
import com.globallogic.bci.exercise.login.JwtUtil
import com.globallogic.bci.exercise.model.User
import com.globallogic.bci.exercise.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.validation.BindingResult
import spock.lang.Specification

class HomeControllerTest extends Specification {

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
}
