#!/bin/bash

# Configuration
PROJECT_ROOT=$(pwd)
NATIVE_DIR="$PROJECT_ROOT/app/shared/native_lib"
ANDROID_CPP_DIR="$PROJECT_ROOT/app/shared/src/androidMain/cpp"
ANDROID_JNI_DIR="$PROJECT_ROOT/app/shared/src/androidMain/jniLibs/arm64-v8a"
NDK_PATH="/Users/edison/Library/Android/sdk/ndk/27.1.12297006" # Update if needed

echo "🚀 Starting Full Native Compilation..."

# 1. iOS (Simulator Arm64)
echo "🍎 Compiling for iOS Simulator..."
cd "$NATIVE_DIR"
xcrun --sdk iphonesimulator clang -arch arm64 -c simple_math.c -o simple_math.o
ar rcs libsimple_math.a simple_math.o
rm simple_math.o

# 2. Desktop (macOS Arm64 JNI)
echo "💻 Compiling for Desktop (JNI)..."
xcrun --sdk macosx clang -shared -fPIC \
    -I"$JAVA_HOME/include" \
    -I"$JAVA_HOME/include/darwin" \
    simple_math.c simple_math_jni.c \
    -o libsimple_math_jni.dylib

# 3. Android (Arm64-v8a)
echo "🤖 Compiling for Android (Arm64)..."
mkdir -p "$ANDROID_JNI_DIR"
"$NDK_PATH/toolchains/llvm/prebuilt/darwin-x86_64/bin/aarch64-linux-android24-clang++" -shared -fPIC \
    -I"$NATIVE_DIR" \
    "$ANDROID_CPP_DIR/simple_math_jni.cpp" "$NATIVE_DIR/simple_math.c" \
    -o "$ANDROID_JNI_DIR/libsimple_math_android.so"

echo "✅ All native libraries compiled successfully!"
