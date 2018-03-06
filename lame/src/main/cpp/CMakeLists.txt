cmake_minimum_required(VERSION 3.4.1)

set(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${PROJECT_SOURCE_DIR}/../jniLibs/${ANDROID_ABI})

aux_source_directory(${CMAKE_HOME_DIRECTORY} LAME_SRC_LIST)
aux_source_directory(${CMAKE_HOME_DIRECTORY}/vector LAME_VECTOR_SRC_LIST)

add_library( lame
			 SHARED
			 ${LAME_SRC_LIST}
			 ${LAME_VECTOR_SRC_LIST})