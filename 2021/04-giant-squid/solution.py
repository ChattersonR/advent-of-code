#!/usr/bin/env python3

import numpy as np

with open("./data.in") as fin:
    raw_data = fin.read().strip()
data = np.array(raw_data.split("\n"), dtype=str)

class BingoBoard