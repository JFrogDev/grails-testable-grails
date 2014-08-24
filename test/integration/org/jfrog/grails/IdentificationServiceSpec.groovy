package org.jfrog.grails

import grails.test.spock.IntegrationSpec
import spock.lang.Shared

/**
 * @author Noam Y. Tenne
 */
class IdentificationServiceSpec extends IntegrationSpec {

    @Shared
    IdentificationService identificationService

    def 'Id'() {
        setup:
        def mal = new Person(name: 'Malcolm Reynolds').save(flush: true, failOnError: true)
        def serenity = new Spaceship(name: 'Serenity', type: 'Firefly', captain: mal).save(flush: true, failOnError: true)

        when:
        def identifiedDomains = identificationService.identifyDomains(serenity)

        then:
        identifiedDomains.Spaceship == serenity.id
        identifiedDomains.subDomains.Person == mal.id
    }
}
