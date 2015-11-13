package eventhandling
import scalafx.Includes._
import javafx.{fxml => jfxf}    
import scalafx.collections.ObservableBuffer
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView;
import scalafx.scene.control.TableColumn
import scalafx.scene.control.TableView;
import scalafx.scene.control._             
import javafx.scene.control.Label
import scalafxml.core.macros.sfxml 
import scalafx.scene.layout._
import mainclasses._
import scalafx.event.ActionEvent
import javafx.{fxml => jfxf}
import java.net.URL 
import java.util
import javafx.scene.{control => jfxsc}
import javafx.scene.{layout => jfxsl}
import javafx.{event => jfxe}
import javafx.{fxml => jfxf}
import scalafx.Includes._
import javafx.scene.control.cell.PropertyValueFactory
import scalafx.scene.control.TableColumn._
import javafx.collections.FXCollections


import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.event.EventHandler
import scalafx.event.EventHandler


import mainclasses._

class AdminEventController {
  
  
   // val treeRoot = new TreeItem[User](new User("XASDAD","","","",""))
//  treeRoot.getChildren.add(new TreeItem[User](new User("ZZDAD","","","","")))

   // val characters = ObservableBuffer[User](
  //  new User("XASDAD","x","x","x","x"),
  //  new User("ZZZ","x","x","x")
 // )
 // characters += new User("XASDAD","x","x","x","x")
 // var data =FXCollections.observableArrayList(new User("A", "B","S","X","D"));
    
  @jfxf.FXML
  var tableID : javafx.scene.control.TableView[User] =_
  @jfxf.FXML
  var D1 : javafx.scene.control.TableColumn[User,String] =_
  @jfxf.FXML
  var D2 : javafx.scene.control.TableColumn[User,String] =_
    @jfxf.FXML
  var D3 : javafx.scene.control.TableColumn[User,String] =_
    @javafx.fxml.FXML
  var D4 : javafx.scene.control.TableColumn[User,String] =_
    @jfxf.FXML
  var D5 : javafx.scene.control.TableColumn[User,String] =_
  
  @jfxf.FXML
  def refresh() {
    //D1.setCellValueFactory(new PropertyValueFactory[User,String]("userName"))
    D2.setCellValueFactory(new PropertyValueFactory[User,String]("password"))
    D1.cellValueFactory_={_.value.userName}
    D1.setCellFactory(TextFieldTableCell.forTableColumn());
 //   D1.setOnEditCommit(new javafx.event.EventHandler[CellEditEvent[User, String]]{

  //  })
       /* this: EventHandler[jfxe.ActionEvent] =>
        override def handle(t : javafx.scene.control.TableColumn.CellEditEvent[User, String]) {
            ( t.getTableView().getItems().get(t.getTablePosition().getRow())).setUserName(t.getNewValue().toString());
        }*/
    /*(t : CellEditEvent[User, String]) => {
        ( t.getTableView().getItems().get(
            t.getTablePosition().getRow())
            ).setUserName(t.getNewValue());
} */
      // tableID.setItems(characters)
    }
      
 
}