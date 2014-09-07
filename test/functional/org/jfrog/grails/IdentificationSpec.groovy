package org.jfrog.grails

import geb.spock.GebReportingSpec
import org.jfrog.grails.pages.IndexPage
import org.jfrog.grails.pages.PerformIdentificationPage
import spock.lang.Stepwise

/**
 * @author Noam Y. Tenne
 */
@Stepwise
class IdentificationSpec extends GebReportingSpec {

    def 'ID a domain'() {
        setup:
        to IndexPage

        when:
        domainToId = 'Spaceship'
        idDomainButton.click()

        then:
        at PerformIdentificationPage
    }

    def 'Check the tree'() {
        expect:
        $('span.jqtree-title-folder').text() ==~ /Spaceship\:\d{1}/

        when:
        $('a.jqtree-toggler').click()

        then:
        $('span.jqtree-title').text() ==~ /Spaceship\:\d{1}/
        $('span.jqtree-title', 1).text() ==~ /Person(.*)\:\d{1}/
    }
}
