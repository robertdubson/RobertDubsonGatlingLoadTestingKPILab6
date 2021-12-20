package simulations
import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class SearchDataByYear extends  Simulation{

  var httpComf =  http.baseUrl("https://datausa.io/api")
    .header("Accept", "application/json")
    .header("content-type", "application/json")

  val sentHeaders = Map(
    "Content-Type" -> "application/javascript",
    "Accept" -> "text/html"
  )

  //scenario

  def getDataByYear() = { exec(http("SearchByYearRequest")
    .get("/data?drilldowns=State&measures=Population&year=1997")
    .check(status is 200))
  }

  var scen = scenario("SearchByYear")
    .exec(getDataByYear())


  // set up
  setUp(scen.inject(atOnceUsers(15))).protocols(httpComf)

}