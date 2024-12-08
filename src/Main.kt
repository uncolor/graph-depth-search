import java.util.*

fun main() {
    val anuj = Vertex("Anuj", isMangoSeller = true)
    val peggy = Vertex("Peggy")
    val alice = Vertex("Alice")
    val jonny = Vertex("Jonny")
    val tom = Vertex("Tom")
    val bob = Vertex("Bob")
    val claire = Vertex("Claire")
    val vlad = Vertex("Vlad")

    val graph = mutableMapOf<Vertex, List<Vertex>>()

    graph[vlad] = listOf(bob, claire, alice)
    graph[bob] = listOf(anuj, peggy)
    graph[alice] = listOf(peggy)
    graph[claire] = listOf(tom, jonny)
    graph[anuj] = listOf()
    graph[peggy] = listOf()
    graph[tom] = listOf()
    graph[jonny] = listOf( )

    val mangoSeller = findMangoSellerByDepthSearch(root = vlad, graph = graph)

    println("Mango seller is $mangoSeller")
}

/**
 * Модель узла графа
 *
 * @property name Имя узла
 * @property isMangoSeller Флаг, условно обозначающий искомый элемент (в данном случае мы ищем продавца манго)
 */
class Vertex(
    val name: String,
    val isMangoSeller: Boolean = false,
) {
    override fun toString(): String {
        return name
    }
}

/**
 * Поиск продавца манго в одностороннем графе с помощью алгоритма поиска в ширину
 *
 * @param root Корневой узел, с которого начинается поиск по графу
 * @param graph Граф, содержащий все узлы графа и связи между ними
 */
fun findMangoSellerByDepthSearch(root: Vertex, graph: MutableMap<Vertex, List<Vertex>>): Vertex? {
    val visitedVertexes = mutableSetOf<Vertex>()
    val queue: Queue<Vertex> = LinkedList()
    queue.add(root)
    while (queue.isNotEmpty()) {
        val vertex = queue.poll()
        if (visitedVertexes.contains(vertex)) {
            continue
        }
        println("Checking ${vertex.name}...")
        if (vertex.isMangoSeller) {
            return vertex
        } else {
            visitedVertexes.add(vertex)
            queue.addAll(graph[vertex].orEmpty())
        }
    }
    return null
}