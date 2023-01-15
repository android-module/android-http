# gradle-maven-kotlin-dsl
publish with 'maven-publish',  'bintray' , Kotlin DSL

# usage

# 上传到maven仓库

## 基础配置

必填项:

上传到bintray: **myBintrayRepo必填** 其他的无所谓

上传到maven: **myPublishedGroupId必填** 其他的无所谓

```groovy
myBintrayRepo=xxx  #上传到bintray时使用
myPublishedGroupId=com.xxxx #如com.caldremch.xxx
mySiteUrl=https://xxxx #项目网站
myGitUrl=https://xxx #项目git地址
myDeveloperId=xxx #开发者id
myDeveloperName=xxx #开发者名字
myDeveloperEmail=xxx #开发者邮箱
myLicenseName=xxxx #协议名字
myLicenseUrl=xxxx #协议链接
myAllLicenses=xxxx #可以设置多个协议

```


## 上传到jcenter(bintry)

### 在local.properties中配置bintray 用户名和apiKey
```groovy
bintray.user=xxx
bintray.apiKey=xxx
```

### 应用gradle脚本
①.groovy
```groovy
apply from:'xxx'
```
②.kotlin dsl
```groovy
apply(from="xxx")
```

### 打包上传
在gradle的任务中, 执行 bintrayUpload 任务,等待上传结束,ok


# 下面以贴出几个文件的使用方式

## bintray-with-maven-publish.gradle
上传到bintray依赖, 使用的是maven-publish 和 bintray 插件, 在模块的build.gradle中添加
```groovy
apply from:'https://raw.githubusercontent.com/caldremch/gradle-maven-kotlin-dsl/master/bintray-with-maven-publish.gradle'
```


## bintray-with-maven-publish.gradle.kts
上传到bintray依赖, 使用的是maven-publish 和 bintray 插件, 在模块的build.gradle.kts中添加
```groovy
apply(from="https://raw.githubusercontent.com/caldremch/gradle-maven-kotlin-dsl/master/bintray-with-maven-publish.gradle.kts")
```

## maven-publish.gradle.kts
上传到maven仓库(nexus等), maven-publish 插件, 在模块的build.gradle.kts中添加
```groovy
apply(from="https://raw.githubusercontent.com/caldremch/gradle-maven-kotlin-dsl/master/maven-publish.gradle.kts")
```

## 上传到nexus或者公司的maven仓库

### 1.在local.properties中配置仓库信息
```groovy
myMavenUrl=maven仓库的url
myMavenUserName=用户名
myMavenPassword=密码
```
### 应用脚本
①.groovy
```groovy
apply from:'xxx'
```
②.kotlin dsl
```groovy
apply(from="xxx")
```

### 打包上传
在gradle的任务中, 执行 publish任务,等待上传结束,ok
