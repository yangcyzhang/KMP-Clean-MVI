cmake_minimum_required(VERSION 3.22.1)

project("simple_math_android")

# Define the shared library
add_library(simple_math_android SHARED
    simple_math_jni.cpp
    ../../../../native_lib/simple_math.c
)

# Include the headers
target_include_directories(simple_math_android PRIVATE
    ../../../../native_lib
)

# Link log library for debugging
find_library(log-lib log)
target_link_libraries(simple_math_android ${log-lib})
