package nfl.bet

class Team {

    String name
    String image

    static belongsTo = [championship : Championship]

    static constraints = {
    }
}
