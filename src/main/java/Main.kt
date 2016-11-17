private val facts = mutableMapOf<String, MutableList<String>>()
private var addedFather = false
private var addedChild = false

fun main(args: Array<String>) {
    Father("Anders", "Christian")
    Father("Erik", "Anders")
    Father("Erik", "Dorte")
    Father("Niels", "Erik")
    println(!Father("Erik", "Anders"))
    println(!Father("Erik", "Lort"))
    println(!Father("Erik", "Dorte"))
}

data class Father(val father: String, val child: String) {
    init {
        reset()
        if (!facts.containsKey(father)) {
            addedFather = true
            facts.put(father, mutableListOf(child))
        } else {
            addedChild = true
            facts[father]?.add(child)
        }
    }

    operator fun not(): Boolean {
        // Undo the invoke of Father before running query
        if (addedFather) facts.remove(this.father)
        if (addedChild) facts[this.father]?.removeLast()

        // Everybody is God's child
        if (this.father == "God") return true

        return facts[this.father]?.any { it == this.child } ?: false
    }
}

private fun reset() {
    addedFather = false
    addedChild = false
}

fun MutableList<String>.removeLast () {
    removeAt(lastIndex)
}