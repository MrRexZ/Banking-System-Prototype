package mainclasses.util
import java.net.URL   
import scalafxml.core.{NoDependencyResolver, FXMLLoader}
import scala.util.{ Try, Success, Failure }
import java.io.IOException
import mainclasses.Main
import scalafx.Includes._

object ResourceLoading {
  private def load(filename : String): URL = {
    Try(Main.getClass.getResource(filename)) match {
      case Success(resource) =>
        if (resource == null) throw new IOException(s"Cannot load resource: $filename")
        else resource
      case Failure(r) => throw new IOException(r)
    }
  }
  
  implicit class ResourceLoading(filename: String) {
    def loadFXML(): FXMLLoader = {
      Try(new FXMLLoader(load(filename), NoDependencyResolver)) match {
        case Success(resource) => {
          resource.load()
          resource
          }
        case Failure(r) => throw new IOException(r)
      }
    }
  }
}
