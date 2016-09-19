package mainclasses

class Admin(var s_uname : String,var s_password : String ,var s_fname : String , var s_lname: String,var s_gender : String, var s_address : String)  
extends Employee(s_fname ,s_lname, s_gender , s_address ) {

}

object Admin {
  var admincounter=1
}
