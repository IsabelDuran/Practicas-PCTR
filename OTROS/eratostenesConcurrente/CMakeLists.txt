cmake_minimum_required(VERSION 3.13)
project(eratostenesConcurrente)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11 -pthread")

add_executable(eratostenesConcurrente main.cpp)