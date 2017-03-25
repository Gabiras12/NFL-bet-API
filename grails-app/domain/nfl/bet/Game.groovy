package nfl.bet

class Game {

  Date matchDate
  String stadium
  Team winner
  int scoreDiference
  Team homeTeam
  Team visitingTeam

  static hasMany = [bets : Bet]
  static belongsTo = [round : Round]

  static constraints = {
  }
}
