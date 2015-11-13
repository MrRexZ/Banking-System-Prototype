package eventhandling
import scalafx.scene.control._      
import scalafx.scene.control.TextField
import scalafx.scene.control.Button
import scalafx.scene.control.ListView
import scalafx.event.ActionEvent
import scalafxml.core.macros.sfxml
import scalafx.scene.layout.AnchorPane
import mainclasses._
import scalafx.application.Platform
import scala.collection.mutable.ArrayBuffer
import scalafx.scene.layout._
import mainclasses._
import scalafx.event.ActionEvent
import scalafx.scene.control.Label
import scalafx.scene.Parent
import scalafx.scene.layout._
import mainclasses._
import scalafx.event.ActionEvent

@sfxml
class RegistrationLogin(
    private val r_fname: TextField,
    private val r_lname : TextField,
    private val r_username : TextField,
    private val r_dob : DatePicker,
    private val log_username : TextField,
    private val log_password : TextField,
    private val r_address : TextArea,
    private val r_contactnum : TextField,
    private val r_password : PasswordField,
    private val r_confirmpass : PasswordField,
    private val r_male : RadioButton,
    private val r_female : RadioButton,
    private val r_other : RadioButton,
    private val r_nation : TextField,
    private val radioGroup : ToggleGroup
    ) {
  
      var gender : String = null

  def foo(e : ActionEvent) {
}
    def openRegistrationForm(e : ActionEvent) {
  Main.roots.setCenter(Main.registrationpage)
}
   // var u_uname : String,var u_password : String,var u_fname : String = null, var u_lname: String= null, var u_address : String=null,var u_contact : String=null,var u_gender : String=null,var u_dob : String = null,var u_nation : String=null,var u_accno : String = null
    def createUser(e : ActionEvent) {
    if (r_male.selected.value==true) gender="M"
    if (r_female.selected.value==true) gender="F"
    if (r_other.selected.value==true) gender="Other"
  
       Main.user += new User(r_username.text.value,r_password.text.value, r_fname.text.value, r_lname.text.value,r_address.text.value,r_contactnum.text.value,gender,r_dob.getValue().toString(),r_nation.text.value,Main.counteraccno.toString(),1000)
    println(Main.counteraccno)
    Main.counteraccno=Main.counteraccno+1
   
//Main.user += new User(r_username.text.value,r_password.text.value,r_fname.text.value, r_lname.text.value,r_address.text.value,r_contactnum.text.value,"M",r_dob.getValue().toString(),r_nation.text.value)
        Main.roots.setCenter(Main.mainpage)

    }
    
    def login(e : ActionEvent) {
      for (y <- 0 until Main.user.length) {
        if (Main.user(y).u_uname == log_username.text.value){
       //Main.controller.displayusername.setText("Welcome " + log_username.text.value)
          Main.usercontroller.displayusername.text="Welcome " + log_username.text.value
         Main.loggedin=y
          Main.roots.setCenter(Main.userpageroot)
        }
      }
      
      for (y <- 0 until Main.admin.length) {
        if (Main.admin(y).s_uname == log_username.text.value){
                  Main.roots.setCenter(Main.adminroot)
        }
      }
    }
    def quitwindow(e :ActionEvent) {
      Platform.exit()
    }
}