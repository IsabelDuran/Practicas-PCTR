cmake_minimum_required(VERSION 3.13)
project(transformacionParalelaMatriz)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11 -pthread")

add_executable(transformacionParalelaMatriz main.cpp)