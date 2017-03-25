package nfl.bet

class Championship {

    int championshipNumber
    int championshipYear

    static hasMany = [teams : Team, rounds : Round]

    static constraints = {
      championshipNumber unique: true
    }
}
