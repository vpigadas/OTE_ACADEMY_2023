package gr.ote.academy

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.snackbar.Snackbar
import gr.ote.academy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        binding.mainBtnLogin.setOnClickListener {
            performLogin()
        }

        binding.mainEditUsername.doAfterTextChanged {
            Log.d("EditText", it.toString())
        }
    }

    private fun performLogin() {
        val username = binding.mainEditUsername.text.toString()
        val password = binding.mainEditPassword.text.toString()


//        Toast.makeText(
//            this@MainActivity,
//            "Button clicked!!!! with username $username and password $password",
//            Toast.LENGTH_LONG
//        ).show()

        Snackbar.make(
            binding.mainBtnLogin,
            "Button clicked!!!! with username $username and password $password",
            Snackbar.LENGTH_LONG
        ).show()

        val intent = Intent(this@MainActivity, MainActivity2::class.java)
        intent.putExtra("username", username)
        intent.putExtra("password", password)

        val dog = Dog("Orfeas", 14)
        val cat = Cat("Maria", 10)

        val bundle = Bundle()
        bundle.putSerializable("dog", dog)
        bundle.putParcelable("cat", cat)
        intent.putExtras(bundle)

        startActivity(intent)
        startActivityForResult(intent, 1000)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPostResume() {
        super.onPostResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1000) {
            if (resultCode == Activity.RESULT_OK) {

            } else if (resultCode == Activity.RESULT_CANCELED) {

            }
        }
    }
}