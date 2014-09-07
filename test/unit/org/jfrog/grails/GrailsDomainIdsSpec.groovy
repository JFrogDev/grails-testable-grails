package org.jfrog.grails

import grails.test.mixin.Mock
import spock.lang.Specification

/**
 * @author Noam Y. Tenne
 */
@Mock(Person)
class GrailsDomainIdsSpec extends Specification {

    def 'ID a domain'() {
        given:
        def mal = new Person(name: 'Malcolm Reynolds').save(failOnError: true, flush: true)

        expect:
        Person.findAll().first() == mal

        and:
        DomainIds.domainId(mal) == [Person: mal.id]
    }
}