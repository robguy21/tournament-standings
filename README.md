# Oustanding
1. ~~Tests~~
2. ~~Reading from stdin~~
3. ~~Output formats~~
   1. ~~JSON~~
   2. ~~CSV~~
5. Make sure help is accurate

# Usage
To write fixture results directly into the command remember to surround the entire list with a single set of quotation marks
```shell
# Example: output result of cat directly to command
/path/to/command "$(cat my-file.txt)"
```

```shell
# Example: write results in manually
/path/to/command "Tottenham 0, Chelsea 4 
Man City 4, West Ham 1
Liverpool 2, Wolves 0"
```

If the results are stored in a file, use the `-f` or `--file` option to specify the path to the file
```shell
# Example: write results in manually
/path/to/command --file /my/path/to/the/file.txt
```

# Limitations
All results are expected to be the following format:
```
<TEAM_NAME> <TOTAL>, <TEAM_NAME> <TOTAL>
<TEAM_NAME> <TOTAL>, <TEAM_NAME> <TOTAL>
```
_and so on_

# Missing Pieces
No repositories, may be nifty to store the tournament in memory so that the cli can be somewhat stateful

Currently, there is only a concept of a "League", my initial idea was to have Tournaments with different types 
(League / Single Elimination / etc) and then you could extend these base cases to a Tournament structure that makes use of different
tournament structures (ie - FIFA World Cup goes from Group Format to Single Elimination Format)

Store the `PointRules` a bit more elegantly so that it is tied to the League (can be overridden for special occasions)-
ie - Premier League extends League but can override PointRules if they decided to award 5 points to winners instead of 3

Ideally we would be able to get this data from some stream of events, this "tournament service" could then be used to integrate
all fixture events as they happen (Goal, Red card, Shot on target, Foul, Tri, Scrum, etc). If something like this were to be integrated,
I believe that this code base is structured in a way that it would be a kind migration to such a system.