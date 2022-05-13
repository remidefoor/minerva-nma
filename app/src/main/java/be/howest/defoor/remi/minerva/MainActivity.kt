package be.howest.defoor.remi.minerva

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment: NavHostFragment = supportFragmentManager.findFragmentById(R.id.main_nav_host_fragment)
            as NavHostFragment
        navController = navHostFragment.navController

        setupBottomNavMenu(navController)
        setupActionBarWithNavController(navController)
    }

    private fun setupBottomNavMenu(navController: NavController) {
        val bottomNavMenu: BottomNavigationView? = findViewById(R.id.main_bottom_nav_menu)
        bottomNavMenu?.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}
