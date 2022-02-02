# think-swing

[![Build Status](https://travis-ci.org/think-project/think-framework.svg?branch=master)](https://travis-ci.org/think-project/think-framework)
[![codecov](https://codecov.io/gh/think-projects/think-framework/branch/master/graph/badge.svg?token=QJ2NQ55LEY)](https://codecov.io/gh/think-projects/think-framework)

一套代码生成工具,包含:
```
1. 一个基于JDBC实现的ProwDesinger数据库驱动
2. 一个基于Freemarker的代码生成工具
3. 一个基于Swing的数据库连接工具
```

maven命令 `mvn clean verify sonar:sonar`

```
mvn clean deploy -DaltDeploymentRepository=sonatype-nexus-snapshots::default::https://oss.sonatype.org/content/repositories/snapshots/ \
    -Dmaven.test.skip=true
```

Introduction
---

- git clone https://github.com/hdhxby/think-framework.git
- cd think-framework && mvn install
- have fun.

Documentation
---

- 中文 https://github.com/alibaba/druid/wiki/%E5%B8%B8%E8%A7%81%E9%97%AE%E9%A2%98
