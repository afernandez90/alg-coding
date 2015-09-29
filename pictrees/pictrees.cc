#include <iostream>
#include <cmath>
#include <vector>
#include <algorithm>

using namespace std;

double angle(double x, double y) {
  double result = atan2(y, x) * 180 / M_PI;
  if (result < 0) result += 360;
  return result;
}

double bestAngle(const vector<double>& trees, double fov) {
  int n = trees.size();
  if (n <= 0) return 0;
  int l = 0;
  int r = 0;
  double currentBest = trees[0];
  int currentMax = 1;
  int count = 1;
  double pad = 0;
  while (l < n) {
    --count;
    while (trees[r] + pad - trees[l] < fov) {
      ++r;
      ++count;
      if (r == n) {
        pad = 360;
        r = 0;
      }
    }
    if (count > currentMax) {
      currentMax = count;
      currentBest = trees[l];
    }
    ++l;
  }
  return currentBest;
}

int main() {
  int n; // Number of trees
  cin >> n;
  vector<double> trees(n);
  for (int i = 0; i < n; ++i) {
    double x, y;
    cin >> x >> y;
    trees[i] = angle(x, y);
  }
  sort(begin(trees), end(trees));
  double fov; // Camera's field of view
  cin >> fov;
  cout << "Best angle: ";
  if (fov >= 360) cout << trees.size();
  else cout << bestAngle(trees, fov);
  cout << endl;
}

