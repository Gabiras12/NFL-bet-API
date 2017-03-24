package nfl.bet

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ChampionshipController {

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Championship.list(params), model:[championshipCount: Championship.count()]
    }

    def show(Championship championship) {
        respond championship
    }

    @Transactional
    def save(Championship championship) {
        if (championship == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        if (championship.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond championship.errors, view:'create'
            return
        }

        championship.save flush:true

        respond championship, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(Championship championship) {
        if (championship == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        if (championship.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond championship.errors, view:'edit'
            return
        }

        championship.save flush:true

        respond championship, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Championship championship) {

        if (championship == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        championship.delete flush:true

        render status: NO_CONTENT
    }
}
