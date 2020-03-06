import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import kotlinx.serialization.Serializable

@Serializable
data class Todo(
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
)

val client = HttpClient(Apache) {
    install(JsonFeature) {
        serializer = KotlinxSerializer()
    }
}

suspend fun main() {
    val todo = client.get<Todo>("https://jsonplaceholder.typicode.com/todos/1")
    println(todo)
}