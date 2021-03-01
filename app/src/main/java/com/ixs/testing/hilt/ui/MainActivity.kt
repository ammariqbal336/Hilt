package com.ixs.testing.hilt.ui


import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.ixs.testing.hilt.R
import com.ixs.testing.hilt.model.Blog
import com.ixs.testing.hilt.utils.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private val viewModel : MainViewModel by viewModels()

//    @Inject
//     lateinit var mainFragmentFactory: MainFragmentFactory
    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    subscribeObserver()
    viewModel.setStateEvent(MainStateEvent.GetLBlogEvent)
//        supportFragmentManager.fragmentFactory = mainFragmentFactory
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.main_Fragment,MainFragment::class.java,null)
//            .commit()



    }

    private fun subscribeObserver(){
        viewModel.dataState.observe(this, Observer { dataState ->
            when(dataState){
                is DataState.Success<List<Blog>> ->{
                   LoadingProgress(false)
                    GetData(dataState.data)
                }
                is DataState.Error ->{
                    LoadingProgress(false)
                    GetError(dataState.exception.message)
                }
                is DataState.loading ->{
                    LoadingProgress(true)
                }
            }
        })
    }

    private fun GetError(message :String?){
        if(message !=null){
            tvtext.text = message
        }
        else{
            tvtext.text = "Unknown Error"
        }
    }

    private fun LoadingProgress(visible: Boolean){
        progress_circular.visibility = if(visible) View.VISIBLE else View.GONE
    }

    private fun GetData(blogs :List<Blog>){
        val sb =StringBuilder()
        for(blog in blogs){
            sb.append(blog.title + "\n")
        }
        tvtext.text = sb.toString()
    }
}


//class SomeThing
//@Inject
//constructor(
//    @Impl1  private val something : ISomething,
//    @Impl2 private val something2 : ISomething,
//) {
//     fun doNewThing1(): String {
//        return something.doNewThing()
//    }
//    fun doNewThing2(): String {
//        return something2.doNewThing()
//    }
//
//}

//class doAnotherThing
//@Inject
//constructor(
//):ISomething{
//    fun doAnotherThing1():String{
//        return "Check "
//    }
//
//    override fun doNewThing(): String {
//        return "Do InterFace Implementation "
//    }
//}
//
//interface ISomething{
//    fun doNewThing() :String
//}
//
//class Something1():ISomething{
//    override fun doNewThing(): String {
//        return "ISomething"
//    }
//
//}
//
//class Something2():ISomething{
//    override fun doNewThing(): String {
//        return "ISomething2"
//    }
//
//}
//
////@InstallIn(SingletonComponent::class)
////@Module
//// class Module{
////
////    @Impl1
////    @Singleton
////    @Provides
////    fun SomeStringImpl():ISomething{
////        return Something1()
////    }
////
////    @Impl2
////    @Singleton
////    @Provides
////    fun SomeStringImpl2():ISomething{
////        return Something2()
////    }
////
////
////
////
////}
//
//@Qualifier
//@Retention(AnnotationRetention.BINARY)
//annotation class Impl1
//
//@Qualifier
//@Retention(AnnotationRetention.BINARY)
//annotation class Impl2