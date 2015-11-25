



package mainclasses
import scalafx.beans.property.{ObjectProperty, StringProperty} 
import Main.{usercontroller => form, admincontroller => admineditform}
import javax.swing.JOptionPane;

class User(var u_uname : String,var u_password : String,var u_fname : String = null, var u_lname: String= null, var u_address : String=null,var u_contact : String=null,var u_gender : String=null,var u_dob : String = null,var u_nation : String=null,var u_accno : String, var u_balance : Double) extends Person(u_fname,u_lname,u_address,u_gender: String, "Normal User") {

  var normalAcc=new NormalAccount(u_uname,u_fname,u_lname,u_address,u_contact,u_dob,u_nation)
  var premiumAcc : PremiumAccount=null
  var userName = new StringProperty(this, "userName", u_uname)
  val firstName = new StringProperty(this, "firstName", u_fname)
  val lastName = new StringProperty(this, "lastName", u_lname)
  val password = new StringProperty(this, "password", u_password)
  val location= new StringProperty(this, "location", u_address)
  val contact = new StringProperty(this, "contact", u_contact)
  val nation = new StringProperty(this, "nation", u_nation)
  val accno=new StringProperty(this, "accno", u_accno)
  val balance=new StringProperty(this, "balance", u_balance.toString)
  val dob=new StringProperty(this, "dob", u_dob.toString)
  val gender=new StringProperty(this, "gender", u_gender)
  var status = new StringProperty(this, "status", p_status)

  def rebind() {
  userName = new StringProperty(this, "userName", u_uname)
 }
  def transferMoney(ano_user : User) {
     u_balance -= Main.usercontroller.display_amount.text.value.substring(8).toInt
     ano_user.u_balance += Main.usercontroller.display_amount.text.value.substring(8).toInt
     balance.value=u_balance.toString()
     ano_user.balance.value=ano_user.u_balance.toString()
  }
  

  
  def updateInfo( a : eventhandling.UserEventController#Controller){
    u_fname= checkIfEmpty(form.edit_fname.text.value,u_fname)
    u_lname= checkIfEmpty(form.edit_lname.text.value,u_lname)
    u_gender=checkIfEmpty(form.edit_gender.text.value,u_gender)
    u_dob= checkIfEmpty(form.edit_dob.getValue.toString(),u_dob)
    u_nation=checkIfEmpty(form.edit_nation.text.value,u_nation)
    u_address=checkIfEmpty(form.edit_address.text.value,u_address)
    u_contact = checkIfEmpty(form.edit_contactno.text.value,u_contact)
    u_password=checkIfEmpty(form.edit_password.text.value,u_password)
    
    updateAccount()
    
  }
  
    def updateInfo( a : eventhandling.AdminEventController#Controller){
      
     u_uname= checkIfEmpty(admineditform.edit_uname.text.value,u_uname)
    u_fname= checkIfEmpty(admineditform.edit_fname.text.value,u_fname)
    u_lname= checkIfEmpty(admineditform.edit_lname.text.value,u_lname)
    u_gender=checkIfEmpty(admineditform.edit_gender.text.value,u_gender)
    u_dob= checkIfEmpty(admineditform.edit_dob.getValue.toString(),u_dob)
    u_nation=checkIfEmpty(admineditform.edit_nation.text.value,u_nation)
    u_address=checkIfEmpty(admineditform.edit_address.text.value,u_address)
    u_contact = checkIfEmpty(admineditform.edit_contactno.text.value,u_contact)
    u_password=checkIfEmpty(admineditform.edit_password.text.value,u_password)
    updateAccount()
    
    }
    
    
    def updateAccount() {
          if (normalAcc!=null){
      println("normal account detected")
      normalAcc.nu_uname=u_fname
      normalAcc.nu_password=u_password
      normalAcc.nu_nation=u_nation
      normalAcc.nu_fname=u_fname
      normalAcc.nu_lname=u_lname
      normalAcc.nu_dob=u_dob
      normalAcc.nu_contact=u_contact
      normalAcc.nu_address=u_address
    }
          else if (premiumAcc!=null) {
      println("Premium account detected")
      premiumAcc.pu_uname=u_uname
      premiumAcc.pu_password=u_password
      premiumAcc.pu_nation=u_nation
      premiumAcc.pu_fname=u_fname
      premiumAcc.pu_lname=u_lname
      premiumAcc.pu_dob=u_dob
      premiumAcc.pu_contact=u_contact
      premiumAcc.pu_address=u_address
    }
    }
  
  def checkIfEmpty(x : String, y : String): String = x match {
    case "" => y
    case _ => x
  }
  
  def removeUser() {

  }
  
  def upgradeUser() {
    if (u_balance>=500) {
      u_balance=u_balance-500
      premiumAcc=new PremiumAccount(u_uname ,u_password ,u_fname, u_lname,u_address,u_contact,u_dob,u_nation,0)
      normalAcc=null
      p_status= "Premium User"
      Main.admincontroller.updateTable(Main.user.indexWhere( _.u_accno == u_accno))
      }
    else JOptionPane.showMessageDialog(null, "Not enough balance, you have " + u_balance + "in your account at the moment");
    
  }
}

object User {
  var counteraccno=0
}