
#include "stdafx.h"
 
#define EOF (-1)

#ifdef __cplusplus    // If used by C++ code, 
extern "C" {          // we need to export the C interface
#endif
 
using namespace std;

// Global variable.
extern "C" __declspec (dllexport) int* _data = new int(123);

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
};};

// The structure with the pointer field, which refers to the next structure.
struct LinkedStructure
{
	int content;
	LinkedStructure* next; 
};

//--------------------------------------functions--------------------------------------

// Puts a string to the standard output stream. CDECL calling convention.
__declspec(dllexport) void printStringCdeclCall(LPTSTR lpszMsg)
{
	cout<<lpszMsg;
}


// Returns a sum of two numbers. Std calling convention.
__declspec(dllexport) int _stdcall sumStdCall(int a,int b)
{
    return a + b;
}

// Copies SampleStructures:inStructure to the outStructure.
__declspec(dllexport) void _stdcall copySampleStructure( SampleStructure* inStructure,
										                 SampleStructure* outStructure)
{
	outStructure->b = inStructure->b;
	outStructure->s = inStructure->s;
	outStructure->i = inStructure->i;
	outStructure->l = inStructure->l;
	outStructure->c = inStructure->c;
	outStructure->w = inStructure->w;
	outStructure->d = inStructure->d;
	outStructure->f = inStructure->f;
	outStructure->ld = inStructure->ld;
}

// Assigns 0 to the Key and empty string to the Value fields of KeyValueStructure.
__declspec(dllexport) void _stdcall fillKeyValueStructure(KeyValueStructure* inStructure)
{
	*(inStructure->key) = 365;
	inStructure->value = " days of the year.";
}


// Copies UnionStructure to another UnionStructure according to the isSymbol flag value.
__declspec(dllexport) void _stdcall copyUnionStructure(UnionStructure* inStructure,
												       UnionStructure* outStructure)
{
	outStructure->isChar = inStructure->isChar;
	if (inStructure->isChar  == 0)
		outStructure->code = inStructure->code;
	else
		outStructure->symbol = inStructure->symbol;
}

// Fills the content of 10 linkedStructure with numbers from 1 to 10 
// and refers each to the next LinkedStructure.
__declspec(dllexport) void _stdcall fillLinkedStructures( LinkedStructure* firstLinkedStructure)
{	
	LinkedStructure* current = new LinkedStructure();
	current->next = NULL;
	LinkedStructure* previous;
	int n = 10;
	for(int i = 0; i < n; i++)
	{
		current->content = i;
		previous = new LinkedStructure();
		previous->next = current;
		current  = previous;
	}
	*firstLinkedStructure = *(previous->next);	
}

// Returns the minimum value in the array of doubles.
__declspec(dllexport) double _stdcall findMinimum(double* array,int size)
{
	double result = array[0];
	for(int i=1 ; i< size; i++)
	{
			if(array[i] < result)
				result = array[i];
	}
	return result;
}

// Returns the Value field according to the incoming Key value,
// finding the appropriate one among the specified KeyValueStructure array.
_declspec(dllexport) char* _stdcall findValueByKey(KeyValueStructure * structures,
									               int size, int key)
{
	char* result;
	for(int i=0; i < size; i++)
		if (*structures[i].key == key)
			result = structures[i].value;
	return result;
}

// Removes duplicated values in the incoming array of doubles, changing the incoming array.
_declspec(dllexport) void _stdcall removeDuplicates(double* inArray,
										            int* length)
{
	int currentSize = *length;
	double current;
	for(int i = 0;i < currentSize; i++)
	{
		current = inArray[i];
		for(int j=(i+1); j < *length ; j++)
			if(inArray[j] == current)
			{
				for(int k = j ; k < currentSize; k++)
					inArray[k] = inArray[k+1];
				currentSize--;
				j--;
			}
	}
	*length = currentSize;
}
// Removes duplicated values in the incoming array of characters without changing the incoming value.
_declspec(dllexport) void _stdcall removeDuplicatesFromString
										(char* inString, char** returnValue)
{
	int len = (int)strlen(inString);
	char* outString =new char[len];
	
	
	char current;
	int pos=0;
	int outPos=0;
	bool oneMore = false; 
	while (current != '\0')
	{
		oneMore = false;
		current  = inString[pos];
		for(int i=(pos+1) ; i < len; i++)
			if(inString[i] == current)
				oneMore = true;
		if (!oneMore)
		{
			outString[outPos] = current;
			outPos++;
		}
		pos++;
	}
	*returnValue = outString;
}



// Finds the longest string in the specified zero-terminated string array.
_declspec(dllexport) wchar_t* _stdcall findLongest(wchar_t* inArray)
{
	
	int pos = 0;
	int len = (int) wcslen(inArray);
	wchar_t* longest = new wchar_t[len + 1];
	wchar_t* word;
	wcsncpy(longest, inArray + pos, len + 1);
	
	while (*(inArray+pos) != '\0')
	{
		len = (int) wcslen(inArray + pos);
		word = new wchar_t[len + 1];
		wcsncpy(word, inArray + pos, len + 1);
		if (wcslen(word) > wcslen(longest))
			longest = word;
		pos += len + 1;
	}
	return longest;
}

// Converts Unicode string to the ANSI one.
_declspec(dllexport) void _stdcall convert(wchar_t* inArray, char* outArray)
{
	
	int len=0,pos=0;
	
	while (*(inArray+pos) != '\0')
	{
		len = (int) wcslen(inArray + pos);
		wchar_t* word = new wchar_t[len + 1];
		char* charWord = new char[len + 1];
		wcsncpy(word, inArray + pos, len + 1);
		wcstombs(charWord, word, len+1);
        strncpy(outArray + pos, charWord, len+1);
		pos += len + 1;
	}
}

typedef int  _stdcall SumCallback(int number);

// Calculates a sum of all the natural numbers from 0 to number using java-side callback.
_declspec(dllexport) int _stdcall calculateSum(SumCallback* func,int number)
{
	int sum = 0;
	sum = func(number);
	return sum;
}


#ifdef __cplusplus
}
#endif


BOOL APIENTRY DllMain( HANDLE hModule, 
                       DWORD  ul_reason_for_call, 
                       LPVOID lpReserved
					 )
{
    return TRUE;
}

