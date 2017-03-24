package nfl.bet

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class BetController {

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Bet.list(params), model:[betCount: Bet.count()]
    }

    def show(Bet bet) {
        respond bet
    }

    @Transactional
    def save(Bet bet) {
        if (bet == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        if (bet.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond bet.errors, view:'create'
            return
        }

        bet.save flush:true

        respond bet, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(Bet bet) {
        if (bet == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        if (bet.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond bet.errors, view:'edit'
            return
        }

        bet.save flush:true

        respond bet, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Bet bet) {

        if (bet == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        bet.delete flush:true

        render status: NO_CONTENT
    }
}
