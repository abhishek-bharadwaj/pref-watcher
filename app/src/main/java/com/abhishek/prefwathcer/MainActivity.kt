package com.abhishek.prefwathcer

import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener {

    private lateinit var pref: UserPref

    companion object {
        val TAG = MainActivity::class.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pref = UserPref.getInstance(this)
        storeValueToPref()
    }

    override fun onStart() {
        super.onStart()
        pref.prefs.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onStop() {
        pref.prefs.unregisterOnSharedPreferenceChangeListener(this)
        super.onStop()
    }

    private fun storeValueToPref() {
        Observable.defer { Observable.just(true) }.delay((Random().nextInt(3) * 1000).toLong(), TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .subscribe(object : Observer<Boolean> {
                    override fun onSubscribe(@NonNull d: Disposable) {

                    }

                    override fun onNext(@NonNull aBoolean: Boolean) {
                        pref.name = "Random Guy..." + System.currentTimeMillis()
                        Log.d(TAG, "Saved to preference!!")
                    }

                    override fun onError(@NonNull e: Throwable) {

                    }

                    override fun onComplete() {}
                })
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String) {
        Log.d(TAG, "Pref changed---> " + key)
        bindStringPref(tv_name, key)
    }

    private fun bindStringPref(textView: TextView, key: String) {
        textView.text = pref.prefs.getString(key, null)
    }
}
