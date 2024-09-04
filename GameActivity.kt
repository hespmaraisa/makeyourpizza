package br.edu.iserj.pizzagamemara

import android.app.AlertDialog

import android.content.Intent

import android.os.Bundle

import android.widget.Button

import android.widget.ImageView

import android.widget.TextView

import androidx.appcompat.app.AppCompatActivity

import kotlin.random.Random

class GameActivity : AppCompatActivity() {

private lateinit var orderTextView: TextView

private lateinit var pizzaImageView: ImageView

// Atualizando a lista de pedidos possíveis

private val orders = arrayOf("Ketchup", "Cucumber", "Pepper",

"Shrimp", "Mushroom")

private var currentOrder = ""

private var ingredients = mutableListOf<String>()

override fun onCreate(savedInstanceState: Bundle?) {

super.onCreate(savedInstanceState)

setContentView(R.layout.activity_game)

orderTextView = findViewById(R.id.orderTextView)

pizzaImageView = findViewById(R.id.pizzaImageView)

generateNewOrder()

findViewById<Button>(R.id.addKetchupButton).setOnClickListener {

addIngredient("Ketchup")

}

findViewById<Button>(R.id.addPimenButton).setOnClickListener

{

addIngredient("Cucumber")

}

findViewById<Button>(R.id.addPepiButton).setOnClickListener

{

addIngredient("Pepper")

findViewById<Button>(R.id.addCamButton).setOnClickListener {

addIngredient("Shrimp")

}

findViewById<Button>(R.id.submitPizzaButton).setOnClickListener {

submitPizza()

}

}

private fun generateNewOrder() {

currentOrder = orders[Random.nextInt(orders.size)]

orderTextView.text = "Order: $currentOrder"

ingredients.clear()

}

private fun addIngredient(ingredient: String) {

ingredients.add(ingredient)

updatePizzaImage()

}

private fun updatePizzaImage() {

// Atualize a imagem da pizza de acordo com os ingredientes

adicionados

when {

ingredients.contains("Ketchup") &&

ingredients.contains("Pimentão") -> {

pizzaImageView.setImageResource(R.drawable.ketchup)

}

ingredients.contains("Ketchup") &&

ingredients.contains("Pepino") -> {

pizzaImageView.setImageResource(R.drawable.pepino)

}

ingredients.contains("Ketchup") &&

ingredients.contains("Camarão") -> {

pizzaImageView.setImageResource(R.drawable.camaroes)

}

ingredients.contains("Ketchup") -> {

pizzaImageView.setImageResource(R.drawable.ketchup)

}

ingredients.contains("Pimentão") -> {

pizzaImageView.setImageResource(R.drawable.pimentao)

}

ingredients.contains("Pepino") -> {

pizzaImageView.setImageResource(R.drawable.pepino)

}

ingredients.contains("Camarão") -> {

  pizzaImageView.setImageResource(R.drawable.camaroes)

}

else -> {

pizzaImageView.setImageResource(R.drawable.pizza_base)

}

}

}

private fun submitPizza() {

val resultMessage = if (ingredients.contains(currentOrder))

{

"Correct! You made a $currentOrder pizza."

} else {

"Incorrect! You did not make the correct pizza."

}

showResultDialog(resultMessage)

}

private fun showResultDialog(message: String) {

val dialogView =

layoutInflater.inflate(R.layout.dialog_pizza_result, null)

val resultImageView =

dialogView.findViewById<ImageView>(R.id.pizzaResultImageView)

val closeButton =

dialogView.findViewById<Button>(R.id.closeDialogButton)

// Configura a imagem da pizza no Dialog com base nos

ingredientes escolhidos

resultImageView.setImageDrawable(pizzaImageView.drawable)

val dialog = AlertDialog.Builder(this)

.setView(dialogView)

.setCancelable(false)

.create()

closeButton.setOnClickListener {

dialog.dismiss()

generateNewOrder()

}

dialog.show()

}
}
