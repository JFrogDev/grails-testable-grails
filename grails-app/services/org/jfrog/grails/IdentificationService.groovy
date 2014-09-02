package org.jfrog.grails

import org.codehaus.groovy.grails.commons.DomainClassArtefactHandler
import org.codehaus.groovy.grails.commons.GrailsApplication
import org.jfrog.grails.DomainIds

import java.lang.reflect.Field

/**
 * @author Noam Y. Tenne
 */
class IdentificationService {

    GrailsApplication grailsApplication

    Map identifyDomains(Object domain) {
        if (!grailsApplication.isArtefactOfType(DomainClassArtefactHandler.TYPE, domain.class)) {
            return [:]
        }
        recursiveDomainId(domain)
    }

    private Map recursiveDomainId(Object domain) {
        def map = DomainIds.domainId(domain)

        def declaredFields = domain.class.getDeclaredFields()
        def allDomainFields = declaredFields.findAll { Field field ->
            grailsApplication.isArtefactOfType(DomainClassArtefactHandler.TYPE, field.getType())
        }
       def subDomains = [:]
        allDomainFields.each { Field field ->
            field.setAccessible(true)
            subDomains.putAll(recursiveDomainId(field.get(domain)))
        }
        map.subDomains = subDomains
        map
    }
}
