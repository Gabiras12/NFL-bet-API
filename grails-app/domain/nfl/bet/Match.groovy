package nfl.bet

class Match {

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
