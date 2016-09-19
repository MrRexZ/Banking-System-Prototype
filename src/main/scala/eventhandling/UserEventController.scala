package eventhandling             

import scalafx.Includes._ 
import scalafxml.core.macros.sfxml     
import scalafx.scene.control.{Label,TextField,TextArea,DatePicker,PasswordField,Button}
import scalafx.scene.layout._
import mainclasses._
import scalafx.event.ActionEvent
import scalafx.scene.layout.GridPane
import scalafx.scene.layout.AnchorPane
import javax.swing.JOptionPane;
import java.time.LocalDate
import java.time.format.DateTimeFormatter
 
@sfxml
class UserEventController(
    var displayusername : scalafx.scene.control.Label,
    var balancepane : AnchorPane, 
    var balance : Label,
    var display_debt : Label,
 
    var transferpane : AnchorPane,
    var enter_targetacc: TextField,
    var enter_amount: TextField,
    var display_balance : Label,
    var display_sendername : Label,
    var display_senderno : Label,
    var display_destname : Label,
    var display_destno : Label,
    var display_amount : Label,
    var confirmtransactionbutton : Button,
    var transactiondisplayno : Label,
    
    var personaldetailspane : AnchorPane,
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
    var edit_confirmpassword:PasswordField,
    var changelabel : Label,
    var changelabel1 :Label,
    
    var upgradepane : AnchorPane,
    
    var loanpane : AnchorPane,
    private var amountborrow : TextField,
    private var time : TextField,
    private var loan_msg : Label,
    private var display_loanval : Label,
    private var display_loansuccess : Label,
    
    var aboutpane : AnchorPane,
     
    private var suc_upgrade : AnchorPane
    )  {
  
  def checkBalance(e: ActionEvent) {
      Main.enable(balancepane)
      Main.disable(upgradepane,transferpane,aboutpane,loanpane,personaldetailspane,suc_upgrade)
  balance.text="Your balance is : "+ Main.user(Main.loggedin).u_balance.toString
  display_debt.text="Your debt is : " + Main.user(Main.loggedin).debt.toString
  }
  
    
  def openTransfer(e: ActionEvent) {
      Main.enable(transferpane)
      Main.disable(upgradepane,balancepane,aboutpane,loanpane,personaldetailspane,suc_upgrade)
    display_balance.text.value="Balance : "+ Main.user(Main.loggedin).u_balance.toString
    Main.disable(display_sendername,display_senderno,display_destname,display_destno,display_amount,transactiondisplayno,confirmtransactionbutton)
    }
  
  def continueTransfer(e : ActionEvent) {
   
    transactiondisplayno.text.value="Transaction No. : "
    if ((enter_targetacc.text.value.toInt<=0 || Main.user.indexWhere( _.u_accno == enter_targetacc.text.value)== -1) && (enter_amount.text.value.toInt<0 || enter_amount.text.value.toInt>Main.user(Main.loggedin).u_balance) )
      JOptionPane.showMessageDialog(null, "Please enter the correct destination account number and check your amount");
    else if (enter_targetacc.text.value.toInt<=0 || Main.user.indexWhere( _.u_accno == enter_targetacc.text.value)== -1)
      JOptionPane.showMessageDialog(null, "Please enter the correct destination account number");
    else if (enter_amount.text.value.toInt<=0 || enter_amount.text.value.toInt>Main.user(Main.loggedin).u_balance)
      JOptionPane.showMessageDialog(null, "Please enter the correct amount");
    else if (enter_targetacc.text.value.toInt>0 && Main.user(Main.user.indexWhere( _.u_accno == enter_targetacc.text.value))!=null && enter_amount.text.value.toInt>0 && enter_amount.text.value.toInt<=Main.user(Main.loggedin).u_balance ) {
    display_sendername.text.value = "Sender Name :" + Main.user(Main.loggedin).u_fname + " "+Main.user(Main.loggedin).u_lname
    display_senderno.text.value ="Sender Account No. :" + Main.user(Main.loggedin).u_accno
    display_destno.text.value ="Destination Account No. :" + enter_targetacc.text.value
    display_destname.text.value = "Recipient Name : " +Main.user(Main.user.indexWhere( _.u_accno == enter_targetacc.text.value)).u_fname +" " +Main.user(Main.user.indexWhere( _.u_accno == enter_targetacc.text.value)).u_lname
    display_amount.text.value="Amount :" + enter_amount.text.value
     Main.enable(display_sendername,display_senderno,display_destname,display_destno,display_amount,transactiondisplayno,confirmtransactionbutton)
    }


  }
  
  def confirmTransaction (e : ActionEvent){
    transactiondisplayno.text.value="Transaction No. : " + Main.countertransaction.toString
    Main.transactionList += TransactionRecords(transactiondisplayno.text.value.substring(18,transactiondisplayno.text.value.length),display_amount.text.value.substring(8,display_amount.text.value.length),enter_targetacc.text.value,Main.user(Main.loggedin).u_accno.toString())
    Main.countertransaction=Main.countertransaction+1
    Main.user(Main.loggedin).transferMoney(Main.user(Main.user.indexWhere( _.u_accno == enter_targetacc.text.value)))
    enter_amount.clear
    enter_targetacc.clear
    display_balance.text.value="Balance : "+ Main.user(Main.loggedin).u_balance.toString

  }
     

  def viewPersonalDetails(event: ActionEvent) {
     Main.enable(personaldetailspane)
     Main.disable(transferpane,balancepane,aboutpane,loanpane,upgradepane,suc_upgrade)
     Main.disable(edit_fname,edit_lname,edit_gender,edit_dob,edit_nation,edit_address,edit_contactno,edit_password,edit_confirmpassword)
     Main.enable(pd_fname,pd_lname,pd_gender,pd_dob,pd_nation,pd_address,pd_contactno,pd_uname,pd_password) 
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
    if (Main.user(Main.loggedin).premiumAcc==null){
      Main.enable(upgradepane)
      Main.disable(transferpane,balancepane,aboutpane,loanpane,personaldetailspane)
    }
    else JOptionPane.showMessageDialog(null, "Your account is already upgraded!");
 }
  
  def confirmUpgradeAccount(event : ActionEvent) {
    if (Main.user(Main.loggedin).premiumAcc==null) {
      Main.user(Main.loggedin).upgradeUser()
      suc_upgrade.visible=true
      upgradepane.visible=false
      }  
    else JOptionPane.showMessageDialog(null, "Your account is already upgraded!");
  }
  
  def editpersonaldetails (e : ActionEvent) {
    changelabel.text.value="Password"
    changelabel1.text.value="Confirm Password"
      Main.disable(pd_fname,pd_lname,pd_gender,pd_dob,pd_nation,pd_address,pd_contactno,pd_uname,pd_password)
      Main.enable(edit_fname,edit_lname,edit_gender,edit_dob,edit_nation,edit_address,edit_contactno,edit_password,edit_confirmpassword)
      edit_fname.text.value= Main.user(Main.loggedin).u_fname
      edit_lname.text.value= Main.user(Main.loggedin).u_lname
      edit_gender.text.value= Main.user(Main.loggedin).u_gender
      edit_dob.value = LocalDate.parse(Main.user(Main.loggedin).u_dob, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
      edit_nation.text.value = Main.user(Main.loggedin).u_nation
      edit_address.text.value =Main.user(Main.loggedin).u_address
      edit_contactno.text.value=Main.user(Main.loggedin).u_contact
      edit_confirmpassword.text.value=Main.user(Main.loggedin).u_password
      edit_password.text.value=Main.user(Main.loggedin).u_password
  }

  def confirmchanges (e : ActionEvent) {
  Main.user(Main.loggedin).updateInfo(Main.usercontroller)
    changelabel.text.value="Username"
    changelabel1.text.value="Password"
  Main.disable(edit_fname,edit_lname,edit_gender,edit_dob,edit_nation,edit_address,edit_contactno,edit_password,edit_confirmpassword)
  Main.enable(pd_fname,pd_lname,pd_gender,pd_dob,pd_nation,pd_address,pd_contactno,pd_uname,pd_password) 
    if ( pd_fname.text.value!="") pd_fname.text.value= ": "+Main.user(Main.loggedin).u_fname
    if ( pd_lname.text.value!="") pd_lname.text.value= ": "+Main.user(Main.loggedin).u_lname
    pd_gender.text.value= ": "+Main.user(Main.loggedin).u_gender
   
    if (pd_dob.text.value!="") pd_dob.text.value= ": "+Main.user(Main.loggedin).u_dob
    pd_nation.text.value= ": "+Main.user(Main.loggedin).u_nation
    pd_address.text.value= ": "+Main.user(Main.loggedin).u_address
    pd_contactno.text.value= ": "+Main.user(Main.loggedin).u_contact
    if ( pd_uname.text.value!="")  pd_uname.text.value=": "+Main.user(Main.loggedin).u_uname
   if (pd_password.text.value!="")  pd_password.text.value= ": "+Main.user(Main.loggedin).u_password
   Main.admincontroller.updateTable(Main.loggedin)
  }
  
  def requestLoan(e : ActionEvent) {
    if (Main.user(Main.loggedin).premiumAcc!=null) {
      Main.enable(loanpane)
      Main.disable(transferpane,balancepane,aboutpane,upgradepane,personaldetailspane,suc_upgrade)
      display_loanval.text.value=Main.generatedRandInterest.toString + "%"
   }
    else JOptionPane.showMessageDialog(null, "Please upgrade your account to premium to use this feature!");  
  }
  
  def confirmLoan(e : ActionEvent) {
       Main.user(Main.loggedin).u_balance+=amountborrow.text.value.toDouble 
       Main.user(Main.loggedin).debt=Main.user(Main.loggedin).premiumAcc.calculateLoan(amountborrow.text.value.toDouble, display_loanval.text.value.substring(0,1).toDouble, time.text.value.toInt)
  
       loan_msg.text.value = "Loan successful, your debt is now at :" +  Main.user(Main.loggedin).debt 
       display_loansuccess.visible=true
       display_loansuccess.text.value=loan_msg.text.value
       
       amountborrow.clear
       time.clear
  }
  def showCredits(e : ActionEvent){
      Main.enable(aboutpane)
      Main.disable(transferpane,balancepane,upgradepane,loanpane,personaldetailspane)
  }
  def logout(event: ActionEvent) {
    suc_upgrade.visible=false
     display_loansuccess.visible=false
    Main.registercontroller.loginasuser=false
    Main.roots.setCenter(Main.mainpage)
    }
}