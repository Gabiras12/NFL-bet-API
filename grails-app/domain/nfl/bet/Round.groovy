package nfl.bet

class Round {

    Date startDate
    Date endDate
    Date betLimitDate
    boolean roundHasBeenFineshed

    static belongsTo = [championship : Championship]
    static hasMany = [games : Game]

    static constraints = {
      roundHasBeenFineshed default: false
    }
}
