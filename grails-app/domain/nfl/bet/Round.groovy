package nfl.bet

class Round {

    Date startDate
    Date endDate
    Date betLimitDate

    static belongsTo = [championship : Championship]
    static hasMany = [games : Game]

    static constraints = {
    }
}
