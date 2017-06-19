# Toys

往昔所为一些有趣的玩具式的东西，今视之陋不可言，且惰于重构，然丢之尚觉可惜，遂留，以念之
常爱折腾，以致所丢项目甚多，而惜所留者。

## 2048
大一11月份做的一个2048，是时初涉Java，所为浅陋。
画面尚可，具有简陋AI

![2048](https://github.com/omsfuk/toys/blob/master/_2048/screenshot.PNG?raw=true "2048")

## FileServer
一个简单的web文件服务器。大一寒假无聊时搞得一个工具，主要是想通过局域网在手机和电脑之间传文件。
#### 功能定位
* 可以用来在终端之间（pc，phone等等）互传数据。把想共享的文件（文件夹）放在file目录下，即可实现局域网内的共享。

#### 使用说明
* 默认端口为8080
* file目录不可删除，为根目录。
* file目录下的favicon.icon为网站图标。可以删除或替换。
* url将被映射到file下。
* window系统双击start.bat启动服务
* 在浏览器中输入本机IP + ":8080"（例如192.169.32.43:8080)）即可访问。
* 若是在本机运行，则可以写localhost:8080

#### 常见问题
* 双击start.bat后窗口一闪接着自动关闭：端口被占用。请确保8080端口未被占用。

## BigDataSort
大一下高程小作业。163邮箱16亿数据排序。在有限的内存下，对16亿的大数据进行排序。
用2G数据测试，需要3分钟左右。


## Othello
黑白棋，丢了。，，，，立个碑~_~（黑白棋之墓）
所丢之一，大一寒假搞的东西，具有良好的用户界面。
其实界面是副产品，主要是为了写个AI。想看看AI是如何虐掉自己的=_=  
不失所望，最大最小搜索，不加Alpha-Bata剪纸，完虐自己
