package com.example.food_order.DataModel

data class AddtoFirebase(
    var foodId: String? = null,
    val foodName :String?= null,
                         var foodPrice:String?=null,
                         val foodDisc :String?=null,
                         val foodImg :String?=null,
                         val foodIngred :String?= null,
                         var fooddQuantity:Int ?=null,
    var baseprice:String?=null// act as base price for food item for adding that item

)
