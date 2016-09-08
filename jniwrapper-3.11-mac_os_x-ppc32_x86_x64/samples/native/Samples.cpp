#include "stdafx.h"
#include "Samples.h"

#include <stdio.h>

#ifdef __cplusplus    // If used by C++ code,
extern "C" {          // we need to export the C interface
#endif

using namespace std;

// Global variable.
char _data[] = "Test string.";

//--------------------------------------functions--------------------------------------

// Puts a string to the standard output stream. CDECL calling convention.
declexport(void) printStringCdeclCall(const char* lpszMsg)
{
	printf("%s\n", lpszMsg);
}

// Returns a sum of two numbers. Std calling convention.
declexport(int) STDCALL sumStdCall(int a,int b)
{
    return a + b;
}

// Copies SampleStructures:inStructure to the outStructure.
declexport(void) STDCALL copySampleStructure(SampleStructure* inStructure,
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
declexport(void) STDCALL fillKeyValueStructure(KeyValueStructure* inStructure)
{
	*(inStructure->key) = 365;
	inStructure->value = " days of the year.";
}

// Copies UnionStructure to another UnionStructure according to the isSymbol flag value.
declexport(void) STDCALL copyUnionStructure(UnionStructure* inStructure,
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
declexport(void) STDCALL fillLinkedStructures( LinkedStructure* firstLinkedStructure)
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
declexport(double) STDCALL findMinimum(double* array,int size)
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
declexport(char*) STDCALL findValueByKey(KeyValueStructure * structures,
											int size, int key)
{
	char* result;
	for(int i=0; i < size; i++)
		if (*structures[i].key == key)
			result = structures[i].value;
	return result;
}

// Removes duplicated values in the incoming array of doubles, changing the incoming array.
declexport(void) STDCALL removeDuplicates(double* inArray,
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
declexport(void) STDCALL removeDuplicatesFromString(char* inString, char** returnValue)
{
	int len = (int)strlen(inString);
	char* outString =new char[len];

	char current = inString[0];
	int pos=0;
	int outPos=0;
	bool oneMore = false;
	while (current != '\0')
	{
		oneMore = false;
		for(int i=(pos+1) ; i < len; i++)
			if(inString[i] == current)
				oneMore = true;
		if (!oneMore)
		{
			outString[outPos] = current;
			outPos++;
		}
		current = inString[++pos];
	}
	outString[outPos] = 0;
	*returnValue = outString;
}

// Finds the longest string in the specified zero-terminated string array.
declexport(wchar_t*) STDCALL findLongest(wchar_t* inArray)
{
	int pos = 0;
	int len = (int) wcslen(inArray);
	static wchar_t longest[100], word[100];
	wcscpy(longest, inArray + pos);

	while (*(inArray+pos) != '\0')
	{
		len = (int) wcslen(inArray + pos);
		wcscpy(word, inArray + pos);
		
		if (wcslen(word) > wcslen(longest))
			wcscpy(longest, word);
		pos += len + 1;
	}
	return longest;
}

// Converts Unicode string to the ANSI one.
declexport(void) STDCALL convert(wchar_t* inArray, char* outArray)
{
	int len=0,pos=0;

	while (*(inArray+pos) != '\0')
	{
		len = (int) wcslen(inArray + pos);
		wchar_t word[100];
		char* charWord = new char[len + 1];
		wcscpy(word, inArray + pos);
		wcstombs(charWord, word, len + 1);
        strcpy(outArray + pos, charWord);
		pos += len + 1;
	}
}

#if defined(__GNUC__)
typedef int (*SumCallback)(int) STDCALL;
#else
typedef int (STDCALL* SumCallback)(int);
#endif // Win32 | Linux

// Calculates a sum of all the natural numbers from 0 to number using java-side callback.
declexport(int) STDCALL calculateSum(SumCallback func, int number)
{
	int sum = 0;
	sum = func(number);
	return sum;
}

// Calculate a sum from all bit fields in structure.
declexport(int) STDCALL calculateSunFromBitStructure(BitStructure inStructure)
{
	return inStructure.b1 + inStructure.b2 + inStructure.b3 + inStructure.b4 + inStructure.b5;
}

#if defined(__GNUC__)
typedef void (*CreateArrayCallback)(int from, int to, int &count, int **array) STDCALL;
#else
typedef void (STDCALL* CreateArrayCallback)(int from, int to, int &count, int **array);
#endif // Win32 | Linux

// Create an array and return count of elements in array.
declexport(int*) STDCALL createArray(int from, int to, int &count, CreateArrayCallback func)
{
	int *array = NULL;
	func(from, to, count, &array);
	return array;
}

#if defined(__GNUC__)
typedef void (*FillArrayCallback)(int from, int count, int *array) STDCALL;
#else
typedef void (STDCALL* FillArrayCallback)(int from, int count, int *array);
#endif // Win32 | Linux

// Fill array in callback.
declexport(bool) STDCALL fillArray(int from, int count, FillArrayCallback func)
{
	int *array = new int[count];

	func(from, count, array);

	bool result = true;
	for (int i = 0, val = from; i < count; i++, val++)
	{
		printf("array[%d] = %d should be %d \n", i, array[i], val);
		if (array[i] != val)
		{
			result = false;
		}
	}
	delete[] array;

	return result;
}

#ifdef __cplusplus
}
#endif
