import random

with open("data/text.txt", "w") as f:
    for i in range(100):
        print(random.random() * 1000, file=f)
