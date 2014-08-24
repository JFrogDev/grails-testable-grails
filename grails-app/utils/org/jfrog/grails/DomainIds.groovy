package org.jfrog.grails
/**
 * @author Noam Y. Tenne
 */
class DomainIds {

    static Map domainId(Object domain) {
        def map = [:]
        map."${domain.class.simpleName}" = domain.id
        map
    }
}
