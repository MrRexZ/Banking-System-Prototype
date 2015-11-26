package mainclasses

import scalafx.Includes._          
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.layout.BorderPane
import scalafx.scene.control.Label
import scalafx.application.JFXApp  
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import address.util.ResourceLoading._
import scala.collection.mutable.ArrayBuffer
import scalafxml.core.{NoDependencyResolver, FXMLLoader}
import scalafx.delegate.SFXDelegate
import scalafx.scene.Node
import scalafx.scene.layout.AnchorPane
import scala.collection.mutable.Map
import scalafx.collections.ObservableBuffer

import scala.util.Random
object Main extends JFXApp{
  var user = ObservableBuffer[User]()
  var AccNoToIndex= scala.collection.mutable.Map[Int,Int]()

  var admin= ArrayBuffer[Admin]()
  admin += new Admin("admin","admin","","","","")
  var transactionList= ObservableBuffer[TransactionRecords]()
  var generatedRandInterest=0
 val registrationpagelayout="view/registrationform.fxml".loadFXML();
 val registercontroller=registrationpagelayout.getController[eventhandling.RegistrationLogin#Controller]
 val registrationpage=registrationpagelayout.getRoot[javafx.scene.layout.AnchorPane]

 val userloader="view/userpage.fxml".loadFXML();    
 val usercontroller=userloader.getController[eventhandling.UserEventController#Controller]()
 val userpageroot=userloader.getRoot[javafx.scene.layout.AnchorPane]

  val adminloader = "view/adminpage.fxml".loadFXML();  
  val admincontroller=adminloader.getController[eventhandling.AdminEventController#Controller]()
  val adminroot=adminloader.getRoot[javafx.scene.layout.AnchorPane]

 val rootlayout="view/RootLayout.fxml".loadFXML();
 val roots = rootlayout.getRoot[javafx.scene.layout.BorderPane]

 val mainlayout = "view/mainpage.fxml".loadFXML(); 
 val mainpage=mainlayout.getRoot[javafx.scene.layout.AnchorPane]
 roots.setCenter(mainpage)
 
    
  def enable(node:Node*) {
     node.foreach(_.visible =true)
  }
   def disable(node:Node*) {
     node.foreach(_.visible =false)
  }
    
  var countertransaction=1;
  var loggedin : Integer = 1;

  stage = new PrimaryStage(){
    scene=new Scene(800,550) {
      root=roots
    }
    
  }
}
