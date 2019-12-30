package com.example.exercise4

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DecimalFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textDOB.setOnClickListener() {
            val c: Calendar = Calendar.getInstance()
            val currentDay = c.get(Calendar.DAY_OF_MONTH)
            val currentMonth = c.get(Calendar.MONTH)
            val currentYear = c.get(Calendar.YEAR)

            val dpd = DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener
                { view, year, month, day ->
                    textDOB.setText(day.toString() + "/" + (month + 1).toString() + "/" + year.toString())

                    calculateBtn.setOnClickListener(){

                        val decimalPoint = DecimalFormat("##.##")

                        val age = currentYear - year
                        textAge.setText(age.toString())
                        val basicSaving= getBasicSaving()
                        textMinBasicSav.setText("RM " + decimalPoint.format(basicSaving))
                        val investAmount = basicSaving * 30 / 100
                        textInvestAmount.setText("RM " + decimalPoint.format(investAmount))
                    }

                }, currentYear, currentMonth, currentDay
            )
            dpd.show()
        }

        resetBtn.setOnClickListener(){
            textDOB.setText("")
            textAge.setText("")
            textMinBasicSav.setText("")
            textInvestAmount.setText("")
        }
    }

    fun getBasicSaving():Int{
        return when(Integer.valueOf(textAge.getText().toString())){
            in 16..20->5000
            in 21..25->14000
            in 26..30->29000
            in 31..35->50000
            in 36..40->78000
            in 41..45->116000
            in 46..50->165000
            in 51..55->228000
            else ->0
        }
    }
}
