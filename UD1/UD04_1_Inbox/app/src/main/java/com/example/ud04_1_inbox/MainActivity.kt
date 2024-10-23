package com.example.ud04_1_inbox

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    //Una actividad en Android es como una pantalla, la parte visible con la que el usuario interactúa.

    //Se ejecuta cuando la actividad es creada por primera vez, como cuando el usuario abre la aplicación.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) //Llama a la función original de onCreate en AppCompatActivity. Es decir, se asegura de que se inicialice correctamente antes de añadir cosas nuestras.
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar) //esto es para decir que viene por defecto y asi sustituye la que viene por defecto

        //controlador de navegacion
        val navHostFragmento = supportFragmentManager.findFragmentById(R.id.container_fragment) as NavHostFragment

        val navControla = navHostFragmento.navController

        val appBarConfiguration = AppBarConfiguration.Builder(navControla.graph).build()
        toolbar.setupWithNavController(navControla, appBarConfiguration)


        val barraAbajo = findViewById<BottomNavigationView>(R.id.bottomtoolbar)
        barraAbajo.setupWithNavController(navControla)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //que se hace cuando se selecciona un item de las opciones
       val navControlador = findNavController(R.id.container_fragment) //para que encuentre en la actividad el controlador del navegador
        NavigationUI.onNavDestinationSelected(item,navControlador)
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //hay que indicar que los menus de la toolbar son por defecto tambien aunq la barra ya se mantenga
        menuInflater.inflate(R.menu.menu_toolbar,menu)
        return super.onCreateOptionsMenu(menu)
    }

}