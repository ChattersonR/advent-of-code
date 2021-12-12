#!/usr/bin/env python3

import numpy as np

with open("./data.in") as fin:
    raw_data = fin.read().strip()
data = np.array(raw_data.split("\n"), dtype=int)
#data = [199, 200, 208, 210, 200, 207, 240, 269, 260, 263]

def countIncreasing(dataCp):
    prev = dataCp[0]
    inc = 0
    for i in dataCp[1:]:
        if i > prev: inc += 1
        prev = i

    return inc

sumData = np.zeros(len(data)-2, dtype=int)
window = [-1, 0, 1]

for i in range(1, len(data)):
    if i + window[len(window)-1] >= len(data):
        continue

    for j in window:
        sumData[i-1] = sumData[i-1] + data[i+j]

print("Increasing: %s"%countIncreasing(data))
print("Increasing with sliding window: %s"%countIncreasing(sumData))