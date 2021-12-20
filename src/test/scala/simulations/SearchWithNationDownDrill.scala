package simulations
import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class SearchWithNationDownDrill extends Simulation{

  var httpComf =  http.baseUrl("https://datausa.io/api")
    .header("Accept", "application/json")
    .header("content-type", "application/json")

  val sentHeaders = Map(
    "Content-Type" -> "application/javascript",
    "Accept" -> "text/html"
  )

  //scenario

  def getDataWithNationsDrill() = { exec(http("SortByNation")
    .get("/data?drilldowns=Nation&measures=Population")
    .check(status is 200))
  }

  var scen = scenario("SearchByState")
    .exec(getDataWithNationsDrill())


  // set up
  setUp(scen.inject(atOnceUsers(15))).protocols(httpComf)

}