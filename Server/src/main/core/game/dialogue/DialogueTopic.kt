package core.game.dialogue

open class Topic<T>(val expr: FacialExpression, val text: String, val toStage: T, val skipPlayer: Boolean = false) {
    constructor(text: String, toStage: T, skipPlayer: Boolean = false) : this(FacialExpression.ASKING, text, toStage, skipPlayer)
}
class IfTopic<T>(expr: FacialExpression, text: String, toStage: T, val showCondition: Boolean, skipPlayer: Boolean = false) : Topic<T>(expr, text, toStage, skipPlayer) {
    constructor(text: String, toStage: T, showCondition: Boolean, skipPlayer: Boolean = false) : this(FacialExpression.ASKING, text, toStage, showCondition, skipPlayer)
}