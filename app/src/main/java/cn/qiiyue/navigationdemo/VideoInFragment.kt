package cn.qiiyue.navigationdemo


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class VideoInFragment : Fragment() {

    private lateinit var textView: TextView
    private var text: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_video_in, container, false)
        textView = view.findViewById(R.id.tv)

        textView.text = text

        return view
    }

    fun setTv(string: String) {
        text = string
    }

}
