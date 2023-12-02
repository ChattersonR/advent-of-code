#!/usr/bin/env python3
# pivot table; column = row
# if sum(column) >= len(column)/2, common = 1, least common = 0
#

import numpy as np

with open("./data.in") as fin:
    raw_data = fin.read().strip()
data = np.array(raw_data.split("\n"), dtype=str)

def parseData(data):
    col = len(data[0])
    outArr = np.empty(shape=(col, len(data)))
    for i in range(len(data)):
        for j in range(col):
            outArr[j,i] = data[i][j]

    return outArr

def calculateCommon(row):
    commonVal = bool(int((len(row)-1)/np.sum(row))%2)
    return (int(commonVal), int(not commonVal))

def powerUsage(pivotData):
    gamma = ""   # most common
    epsilon = "" # least common
    for arr in pivotData:
        gamEps = calculateCommon(arr)
        gamma = gamma + str(gamEps[0])
        epsilon = epsilon + str(gamEps[1])
    return(gamma, epsilon)

def lifeSupport(data, pivotData, pos, commonIndex):
    if len(data) is 1:
        return data[0]
    common = calculateCommon(pivotData[pos])
    def filterFun(var):
        return int(var[pos]) is common[commonIndex]
    filterData = list(filter(filterFun, data))
    return lifeSupport(filterData, parseData(filterData), pos+1, commonIndex)

pivotData = parseData(data)
#print(pivotData)
power = powerUsage(pivotData)
print("power: %s, rating: %s"%(power, int(power[0], 2) * int(power[1], 2)))

o2 = lifeSupport(data, pivotData, 0, 0)
co2 = lifeSupport(data, pivotData, 0, 1)
print("o2: %s, co2: %s, rating: %s"%(o2,co2, (int(o2,2)*int(co2,2))))