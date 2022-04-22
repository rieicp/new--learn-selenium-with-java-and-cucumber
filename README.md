# Testing login SonarQube
- 需要事先運行 sonarqube 的docker容器，端口設置為9001

# Selenium
- 合适的 chromedriver 版本下载
  - URL:  https://sites.google.com/chromium.org/driver/downloads
  - 解压缩 chromedriver 添加可执行属性
  - 保存到目录，如 /home/nwe/Downloads/chromedriver
- 在程序中使用
  - System.setProperty("webdriver.chrome.driver",”/home/nwe/Downloads/chromedriver”)

# helm Commands
* helm create simple-spring-app-4-testing-debugging-in-k8s
* helm install simple-spring-app-4-testing-debugging-in-k8s simple-spring-app-4-testing-debugging-in-k8s
* helm uninstall simple-spring-app-4-testing-debugging-in-k8s


