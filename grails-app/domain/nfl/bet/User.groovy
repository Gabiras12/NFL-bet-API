package nfl.bet

class User {

    String name
    String email
    String password

    static hasMany = [bets: Bet]

    static constraints = {
    }
}
