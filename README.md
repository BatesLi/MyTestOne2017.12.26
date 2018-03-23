# 仿知乎日报的项目
项目结构: 一个主Activity+三个Fragment为主要结构,用Viewpager来管理fragment
框架:网络层采用 Retrofit 加 RxJava 的模式，访问的接口在这里-->http://gank.io/api。
图片加载使用的是 Glide 加载。
用WebView展示日报/干货详情页面 ,使用的是 android 自带的 WebView。
整个界面风格比较简约，使用 android 的 CoordinatorLayout 结合 ToolBar 和 FloatingActionButton 做动态布局，主题切换采用的是淡进淡出的效果。
使用 RecycleView+CardView 解析接口中获取的 json 数据并展示。
下拉刷新使用的是系统的 SwipeRefreshLayout，上拉加载是以添加装饰的方式放在在 Recycle 的 Adapter 
