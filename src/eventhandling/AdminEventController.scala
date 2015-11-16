package eventhandling
import scalafx.Includes._     
import scalafxml.core.macros.sfxml  
import scalafx.scene.control.{Label,TextField,TableView,TableColumn,Alert,ScrollPane,DatePicker,TextArea,PasswordField}
import mainclasses._
import address._
import scalafx.Includes._
import javafx.beans.property.StringProperty
import scalafx.event.ActionEvent
import scalafx.scene.input.MouseEvent
import scalafx.scene.layout.Priority
import javax.swing.JOptionPane;
import scalafx.collections.ObservableBuffer
import javafx.scene.control.cell.TextFieldTableCell;
import scalafx.scene.layout.AnchorPane
import java.lang.Object


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
     private var search: TextField,
     

    var personaldetailspanel : AnchorPane,
    var pd_fname : scalafx.scene.control.Label,
    var pd_lname : Label,
    var pd_gender : Label,
    var pd_dob : Label, 
    var pd_nation : Label, 
    var pd_address : Label,
    var pd_contactno : Label, 
    var pd_uname : Label,  
    var pd_password :Label,
    var edit_fname : TextField,
    var edit_lname : TextField,
    var edit_gender : TextField,
    var edit_dob : DatePicker,
    var edit_nation : TextField,
    var edit_address : TextArea,
    var edit_contactno : TextField,
    var edit_uname : TextField,
    var edit_password :PasswordField   ) {

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
  tableID.visible=true
  var selectedAcc : String=null;
tableID.selectionModel().selectedItem.onChange(
      (_, _, newValue) => selectedAcc=newValue.u_accno)
  /* tableID.selectionModel().selectedItem.onChange(
      (_, _, newValue) => {col_uname.setCellFactory(TextFieldTableCell.forTableColumn());
      Main.user(0).u_uname=newValue.u_uname
      println(newValue.u_uname)
      col_uname.setCellFactory(TextFieldTableCell.forTableColumn())}
    )
   col_uname.cellFactory = _ => new TextFieldTableCell[Person, String] (new DefaultStringConverter())

//tableID.selectionModel().selectedItem.onChange((_, _, newValue) => col_uname.setCellFactory(TextFieldTableCell.forTableColumn()))
//col_uname.cellFactory = _ => new TextFieldTableCell[User, String] (new DefaultStringConverter())  

col_uname.onEditCommit = (evt: CellEditEvent[User, String]) => { 
    println("hoi")
    val person = evt.rowValue
    val newLastNameVal = evt.newValue
    // Update current person data set
    person.u_uname = newLastNameVal}
    
   */ 
def startSearch(e :ActionEvent ) {
         tableID.items=null
      var tempCollections =ObservableBuffer[User]()
      for (counter <- 0 to Main.user.length-1)
        if (search.text.value==Main.user(counter).u_uname.substring(0,search.text.value.length))
          tempCollections += Main.user(counter)
       tableID.items=tempCollections

    }
 def refresh(e : ActionEvent) {
   
 }
    def confirmChanges(e :ActionEvent) {
       Main.user(selectedAcc.toInt-1).updateInfo()
    }
    
    def editSelected (e : ActionEvent){
      personaldetailspanel.visible=true
      scrollpane.visible=false
     edit_fname.text.value= Main.user(selectedAcc.toInt-1).u_fname
      edit_lname.text.value= Main.user(selectedAcc.toInt-1).u_lname
      edit_gender.text.value= Main.user(selectedAcc.toInt-1).u_gender
      //edit_dob = Main.user(selectedAcc.toInt-1).u_dob
      edit_nation.text.value = Main.user(selectedAcc.toInt-1).u_nation
      edit_address.text.value =Main.user(selectedAcc.toInt-1).u_address
      edit_contactno.text.value=Main.user(selectedAcc.toInt-1).u_contact
      edit_uname.text.value=Main.user(selectedAcc.toInt-1).u_uname
      edit_password.text.value=Main.user(selectedAcc.toInt-1).u_password
     
    }
      def logout(event: ActionEvent) {
    Main.roots.setCenter(Main.mainpage)
    }
}