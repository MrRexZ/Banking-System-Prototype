package eventhandling
import scalafx.Includes._       
import scalafxml.core.macros.sfxml  
import scalafx.scene.control.{Label,TextField,TableView,TableColumn,Alert,ScrollPane,DatePicker,TextArea,PasswordField,CheckBox,RadioButton}
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
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import scala.collection.mutable.ArrayBuffer
 

@sfxml
class AdminEventController(
    private var querydisplay : Label,
    var queryScrollPane : ScrollPane,
    
     var tableUser: TableView[User],
     var col_uname : TableColumn[User,String],
     var col_balance : TableColumn[User,String],
     var col_fname : TableColumn[User,String],
     var col_lname : TableColumn[User,String],
     var col_password : TableColumn[User,String],
     var col_address : TableColumn[User,String],
     var col_contact : TableColumn[User,String],
     var col_gender : TableColumn[User,String],
     var col_dob: TableColumn[User,String],
     var col_nation : TableColumn[User,String],
     var col_accno : TableColumn[User,String],
     var col_status : TableColumn[User,String],
     var col_debt   : TableColumn[User,String],
     var col_yo : TableColumn[User,String],
     var userScrollPane : ScrollPane,
     
     
     private val transactionTable : TableView[TransactionRecords],
     private val col_transactionno : TableColumn[TransactionRecords,String],
     private val col_transactionamount :TableColumn[TransactionRecords,String],
     private val col_senderno : TableColumn[TransactionRecords,String],
     private val col_receiverno : TableColumn[TransactionRecords,String],
     private val transactionScrollPane : ScrollPane,
     
     
     var search: TextField,
     
     

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
    var edit_password :TextField,  
    
    private var fil_uname : CheckBox,
    private var fil_password : CheckBox,
    private var fil_nation : CheckBox,
    private var fil_fname : CheckBox,
    private var fil_lname : CheckBox,
    private var fil_status : CheckBox,
    private var r_and : RadioButton,
    private var r_or : RadioButton) {

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
    col_status.cellValueFactory = {_.value.status}
    col_debt.cellValueFactory = {_.value.debtPr}
    
    col_transactionno.cellValueFactory = {_.value.noTr}
    col_transactionamount.cellValueFactory = {_.value.amountTr}
    col_senderno.cellValueFactory = {_.value.senderNoTr}
    col_receiverno.cellValueFactory = {_.value.recipientNoTr}
    
    var searchQuery=ArrayBuffer[String]()
    var tempCollections =ObservableBuffer[User]()
    var tempType = ArrayBuffer[Tuple2[String,User]]()
    var collectionArray= ArrayBuffer[ArrayBuffer[Tuple2[String,User]]]();
    var count=0;
    
    tableUser.items=Main.user
    transactionTable.items=Main.transactionList
    userScrollPane.fitToHeight=true
    userScrollPane.fitToWidth=true
    transactionScrollPane.fitToHeight=true
    
  tableUser.editable=true
  var selectedAcc : String=null;
tableUser.selectionModel().selectedItem.onChange(
     (_, _, newValue) => selectedAcc=newValue.u_accno)

def startSearch(e :ActionEvent ) {
  
  if (searchQuery.length>0){
   tempCollections.clear
  var limit=count/Main.user.length
  
  if (r_or.selected.value==true)  searchOR(collectionArray);
   if (r_and.selected.value==true) searchAND(collectionArray,limit);
  }
 }
def searchOR(copyUser : ArrayBuffer[ArrayBuffer[Tuple2[String,User]]]) {
  
      tableUser.items=null
     var filtered=scala.collection.mutable.Map[User,Int]()
    for (loop <- 0 to searchQuery.length-1) 
     for (counter <- 0 to copyUser(loop).length-1)
        if (searchQuery(loop).length <= copyUser(loop)(counter)._1.length)  
          if (searchQuery(loop)==copyUser(loop)(counter)._1.substring(0,searchQuery(loop).length)){

          if (filtered.get(copyUser(loop)(counter)._2)==None){
            filtered += (copyUser(loop)(counter)._2 -> 0) 
            tempCollections += copyUser(loop)(counter)._2
          }

          } 
     filtered.clear()
     tableUser.items=tempCollections
       
}
def searchAND(copyUser : ArrayBuffer[ArrayBuffer[Tuple2[String,User]]], limit : Int){
  
      tableUser.items=null
     var filtered=scala.collection.mutable.Map[User,Int]()
     println(filtered)
    for (loop <- 0 to searchQuery.length-1) {
     for (counter <- 0 to copyUser(loop).length-1)
        if (searchQuery(loop).length <= copyUser(loop)(counter)._1.length)  
          if (searchQuery(loop)==copyUser(loop)(counter)._1.substring(0,searchQuery(loop).length)){
           
          if (filtered.get(copyUser(loop)(counter)._2)==None)      
            filtered += (copyUser(loop)(counter)._2 -> 0)   
          else if (filtered.get(copyUser(loop)(counter)._2)!=None) {
            filtered(copyUser(loop)(counter)._2) += 1
          }
          if (filtered(copyUser(loop)(counter)._2) == limit-1) 
            tempCollections += copyUser(loop)(counter)._2
          } 
    }
     tableUser.items=tempCollections
}
 def refresh(e : ActionEvent) {
   tableUser.items=Main.user
   tempCollections.clear()
   searchQuery.clear()
   tempType.clear()
   collectionArray.clear()
   querydisplay.text.value=""
   count=0
 }
 
 def updateTable(ArrayIndex : Int) {
      Main.user(ArrayIndex).userName.value=Main.user(ArrayIndex).u_uname
      Main.user(ArrayIndex).firstName.value=Main.user(ArrayIndex).u_fname
      Main.user(ArrayIndex).lastName.value=Main.user(ArrayIndex).u_lname
      Main.user(ArrayIndex).password.value=Main.user(ArrayIndex).u_password
      Main.user(ArrayIndex).location.value=Main.user(ArrayIndex).u_address
      Main.user(ArrayIndex).contact.value=Main.user(ArrayIndex).u_contact
      Main.user(ArrayIndex).nation.value=Main.user(ArrayIndex).u_nation
      Main.user(ArrayIndex).accno.value=Main.user(ArrayIndex).u_accno
      Main.user(ArrayIndex).balance.value=Main.user(ArrayIndex).u_balance.toString()
      Main.user(ArrayIndex).dob.value=Main.user(ArrayIndex).u_dob
      Main.user(ArrayIndex).gender.value=Main.user(ArrayIndex).u_gender
      Main.user(ArrayIndex).status.value=Main.user(ArrayIndex).p_status
 }
    def confirmChanges(e :ActionEvent) { 
      
      Main.user(Main.user.indexWhere( _.u_accno == selectedAcc)).updateInfo(Main.admincontroller)
      updateTable(Main.user.indexWhere( _.u_accno == selectedAcc))
      personaldetailspanel.visible=false
      userScrollPane.visible=true
    }
    
    def editSelected (e : ActionEvent){
     personaldetailspanel.visible=true
     userScrollPane.visible=false
     edit_fname.text.value= Main.user(Main.user.indexWhere( _.u_accno == selectedAcc)).u_fname
      edit_lname.text.value= Main.user(Main.user.indexWhere( _.u_accno == selectedAcc)).u_lname
      edit_gender.text.value= Main.user(Main.user.indexWhere( _.u_accno == selectedAcc)).u_gender
      edit_dob.value = LocalDate.parse(Main.user(Main.user.indexWhere( _.u_accno == selectedAcc)).u_dob, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
      edit_nation.text.value = Main.user(Main.user.indexWhere( _.u_accno == selectedAcc)).u_nation
      edit_address.text.value =Main.user(Main.user.indexWhere( _.u_accno == selectedAcc)).u_address
      edit_contactno.text.value=Main.user(Main.user.indexWhere( _.u_accno == selectedAcc)).u_contact
      edit_uname.text.value=Main.user(Main.user.indexWhere( _.u_accno == selectedAcc)).u_uname
      edit_password.text.value=Main.user(Main.user.indexWhere( _.u_accno == selectedAcc)).u_password

    }
    
    def removeElement (e : ActionEvent) {
        Main.user.remove(Main.user.indexWhere( _.u_accno == selectedAcc))
        if (tempCollections.length>0)
         tableUser.items().remove(tempCollections.indexWhere( _.u_accno == selectedAcc))
        
     /* var counter=0   
      while (Main.user(counter).u_accno!=selectedAcc)     
        counter=counter+1
      Main.user.remove(counter)
      tableUser.items=Main.user*/
    }
    
    def addQuery (e :ActionEvent) {
    searchQuery += search.text.value
    querydisplay.text.value = querydisplay.text.value + search.text.value + ","
    search.clear()
    
    var tempType = ArrayBuffer[Tuple2[String,User]]()
   for (counter <- Main.user){
   if (fil_uname.selected.value==true)    {tempType += ((counter.u_uname,counter));count=count+1}
   if (fil_password.selected.value==true) {tempType += ((counter.u_password,counter));count=count+1}
   if (fil_nation.selected.value==true)   {tempType += ((counter.u_nation,counter));count=count+1}
   if (fil_fname.selected.value==true)    {tempType += ((counter.u_fname,counter));count=count+1}
   if (fil_lname.selected.value==true)    {tempType += ((counter.u_lname,counter));count=count+1}
   if (fil_status.selected.value==true)   {tempType += ((counter.p_status,counter));count=count+1}
   }

 
    collectionArray.append(tempType)
    fil_uname.selected=false
    fil_password.selected=false
    fil_nation.selected=false
    fil_fname.selected=false
    fil_lname.selected=false
    fil_status.selected=false
    
}
    
      def openTransactionTable(event: ActionEvent){
        userScrollPane.visible=false
        transactionScrollPane.visible=true
      }
      
      def openUserTable(event : ActionEvent) {
        userScrollPane.visible=true
        transactionScrollPane.visible=false
      }
      def logout(event: ActionEvent) {
    Main.roots.setCenter(Main.mainpage)
    Main.registercontroller.loginasadmin=false

    }
}
