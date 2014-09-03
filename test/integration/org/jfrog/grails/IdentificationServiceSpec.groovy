package org.jfrog.grails

import grails.test.spock.IntegrationSpec
import spock.lang.Shared
import spock.lang.Unroll

/**
 * @author Noam Y. Tenne
 */
class IdentificationServiceSpec extends IntegrationSpec {

    @Shared
    IdentificationService identificationService

    def 'ID domains with one level of nesting'() {
        setup:
        def mal = new Person(name: 'Malcolm Reynolds').save(flush: true, failOnError: true)
        def serenity = new Spaceship(name: 'Serenity', type: 'Firefly', captain: mal).save(flush: true, failOnError: true)

        when:
        def identifiedDomains = identificationService.identifyDomains(serenity)

        then:
        identifiedDomains.Spaceship == serenity.id
        identifiedDomains.subDomains.Person == mal.id
    }

    @Unroll
    def 'ID an object that isn\'t a domain'() {
        expect:
        identificationService.identifyDomains(object) == [:]

        where:
        object << [identificationService, new Object()]
    }
}
