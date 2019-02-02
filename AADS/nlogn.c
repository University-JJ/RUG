#include <stdio.h>
#include <math.h>
#include <stdlib.h>

int main()
{
    int n=1,i, aux=1;
    for(i=1;i=n;i++)
    {
     aux*=i;
    }
    while(aux<1000000)
    {
     aux+=1;                  
    }
    printf("Minimum value of n in n!->", n-1);
    return 0;
}
