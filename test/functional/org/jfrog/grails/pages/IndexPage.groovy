package org.jfrog.grails.pages

import geb.Page

/**
 * @author Noam Y. Tenne
 */

class IndexPage extends Page {
    static url = "identification/index"

    static at = {
        title == 'Domain Identification'
    }

    static content = {
        idDomainButton(to: PerformIdentificationPage) { $("input", value: "ID Domain") }
    }
}