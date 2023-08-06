package com.minhaempresa.eletric_car.ui



import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.minhaempresa.eletric_car.R
import com.minhaempresa.eletric_car.ui.adapter.CarAdapter
import com.minhaempresa.eletric_car.data.CarFactory
import com.minhaempresa.eletric_car.ui.adapter.TabAdapter


class MainActivity : AppCompatActivity() {
    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager2


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
        setupTabs()
    }

    fun setupView() {
        tabLayout = findViewById(R.id.tab_layout)
        viewPager = findViewById(R.id.vp_view_pager)
    }



    fun setupTabs() {
        val tabsAdapter = TabAdapter(this)
        viewPager.adapter = tabsAdapter
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            //quando for selecionar
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    viewPager.currentItem = it.position
                }
            }

            //quando não for selecionar
            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            //quando da reselecte
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.getTabAt(position)?.select()
            }
        })

    }


}









/* Log.d("texto preço ->" , preco.toString()) esse codigo serve para log e fazer testes
   Log.d("texto digitado ->" , km.toString())
   Log.d("Resultado ->" , resultado.toString())*/