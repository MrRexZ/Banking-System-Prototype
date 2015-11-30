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
  user += new User("D_arc","123","David","Manes","20 Fife Rd, Kingston upon Thames, Greater London KT1 1SZ, UK","+44 3069 990327","M","1995-10-21","United Kingdom","1",2000,0)
    user += new User("D_dd","456","Sam","Pinkerton","42 Kenley Rd, Kingston upon Thames, Greater London KT1 3RS, UK","+44 3069 990447","M","1990-01-02","United Kingdom","2",1500,0)
  user += new User("Apple123","789","Jennie","McKendric","Hogsmill Ln, Kingston upon Thames, Greater London KT7 0WG, UK","+44 3069 990248","F","1956-03-15","United Kingdom","3",1000,0)
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
