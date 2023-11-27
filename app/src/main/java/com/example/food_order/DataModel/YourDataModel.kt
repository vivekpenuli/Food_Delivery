package com.example.food_order.DataModel

//data class YourDataModel( val foodName :String?= null,
//                          val foodPrice:String?=null,
//                          val foodImg :String?=null,
//)
data class YourDataModel(
    var foodId: String? = null,
    val foodName :String?= null,
                         val foodPrice:String?=null,
                         val foodDisc :String?=null,
                         val foodImg :String?=null,
                         val foodIngred :String?= null)