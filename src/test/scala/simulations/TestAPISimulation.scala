package simulations
import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class TestAPISimulation extends Simulation {

  //http conf

  var httpComf =  http.baseUrl("https://datausa.io/api")
    .header("Accept", "application/json")
    .header("content-type", "application/json")

  val sentHeaders = Map(
    "Content-Type" -> "application/javascript",
    "Accept" -> "text/html"
  )

  //scenario

  var scen = scenario("SearchByNations")
    .exec(http("SearchRequest")
      .get("/data?drilldowns=Nation&measures=Population")
      .headers(sentHeaders)
      .check(status is 200))

  // set up
  setUp(scen.inject(atOnceUsers(15))).protocols(httpComf)
}

