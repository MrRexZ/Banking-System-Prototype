//import scala.reflect._
package mainclasses

import scala.reflect.runtime.universe._ 
import scala.reflect.runtime.universe

class Employee[A : TypeTag]{
//if (typeOf[A] =:= typeOf[String] )
//{println("yes")
 // }
  //def getTypeTag[T: TypeTag](obj: T) = typeTag[T]
  def getTypeTag[T: TypeTag](obj: T)(implicit ev: TypeTag[T])=ev.tpe
 def method[A : TypeTag](x : A) = typeOf[A] match {
 case bar if bar =:= typeOf[String] => println("list of strings")
// case t if t <:< typeOf[Foo] => "list of foos"
}
 
 
/*if (A.isInstanceOf[java.lang.String])
{
  
}

if (A.isInstanceOf[Person])
{
  println("booho")
}*/

}


object Yo extends App{
  
}