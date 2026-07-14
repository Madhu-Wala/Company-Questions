#include <vector>
using namespace std;

vector<int> rotateArray(vector<int>& arr, int k)
{
    int n = arr.size();
    if(n == 0)
        return arr;
    k %= n;
    vector<int> temp(n);
    for(int i = 0; i < n; i++)
    {
        temp[(i + k) % n] = arr[i];
    }
    return temp;
}
