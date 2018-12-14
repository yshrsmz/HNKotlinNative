## Setup

- JDK8
- Android Studio 3.2.1
- XCode 10.1

レポジトリ直下に`local.properties`を作成(Android Studioでプロジェクトをインポートしたら自動生成される？)

```
ndk.dir=/path/to/android/sdk/ndk-bundle # 例: /Users/yshrsmz/Android/Sdk/ndk-bundle
sdk.dir=/path/to/android/sdk # 例: /Users/yshrsmz/Android/Sdk
```

## 方針

- `HNApi`には`suspend`なメソッドを書く
- iOSはそのまま`suspend`なメソッドを使えないので、`data/src/iosMain`にコールバックなどiOSに適した形のラッパーメソッドを書く


## ディレクトリ構成

- app
  - Androidアプリモジュール
- data 
  - 共通データ層モジュール
  - `src/commonMain`が共通コード
  - `src/androidMain`がAndroid向け
  - `src/iosMain`がiOS向け
- iosApp
  - iOSアプリ。XCodeで`iosApp/iosApp.xcodeproj`を開く。

## references

- HackerNews api: https://github.com/HackerNews/API
- ktor-mpp: https://github.com/ktorio/ktor-samples/tree/master/mpp
- kotlin coroutines native: https://github.com/Kotlin/kotlinx.coroutines/tree/master/native
- kotlin native samples: https://github.com/JetBrains/kotlin-native/tree/master/samples
- kotlinx serialization: https://github.com/Kotlin/kotlinx.serialization
- ktor maven repo: https://dl.bintray.com/kotlin/ktor/io/ktor/

