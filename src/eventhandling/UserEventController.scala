package eventhandling             
import scalafxml.core.macros.sfxml  
import scalafx.scene.control.{Label,TextField,TextArea,DatePicker,PasswordField}
import scalafx.scene.layout._
import mainclasses._
import scalafx.event.ActionEvent
import scalafx.Includes._
import scalafx.scene.layout.GridPane
import scalafx.scene.layout.AnchorPane
import javax.swing.JOptionPane;

@sfxml
class UserEventController(
    var displayusername : scalafx.scene.control.Label,
    var balancepanel : AnchorPane, 
    var balance : Label,
    
    
    var transferpanel : AnchorPane,
    var enter_targetacc: TextField,
    var enter_amount: TextField,
    var display_balance : Label,
    var display_sendername : Label,
    var display_senderno : Label,
    var display_destname : Label,
    var display_destno : Label,
    var display_amount : Label,
    var transactionno : Label,
    
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
    var edit_password :PasswordField,
    var edit_confirmpassword:PasswordField
    
    ) {
    
  def checkBalance(e: ActionEvent) {

  balancepanel.visible=true
  transferpanel.visible=false
  personaldetailspanel.visible=false
  balance.text.value="Your balance is : "+ Main.user(Main.loggedin).balance.toString

  }
  
  
  
  
  def openTransfer(e: ActionEvent) {
    transferpanel.visible=true
    balancepanel.visible=false
    personaldetailspanel.visible=false
    display_balance.text.value="Balance : "+Main.user(Main.loggedin).balance.toString
   Main.disable(display_sendername,display_senderno,display_destname,display_destno,display_amount)
    }
  
  def continueTransfer(e : ActionEvent) {
    Main.enable(display_sendername,display_senderno,display_destname,display_destno,display_amount)
//var c= Option(enter_targetacc.text.value)
  //  Main.show(c)
   // Main.te(enter_targetacc.text.value)
    //for (y <- 0 to Main.user.length-1){
      
            //println(Main.user(y).u_accno)
    if (enter_targetacc.text.value.toInt>0 && enter_targetacc.text.value.toInt<=Main.user.length && enter_amount.text.value.toInt>0 && enter_amount.text.value.toInt<=Main.user(Main.loggedin).balance ) {
    display_sendername.text.value = "Sender Name :" + Main.user(Main.loggedin).u_fname + " "+Main.user(Main.loggedin).u_lname
    display_senderno.text.value ="Sender Account No. :" + Main.user(Main.loggedin).u_accno
    display_destno.text.value ="Destination Account No. :" + enter_targetacc.text.value
    display_destname.text.value = "Recipient Name : " +Main.user(enter_targetacc.text.value.toInt-1).u_fname +" " +Main.user(enter_targetacc.text.value.toInt-1).u_lname
    display_amount.text.value="Amount :" + enter_amount.text.value
    }
    else if ((enter_targetacc.text.value.toInt<=0 || enter_targetacc.text.value.toInt>Main.user.length) && (enter_amount.text.value.toInt<0 || enter_amount.text.value.toInt>Main.user(Main.loggedin).balance) )
      JOptionPane.showMessageDialog(null, "Please enter the correct destination account number and check your amount");
    else if (enter_targetacc.text.value.toInt<=0 || enter_targetacc.text.value.toInt>Main.user.length)
      JOptionPane.showMessageDialog(null, "Please enter the correct destination account number");
    else if (enter_amount.text.value.toInt<=0 || enter_amount.text.value.toInt>Main.user(Main.loggedin).balance)
      JOptionPane.showMessageDialog(null, "Please enter the correct amount");

  }
  
  def confirmTransaction (e : ActionEvent){
    transactionno.text.value="Transaction No. : " + Main.countertransaction.toString
    Main.countertransaction=Main.countertransaction+1
    Main.user(Main.loggedin).balance= Main.user(Main.loggedin).balance - display_amount.text.value.substring(8).toInt
    Main.user(enter_targetacc.text.value.toInt-1).balance =Main.user(enter_targetacc.text.value.toInt-1).balance + display_amount.text.value.substring(8).toInt
  }
     

  def viewPersonalDetails(event: ActionEvent) {

    transferpanel.visible=false
    balancepanel.visible=false
  personaldetailspanel.visible=true
  
  Main.disable(edit_fname,edit_lname,edit_gender,edit_dob,edit_nation,edit_address,edit_contactno,edit_password,edit_confirmpassword)
    
    pd_fname.text.value= ": "+Main.user(Main.loggedin).u_fname
    pd_lname.text.value= ": "+Main.user(Main.loggedin).u_lname
    pd_gender.text.value= ": "+Main.user(Main.loggedin).u_gender
    pd_dob.text.value= ": "+Main.user(Main.loggedin).u_dob
    pd_nation.text.value= ": "+Main.user(Main.loggedin).u_nation
    pd_address.text.value= ": "+Main.user(Main.loggedin).u_address
    pd_contactno.text.value= ": "+Main.user(Main.loggedin).u_contact
    pd_uname.text.value=": "+Main.user(Main.loggedin).u_uname
    pd_password.text.value= ": "+Main.user(Main.loggedin).u_password
    
    }
  def upgradeAccount(event: ActionEvent) {
    
    }
  def editpersonaldetails (e : ActionEvent) {

      Main.disable(pd_fname,pd_lname,pd_gender,pd_dob,pd_nation,pd_address,pd_contactno,pd_password)
  }

  def confirmchanges (e : ActionEvent) {
    
  }
  def logout(event: ActionEvent) {
    Main.roots.setCenter(Main.mainpage)
    }
}