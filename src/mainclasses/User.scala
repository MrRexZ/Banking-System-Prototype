



package mainclasses
import scalafx.beans.property.{ObjectProperty, StringProperty}

class User(var u_uname : String,var u_password : String,var u_fname : String = null, var u_lname: String= null, var u_address : String=null,var u_contact : String=null,var u_gender : String=null,var u_dob : String = null,var u_nation : String=null,var u_accno : String, var balance : Int) extends Person(u_fname,u_lname,u_address,u_gender: String, "Normal User") {

  val normalAcc=new NormalAccounts(u_uname,u_fname,u_lname,u_address,u_contact,u_dob,u_nation)
  val userName = new StringProperty(this, "userName", u_uname)
  val firstName = new StringProperty(this, "firstName", u_fname)
  val lastName = new StringProperty(this, "lastName", u_lname)
  val password = new StringProperty(this, "password", u_password)
  val location= new StringProperty(this, "location", u_address)
  val contact = new StringProperty(this, "contact", u_contact)
  val nation = new StringProperty(this, "nation", u_nation)
 val accno=new StringProperty(this, "accno", "2323")

  def setUserName(s : String) {
    userName.set(s)
  }
}