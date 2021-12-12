#!/usr/bin/env python3

import numpy as np

with open("./data.in") as fin:
    raw_data = fin.read().strip()
data = np.array(raw_data.split("\n"), dtype=int)

prev = data[0]
inc = 0
for i in data[1:]:
    if i > prev: inc += 1
    prev = i

print(inc)