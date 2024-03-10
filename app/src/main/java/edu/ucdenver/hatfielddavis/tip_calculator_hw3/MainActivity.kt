package edu.ucdenver.hatfielddavis.tip_calculator_hw3

import android.icu.text.DecimalFormat
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.RadioGroup
import android.widget.Toast
import edu.ucdenver.hatfielddavis.tip_calculator_hw3.databinding.ActivityMainBinding
import java.text.NumberFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        var view: View = binding.root
        setContentView(binding.root)

        binding.radioGroup.setOnCheckedChangeListener { radioGroup: RadioGroup, checkedId: Int ->
            calculateTip()
        }

        binding.editText1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                calculateTip()
            }
        })

    }

   private fun calculateTip(){
       var total: Double = 0.00
       var tax: Double = 0.00
       var tip: Double = 0.00
       var totalWithTip: Double = 0.00

       try{
           total = binding.editText1.text.toString().toDouble()
       } catch (ex: NumberFormatException){
           Toast.makeText(this, "Number must be numerical", Toast.LENGTH_LONG).show()
           return
       }

       try{
           tax = binding.editText2.text.toString().toDouble()
       } catch (ex: NumberFormatException){
           Toast.makeText(this, "Number must be numerical", Toast.LENGTH_LONG).show()
           return
       }

       if(binding.radioButton0.isChecked){
           totalWithTip = total + tax
       } else if(binding.radioButton5.isChecked){
           totalWithTip = total + tax + (total * .05)
           tip = total * 0.05
       } else if(binding.radioButton10.isChecked){
           totalWithTip = total + tax + (total * 0.1)
           tip = total * 0.1
       } else if(binding.radioButton20.isChecked){
           totalWithTip = total + tax + (total * 0.2)
           tip = total * 0.2
       }

       val sf = NumberFormat.getCurrencyInstance(Locale.US)
       binding.textTip.text = sf.format(tip)

       val cf = NumberFormat.getCurrencyInstance(Locale.US)
       binding.textTotalwithTip.text = cf.format(totalWithTip)


   }
    fun onClick(view: View){
        calculateTip();
    }

    fun clear(view: View){
        binding.editText1.setText("")
        binding.editText2.setText("")
        binding.textTip.text = "0.00"
        binding.textTotalwithTip.text = "0.00"
        binding.radioButton0.isChecked = true
    }

}