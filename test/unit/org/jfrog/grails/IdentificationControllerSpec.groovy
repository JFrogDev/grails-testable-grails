package org.jfrog.grails

import grails.converters.JSON
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import groovy.json.JsonSlurper
import spock.lang.Specification

/**
 * @author Noam Y. Tenne
 */
@TestFor(IdentificationController)
@Mock(Person)
class IdentificationControllerSpec extends Specification {

    def 'Perform an identification'() {
        setup:
        params.domainToId = 'Person'

        def person = new Person(name: 'Malcolm Reynolds').save()

        controller.identificationService = Stub(IdentificationService) {
            identifyDomains(person) >> { [Person: 1] }
        }

        when:
        def result = controller.performIdentification()

        then:
        new JsonSlurper().parseText(((JSON) result.data).toString(false)) == [[label: 'Person:1']]
    }
}
