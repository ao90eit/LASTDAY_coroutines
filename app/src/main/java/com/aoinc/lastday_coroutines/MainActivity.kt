package com.aoinc.lastday_coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

// 'suspend' functions can be started, paused, stopped, and resumed

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        CoroutineScope(Dispatchers.Main).run {
//
//        }

        // run blocking -> sequential, blocks thread on suspend
        runBlocking {
            val time = measureTimeMillis {
                val name = getName()
                Log.d("TAG_X", "$name retrieved")
                val surname = getSurname()
                Log.d("TAG_X", "$surname retrieved")
                Log.d("TAG_X", "$name $surname")
            }
            Log.d("TAG_X", "Time expected: 6 seconds, time taken: $time")
        }

        getBothNames()

//        cancelTask()

        Log.d("TAG_X", "outer log")
    }

    suspend fun getName(): String {
        delay(4000L)
        return "Adam"
    }

    suspend fun getSurname(): String {
        delay(2000L)
        return "Ormsby"
    }

    fun getBothNames() = runBlocking {
        val time = measureTimeMillis {
            val name = async { getName() }
            val surname = async { getSurname() }

            Log.d("TAG_X", "${name.await()} ${surname.await()}")
        }

        Log.d("TAG_X", "Time expected: 4 seconds, time taken: $time")
    }

    fun cancelTask() = runBlocking {
        val doThing = async { doSomething() }
        doThing.join()
    }

    suspend fun doSomething() {
        throw UnsupportedOperationException("oops")
    }
}