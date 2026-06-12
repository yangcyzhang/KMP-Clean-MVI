# 100% Kotlin：基于 KMP + Compose Multiplatform 的全栈架构实战（Clean Architecture + MVI）

## 前言
随着 Kotlin Multiplatform (KMP) 进入稳定期，Compose Multiplatform 的跨端能力也日益成熟。我们是否真的能用一套代码，搞定 Android、iOS、Web、Desktop 甚至后端？

答案是肯定的。

本文分享一个开源项目 **KMP-Clean-MVI**，它演示了如何使用纯 Kotlin 构建一个包含移动端、网页、桌面端和后端服务的全栈系统，并深度集成 **Clean Architecture**、**MVI** 以及 **跨平台原生 C 代码调用** 技术。

---

## 🚀 项目亮点
该项目实现了真正的“一码多端”与“全栈共享”：
*   **全平台覆盖**：Android, iOS, Desktop, Web (Wasm/JS), Server。
*   **UI 共享**：基于 **Compose Multiplatform** 实现像素级一致的跨端 UI。
*   **全栈逻辑复用**：`/core` 模块中的业务逻辑与模型同时供前端各端与 **Ktor Server** 后端使用。
*   **硬核原生集成**：在全平台实现了对底层 C 语言库的“真原生”调用（非模拟）。
*   **现代架构**：严格遵循 **Clean Architecture** 分层，并结合 **MVI (Model-View-Intent)** 实现响应式状态管理。

---

## 🛠 技术栈
*   **语言**：100% Kotlin
*   **跨端 UI**：Compose Multiplatform
*   **后端框架**：Ktor Server
*   **网络请求**：Ktor Client
*   **原生调用**：CInterop (iOS), JNI (Android/Desktop), JS Interop (Web)
*   **架构驱动**：Kotlin Coroutines + Flow

---

## 📸 运行效果
项目在各端表现高度一致，从移动端到桌面端，再到现代化的 Wasm 网页端，均能完美运行。

| Android | iOS |
| :---: | :---: |
| ![Android](https://raw.githubusercontent.com/yangcyzhang/KMP-Clean-MVI/main/screenshots/android.png) | ![iOS](https://raw.githubusercontent.com/yangcyzhang/KMP-Clean-MVI/main/screenshots/ios.png) |
| **Desktop** | **Web (Wasm)** |
| ![Desktop](https://raw.githubusercontent.com/yangcyzhang/KMP-Clean-MVI/main/screenshots/desktop.png) | ![Web](https://raw.githubusercontent.com/yangcyzhang/KMP-Clean-MVI/main/screenshots/web.png) |

---

## 🏗 核心架构设计

为了保证代码的可维护性，项目采用了经典的分层设计：

### 1. Core 模块 (Domain Layer)
这是纯 Kotlin 逻辑层。包含了所有的领域模型 (Entities) 和业务校验规则。
*   **后端复用**：Ktor Server 直接引用该模块，实现前后端数据模型的“绝对同步”。

### 2. Shared 模块 (Presentation & UI)
基于 Compose Multiplatform。
*   **MVI 模式**：通过 `Intent` 驱动状态更新，`ViewState` 统一管理 UI。这种单向数据流极大降低了跨端 UI 调试的复杂性。
*   **Expect/Actual 原生桥接**：定义了统一的 `nativeAdd` 接口，各平台根据自身特性实现底层调用。

### 3. Native 逻辑集成 (The Hardcore Part)
这是本项目最硬核的部分，演示了如何让 KMP 穿越到 C 语言层：

*   **iOS (CInterop)**：通过 `.def` 声明，将 C 静态库直接链接进 Kotlin/Native Framework。
*   **Android (NDK/JNI)**：使用 NDK Clang 编译出 `.so` 库，通过 `System.loadLibrary` 实现高性能调用。
*   **Desktop (JNI)**：编译 `.dylib` 动态库，在 JVM 运行时动态加载。
*   **Web (JS Interop)**：利用 JS 引擎的互操作能力。

---

## 📂 项目结构
*   **`/core`**: 核心逻辑与通用数据模型。
*   **`/server`**: 基于 Ktor 的后端服务。
*   **`/app/shared`**: 共享的 UI 与 ViewModel 逻辑，包含原生 C 库。
*   **`/app/androidApp` / `iosApp` / `webApp` / `desktopApp`**: 各平台启动入口。

---

## 🏃 如何快速运行？

### 1. 编译原生库
在根目录运行一键编译脚本（需安装 NDK）：
```bash
./compile_native.sh
```

### 2. 启动后端 (Server)
*后端默认运行在 `http://localhost:8081`*
```bash
./gradlew :server:run
```

### 3. 启动前端
*   **Android**: 直接在 Android Studio 中运行 `androidApp` 配置。
*   **Desktop**: 执行 `./gradlew :app:desktopApp:run`
*   **Web (Wasm)**: 执行 `./gradlew :app:webApp:wasmJsBrowserDevelopmentRun`
*   **iOS**: 在 Xcode 中打开 `app/iosApp/iosApp.xcodeproj` 运行。

---

## 结语
Kotlin Multiplatform 不再仅仅是一个工具，它正演变成一种全新的开发范式。通过 **KMP-Clean-MVI**，我们可以看到从高性能底层 C 库调用，到后端 API，再到多端 UI 渲染的全链路 Kotlin 开发能力。

如果你正在寻找一个现代化的全栈跨平台开发模版，欢迎点个 Star！

**GitHub 传送门**：[https://github.com/yangcyzhang/KMP-Clean-MVI](https://github.com/yangcyzhang/KMP-Clean-MVI)
