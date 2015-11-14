



package mainclasses
import scalafx.beans.property.{ObjectProperty, StringProperty} 
import Main.{usercontroller => form}

class User(var u_uname : String,var u_password : String,var u_fname : String = null, var u_lname: String= null, var u_address : String=null,var u_contact : String=null,var u_gender : String=null,var u_dob : String = null,var u_nation : String=null,var u_accno : String, var u_balance : Int) extends Person(u_fname,u_lname,u_address,u_gender: String, "Normal User") {

 val normalAcc=new NormalAccounts(u_uname,u_fname,u_lname,u_address,u_contact,u_dob,u_nation)
  val userName = new StringProperty(this, "userName", u_uname)
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
  //  userName.set(s)
  //}
  
  def transferMoney(ano_user : User) {
     u_balance= u_balance - Main.usercontroller.display_amount.text.value.substring(8).toInt
     ano_user.u_balance=ano_user.u_balance + Main.usercontroller.display_amount.text.value.substring(8).toInt
  }
  
  def updateInfo(u : User){
    u_fname= checkIfEmpty(form.edit_fname.text.value,u_fname)
    u_lname= checkIfEmpty(form.edit_lname.text.value,u_lname)
    u_gender=checkIfEmpty(form.edit_gender.text.value,u_gender)
   // u_dob= form.edit_dob.getValue.toString()
    u_nation=form.edit_nation.text.value
    u_address=form.edit_address.text.value
    u_contact = form.edit_contactno.text.value
    u_password=form.edit_password.text.value
  }
  
  def checkIfEmpty(x : String, y :String): String = x match {
    case "" => y
    case _ => x
  }
}