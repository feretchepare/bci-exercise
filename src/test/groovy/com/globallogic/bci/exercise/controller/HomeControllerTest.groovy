package com.globallogic.bci.exercise.controller

import com.globallogic.bci.exercise.dto.SignUpDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.validation.BindingResult
import spock.lang.Specification

@SpringBootTest
class HomeControllerTest extends Specification {

    @Autowired
    private HomeController homeController

    def "assert bean creation"() {
        expect:
        homeController != null
    }

    def "when a new email is being sent then a new user is created"() {
        given:
        BindingResult bindingResult = Mock()
        SignUpDto dto = new SignUpDto()
        dto.setEmail "mail@mail.com"
        dto.setPassword "abcdef12"

        when:
        def result = homeController.signUp(dto, bindingResult)

        then:
        result.getStatusCode().value() == 200;
    }
}
