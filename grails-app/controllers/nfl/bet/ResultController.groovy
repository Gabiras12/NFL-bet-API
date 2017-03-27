package nfl.bet


import grails.rest.*
import grails.converters.*

class ResultController {
	static responseFormats = ['json', 'xml']
	public static final int VD = 1

	def index() {
			def bets = Bet.list()
			bets.each { bet ->
				if (bet.game.winner.id == bet.beatingTeam.id) {
					if (bet.game.scoreDiference > 10) {
						if (bet.type == VD) {
							bet.points = 10
						} else {
							bet.points = 5
						}
					} else {
						if (bet.type == VD) {
							bet.points = 5
						} else {
							bet.points = 10
						}
					}
					bet.hit = true
				}

				def round = bet.game.round
				round.roundHasBeenFineshed = true
				round.save(flush: true)
				bet.hasBeenComputed = true
				bet.save(flush: true)
			}
			respond bets
		}
}
