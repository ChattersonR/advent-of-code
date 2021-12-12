#!/usr/bin/env python3

import numpy as np

commandspt1 = {
    "forward": lambda x,y,aim,dist: (x+dist, y, aim),
    "up": lambda x,y,aim,dist: (x, y-dist, aim),
    "down": lambda x,y,aim,dist: (x, y+dist, aim),
}

commandspt2 = {
    "forward": lambda x,y,aim,dist: (x+dist, y+(aim*dist), aim),
    "up": lambda x,y,aim,dist: (x, y, aim-dist),
    "down": lambda x,y,aim,dist: (x, y, aim+dist),
}

with open("./data.in") as fin:
    raw_data = fin.read().strip()
data = np.array(raw_data.split("\n"), dtype=str)

def navigate(commandDict):
    position = (0,0,0)
    for cmd in data:
        cmd2 = cmd.split(" ")
        position = commandDict[cmd2[0]](position[0], position[1], position[2], int(cmd2[1]))
    return position


positionPt1 = navigate(commandspt1)
print("Pt 1: Position: %s. Distance: %s"%(positionPt1, (positionPt1[0]*positionPt1[1])))

positionPt2 = navigate(commandspt2)
print("Pt 2: Position: %s. Distance: %s"%(positionPt2, (positionPt2[0]*positionPt2[1])))