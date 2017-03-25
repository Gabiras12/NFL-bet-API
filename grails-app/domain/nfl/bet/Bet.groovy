package nfl.bet


class Bet {
    int type
    float points
    boolean hasBeenComputed
    boolean hit
    Team beatingTeam
    User user
    static belongsTo = [game : Game]

    static constraints = {
    }
}
