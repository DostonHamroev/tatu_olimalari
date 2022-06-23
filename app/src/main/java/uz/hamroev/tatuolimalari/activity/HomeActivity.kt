package uz.hamroev.tatuolimalari.activity

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.Navigation
import uz.hamroev.tatuolimalari.R
import uz.hamroev.tatuolimalari.adapter.NavAdapter
import uz.hamroev.tatuolimalari.databinding.ActivityHomeBinding
import uz.hamroev.tatuolimalari.model.Nav

class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding
    lateinit var navController: NavController

    lateinit var listNav1: ArrayList<Nav>
    lateinit var listNav2: ArrayList<Nav>
    lateinit var listNav3: ArrayList<Nav>
    lateinit var navAdapter1: NavAdapter
    lateinit var navAdapter2: NavAdapter
    lateinit var navAdapter3: NavAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        navController = Navigation.findNavController(this, R.id.my_nav_host_fragment)

        loadBottomNavigation()
        binding.menuButton.setOnClickListener {
            binding.drawerLayout.open()
        }

        loadNavMenus()
        loadNav()


    }

    private fun loadNav() {

        navAdapter1 =
            NavAdapter(binding.root.context, listNav1, object : NavAdapter.OnNavCLickListener {
                override fun onClick(nav: Nav, position: Int) {
                    when (position) {
                        0 -> {
                            binding.drawerLayout.closeDrawers()
                            navController.popBackStack()
                            navController.navigate(R.id.homeFragment)
                            binding.bottomHomeIv.setImageResource(R.drawable.ic_home)
                            binding.bottomHomeDotIv.visibility = View.VISIBLE

                            binding.bottomSearchIv.setImageResource(R.drawable.ic_search_grey)
                            binding.bottomSearchDotIv.visibility = View.GONE

                            binding.bottomSaveIv.setImageResource(R.drawable.ic_save_grey)
                            binding.bottomSaveDotIv.visibility = View.GONE
                        }
                        1 -> {
                            binding.drawerLayout.closeDrawers()
                            navController.popBackStack()
                            navController.navigate(R.id.searchFragment)
                            binding.bottomHomeIv.setImageResource(R.drawable.ic_home_grey)
                            binding.bottomHomeDotIv.visibility = View.GONE

                            binding.bottomSearchIv.setImageResource(R.drawable.ic_search)
                            binding.bottomSearchDotIv.visibility = View.VISIBLE

                            binding.bottomSaveIv.setImageResource(R.drawable.ic_save_grey)
                            binding.bottomSaveDotIv.visibility = View.GONE
                        }
                        2 -> {
                            binding.drawerLayout.closeDrawers()
                            navController.popBackStack()
                            navController.navigate(R.id.savedFragment)
                            binding.bottomHomeIv.setImageResource(R.drawable.ic_home_grey)
                            binding.bottomHomeDotIv.visibility = View.GONE

                            binding.bottomSearchIv.setImageResource(R.drawable.ic_search_grey)
                            binding.bottomSearchDotIv.visibility = View.GONE

                            binding.bottomSaveIv.setImageResource(R.drawable.ic_save)
                            binding.bottomSaveDotIv.visibility = View.VISIBLE
                        }
                    }
                }
            })
        binding.rvNav1.adapter = navAdapter1

        navAdapter2 =
            NavAdapter(binding.root.context, listNav2, object : NavAdapter.OnNavCLickListener {
                override fun onClick(nav: Nav, position: Int) {
                    when (position) {
                        0 -> {
                            binding.drawerLayout.closeDrawers()
                            try {
                                val intent = Intent(Intent.ACTION_SEND)
                                intent.type = "text/plain"
                                intent.putExtra(
                                    Intent.EXTRA_SUBJECT,
                                    "TATU OLimalari - creted by iAndroid.uz"
                                )
                                val shareMessage =
                                    "https://play.google.com/store/apps/details?id=${packageName}"
                                intent.putExtra(Intent.EXTRA_TEXT, shareMessage)
                                startActivity(Intent.createChooser(intent, "share by Doston"))
                            } catch (e: Exception) {
                            }
                            binding.drawerLayout.closeDrawers()
                        }
                        1 -> {
                            binding.drawerLayout.closeDrawers()
                            try {
                                val uri: Uri =
                                    Uri.parse("market://details?id=${packageName}")
                                val intent = Intent(Intent.ACTION_VIEW, uri)
                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                                startActivity(intent)
                            } catch (e: ActivityNotFoundException) {
                                val uri: Uri =
                                    Uri.parse("http://play.google.com/store/apps/details?id=${packageName}")
                                val intent = Intent(Intent.ACTION_VIEW, uri)
                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                                startActivity(intent)
                            }
                            binding.drawerLayout.closeDrawers()
                        }
                    }
                }
            })
        binding.rvNav2.adapter = navAdapter2

        navAdapter3 =
            NavAdapter(binding.root.context, listNav3, object : NavAdapter.OnNavCLickListener {
                override fun onClick(nav: Nav, position: Int) {
                    when (position) {
                        0 -> {
                            finish()
                        }
                    }
                }
            })
        binding.rvNav3.adapter = navAdapter3


    }

    private fun loadNavMenus() {
        listNav1 = ArrayList()
        listNav1.add(Nav(R.drawable.ic_home_white, "Aсосий"))
        listNav1.add(Nav(R.drawable.ic_search_white, "Қидириш"))
        listNav1.add(Nav(R.drawable.ic_save_white, "Сақланганлар"))
        listNav2 = ArrayList()
        listNav2.add(Nav(R.drawable.ic_share, "Юбориш"))
        listNav2.add(Nav(R.drawable.ic_rate, "Баҳолаш"))
        listNav3 = ArrayList()
        listNav3.add(Nav(R.drawable.ic_exit, "Чиқиш"))
    }

    private fun loadBottomNavigation() {

        navController.popBackStack()
        navController.navigate(R.id.homeFragment)
        binding.bottomHomeIv.setImageResource(R.drawable.ic_home)
        binding.bottomHomeDotIv.visibility = View.VISIBLE

        binding.apply {
            bottomNavHome.setOnClickListener {
                navController.popBackStack()
                navController.navigate(R.id.homeFragment)
                bottomHomeIv.setImageResource(R.drawable.ic_home)
                bottomHomeDotIv.visibility = View.VISIBLE

                bottomSearchIv.setImageResource(R.drawable.ic_search_grey)
                bottomSearchDotIv.visibility = View.GONE

                bottomSaveIv.setImageResource(R.drawable.ic_save_grey)
                bottomSaveDotIv.visibility = View.GONE
            }
            bottomNavSearch.setOnClickListener {

                navController.popBackStack()
                navController.navigate(R.id.searchFragment)
                bottomHomeIv.setImageResource(R.drawable.ic_home_grey)
                bottomHomeDotIv.visibility = View.GONE

                bottomSearchIv.setImageResource(R.drawable.ic_search)
                bottomSearchDotIv.visibility = View.VISIBLE

                bottomSaveIv.setImageResource(R.drawable.ic_save_grey)
                bottomSaveDotIv.visibility = View.GONE
            }
            bottomNavSave.setOnClickListener {

                navController.popBackStack()
                navController.navigate(R.id.savedFragment)
                bottomHomeIv.setImageResource(R.drawable.ic_home_grey)
                bottomHomeDotIv.visibility = View.GONE

                bottomSearchIv.setImageResource(R.drawable.ic_search_grey)
                bottomSearchDotIv.visibility = View.GONE

                bottomSaveIv.setImageResource(R.drawable.ic_save)
                bottomSaveDotIv.visibility = View.VISIBLE
            }
        }
    }


    override fun onSupportNavigateUp(): Boolean {

        return Navigation.findNavController(this, R.id.my_nav_host_fragment).navigateUp()
    }

}