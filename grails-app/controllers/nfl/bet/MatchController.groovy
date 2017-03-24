package nfl.bet

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class MatchController {

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Match.list(params), model:[matchCount: Match.count()]
    }

    def show(Match match) {
        respond match
    }

    @Transactional
    def save(Match match) {
        if (match == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        if (match.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond match.errors, view:'create'
            return
        }

        match.save flush:true

        respond match, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(Match match) {
        if (match == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        if (match.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond match.errors, view:'edit'
            return
        }

        match.save flush:true

        respond match, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Match match) {

        if (match == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        match.delete flush:true

        render status: NO_CONTENT
    }
}
