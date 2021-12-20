package simulations
import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class SearchByNation extends Simulation{

  var httpComf =  http.baseUrl("https://datausa.io/api")
    .header("Accept", "application/json")
    .header("content-type", "application/json")

  val sentHeaders = Map(
    "Content-Type" -> "application/javascript",
    "Accept" -> "text/html"
  )

  //scenario

  def getDataByNation() = { exec(http("GetByNation")
    .get("/data?measures=Population&Nation=%27United%20States%27")
    .check(status is 200))
  }

  var scen = scenario("SearchByState")
    .exec(getDataByNation())


  // set up
  setUp(scen.inject(atOnceUsers(20))).protocols(httpComf)

}