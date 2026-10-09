# nacos-plugin

Nacos 插件集合，为 Nacos 提供可插拔的插件能力，支持用户自定义和高度可扩展性。

![](https://tva1.sinaimg.cn/large/008i3skNly1gxmnilyukqj30qp0fgglx.jpg)

## 项目简介

本项目是 [Alibaba Nacos](https://nacos.io/) 的插件扩展集合，基于 Nacos 2.5.1 版本开发，采用 Java SPI（Service Provider Interface）机制实现插件的动态发现与加载。

- **组织**: nacos-group
- **许可证**: Apache License 2.0
- **版本**: 2.5.1-SNAPSHOT
- **技术栈**: Java 1.8 + Maven

## 项目结构

```
nacos-plugin-ext
├── nacos-encryption-plugin-ext/          # 加密插件扩展
│   └── nacos-aes-encryption-plugin       # AES 加密算法实现
├── nacos-trace-plugin-ext/               # 追踪插件扩展
│   └── nacos-trace-logging-plugin        # 日志追踪实现
├── nacos-custom-environment-plugin-ext/  # 自定义环境插件扩展
│   └── nacos-db-password-encryption-plugin  # 数据库密码加密
├── nacos-datasource-plugin-ext/          # 数据源插件扩展
│   ├── nacos-datasource-plugin-ext-base  # 数据源基础抽象层
│   ├── nacos-postgresql-datasource-plugin-ext   # PostgreSQL
│   ├── nacos-opengauss-datasource-plugin-ext    # OpenGauss
│   ├── nacos-oracle-datasource-plugin-ext       # Oracle
│   ├── nacos-dm-datasource-plugin-ext           # 达梦数据库
│   ├── nacos-mssql-datasource-plugin-ext        # SQL Server
│   └── nacos-kingbase-datasource-plugin-ext     # 人大金仓
└── nacos-config-change-plugin-ext/       # 配置变更插件扩展
    ├── nacos-whitelist-config-change-plugin       # 白名单过滤
    ├── nacos-fileformat-config-change-plugin      # 文件格式处理
    └── nacos-webhook-config-change-plugin         # Webhook 通知
```

## 插件模块说明

### 1. 加密插件（nacos-encryption-plugin-ext）

提供配置数据的加密/解密能力。

- **模块**: `nacos-aes-encryption-plugin`
- **SPI 接口**: `EncryptionPluginService`
- **功能**:
  - AES/CBC/PKCS5Padding 加密算法
  - 支持加密、解密、密钥生成
  - 通过 Base64 + Hex 编码处理密钥

### 2. 追踪插件（nacos-trace-plugin-ext）

提供操作审计和追踪能力。

- **模块**: `nacos-trace-logging-plugin`
- **SPI 接口**: `NacosTraceSubscriber`
- **功能**:
  - 订阅 Nacos 事件
  - 记录操作日志
  - 支持审计追踪

### 3. 自定义环境插件（nacos-custom-environment-plugin-ext）

提供环境变量和配置的自定义处理能力。

- **模块**: `nacos-db-password-encryption-plugin`
- **SPI 接口**: `CustomEnvironmentPluginService`
- **功能**:
  - 数据库密码加密存储
  - 环境变量自定义转换

### 4. 数据源插件（nacos-datasource-plugin-ext）

提供多数据库支持能力，是本项目最复杂的模块。

- **基础模块**: `nacos-datasource-plugin-ext-base`
  - 提供抽象数据库方言（`DatabaseDialect`）
  - 基础 Mapper 实现
  - 数据库方言管理器
- **支持的数据库**:
  - PostgreSQL
  - OpenGauss（华为高斯）
  - Oracle
  - 达梦数据库（DM）
  - SQL Server（MSSQL）
  - 人大金仓（KingBase）
- **SPI 接口**: `DatabaseDialect`、`Mapper`
- **功能**:
  - 数据库方言适配
  - SQL 语句映射
  - 主键策略支持
  - Schema 定义

### 5. 配置变更插件（nacos-config-change-plugin-ext）

提供配置变更的拦截和扩展能力。

- **模块**:
  - `nacos-whitelist-config-change-plugin`: 基于文件类型白名单过滤导入的配置
  - `nacos-fileformat-config-change-plugin`: 配置格式处理
  - `nacos-webhook-config-change-plugin`: 配置变更时触发 Webhook 通知
- **SPI 接口**: `ConfigChangePluginService`
- **功能**:
  - 配置变更前/后置拦截
  - 支持 HTTP 导入过滤
  - Webhook 异步通知
  - 插件优先级排序

## 技术特性

### 插件机制

- 基于 Java SPI 实现插件自动发现
- 配置文件位于 `src/main/resources/META-INF/services/` 目录
- 支持运行时动态加载和卸载

### 构建配置

- **编译目标**: Java 1.8
- **编码**: UTF-8
- **核心插件**:
  - `maven-shade-plugin` (3.2.4): 合并依赖，排除签名文件，处理 SPI 资源
  - `flatten-maven-plugin` (1.1.0): 处理 `${revision}` 版本占位符，生成 CI 友好的 POM

### 依赖管理

- 核心 Nacos 依赖版本: 2.5.1
- 部分依赖标记为 `provided` 作用域（如 `nacos-datasource-plugin`、`nacos-common`）
- 由 Nacos 运行时容器提供基础能力
- 统一版本管理在父 POM 的 `dependencyManagement` 中定义

### 设计模式

1. **策略模式**: 各插件实现统一 SPI 接口，支持多种实现
2. **工厂模式**: 通过 SPI 机制动态加载具体实现
3. **模板方法模式**: 数据源插件提供抽象基类，子类实现具体数据库方言
4. **责任链模式**: 配置变更插件支持按优先级顺序执行

## 快速开始

### 编译构建

```bash
mvn clean install
```

### 使用插件

1. 选择需要的插件模块
2. 编译打包生成 JAR 文件
3. 将 JAR 文件放入 Nacos 的 `plugins` 目录
4. 重启 Nacos 服务，插件自动加载

### 开发自定义插件

1. 继承对应的 SPI 接口
2. 在 `META-INF/services/` 目录创建 SPI 配置文件
3. 实现业务逻辑
4. 打包部署到 Nacos

## 模块依赖关系

```
nacos-plugin-ext (父 POM)
├── nacos-encryption-plugin (provided)
├── nacos-trace-plugin (provided)
├── nacos-custom-environment-plugin (provided)
├── nacos-config-plugin (provided)
├── nacos-datasource-plugin (provided)
└── nacos-common (provided)
```

所有 Nacos 核心依赖均标记为 `provided`，表示这些依赖由 Nacos 运行时环境提供，避免重复打包和版本冲突。

## 注意事项

1. **版本兼容性**: 本插件集基于 Nacos 2.5.1 开发，请确保 Nacos 服务端版本匹配
2. **Java 版本**: 需要 Java 1.8 或更高版本
3. **数据库支持**: 数据源插件需要对应数据库的 JDBC 驱动（需自行添加）
4. **插件冲突**: 同一类型的插件可能存在冲突，请注意 SPI 配置文件的优先级

## 贡献指南

欢迎提交 Issue 和 Pull Request！

## 许可证

[Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0)
