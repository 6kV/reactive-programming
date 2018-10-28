package com.nk

data class Tribe(var name: String="",
                 var leader: String="",
                 var featuresTeams: MutableList<FeatureTeam> = mutableListOf())  {


    operator fun FeatureTeam.unaryPlus() {
        featuresTeams.add(this)
    }

}

infix fun Tribe.withBoss(name:String){
    leader = name
}

data class FeatureTeam(var teamName: String = "",
                       var members: MutableList<Member> = mutableListOf() ) {

    operator fun Member.unaryMinus() {
        members.add(this)
    }

    operator fun invoke(test: String){
        teamName = test
    }


}

data class Member(var name: String = "",
                  var role: Role = Role.DEVELOPER)

enum class Role {
    DEVELOPER,
    PPO,
    UX,
    QA
}

fun tribe(name:String, block: Tribe.() -> Unit): Tribe {
    val tribe = Tribe(name= name)
    block(tribe)
    return tribe
}

fun featureTeam(name: String, block: FeatureTeam.() -> Unit): FeatureTeam {
    val featureTeam = FeatureTeam(teamName = name)
    block(featureTeam)
    return featureTeam
}

fun member(block: Member.() -> Unit): Member {
    val featureTeam = Member()
    block(featureTeam)
    return featureTeam
}

// Tribe {
// name =
// leader =
// + FeatureTeam {
//

infix fun String.`as a`(role: Role) : Member {
    return Member(this,role)
}

fun main(args: Array<String>) {


    val tribe = Tribe("Ze Tribe", "Ze Boss", mutableListOf(
            FeatureTeam("F1", mutableListOf(Member("the ppo", Role.PPO),
                    Member ("the developer", Role.DEVELOPER),
                    Member ("Olive et tom", Role.UX)))))

    val ux_designer = Role.UX
    val developer = Role.DEVELOPER

    val dslTribe = tribe("Ze Tribe") {

        this withBoss "Ze Boss"

        + featureTeam("F1") {
            - ("the ppo" `as a`  Role.PPO)
            - ("the developer" `as a` developer)
            - ("Olive et tom" `as a` ux_designer)
        }
    }


    println("tribe = ${tribe}")
    println("tribe = ${dslTribe}")
    println(tribe == dslTribe)

}