package gr.ote.academy

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import gr.ote.academy.databinding.ActivityMain2Binding
import gr.ote.academy.fragments.BlankFragment

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        val username = intent.extras?.getString("username")
        val password = intent.extras?.getString("password")
        val age = intent.extras?.getString("age")

        val dog: Dog? = intent.extras?.serializable(intent, "dog", Dog::class.java)

        val cat: Cat? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.extras?.getParcelable("cat", Cat::class.java)
        } else {
            intent.extras?.getParcelable("cat") as Cat?
        }

        username

        binding.main2BtnClose.setOnClickListener {

            val intent = Intent()
            intent.putExtras(this@MainActivity2.intent)

            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

    override fun onPostResume() {
        super.onPostResume()

        val frInstance = BlankFragment()

        try {
            val frTransaction = supportFragmentManager.beginTransaction()
            //frTransaction.add(frInstance, BlankFragment::class.java.simpleName)
            frTransaction.replace(binding.main2FragmentContainer.id, frInstance)
            frTransaction.commit()
        } catch (exception: Exception) {

        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}

fun <T : java.io.Serializable> Bundle.serializable(
    intent: Intent, key: String, className: Class<T>
): T? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
    intent.extras?.getSerializable(key, className)
} else {
    intent.extras?.getSerializable(key) as T?
}