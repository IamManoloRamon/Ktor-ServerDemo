package ae.therafits

import ae.therafits.plugins.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

//fun main(args: Array<String>): Unit =
//    io.ktor.server.netty.EngineMain.main(args)
//
//@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
//fun Application.module() {
//    configureRouting()
}


fun main() {
    embeddedServer(Netty, port = 8080) {
        //TODO: 9. RequestsResponses 13.11
        module();
    }.start(wait = true)
}

fun Application.module() {
    install(Routing) {
        get("/") {
            call.respondText("ktor Server!")
        }
        get("/users/{username}") {
            val username = call.parameters["username"]
            val header = call.request.headers["Connection"]
            if (username == "admin") {
                call.response.header(name = "CustomHeader", value = "Admin")
                call.respond(message = "Hello Admin", status = HttpStatusCode.OK)
            }
            call.respondText("Greetings, $username with header: $header")
        }
        get("/user") {
            val name = call.request.queryParameters["name"]
            val age = call.request.queryParameters["age"]
            call.respondText("Hi, my name is $name, I'm $age years old!")
        }
        get("/person") {
            try {
                val person = Person(name = "John", age = 26)
                call.respond(message = person, status = HttpStatusCode.OK)
            } catch (e: Exception) {
                call.respond(message = "${e.message}", status = HttpStatusCode.BadRequest)
            }
        }
    }
}