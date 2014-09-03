package org.jfrog.grails

import grails.test.mixin.Mock
import spock.lang.Specification

/**
 * @author Noam Y. Tenne
 */
class PureDomainIdsSpec extends Specification {

    def 'ID a domain'() {
        expect:
        def domain = new DummyDomain(id: '1337')
        DomainIds.domainId(domain) == [DummyDomain: '1337']
    }

    class DummyDomain {
        String id
    }
}