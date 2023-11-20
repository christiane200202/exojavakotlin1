package com.example.premierkotlin

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.pow
import kotlin.math.sqrt

class MainActivity : AppCompatActivity(),SensorEventListener{

    lateinit var txty:TextView
    lateinit var txtx:TextView
    lateinit var txtz:TextView
    lateinit var txtMove:TextView
    lateinit var acc: Sensor
    lateinit var manager: SensorManager
    lateinit var mySensors:List<Sensor>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        this.txtx=findViewById(R.id.txtx) as TextView;
        this.txty=findViewById(R.id.txty) as TextView;
        this.txtz=findViewById(R.id.txtz) as TextView;
        this.txtMove=findViewById(R.id.txtMove) as TextView;
        this.manager=getSystemService(Context.SENSOR_SERVICE) as SensorManager
        this.acc = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        mySensors = listOf(acc)
        var s:String=""
        for (a in mySensors) {
            s +=a.toString()+"/"
        }
        val test = ""
        this.manager.registerListener(this, this.acc, SensorManager.SENSOR_DELAY_GAME)
    }

    override fun onSensorChanged(p0: SensorEvent?) {

        if(p0!=null){
            this.txtx.text = "x:" +p0.values[0].toString()
            this.txty.text = "x:" +p0.values[1].toString()
            this.txtz.text = "x:" +p0.values[2].toString()

            val vectorLength: Float = sqrt(
                p0.values[0].toDouble().pow(2.0) + p0.values[1].toDouble().pow(2.0)
                        + p0.values[2].toDouble().pow(2.0)
            ).toFloat()

        txtMove.x = txtMove.x + p0.values[0] * (-1 * vectorLength)
        txtMove.y = txtMove.y + p0.values[1] * vectorLength

        }

    }
    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        TODO("Not yet implemented")
    }
}