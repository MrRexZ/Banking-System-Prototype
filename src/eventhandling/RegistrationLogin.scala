package eventhandling

import scalafx.Includes._   
import mainclasses._
import address._
import scalafx.scene.control._       
import scalafx.scene.control.TextField
import scalafx.scene.control.Button
import scalafx.scene.control.ListView
import scalafx.event.ActionEvent
import scalafxml.core.macros.sfxml
import scalafx.scene.layout.AnchorPane
import scalafx.application.Platform
import scala.collection.mutable.ArrayBuffer
import scalafx.scene.layout._

import scalafx.event.ActionEvent
import scalafx.scene.control.Label
import scalafx.scene.Parent
import scalafx.scene.layout._
import scalafx.event.ActionEvent
import javax.swing.JOptionPane;
import java.time.LocalDate

@sfxml
class RegistrationLogin(
    var r_fname: TextField,
    var r_lname : TextField,
    var r_username : TextField,
    var r_dob : DatePicker,
    var log_username : TextField,
    var log_password : TextField,
    var r_address : TextArea,
    var r_contactnum : TextField,
    private var r_password : PasswordField,
    private var r_confirmpass : PasswordField,
    private var r_male : RadioButton,
    private var r_female : RadioButton,
    private var r_other : RadioButton,
    private var r_nation : TextField,
    private var radioGroup : ToggleGroup
    ) {

      var gender : String = null
  def foo(e : ActionEvent) {
}
    def openRegistrationForm(e : ActionEvent) {
  Main.roots.setCenter(Main.registrationpage)
  

  
}
    def createUser(e : ActionEvent) {
    if (r_male.selected.value==true) gender="M"
    if (r_female.selected.value==true) gender="F"
    if (r_other.selected.value==true) gender="Other"
   
   if (r_dob.value.value==null) JOptionPane.showMessageDialog(null, "Please enter your birthdate");
   else if (r_username.text.value=="") JOptionPane.showMessageDialog(null, "Please enter your username");
   else if (r_password.text.value=="") JOptionPane.showMessageDialog(null, "Please enter your password");
   else if (r_username.text.value=="") JOptionPane.showMessageDialog(null, "Please enter your username");
   else if (r_password.text.value!=r_confirmpass.text.value) JOptionPane.showMessageDialog(null, "Your password do not match. Please retype your password.");
   else if (r_fname.text.value=="" || r_lname.text.value=="") JOptionPane.showMessageDialog(null, "Please enter your first name and/or last name");
   else {Main.user += new User(r_username.text.value,r_password.text.value, r_fname.text.value, r_lname.text.value,r_address.text.value,r_contactnum.text.value,gender,r_dob.getValue().toString(),r_nation.text.value,Main.counteraccno.toString(),1000)
    Main.counteraccno=Main.counteraccno+1

    Main.roots.setCenter(Main.mainpage)}

    }
    
    def login(e : ActionEvent) {
      for (y <- 0 until Main.user.length) {
        if (Main.user(y).u_uname == log_username.text.value){
       //Main.controller.displayusername.setText("Welcome " + log_username.text.value)
          Main.usercontroller.displayusername.text="Welcome " + log_username.text.value + LocalDate.now
         Main.loggedin=y
          Main.roots.setCenter(Main.userpageroot)
        }
        
      }
      
      for (y <- 0 until Main.admin.length) {
        if (Main.admin(y).s_uname == log_username.text.value){
          Main.admincontroller.tableID.items=null
          Main.admincontroller.tableID.items=Main.user
                  Main.roots.setCenter(Main.adminroot)
                  Main.admincontroller.personaldetailspanel.visible=false
                  Main.admincontroller.scrollpane.visible=true
             
        }
      }
    }
    def quitwindow(e :ActionEvent) {
      Platform.exit()
    }
}