
程序功能：

1. 针对CI看板变红后，需要手工复查Local Check的现状而设计，由程序自动进行复查。
2. 程序运行模式一：程序直接运行时，用户手工选择检查的模块后，自动进入检查。 程序会从库中最新版本开始往前检查，直到找到Local Check故障为一个循环。找到后可以选择是否进入下一次循环。
3. 程序运行模式二：从CI调用，由CI传入模块号，待检查的起止版本号，程序自动进入检查，输出报表。
4. 本程序检查Local Check故障；无法检查编译错误；只用于检查4x代码。


使用步骤：

1. 修改conf目录下的config.properties

2. 环境变量中配置了JAVA_HOME的，直接执行run.bat即可。否则需要在run.bat中手动添加。注意至少为JDK 1.6版本。

3. 模式一：报表位于temp/report目录，按模块和版本号存放。入库前和入库后都有报表。
   
   例如，想要检查UNI模块下版本号为2011的报表，可以到 临时目录/report/UNI/2011/下，查找current_checkstyle_report.html和old_checkstyle_report.html。
   这两个文件分别对应入库前的错误数和入库后的错误数。
   
4. 模式二：报表位于report目录。

源码位置：
https://10.67.9.12:8443/TMN/NETNUMENN31_Common_SH/trunk/antools/IncrementStaticCheckLocal/src
https://10.67.9.12:8443/TMN/NETNUMENN31_Common_SH/trunk/antools/IncrementStaticCheckLocal/testsrc

   
版本更新记录：
2011.4:
第一版

2011.4.17：
列出新增故障数

2011.8.2：
列出减少的故障数

2012.8.7：
1) 正式引入版本号 2.0
2) 程序改名为“静态缺陷增量检查工具 (Code Defect Incremental Check)”
3) 修复无法查询“删除文件夹操作”的故障，已经没有已知缺陷
4) 支持与CI看板集成
5) 其他细节改进

2012.12.20 更新2.1版
1)支持SVN1.6，1.7版本
2)支持SVN中英文版本
3)易用性改进：启动程序时，会检查当前SVN版本是否满足程序要求，不满足会给出明确提示。

2012.12.25 更新2.1.1版
修复SVN版本为1.7.6-SlikSvn-1.7.6-X64（在持续集成服务器上）的程序异常故障。

