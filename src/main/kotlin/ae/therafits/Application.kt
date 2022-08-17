package ae.therafits

import ae.therafits.plugins.*
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
//}


fun main() {
    embeddedServer(Netty, port = 8080) {
        module();
    }.start(wait = true)
}
fun Application.module() {
   routing {
       get("/") {
           call.respondText("Hello Ktor!")
       }
   }
}