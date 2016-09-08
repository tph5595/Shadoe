#ifndef __SAMPLES_H
#define __SAMPLES_H

#if defined _WIN32
#  include "win32/jnit_win32.h"
#elif defined(__linuxPPC32__)
#  include "linux_ppc32/JNIT_linux.h"
#elif defined(__PPC64__)
#  include "linux_ppc64/JNIT_linux.h"
#elif defined(__linux64__)
#  include "linux64/JNIT_linux.h"
#elif defined(__linux__)
#  include "linux/JNIT_linux.h"
#elif defined(__SUNOS__)
#  include "sunos/JNIT_sunos.h"
#elif defined(__SUNOS_SPARC64__)
#  include "sunos_sparc64/JNIT_sunos_sparc64.h"
#elif defined(__SUNOS_SPARC__)
#  include "sunos_sparc/JNIT_sunos_sparc.h"
#elif defined(__SUNOS64__)
#  include "sunos64/JNIT_sunos64.h"
#elif defined(MACOS_INTEL)
#  include "macosx_intel/JNIT_macosx.h"
#elif defined(MACOS_INTEL64)
#  include "macosx_intel64/JNIT_macosx.h"
#elif defined(__MACH__)
#  include "macosx/JNIT_macosx.h"
#elif defined(__AIX_PPC__)
#  include "aix_ppc/JNIT_aixppc.h"
#endif

#include <string>

declexport(char) _data[];

// The Sample Structure with fields of most usable types.
struct SampleStructure
{
	bool b;
	short s;
    int i;
	long l;
	char c;
	wchar_t w;
	float f;
    double d;
	long double ld;
};

// The structure with the pointer field. 
struct KeyValueStructure
{
    int* key;
    char* value;
};

// The structure with union.
struct UnionStructure
{
	bool isChar;
	union 
	{
		wchar_t symbol;
		int code;
	};
};

// The structure with the pointer field, which refers to the next structure.
struct LinkedStructure
{
	int content;
	LinkedStructure* next; 
};

// The structure with the bit fields.
struct BitStructure
{
	unsigned b1 : 1;
	unsigned b2 : 2;
	unsigned b3 : 3;
	unsigned b4 : 2;
	unsigned b5 : 10;
};

#endif // __SAMPLES_H
