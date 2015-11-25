

package mainclasses

class PremiumAccount(var pu_uname : String,var pu_password : String,var pu_fname : String = null, var pu_lname: String= null, var pu_address : String=null,var pu_contact : String=null,var pu_dob : String = null,var pu_nation : String=null,var debt : Double){
  
  def calculateLoan(principal : Double, interestinc : Double,time : Int) : Double={

    if (time!=0) calculateLoan(principal*((interestinc/100)+1),interestinc,time-1)
    else {
      debt += principal
      Main.user(Main.loggedin).balance.value=Main.user(Main.loggedin).u_balance.toString()
      return debt
  }
 }
}