package cn.qiiyue.navigationdemo


import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class VideoFragment : Fragment() {

    private val TAG = "VideoFragment"
    private lateinit var rootView: View
    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout
    private var tabNames = arrayListOf<String>()
    private var fragments = arrayListOf<VideoInFragment>()
    private var pagerAdapter: VideoFragment.MyPagerAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        Log.d(TAG, "onCreateView")

        rootView = inflater.inflate(R.layout.fragment_video, container, false)

        initView()

        return rootView
    }

    private fun initView() {
        viewPager = rootView.findViewById(R.id.video_view_pager)
        tabLayout = rootView.findViewById(R.id.video_tab_layout)
        tabLayout.tabMode = TabLayout.MODE_SCROLLABLE

        for (index in 0 until 10) {
            tabNames.add("第 $index 项")
            fragments.add(VideoInFragment())
            fragments[index].setTv(index.toString())
            tabLayout.addTab(tabLayout.newTab().setText(tabNames[index]).setCustomView(R.layout.custom_tab_layout))
            var holder = CustomViewHolder(tabLayout.getTabAt(index)!!.customView!!)
            holder.textView?.text = "第 $index 项"
            if (index == 0) {
                holder.textView?.textSize = 20F
            }
        }

        if (activity != null) {
            pagerAdapter = MyPagerAdapter(activity!!.supportFragmentManager)
            viewPager.adapter = pagerAdapter
        }

        initListener()
    }

    private fun initListener() {
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    var holder = CustomViewHolder(tab.customView!!)
                    holder.textView?.textSize = 15F
                }
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    viewPager.currentItem = tab.position
                    var holder = CustomViewHolder(tab.customView!!)
                    holder.textView?.textSize = 20F
                }
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

    inner class CustomViewHolder(view: View) {
        var textView: TextView? = null

        init {
            textView = view.findViewById(R.id.title)
        }

    }

}
