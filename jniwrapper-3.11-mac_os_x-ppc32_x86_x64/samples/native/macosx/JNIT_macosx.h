/*
 * Copyright 2000-2004 TeamDev Ltd. All rights reserved.
 * TeamDev PROPRIETARY and CONFIDENTIAL. 
 * Use is subject to license terms.
 */
#ifndef __JNIT_MACOSX_H
#define __JNIT_MACOSX_H

#define declexport(rettype) extern "C" rettype

typedef signed char jni_int8;
typedef unsigned char jni_uint8;
typedef signed short jni_int16;
typedef unsigned short jni_uint16;
typedef signed int jni_int32;
typedef unsigned int jni_uint32;
typedef long long jni_int64;
typedef float jni_singlefloat;
typedef double jni_doublefloat;

#define STDCALL
#define CDECL

#endif
