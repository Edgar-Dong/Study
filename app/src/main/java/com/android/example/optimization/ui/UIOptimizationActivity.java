package com.android.example.optimization.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.FrameMetrics;
import android.view.Window;

import com.android.example.R;
import com.android.example.log.Logger;

public class UIOptimizationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui_optimization);

        renderingPerformanceMeasurement();
    }

    /**
     * 渲染性能测量
     */
    private void renderingPerformanceMeasurement() {
        HandlerThread handlerThread = new HandlerThread("FrameMetrics");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());
        //24版本以后的API支持该选项
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //整个渲染过程在子线程中进行
            getWindow().addOnFrameMetricsAvailableListener(new Window.OnFrameMetricsAvailableListener() {
                @Override
                public void onFrameMetricsAvailable(Window window, FrameMetrics frameMetrics, int dropCountSinceLastInvocation) {
                    //帧渲染测量完毕后，会回调该方法
                    //渲染性能参数都封装在FrameMetrics参数中

                    FrameMetrics fm = new FrameMetrics(frameMetrics);

                    //1. 动画执行回调时间，单位纳秒
                    Logger.get().d("FrameMetrics", "ANIMATION_DURATION:" + fm.getMetric(FrameMetrics.ANIMATION_DURATION));

                    // 2. 向 GPU 发送绘制命令花费的时间, 单位纳秒
                    Logger.get().i("FrameMetrics", "COMMAND_ISSUE_DURATION : " +
                            fm.getMetric(FrameMetrics.COMMAND_ISSUE_DURATION));

                    // 3. 将组件树 ( View Hierarchy ) 转为显示列表 ( DisplayLists )
                    // 计算过程所花费的时间, 单位纳秒
                    Logger.get().i("FrameMetrics", "DRAW_DURATION : " +
                            fm.getMetric(FrameMetrics.DRAW_DURATION));

                    // 4. 绘制的该帧是否是第一帧, 0 是, 1 不是
                    // 第一帧渲染会慢一些
                    // 第一帧不会引发动画中的跳帧问题, 这些问题都会被窗口动画隐藏
                    // 不必进行显示过程中的 jank 计算
                    Logger.get().i("FrameMetrics", "FIRST_DRAW_FRAME : " +
                            fm.getMetric(FrameMetrics.FIRST_DRAW_FRAME));

                    // 5. 处理输入事件花费的时间, 单位纳秒
                    Logger.get().i("FrameMetrics", "INPUT_HANDLING_DURATION : " +
                            fm.getMetric(FrameMetrics.INPUT_HANDLING_DURATION));

                    // 6. 该值是个时间戳, 表示该帧的 vsync 信号发出时间
                    // 这个时间是当前帧的预期开始时间
                    // 如果该时间与 VSYNC_TIMESTAMP 时间戳不同
                    // 那么说明 UI 线程被阻塞了, 没有及时响应 vsync 信号
                    Logger.get().i("FrameMetrics", "INTENDED_VSYNC_TIMESTAMP : " +
                            fm.getMetric(FrameMetrics.INTENDED_VSYNC_TIMESTAMP));

                    // 7. 组件树 ( view hierarchy ) 测量 ( measure ) 和摆放 ( layout ) 花费的时间
                    // 单位 纳秒
                    Logger.get().i("FrameMetrics", "LAYOUT_MEASURE_DURATION : " +
                            fm.getMetric(FrameMetrics.LAYOUT_MEASURE_DURATION));

                    // 8. CPU 传递多维向量图形数据给 GPU 花费的时间, 单位纳秒
                    Logger.get().i("FrameMetrics", "SWAP_BUFFERS_DURATION : " +
                            fm.getMetric(FrameMetrics.SWAP_BUFFERS_DURATION));

                    // 9. 显示列表 ( DisplayLists ) 与显示线程同步花费的时间, 单位纳秒
                    Logger.get().i("FrameMetrics", "SYNC_DURATION : " +
                            fm.getMetric(FrameMetrics.SYNC_DURATION));

                    // 10. CPU 渲染到传递到 GPU 所用的总时间, 上述所花费的有意义的时间之和
                    // 单位纳秒
                    Logger.get().i("FrameMetrics", "TOTAL_DURATION : " +
                            fm.getMetric(FrameMetrics.TOTAL_DURATION));

                    // 11. UI 线程响应并开始处理渲染的等待时间, 一般是 0, 如果大于 0 说明出问题了
                    Logger.get().i("FrameMetrics", "UNKNOWN_DELAY_DURATION : " +
                            fm.getMetric(FrameMetrics.UNKNOWN_DELAY_DURATION));

                    // 12. vsync 信号发出的时间戳, 该时刻 GPU 应该进行绘制, 间隔 16ms
                    // 同时 CPU 开始渲染
                    Logger.get().i("FrameMetrics", "VSYNC_TIMESTAMP : " +
                            fm.getMetric(FrameMetrics.VSYNC_TIMESTAMP));
                }
            }, handler);
        }
    }
}