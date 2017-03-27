package nfl.bet


import grails.rest.*
import grails.converters.*

class ResultController {
	static responseFormats = ['json', 'xml']
	public static final int VD = 1
	public static final int VF = 2

	def index() {
			def bets = Bet.list()
			bets.each { bet ->
				if (bet.game.winner.id == bet.beatingTeam.id) {
					if (bet.game.scoreDiference > 10) {
						calculateUserPoints(bet, VD)
					} else {
						calculateUserPoints(bet, VF)
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

	private void calculateUserPoints(Bet bet, int bettingType) {
		if (bet.type == bettingType) {
			bet.points = 10
		} else {
			bet.points = 5
		}
	}
}
