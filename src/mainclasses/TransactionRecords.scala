package mainclasses
import scalafx.beans.property.{ObjectProperty, StringProperty} 

class TransactionRecords(private var transactionNo : String, private var amountTransferred : String, private var recipientAccno : String, private var senderAccno : String) {
    var noTr = new StringProperty(this, "noTr", transactionNo)
    var amountTr = new StringProperty(this, "amountTr", amountTransferred)
    var recipientNoTr = new StringProperty(this, "recipientNoTr", recipientAccno)
    var senderNoTr = new StringProperty(this, "senderNoTr", senderAccno)
    
}

object TransactionRecords {
  def apply(transactionNo : String, amountTransferred : String, recipientAccno : String, senderAccno : String) : TransactionRecords = {
    new TransactionRecords(transactionNo,amountTransferred,recipientAccno,senderAccno)
  }
}
