package com.ixs.testing.hilt


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
     lateinit var someThing: SomeThing
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        println(someThing.doThing())
        println(someThing.doNewThing())


    }
}


class SomeThing
@Inject
constructor(
   private val doAnotherThing: ISomething
){
    fun doThing() : String{
        return "Do Thing"
    }

    fun doNewThing() : String{
      return doAnotherThing.doNewThing()
    }
}

class doAnotherThing
@Inject
constructor(
    private val str:String
):ISomething{
    fun doAnotherThing():String{
        return "Do Another Thing $str"
    }

    override fun doNewThing(): String {
        return "Do InterFace Implementation $str"
    }
}

interface ISomething{
    fun doNewThing() :String
}

@InstallIn(SingletonComponent::class)
@Module
 class Module{
//    @Singleton
////    @Binds
////    abstract fun SomeThingImpl(
////        doAnotherThing: doAnotherThing
////    ): ISomething
//    @Singleton
//    @Provides
//    fun PassString(str :String):String{
//        return "Check IT"
//    }
    @Singleton
    @Provides
    fun SomeStringImpl():String{
        return "Do String Implementation"
    }

    @Singleton
    @Provides
     fun SomeThingImpl(
        str: String
    ):ISomething{
        return doAnotherThing(str)
    }


}