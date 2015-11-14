package eventhandling
import scalafx.Includes._    
import scalafxml.core.macros.sfxml  
import scalafx.scene.control.{TableView,TableColumn,Label,Alert,ScrollPane,TextField}
import mainclasses._
import address._
import scalafx.Includes._
import javafx.beans.property.StringProperty
import scalafx.event.ActionEvent
import scalafx.scene.input.MouseEvent
import scalafx.scene.layout.Priority
import javax.swing.JOptionPane;
import scalafx.collections.ObservableBuffer

@sfxml
class AdminEventController(
    private var tableID: TableView[User],
    private var col_uname : TableColumn[User,String],
    private var col_balance : TableColumn[User,String],
    private var col_fname : TableColumn[User,String],
    private var col_lname : TableColumn[User,String],
    private var col_password : TableColumn[User,String],
    private var col_address : TableColumn[User,String],
    private var col_contact : TableColumn[User,String],
    private var col_gender : TableColumn[User,String],
    private var col_dob: TableColumn[User,String],
    private var col_nation : TableColumn[User,String],
    private var col_accno : TableColumn[User,String],
     var scrollpane : ScrollPane,
     private var search: TextField) {

    col_uname.cellValueFactory = {_.value.userName}
    col_balance.cellValueFactory = {_.value.balance}
    col_fname.cellValueFactory = {_.value.firstName}
    col_lname.cellValueFactory = {_.value.lastName}
    col_password.cellValueFactory = {_.value.password}
    col_address.cellValueFactory = {_.value.location}
    col_contact.cellValueFactory = {_.value.contact}
    col_gender.cellValueFactory = {_.value.gender}
    col_dob.cellValueFactory = {_.value.dob}
    col_nation.cellValueFactory = {_.value.nation}
    col_accno.cellValueFactory = {_.value.accno}
    col_gender.cellValueFactory = {_.value.gender}
      
    tableID.items=Main.user
    scrollpane.fitToHeight=true
    scrollpane.fitToWidth=true
   //personTable.items = MyApp.personData

def startSearch(e :ActionEvent ) {
      tableID.items=null
      var tempCollections =ObservableBuffer[User]()
      for (counter <- 0 to Main.user.length-1)
        if (search.text.value==Main.user(counter).u_uname.substring(0,search.text.value.length))
          tempCollections += Main.user(counter)
      
       tableID.items=tempCollections
      if (tableID.items==null)
           JOptionPane.showMessageDialog(null, "No Keyword found");
    }
 def refresh(e : ActionEvent) {
   
 }
    
      def logout(event: ActionEvent) {
    Main.roots.setCenter(Main.mainpage)
    }
}