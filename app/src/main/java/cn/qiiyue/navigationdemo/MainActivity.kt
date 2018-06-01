package cn.qiiyue.navigationdemo

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var viewPager: ViewPager
    lateinit var tabLayout: TabLayout
    private var tabNames = arrayOf("首页", "视频", "头条", "我的")
    lateinit var fragments: Array<Fragment>
    private var pagerAdapter: MyPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        initListener()

    }

    private fun initListener() {
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager.currentItem = tabLayout.selectedTabPosition
            }

        })

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                tabLayout.getTabAt(position)?.select()
            }

        })
    }

    private fun initView() {
        viewPager = findViewById(R.id.container)
        tabLayout = findViewById(R.id.tab_layout)

        //初始化ViewPager
        fragments = arrayOf(MainFragment(), VideoFragment(), NewsFragment(), USerFragment())
        pagerAdapter = MyPagerAdapter(supportFragmentManager)
        viewPager.adapter = pagerAdapter

        //初始化TabLayout
        for (index in 0 until tabNames.size) {
            tabLayout.addTab(tabLayout.newTab().setText(tabNames[index]))
        }
        tabLayout.setSelectedTabIndicatorHeight(0)
    }


    inner class MyPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

        override fun getItem(position: Int): Fragment? {
            if (fragments != null && fragments.isNotEmpty() && position < fragments?.size) {
                return fragments[position]
            }
            return null
        }

        override fun getCount(): Int {
            if (tabNames.isNotEmpty()) {
                return tabNames.size
            }
            return 0
        }

    }

}
