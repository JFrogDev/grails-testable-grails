import org.jfrog.grails.Person
import org.jfrog.grails.Spaceship

/**
 * @author Noam Y. Tenne
 */

class BootStrap {

    def init = { servletContext ->
        def mal = new Person(name: 'Malcolm Reynolds').save(flush: true, failOnError: true)
        println "saved person"
        def serenity = new Spaceship(name: 'Serenity', type: 'Firefly', captain: mal).save(flush: true, failOnError: true)
        println "saved spaceship"
    }

    def destroy = {}
}