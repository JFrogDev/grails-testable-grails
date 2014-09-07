package org.jfrog.grails

import grails.converters.JSON
import org.codehaus.groovy.grails.commons.DomainClassArtefactHandler
import org.codehaus.groovy.grails.commons.GrailsApplication
import org.grails.datastore.gorm.GormStaticApi
import org.jfrog.grails.IdentificationService

class IdentificationController {

    IdentificationService identificationService
    GrailsApplication grailsApplication

    def index() {}

    def performIdentification() {
        def domainToId = params.domainToId
        def artefact = grailsApplication.getArtefacts(DomainClassArtefactHandler.TYPE).find {it.name == domainToId}
        def instance = artefact.clazz.first()
        println instance
        def mapped = identificationService.identifyDomains(instance)
        println mapped
        def data = recursiveTransform(mapped)
        println data
//        def data = [[label   : 'node1',
//                     children: [[label: 'child1'], [label: 'child2']]],
//                    [label   : 'node2',
//                     children: [[label: 'child3']]]]

        [data: data as JSON]
    }

    private List recursiveTransform(Map toTransform) {
        def entries = toTransform.collectEntries { entryToTransform ->

            def key = entryToTransform.key
            def value = entryToTransform.value

            if (key == 'subDomains') {
                if (value.isEmpty()) {
                    return [:]
                } else {
                    def children = recursiveTransform(value)
                    if (children.empty) {
                        return [:]
                    } else {
                        return [children: children]
                    }
                }
            } else {
                [label: "${key}:${value}"]
            }
        }
        if (entries.isEmpty()) {
            return []
        }
        [entries]
    }
}
