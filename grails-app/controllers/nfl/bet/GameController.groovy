package nfl.bet

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class GameController {

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Game.list(params), model:[gameCount: Game.count()]
    }

    def show(Game game) {
        respond game
    }

    @Transactional
    def save(Game game) {
        if (game == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        if (game.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond game.errors, view:'create'
            return
        }

        game.save flush:true

        respond game, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(Game game) {
        if (game == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        if (game.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond game.errors, view:'edit'
            return
        }

        game.save flush:true

        respond game, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Game game) {

        if (game == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        game.delete flush:true

        render status: NO_CONTENT
    }
}
