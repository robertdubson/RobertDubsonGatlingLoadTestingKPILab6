package simulations
import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class SearchDataByPopulation extends  Simulation{

  //

  var httpComf =  http.baseUrl("https://datausa.io/api")
    .header("Accept", "application/json")
    .header("content-type", "application/json")

  val sentHeaders = Map(
    "Content-Type" -> "application/javascript",
    "Accept" -> "text/html"
  )

  //scenario

  def getDataByPopulation() = { exec(http("SearchRequestByPopulation")
    .get("/data?measures=Population")
    .check(status is 200))
  }

  var scen = scenario("SearchByNations")
    .exec(getDataByPopulation())

  // set up
  setUp(scen.inject(atOnceUsers(5))).protocols(httpComf)

}