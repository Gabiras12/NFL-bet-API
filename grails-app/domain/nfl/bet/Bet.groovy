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
      user nullable: true
      points default: 0, nullable: true
      hasBeenComputed default: false, nullable: true
      hit default: false, nullable: true
    }
}
