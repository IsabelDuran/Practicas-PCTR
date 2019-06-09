cmake_minimum_required(VERSION 3.13)
project(aRendimientoCpp)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11 -pthread")

add_executable(aRendimientoCpp main.cpp)