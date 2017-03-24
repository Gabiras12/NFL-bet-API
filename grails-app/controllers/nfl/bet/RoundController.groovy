package nfl.bet

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class RoundController {

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Round.list(params), model:[roundCount: Round.count()]
    }

    def show(Round round) {
        respond round
    }

    @Transactional
    def save(Round round) {
        if (round == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        if (round.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond round.errors, view:'create'
            return
        }

        round.save flush:true

        respond round, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(Round round) {
        if (round == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        if (round.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond round.errors, view:'edit'
            return
        }

        round.save flush:true

        respond round, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Round round) {

        if (round == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        round.delete flush:true

        render status: NO_CONTENT
    }
}
