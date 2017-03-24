package nfl.bet

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class TeamController {

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Team.list(params), model:[teamCount: Team.count()]
    }

    def show(Team team) {
        respond team
    }

    @Transactional
    def save(Team team) {
        if (team == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        if (team.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond team.errors, view:'create'
            return
        }

        team.save flush:true

        respond team, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(Team team) {
        if (team == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        if (team.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond team.errors, view:'edit'
            return
        }

        team.save flush:true

        respond team, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Team team) {

        if (team == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        team.delete flush:true

        render status: NO_CONTENT
    }
}
