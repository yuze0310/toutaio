package com.yanxuwen.module_bottomtab0.Main

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.shuyu.gsyvideoplayer.GSYVideoManager
import com.yanxuwen.lib_common.Base.BaseActivity
import com.yanxuwen.lib_common.Bean.ARouterPath
import com.yanxuwen.lib_common.Bean.Value
import com.yanxuwen.lib_common.Utils.video.VideoListUtils
import com.yanxuwen.lib_common.retrofit.model.NewsList.NewsContent
import com.yanxuwen.module_bottomtab0.Bean.Key.VideoKey
import com.yanxuwen.module_bottomtab0.R
import com.yanxuwen.myutils.Utils.ToastUtil
import kotlinx.android.synthetic.main.bt0_activity_live2.*

/**
 * Created by yanxw on 2018/9/3.
 * 横屏直播
 */
@Route(path = ARouterPath.Module_Bottomtab0_LiveActiviy2)
class LiveActivity2 : BaseActivity() {
    val mNewsContent: NewsContent by lazy {
        intent.extras.getSerializable(VideoKey.NewsContent) as NewsContent
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.bt0_activity_live2)
        super.onCreate(savedInstanceState)
        setStatusFull(true)
    }
    override fun initData() = Unit

    override fun initView(status: Value.ObservableStatus?)  = initVideoInfo()
    fun initVideoInfo(){
        if(mNewsContent == null || mNewsContent.raw_data == null){
            ToastUtil.showToast(context,"未找到该直播")
            finish()
        }
        var mVideoListUtils= VideoListUtils(context)
        var imgUrl=mNewsContent?.raw_data?.large_image?.url_list?.get((mNewsContent?.raw_data?.large_image?.url_list)?.size!! -1)?.url?:""
        var url=mNewsContent?.raw_data?.live_info?.stream_url?.flv_pull_url?:""
        mVideoListUtils.setData(layout_live,-1,mNewsContent?.raw_data?.titleX?:"",imgUrl,url)
        mVideoListUtils.getPlayer()?.startPlayLogic()
    }

    override fun onBackPressed() {
        if (GSYVideoManager.backFromWindowFull(this)) {
            return
        }
        super.onBackPressed()
    }
    override fun onResume() {
        GSYVideoManager.onResume()
        super.onResume()
    }
    override fun onPause() {
        GSYVideoManager.onPause()
        super.onPause()
    }
    override fun onDestroy() {
        GSYVideoManager.releaseAllVideos()
        super.onDestroy()
    }
}