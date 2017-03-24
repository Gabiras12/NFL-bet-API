package nfl.bet

class Result {

    Double points
    boolean right

    static belongsTo = [bet : Bet]

    static constraints = {
    }
}
