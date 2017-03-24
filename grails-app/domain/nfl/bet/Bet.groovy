package nfl.bet


class Bet {
    int type
    Team beatingTeam
    User user
    Result result

    static constraints = {
      result nullable: true
    }
}
